<?php
$host = 'localhost';
$dbname = 'home_budget_tracker';
$username = 'root';
$password = '252646';

// Create a connection using mysqli
$conn = mysqli_connect($host, $username, $password, $dbname);

// Check the connection
if (!$conn) {
    die("Database connection failed: " . mysqli_connect_error());
}
?>
