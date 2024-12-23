<?php include 'includes/header.php'; 

if (session_status() === PHP_SESSION_NONE) {
    session_start();
}
?>

<div class="container mt-5">
    <h1 class="text-center">Welcome to Home Budget Tracker</h1>
    <?php if (!isset($_SESSION['user_id'])): ?>
    <!-- Content for guests -->
    <p class="text-center mt-3">
        Manage your household budget and track expenses easily. Register now to get started or log in to continue.
    </p>
    <div class="d-flex justify-content-center mt-4">
        <a href="register.php" class="btn btn-primary me-2">Register</a>
        <a href="login.php" class="btn btn-secondary">Login</a>
    </div>
    <?php else: ?>
    <!-- Content for logged-in users -->
    <?php 
        // Use the username from the session or fallback to a default
        $username = isset($_SESSION['username']) ? htmlspecialchars($_SESSION['username']) : 'User';
        ?>
    <p class="text-center mt-3">
        Welcome back, <strong><?= $username ?></strong>! Ready to manage your budget and track your expenses?
    </p>
    <div class="d-flex justify-content-center mt-4">
        <a href="dashboard.php" class="btn btn-primary me-2">Go to Dashboard</a>
        <a href="logout.php" class="btn btn-danger">Logout</a>
    </div>
    <?php endif; ?>
</div>

<?php include 'includes/footer.php'; ?>