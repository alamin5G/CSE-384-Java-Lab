<?php
include 'header.php';
?>

<h1>Welcome to the Game Leaderboard System</h1>
<?php if (!isset($_SESSION['user_id'])): ?>
    <p class="lead">Please <a href="login.php">login</a> or <a href="register.php">register</a> to play the quiz and view your scores.</p>
    <a href="login.php" class="btn btn-primary">Play Quiz Game</a>
<?php else: ?>
    <p class="lead">Welcome, <strong><?= htmlspecialchars($_SESSION['username']) ?></strong>! Ready to test your skills?</p>
    <a href="game.php" class="btn btn-primary">Play Quiz Game</a>
<?php endif; ?>
<?php include 'footer.php'; ?>
