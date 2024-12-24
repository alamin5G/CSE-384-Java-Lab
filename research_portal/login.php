<?php

require_once 'includes/config.php';
include 'includes/header.php';

// Check if user is already logged in
if (isset($_SESSION['role'])) {
    header('Location: dashboard.php');
    exit;
}

// Handle login form submission
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $email = $connection->real_escape_string($_POST['email']);
    $password = $_POST['password'];

    $query = "SELECT * FROM users WHERE email = '$email'";
    $result = $connection->query($query);

    if ($result && $result->num_rows === 1) {
        $user = $result->fetch_assoc();

        // Verify password
        if (password_verify($password, $user['password'])) {
            // Store user data in session
            $_SESSION['id'] = $user['id'];
            $_SESSION['first_name'] = $user['first_name'];
            $_SESSION['role'] = $user['role'];

            // Redirect to dashboard
            header('Location: dashboard.php');
            exit;
        } else {
            $error = "Invalid email or password.";
        }
    } else {
        $error = "Invalid email or password.";
    }
}

// Check if redirected from registration
if (isset($_GET['successMessage'])) {
    $successMessage = $_GET['successMessage'];
}
?>

<div class="row">
    <div class="col-md-5 offset-3">
        <h2>Login</h2>
        <p>Don't have an account? <a href="register.php">Register</a></p>
        <?php if (isset($successMessage)): ?>
            <div class="alert alert-success"><?php echo htmlspecialchars($successMessage); ?></div>
        <?php endif; ?>
        <?php if (isset($error)): ?>
            <div class="alert alert-danger"><?php echo htmlspecialchars($error); ?></div>
        <?php endif; ?>
        <form method="POST" action="login.php">
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" name="email" id="email" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" name="password" id="password" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
            <p>Forgot Password? <a href="reset_password.php">Click Here</a></p>
        </form>
    </div>
</div>

<?php include 'includes/footer.php'; ?>
