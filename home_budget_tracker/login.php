<?php
include("includes/header.php");
include 'includes/db.php';
include 'includes/functions.php';

if (session_status() === PHP_SESSION_NONE) {
    session_start();
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $username = $_POST['username'];
    $password = $_POST['password'];

    $sql = "SELECT * FROM users WHERE username = '$username'";
    $result = mysqli_query($conn, $sql);

    if ($result && mysqli_num_rows($result) > 0) {
        $user = mysqli_fetch_assoc($result);
        if (password_verify($password, $user['password'])) {
            // Set session variables before redirect
            $_SESSION['user_id'] = $user['id']; // User ID from the database
            $_SESSION['username'] = $user['username']; // Username from the database
            
            // Redirect with a success message
            redirectWithMessage('dashboard.php', 'Welcome back, ' . htmlspecialchars($username) . '!');
        } else {
            echo "<div class='alert alert-danger mt-3'>Invalid password.</div>";
        }
    } else {
        echo "<div class='alert alert-danger mt-3'>User not found.</div>";
    }
}
?>

<div class="row center">
    <h2 class="text-center">User Login</h2>
    <p class="text-center">Don't have an account? <a href="register.php">Create an account</a></p>
    <div class="col-md-6 offset-md-3 center">

        <form method="POST" action="login.php">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary center">Login</button>
        </form>
    </div>
</div>
<?php include 'includes/footer.php'; ?>
