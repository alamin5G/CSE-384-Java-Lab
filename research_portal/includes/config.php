<?php
// Database configuration
$host = 'localhost';
$dbname = 'research_portal';
$username = 'root'; // Replace with your MySQL username
$password = '252646';     // Replace with your MySQL password

// Connect using mysqli
$connection = new mysqli($host, $username, $password, $dbname);

// Check connection
if ($connection->connect_error) {
    die("Database connection failed: " . $connection->connect_error);
}
?>
