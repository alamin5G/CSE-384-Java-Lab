<?php
include 'includes/db.php';
include 'includes/functions.php';

if (!isset($_SESSION['user_id'])) {
    header("Location: login.php");
    exit();
}

$user_id = $_SESSION['user_id'];
$expense_id = $_GET['id'] ?? null;

if (!$expense_id) {
    redirectWithMessage('dashboard.php', 'Invalid expense ID.');
}

// Delete the expense (user-specific)
$sql = "DELETE FROM expenses WHERE id = '$expense_id' AND user_id = '$user_id'";
if (mysqli_query($conn, $sql)) {
    redirectWithMessage('dashboard.php', 'Expense deleted successfully.');
} else {
    redirectWithMessage('dashboard.php', 'Error deleting expense: ' . mysqli_error($conn));
}

?>
