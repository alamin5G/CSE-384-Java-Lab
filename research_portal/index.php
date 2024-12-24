<?php include 'includes/header.php'; ?>

<div class="container mt-5 text-center">
    <div class="row">
        <div class="col-md-6 mx-auto">
            <h1 class="display-4">Welcome to the Research Portal</h1>
            <p class="lead mt-3">
                Your one-stop solution for managing research projects, theses, and collaboration between students and faculty.
            </p>
            <?php if (!isset($_SESSION['role'])): ?>
                <p class="mt-4">
                    <a href="login.php" class="btn btn-primary btn-lg me-2">Login</a>
                    <a href="register.php" class="btn btn-outline-primary btn-lg">Register</a>
                </p>
            <?php else: ?>
                <p class="mt-4">
                    Hello, <strong><?php echo htmlspecialchars($_SESSION['first_name']); ?></strong>! Access your
                    <a href="dashboard.php" class="btn btn-success btn-lg"> Dashboard</a>.
                </p>
            <?php endif; ?>
        </div>
    </div>
</div>

<!-- Features Section -->
<div class="container mt-5">
    <div class="row">
        <div class="col-md-4 text-center">
            <img src="assets/img/research.svg" alt="Research Papers" class="img-fluid" style="max-height: 200px;">
            <h3 class="mt-3">Manage Research Papers</h3>
            <p>Submit, view, and manage all your research papers in one convenient place.</p>
        </div>
        <div class="col-md-4 text-center">
            <img src="assets/img/teamwork.svg" alt="Collaboration" class="img-fluid" style="max-height: 200px;">
            <h3 class="mt-3">Collaborate</h3>
            <p>Students and faculty can collaborate seamlessly to drive innovation.</p>
        </div>
        <div class="col-md-4 text-center">
            <img src="assets/img/dashboard.svg" alt="Dashboard" class="img-fluid" style="max-height: 200px;">
            <h3 class="mt-3">Comprehensive Dashboard</h3>
            <p>Track your submissions and stay updated with real-time statistics.</p>
        </div>
    </div>
</div>

<!-- Footer -->
<?php include 'includes/footer.php'; ?>
