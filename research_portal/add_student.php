<?php
require_once 'includes/config.php';
include 'includes/header.php';

// Restrict access to faculty only
if (!isset($_SESSION['role']) || $_SESSION['role'] !== 'faculty') {
    header('Location: login.php');
    exit;
}

$errors = [];
$first_name = $last_name = $email = $program_name = $institute_name = $city = $country = $semester = "";

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $first_name = $connection->real_escape_string($_POST['first_name']);
    $last_name = $connection->real_escape_string($_POST['last_name']);
    $email = $connection->real_escape_string($_POST['email']);
    $password = password_hash('defaultpassword', PASSWORD_BCRYPT); // Default password
    $program_name = $connection->real_escape_string($_POST['program_name']);
    $institute_name = $connection->real_escape_string($_POST['institute_name']);
    $city = $connection->real_escape_string($_POST['city']);
    $country = $connection->real_escape_string($_POST['country']);
    $semester = $connection->real_escape_string($_POST['semester']);
    $profile_photo = 'assets/uploads/profiles/default.png'; // Default photo path

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

    // Validate email format
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $errors[] = "Invalid email format.";
    }

    // Validate email uniqueness
    $query = "SELECT * FROM users WHERE email = '$email'";
    $result = $connection->query($query);
    if ($result && $result->num_rows > 0) {
        $errors[] = "This email is already registered.";
    }

    if (empty($errors)) {
        // Insert student into the database
        $sql = "INSERT INTO users (first_name, last_name, email, password, program_name, institute_name, city, country, semester, profile_photo, role) 
                VALUES ('$first_name', '$last_name', '$email', '$password', '$program_name', '$institute_name', '$city', '$country', '$semester', '$profile_photo', 'student')";
        if ($connection->query($sql)) {
            header('Location: students.php?successMessage=Student added successfully! Default password is: defaultpassword');
            exit;
        } else {
            $errors[] = "Error: " . $connection->error;
        }
    }
}
?>

<div class="container">
    <h2>Add New Student</h2>
    <?php if (!empty($errors)): ?>
        <div class="alert alert-danger">
            <ul>
                <?php foreach ($errors as $error): ?>
                    <li><?php echo htmlspecialchars($error); ?></li>
                <?php endforeach; ?>
            </ul>
        </div>
    <?php endif; ?>
    <form method="POST" action="add_student.php" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="first_name" class="form-label">First Name</label>
            <input type="text" name="first_name" id="first_name" class="form-control" value="<?php echo htmlspecialchars($first_name); ?>" required>
        </div>
        <div class="mb-3">
            <label for="last_name" class="form-label">Last Name</label>
            <input type="text" name="last_name" id="last_name" class="form-control" value="<?php echo htmlspecialchars($last_name); ?>" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" name="email" id="email" class="form-control" value="<?php echo htmlspecialchars($email); ?>" required>
        </div>
        <div class="mb-3">
            <label for="program_name" class="form-label">Program Name</label>
            <input type="text" name="program_name" id="program_name" class="form-control" value="<?php echo htmlspecialchars($program_name); ?>" required>
        </div>
        <div class="mb-3">
            <label for="institute_name" class="form-label">Institute Name</label>
            <input type="text" name="institute_name" id="institute_name" class="form-control" value="<?php echo htmlspecialchars($institute_name); ?>" required>
        </div>
        <div class="mb-3">
            <label for="city" class="form-label">City</label>
            <input type="text" name="city" id="city" class="form-control" value="<?php echo htmlspecialchars($city); ?>">
        </div>
        <div class="mb-3">
            <label for="country" class="form-label">Country</label>
            <input type="text" name="country" id="country" class="form-control" value="<?php echo htmlspecialchars($country); ?>">
        </div>
        <div class="mb-3">
            <label for="semester" class="form-label">Semester</label>
            <input type="text" name="semester" id="semester" class="form-control" value="<?php echo htmlspecialchars($semester); ?>">
        </div>
        <div class="mb-3">
            <label for="profile_photo" class="form-label">Profile Photo (optional)</label>
            <input type="file" name="profile_photo" id="profile_photo" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">Add Student</button>
    </form>
</div>

<?php include 'includes/footer.php'; ?>
