<?php
require_once 'includes/config.php';
include 'includes/header.php';

// Restrict access to students only
if (!isset($_SESSION['role']) || $_SESSION['role'] !== 'student') {
    header('Location: login.php');
    exit;
}

$thesis_id = $_GET['id'] ?? null;

if (!$thesis_id) {
    echo "<div class='alert alert-danger'>Invalid thesis ID.</div>";
    include 'includes/footer.php';
    exit;
}

// Fetch thesis details
$sql = "SELECT * FROM theses WHERE id = $thesis_id AND student_id = {$_SESSION['id']}";
$result = $connection->query($sql);

if ($result && $result->num_rows === 1) {
    $thesis = $result->fetch_assoc();
} else {
    echo "<div class='alert alert-danger'>Thesis not found or you do not have permission to edit it.</div>";
    include 'includes/footer.php';
    exit;
}

$errors = [];
$successMessage = null;

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $title = $connection->real_escape_string($_POST['title']);
    $summary = $connection->real_escape_string($_POST['summary']);
    $supporting_file = $thesis['supporting_file'];

    // Handle file upload
    if (!empty($_FILES['supporting_file']['name'])) {
        $target_dir = "assets/uploads/theses/";
        // Ensure the directory exists
        if (!is_dir($target_dir)) {
            mkdir($target_dir, 0777, true);
        }

        $file_name = basename($_FILES['supporting_file']['name']);
        $target_file = $target_dir . $file_name;
        $file_type = strtolower(pathinfo($target_file, PATHINFO_EXTENSION));
        $allowed_types = ['jpg', 'jpeg', 'png', 'gif', 'pdf', 'doc', 'docx'];

        if (in_array($file_type, $allowed_types)) {
            if (move_uploaded_file($_FILES['supporting_file']['tmp_name'], $target_file)) {
                $supporting_file = $target_file;
            } else {
                $errors[] = "Failed to upload the supporting file.";
            }
        } else {
            $errors[] = "Only JPG, PNG, GIF, PDF, DOC, and DOCX files are allowed.";
        }
    }

    if (empty($errors)) {
        // Update thesis
        $update_query = "UPDATE theses SET title = '$title', summary = '$summary', supporting_file = '$supporting_file' WHERE id = $thesis_id";
        if ($connection->query($update_query)) {
            header('Location: thesis_list.php?successMessage=Thesis updated successfully!');
            exit;
        } else {
            $errors[] = "Error updating thesis: " . $connection->error;
        }
    }
}
?>

<div class="container">
    <h2>Edit Thesis</h2>
    <?php if (!empty($errors)): ?>
        <div class="alert alert-danger">
            <ul>
                <?php foreach ($errors as $error): ?>
                    <li><?php echo htmlspecialchars($error); ?></li>
                <?php endforeach; ?>
            </ul>
        </div>
    <?php endif; ?>
    <?php if ($successMessage): ?>
        <div class="alert alert-success"><?php echo htmlspecialchars($successMessage); ?></div>
    <?php endif; ?>

    <form method="POST" action="edit_thesis.php?id=<?php echo $thesis_id; ?>" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" name="title" id="title" class="form-control" value="<?php echo htmlspecialchars($thesis['title']); ?>" required>
        </div>
        <div class="mb-3">
            <label for="summary" class="form-label">Summary</label>
            <textarea name="summary" id="summary" class="form-control" rows="5" required><?php echo htmlspecialchars($thesis['summary']); ?></textarea>
        </div>
        <div class="mb-3">
            <label for="supporting_file" class="form-label">Update Supporting File (optional)</label>
            <?php if (!empty($thesis['supporting_file'])): ?>
                <p>Current File: <a href="<?php echo htmlspecialchars($thesis['supporting_file']); ?>" download><?php echo basename($thesis['supporting_file']); ?></a></p>
            <?php endif; ?>
            <input type="file" name="supporting_file" id="supporting_file" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">Save Changes</button>
    </form>
</div>

<?php include 'includes/footer.php'; ?>
