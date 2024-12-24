<?php
require_once 'includes/config.php';
include 'includes/header.php';

// Restrict access to faculty only
if (!isset($_SESSION['role']) || $_SESSION['role'] !== 'faculty') {
    header('Location: login.php');
    exit;
}

// Fetch student details
$student_id = $_GET['id'] ?? null;
if (!$student_id) {
    echo "<div class='alert alert-danger'>Invalid student ID.</div>";
    include 'includes/footer.php';
    exit;
}

$sql = "SELECT * FROM users WHERE id = $student_id AND role = 'student'";
$result = $connection->query($sql);
if ($result && $result->num_rows === 1) {
    $student = $result->fetch_assoc();
} else {
    echo "<div class='alert alert-danger'>Student not found.</div>";
    include 'includes/footer.php';
    exit;
}

$errors = [];
$successMessage = null;

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $first_name = $connection->real_escape_string($_POST['first_name']);
    $last_name = $connection->real_escape_string($_POST['last_name']);
    $email = $connection->real_escape_string($_POST['email']);
    $program_name = $connection->real_escape_string($_POST['program_name']);
    $institute_name = $connection->real_escape_string($_POST['institute_name']);
    $semester = $connection->real_escape_string($_POST['semester']);
    $profile_photo = $student['profile_photo'];

    // Handle profile photo upload
    if (!empty($_FILES['profile_photo']['name'])) {
        $target_dir = "assets/uploads/profiles/";
        // Ensure the directory exists
        if (!is_dir($target_dir)) {
            mkdir($target_dir, 0777, true);
        }

        $file_name = basename($_FILES['profile_photo']['name']);
        $target_file = $target_dir . $file_name;
        $file_type = strtolower(pathinfo($target_file, PATHINFO_EXTENSION));
        $allowed_types = ['jpg', 'jpeg', 'png', 'gif'];

        if (in_array($file_type, $allowed_types)) {
            if (move_uploaded_file($_FILES['profile_photo']['tmp_name'], $target_file)) {
                $profile_photo = $target_file;
            } else {
                $errors[] = "Failed to upload the profile photo.";
            }
        } else {
            $errors[] = "Only JPG, JPEG, PNG, and GIF files are allowed.";
        }
    }

    if (empty($errors)) {
        // Update student details
        $update_query = "UPDATE users SET 
            first_name = '$first_name', 
            last_name = '$last_name', 
            email = '$email', 
            program_name = '$program_name', 
            institute_name = '$institute_name', 
            semester = '$semester', 
            profile_photo = '$profile_photo'
        WHERE id = $student_id";

        if ($connection->query($update_query)) {
            $successMessage = "Student details updated successfully!";
        } else {
            $errors[] = "Error updating student details: " . $connection->error;
        }
    }
}
?>

<div class="container">
    <h2>Edit Student Details</h2>
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

    <form method="POST" action="update_student.php?id=<?php echo $student_id; ?>" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="first_name" class="form-label">First Name</label>
            <input type="text" name="first_name" id="first_name" class="form-control" value="<?php echo htmlspecialchars($student['first_name']); ?>" required>
        </div>
        <div class="mb-3">
            <label for="last_name" class="form-label">Last Name</label>
            <input type="text" name="last_name" id="last_name" class="form-control" value="<?php echo htmlspecialchars($student['last_name']); ?>" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" name="email" id="email" class="form-control" value="<?php echo htmlspecialchars($student['email']); ?>" required>
        </div>
        <div class="mb-3">
            <label for="program_name" class="form-label">Program Name</label>
            <input type="text" name="program_name" id="program_name" class="form-control" value="<?php echo htmlspecialchars($student['program_name']); ?>" required>
        </div>
        <div class="mb-3">
            <label for="institute_name" class="form-label">Institute Name</label>
            <input type="text" name="institute_name" id="institute_name" class="form-control" value="<?php echo htmlspecialchars($student['institute_name']); ?>" required>
        </div>
        <div class="mb-3">
            <label for="semester" class="form-label">Semester</label>
            <input type="text" name="semester" id="semester" class="form-control" value="<?php echo htmlspecialchars($student['semester']); ?>">
        </div>
        <div class="mb-3">
            <label for="profile_photo" class="form-label">Profile Photo</label>
            <?php if (!empty($student['profile_photo'])): ?>
                <img src="<?php echo htmlspecialchars($student['profile_photo']); ?>" alt="Profile Photo" class="img-thumbnail mb-3" style="max-width: 100px;">
            <?php endif; ?>
            <input type="file" name="profile_photo" id="profile_photo" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">Save Changes</button>
    </form>
</div>

<?php include 'includes/footer.php'; ?>
