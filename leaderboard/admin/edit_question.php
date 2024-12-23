<?php
include '../header.php';
require '../db.php';

if (!isset($_SESSION['is_admin']) || !$_SESSION['is_admin']) {
    header("Location: ../login.php");
    exit();
}

if ($_SERVER['REQUEST_METHOD'] === 'GET' && isset($_GET['id'])) {
    $stmt = mysqli_prepare($conn, "SELECT * FROM questions WHERE id = ?");
    mysqli_stmt_bind_param($stmt, "i", $_GET['id']);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);
    $question = mysqli_fetch_assoc($result);

    if (!$question) {
        header("Location: search_questions.php?success=Question not found.");
        exit();
    }
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $id = $_POST['id'];
    $question_text = $_POST['question'];
    $option1 = $_POST['option1'];
    $option2 = $_POST['option2'];
    $option3 = $_POST['option3'];
    $option4 = $_POST['option4'];
    $correct_option = $_POST['correct_option'];

    $stmt = mysqli_prepare($conn, "UPDATE questions SET question = ?, option1 = ?, option2 = ?, option3 = ?, option4 = ?, correct_option = ? WHERE id = ?");
    mysqli_stmt_bind_param($stmt, "ssssssi", $question_text, $option1, $option2, $option3, $option4, $correct_option, $id);

    if (mysqli_stmt_execute($stmt)) {
        header("Location: search_questions.php?success=Question updated successfully.");
    } else {
        echo "<div class='alert alert-danger'>Failed to update the question. Please try again.</div>";
    }
    exit();
}
?>


<h1>Edit Question</h1>
<form method="post">
    <input type="hidden" name="id" value="<?= htmlspecialchars($question['id']) ?>">
    <div class="mb-3">
        <label class="form-label">Question</label>
        <textarea name="question" class="form-control" required><?= htmlspecialchars($question['question']) ?></textarea>
    </div>
    <div class="mb-3">
        <label class="form-label">Option 1</label>
        <input type="text" name="option1" class="form-control" value="<?= htmlspecialchars($question['option1']) ?>" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Option 2</label>
        <input type="text" name="option2" class="form-control" value="<?= htmlspecialchars($question['option2']) ?>" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Option 3</label>
        <input type="text" name="option3" class="form-control" value="<?= htmlspecialchars($question['option3']) ?>" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Option 4</label>
        <input type="text" name="option4" class="form-control" value="<?= htmlspecialchars($question['option4']) ?>" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Correct Option (1-4)</label>
        <input type="number" name="correct_option" min="1" max="4" class="form-control" value="<?= htmlspecialchars($question['correct_option']) ?>" required>
    </div>
    <button type="submit" class="btn btn-primary">Update Question</button>
</form>
<?php include '../footer.php'; ?>
