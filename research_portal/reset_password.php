<?php
include 'includes/header.php';
require_once 'includes/config.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $email = $connection->real_escape_string($_POST['email']);

    $query = "SELECT * FROM users WHERE email = '$email'";
    $result = $connection->query($query);

    if ($result && $result->num_rows === 1) {
        // Generate a password reset link (simplified for demonstration)
        $resetLink = "reset_password_action.php?email=" . urlencode($email);
        echo "<div class='alert alert-success'>A password reset link has been sent to your email: $resetLink (for demonstration purposes).</div>";
    } else {
        echo "<div class='alert alert-danger'>Email not found.</div>";
    }
}
?>

<div class="container">
    <h2>Reset Password</h2>
    <form method="POST" action="reset_password.php">
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" name="email" id="email" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Send Reset Link</button>
    </form>
</div>

<?php include 'includes/footer.php'; ?>
