<?php
include("includes/header.php");
include 'includes/db.php';
include 'includes/functions.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $username = trim($_POST['username']);
    $email = trim($_POST['email']);
    $password = password_hash($_POST['password'], PASSWORD_DEFAULT);

    // Check for duplicate username
    $check_username_sql = "SELECT * FROM users WHERE username = '$username'";
    $username_result = mysqli_query($conn, $check_username_sql);
    
    // Check for duplicate email
    $check_email_sql = "SELECT * FROM users WHERE email = '$email'";
    $email_result = mysqli_query($conn, $check_email_sql);

    if (mysqli_num_rows($username_result) > 0) {
        echo "<div class='alert alert-danger mt-3'>Error: Username '$username' is already taken. Please choose a different username.</div>";
    } elseif (mysqli_num_rows($email_result) > 0) {
        echo "<div class='alert alert-danger mt-3'>Error: Email '$email' is already registered. Please use a different email.</div>";
    } else {
        // Insert the new user
        $sql = "INSERT INTO users (username, email, password) VALUES ('$username', '$email', '$password')";
        if (mysqli_query($conn, $sql)) {
            redirectWithMessage('login.php', 'Registration successful! You can now log in.');
        } else {
            echo "<div class='alert alert-danger mt-3'>Error: Could not register user. Please try again later.</div>";
        }
    }
}
?>

<div class="row">
<h2 class="text-center">User Registration</h2>
    <div class="col-md-6 offset-md-3">
    <p class="text-center">Already have an account? <a href="login.php">Log in</a></p>
        <form method="POST" action="register.php">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>
</div>

<?php include 'includes/footer.php'; ?>
