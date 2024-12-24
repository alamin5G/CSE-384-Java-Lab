<?php
include 'header.php';
require 'db.php';

$username = $email = $phone = $name = $age = ""; // Initialize variables
$error = "";

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = $_POST['username'];
    $email = $_POST['email'];
    $phone = $_POST['phone'] ?? "";
    $name = $_POST['name'] ?? "";
    $age = $_POST['age'] ?? null;
    $password = $_POST['password'];
    $confirm_password = $_POST['confirm_password'];

    // Check for duplicate username or email
    $stmt = mysqli_prepare($conn, "SELECT * FROM users WHERE username = ? OR email = ?");
    if ($stmt) {
        mysqli_stmt_bind_param($stmt, "ss", $username, $email);
        mysqli_stmt_execute($stmt);
        $result = mysqli_stmt_get_result($stmt);

        if (mysqli_num_rows($result) > 0) {
            $conflicting_user = mysqli_fetch_assoc($result);
            if ($conflicting_user['username'] === $username) {
                $error = "The username '$username' is already taken.";
            } elseif ($conflicting_user['email'] === $email) {
                $error = "The email '$email' is already associated with another account.";
            }
        }
        mysqli_stmt_close($stmt);
    } else {
        $error = "Database error: " . mysqli_error($conn);
    }

    // Check if passwords match
    if (empty($error) && $password !== $confirm_password) {
        $error = "Passwords do not match!";
    }

    // If no errors, proceed with registration
    if (empty($error)) {
        $hashed_password = password_hash($password, PASSWORD_BCRYPT);

        // Insert user into the database
        $stmt = mysqli_prepare($conn, "INSERT INTO users (username, email, password, phone, name, age) VALUES (?, ?, ?, ?, ?, ?)");
        if ($stmt) {
            mysqli_stmt_bind_param($stmt, "sssssi", $username, $email, $hashed_password, $phone, $name, $age);

            if (mysqli_stmt_execute($stmt)) {
                // Set success message in session
                $_SESSION['success_message'] = "Registration successful! You can now log in.";
                header("Location: login.php");
                exit();
            } else {
                $error = "Registration failed: " . mysqli_stmt_error($stmt);
            }
            mysqli_stmt_close($stmt);
        } else {
            $error = "Database error: " . mysqli_error($conn);
        }
    }
}
?>

<h1 class="mt-4 text-center">Register</h1>
<div class="row">
    <div class="col-md-4 offset-md-4">
        <?php if ($error) echo "<div class='alert alert-danger'>$error</div>"; ?>
        <form method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" name="username" class="form-control" value="<?= htmlspecialchars($username) ?>" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" name="email" class="form-control" value="<?= htmlspecialchars($email) ?>" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" name="password" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="confirm_password" class="form-label">Confirm Password</label>
                <input type="password" name="confirm_password" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="text" name="phone" class="form-control" value="<?= htmlspecialchars($phone) ?>">
            </div>
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" name="name" class="form-control" value="<?= htmlspecialchars($name) ?>">
            </div>
            <div class="mb-3">
                <label for="age" class="form-label">Age</label>
                <input type="number" name="age" class="form-control" value="<?= htmlspecialchars($age) ?>">
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>
</div>
<?php include 'footer.php'; ?>
