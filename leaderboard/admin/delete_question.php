<?php
include '../header.php';
require '../db.php';

if (!isset($_SESSION['is_admin']) || !$_SESSION['is_admin']) {
    header("Location: ../login.php");
    exit();
}

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['delete_id'])) {
    $delete_id = $_POST['delete_id'];

    // Prepare and execute delete query
    $stmt = mysqli_prepare($conn, "DELETE FROM questions WHERE id = ?");
    mysqli_stmt_bind_param($stmt, "i", $delete_id);

    if (mysqli_stmt_execute($stmt)) {
        // Redirect to search page with success message
        header("Location: search_questions.php?success=Question deleted successfully.");
    } else {
        // Redirect with an error message
        header("Location: search_questions.php?success=Failed to delete the question. Please try again.");
    }
    exit();
}

// Redirect back to search_questions.php if accessed directly
header("Location: search_questions.php");
exit();
