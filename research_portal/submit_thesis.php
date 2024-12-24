<?php
require_once 'includes/config.php';
include 'includes/header.php';

// Restrict access to students only
if (!isset($_SESSION['role']) || $_SESSION['role'] !== 'student') {
    header('Location: login.php');
    exit;
}

$title = $summary = $supporting_file = "";
$errors = [];

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $title = $connection->real_escape_string($_POST['title']);
    $summary = $connection->real_escape_string($_POST['summary']);
    $student_id = $_SESSION['id'];

    // Handle file upload
    if (!empty($_FILES['supporting_file']['name'])) {
        $target_dir = "assets/uploads/";
        $target_file = $target_dir . basename($_FILES['supporting_file']['name']);
        $file_type = strtolower(pathinfo($target_file, PATHINFO_EXTENSION));

        // Validate file type
        $allowed_types = ['pdf', 'doc', 'docx'];
        if (!in_array($file_type, $allowed_types)) {
            $errors[] = "Only PDF, DOC, and DOCX files are allowed.";
        } else {
            if (!move_uploaded_file($_FILES['supporting_file']['tmp_name'], $target_file)) {
                $errors[] = "Failed to upload the file.";
            } else {
                $supporting_file = $target_file;
            }
        }
    }

    if (empty($errors)) {
        // Insert thesis data
        $sql = "INSERT INTO theses (title, summary, supporting_file, student_id) VALUES ('$title', '$summary', '$supporting_file', '$student_id')";
        if ($connection->query($sql)) {
            echo "<div class='alert alert-success'>Thesis submitted successfully!</div>";
            $title = $summary = $supporting_file = ""; // Reset form
        } else {
            echo "<div class='alert alert-danger'>Error: " . $connection->error . "</div>";
        }
    }
}
?>

<div class="container">
    <h2>Submit Thesis</h2>
    <?php if (!empty($errors)): ?>
        <div class="alert alert-danger">
            <ul>
                <?php foreach ($errors as $error): ?>
                    <li><?php echo htmlspecialchars($error); ?></li>
                <?php endforeach; ?>
            </ul>
        </div>
    <?php endif; ?>
    <form method="POST" action="submit_thesis.php" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" name="title" id="title" class="form-control" value="<?php echo htmlspecialchars($title); ?>" required>
        </div>
        <div class="mb-3">
            <label for="summary" class="form-label">Summary</label>
            <textarea name="summary" id="summary" class="form-control" rows="5" required><?php echo htmlspecialchars($summary); ?></textarea>
        </div>
        <div class="mb-3">
            <label for="supporting_file" class="form-label">Supporting File (optional)</label>
            <input type="file" name="supporting_file" id="supporting_file" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">Submit Thesis</button>
    </form>
</div>

<?php include 'includes/footer.php'; ?>
