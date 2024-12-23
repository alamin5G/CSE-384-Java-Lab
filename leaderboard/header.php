<?php session_start(); ?>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Game Leaderboard System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="style.css">
    
</head>
<body class="d-flex flex-column min-vh-100">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="/leaderboard/index.php">Game Leaderboard</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/leaderboard/index.php">Home</a>
                </li>
                <?php if (!isset($_SESSION['user_id'])): ?>
                    <li class="nav-item">
                        <a class="nav-link" href="/leaderboard/register.php">Register</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/leaderboard/login.php">Login</a>
                    </li>
                <?php else: ?>
                    <li class="nav-item">
                        <a class="nav-link" href="/leaderboard/profile.php">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/leaderboard/leaderboard.php">Leaderboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/leaderboard/game.php">Play Quiz</a>
                    </li>
                    <?php if (isset($_SESSION['is_admin']) && $_SESSION['is_admin']): ?>
                        <li class="nav-item">
                            <a class="nav-link" href="/leaderboard/admin/manage_questions.php">Add Quiz</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/leaderboard/admin/search_questions.php">Search Quiz</a>
                        </li>
                    <?php endif; ?>
                    <li class="nav-item">
                        <a class="nav-link text-danger" href="/leaderboard/logout.php">Logout</a>
                    </li>
                <?php endif; ?>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-4 flex-grow-1">
