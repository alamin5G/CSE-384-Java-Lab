<?php
include 'header.php';
require 'db.php';

if (!isset($_SESSION['user_id'])) {
    header("Location: login.php");
    exit();
}

$user_id = $_SESSION['user_id'];

// Fetch the user details from the database
$stmt = mysqli_prepare($conn, "SELECT * FROM users WHERE id = ?");
mysqli_stmt_bind_param($stmt, "i", $user_id);
mysqli_stmt_execute($stmt);
$result = mysqli_stmt_get_result($stmt);
$user = mysqli_fetch_assoc($result);

// Initialize variables
$success_message = "";
$error_message = "";

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Retrieve updated form data
    $username = $_POST['username'];
    $email = $_POST['email'];
    $phone = $_POST['phone'] ?? "";
    $name = $_POST['name'] ?? "";
    $age = $_POST['age'] ?? null;

    // Check for duplicate username or email
    $stmt = mysqli_prepare($conn, "SELECT * FROM users WHERE (username = ? OR email = ?) AND id != ?");
    mysqli_stmt_bind_param($stmt, "ssi", $username, $email, $user_id);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);

    if (mysqli_num_rows($result) > 0) {
        $conflicting_user = mysqli_fetch_assoc($result);
        if ($conflicting_user['username'] === $username) {
            $error_message = "The username '$username' is already taken by another user.";
        } elseif ($conflicting_user['email'] === $email) {
            $error_message = "The email '$email' is already associated with another account.";
        }
    } else {
        // Update the user's profile
        $stmt = mysqli_prepare($conn, "UPDATE users SET username = ?, email = ?, phone = ?, name = ?, age = ? WHERE id = ?");
        mysqli_stmt_bind_param($stmt, "ssssii", $username, $email, $phone, $name, $age, $user_id);

        if (mysqli_stmt_execute($stmt)) {
            $_SESSION['username'] = $username; // Update session username
            $success_message = "Profile updated successfully!";
        } else {
            $error_message = "Failed to update profile. Please try again.";
        }
    }

    // Reload the user data after an update or error
    $stmt = mysqli_prepare($conn, "SELECT * FROM users WHERE id = ?");
    mysqli_stmt_bind_param($stmt, "i", $user_id);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);
    $user = mysqli_fetch_assoc($result);
}

// Determine if profile is in editable mode
$is_editable = isset($_GET['edit']);
?>

<h1 class="mt-4 text-center">My Profile</h1>
<div class="row">
    <div class="col-md-6 offset-md-3">
        <?php if ($success_message): ?>
            <div class="alert alert-success"><?= htmlspecialchars($success_message) ?></div>
        <?php endif; ?>
        <?php if ($error_message): ?>
            <div class="alert alert-danger"><?= htmlspecialchars($error_message) ?></div>
        <?php endif; ?>
        <form method="post" action="profile.php<?= $is_editable ? '' : '?edit=true' ?>">
            <div class="mb-3">
                <label class="form-label">Username</label>
                <input type="text" name="username" class="form-control" 
                    value="<?= htmlspecialchars($user['username']) ?>" 
                    <?= $is_editable ? '' : 'readonly' ?> required>
            </div>
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" name="email" class="form-control" 
                    value="<?= htmlspecialchars($user['email']) ?>" 
                    <?= $is_editable ? '' : 'readonly' ?> required>
            </div>
            <div class="mb-3">
                <label class="form-label">Phone</label>
                <input type="text" name="phone" class="form-control" 
                    value="<?= htmlspecialchars($user['phone']) ?>" 
                    <?= $is_editable ? '' : 'readonly' ?>>
            </div>
            <div class="mb-3">
                <label class="form-label">Name</label>
                <input type="text" name="name" class="form-control" 
                    value="<?= htmlspecialchars($user['name']) ?>" 
                    <?= $is_editable ? '' : 'readonly' ?>>
            </div>
            <div class="mb-3">
                <label class="form-label">Age</label>
                <input type="number" name="age" class="form-control" 
                    value="<?= htmlspecialchars($user['age']) ?>" 
                    <?= $is_editable ? '' : 'readonly' ?>>
            </div>
            <?php if ($is_editable): ?>
                <button type="submit" class="btn btn-primary">Update</button>
            <?php else: ?>
                <a href="profile.php?edit=true" class="btn btn-secondary">Edit</a>
            <?php endif; ?>
        </form>
    </div>
</div>
<?php include 'footer.php'; ?>
