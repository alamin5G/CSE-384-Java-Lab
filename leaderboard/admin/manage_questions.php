
<?php
include '../header.php';
require '../db.php';
if (session_status() === PHP_SESSION_NONE) {
    session_start();
}

// Check if the user is an admin
// Check if user is logged in and is an admin
if (!isset($_SESSION['is_admin']) || $_SESSION['is_admin'] != 1) {
    header("Location: ../login.php");
    exit();
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $question = $_POST['question'];
    $option1 = $_POST['option1'];
    $option2 = $_POST['option2'];
    $option3 = $_POST['option3'];
    $option4 = $_POST['option4'];
    $correct_option = $_POST['correct_option'];

    $stmt = mysqli_prepare($conn, "INSERT INTO questions (question, option1, option2, option3, option4, correct_option) 
                                   VALUES (?, ?, ?, ?, ?, ?)");
    if ($stmt) {
        mysqli_stmt_bind_param($stmt, "sssssi", $question, $option1, $option2, $option3, $option4, $correct_option);
        if (mysqli_stmt_execute($stmt)) {
            echo "<div class='alert alert-success'>Question added successfully!</div>";
        } else {
            echo "<div class='alert alert-danger'>Failed to add question. Please try again.</div>";
        }
    } else {
        echo "<div class='alert alert-danger'>Database error: " . mysqli_error($conn) . "</div>";
    }
}
?>

<h1>Manage Questions</h1>
<form method="post">
    <div class="mb-3">
        <label class="form-label">Question</label>
        <textarea name="question" class="form-control" required></textarea>
    </div>
    <div class="mb-3">
        <label class="form-label">Option 1</label>
        <input type="text" name="option1" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Option 2</label>
        <input type="text" name="option2" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Option 3</label>
        <input type="text" name="option3" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Option 4</label>
        <input type="text" name="option4" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Correct Option (1-4)</label>
        <input type="number" name="correct_option" min="1" max="4" class="form-control" required>
    </div>
    <button type="submit" class="btn btn-primary">Add Question</button>
</form>
<?php include '../footer.php'; ?>
