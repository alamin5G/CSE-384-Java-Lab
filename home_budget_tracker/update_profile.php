<?php
include 'includes/header.php';
include 'includes/db.php';
include 'includes/functions.php'; // Include the file with redirectWithMessage function

if (!isset($_SESSION['user_id'])) {
    header("Location: login.php");
    exit();
}

$user_id = $_SESSION['user_id'];

// Fetch user details
$sql = "SELECT * FROM users WHERE id = '$user_id'";
$result = mysqli_query($conn, $sql);
$user = mysqli_fetch_assoc($result);

// Fetch family members (user-specific)
$sql = "SELECT * FROM family_members WHERE user_id = '$user_id'";
$result = mysqli_query($conn, $sql);
$family_members = mysqli_fetch_all($result, MYSQLI_ASSOC);

// Initialize error message
$error_message = '';

// Handle profile update
if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['update_profile'])) {
    $email = trim($_POST['email']);
    $username = trim($_POST['username']);

    // Check for duplicate username
    $check_username_sql = "SELECT id FROM users WHERE username = '$username' AND id != '$user_id'";
    $check_username_result = mysqli_query($conn, $check_username_sql);

    // Check for duplicate email
    $check_email_sql = "SELECT id FROM users WHERE email = '$email' AND id != '$user_id'";
    $check_email_result = mysqli_query($conn, $check_email_sql);

    if (mysqli_num_rows($check_username_result) > 0) {
        $error_message = "The username '$username' is already taken. Please choose a different username.";
    } elseif (mysqli_num_rows($check_email_result) > 0) {
        $error_message = "The email '$email' is already in use. Please use a different email.";
    } else {
        // Update user profile
        $sql = "UPDATE users SET email = '$email', username = '$username' WHERE id = '$user_id'";
        if (mysqli_query($conn, $sql)) {
            redirectWithMessage('update_profile.php', 'Profile updated successfully.');
        } else {
            $error_message = "Error updating profile: " . mysqli_error($conn);
        }
    }
}

// Handle family member addition
if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['add_family'])) {
    $name = $_POST['name'];
    $relationship = $_POST['relationship'];
    $age = $_POST['age'];

    // Add family member (user-specific)
    $sql = "INSERT INTO family_members (user_id, name, relationship, age) VALUES ('$user_id', '$name', '$relationship', '$age')";
    if (mysqli_query($conn, $sql)) {
        redirectWithMessage('update_profile.php', 'Family member added successfully.');
    } else {
        $error_message = "Error adding family member: " . mysqli_error($conn);
    }
}

// Handle family member deletion
if (isset($_GET['delete_family'])) {
    $family_id = $_GET['delete_family'];

    $sql = "DELETE FROM family_members WHERE id = '$family_id' AND user_id = '$user_id'";
    if (mysqli_query($conn, $sql)) {
        redirectWithMessage('update_profile.php', 'Family member deleted successfully.');
    } else {
        $error_message = "Error deleting family member: " . mysqli_error($conn);
    }
}
?>

<div class="container mt-5">
    <h2>Update Profile</h2>

    <!-- Display error message -->
    <?php if (!empty($error_message)): ?>
        <div class="alert alert-danger"><?php echo $error_message; ?></div>
    <?php endif; ?>

    <!-- Update Profile Form -->
    <form method="POST" action="update_profile.php" class="mt-4">
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="username" name="username" value="<?php echo htmlspecialchars($user['username']); ?>" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="<?php echo htmlspecialchars($user['email']); ?>" required>
        </div>
        <button type="submit" name="update_profile" class="btn btn-primary">Update Profile</button>
    </form>

    <!-- Add Family Member Form -->
    <h3 class="mt-5">Family Members</h3>
    <form method="POST" action="update_profile.php" class="mt-4">
        <div class="row g-3">
            <div class="col-md-4">
                <input type="text" class="form-control" name="name" placeholder="Name" required>
            </div>
            <div class="col-md-4">
                <input type="text" class="form-control" name="relationship" placeholder="Relationship" required>
            </div>
            <div class="col-md-4">
                <input type="number" class="form-control" name="age" placeholder="Age" required>
            </div>
        </div>
        <button type="submit" name="add_family" class="btn btn-secondary mt-3">Add Family Member</button>
    </form>

    <!-- Display Existing Family Members -->
    <?php if (!empty($family_members)): ?>
        <h4 class="mt-5">Existing Family Members</h4>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Relationship</th>
                    <th>Age</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <?php foreach ($family_members as $family_member): ?>
                    <tr>
                        <td><?php echo htmlspecialchars($family_member['name']); ?></td>
                        <td><?php echo htmlspecialchars($family_member['relationship']); ?></td>
                        <td><?php echo htmlspecialchars($family_member['age']); ?></td>
                        <td>
                            <a href="update_profile.php?delete_family=<?php echo $family_member['id']; ?>" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this family member?')">Delete</a>
                        </td>
                    </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
    <?php else: ?>
        <p class="text-muted mt-4">No family members added yet.</p>
    <?php endif; ?>
</div>

<?php include 'includes/footer.php'; ?>
