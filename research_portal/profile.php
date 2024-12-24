<?php
require_once 'includes/config.php';
include 'includes/header.php';

// Restrict access to logged-in users
if (!isset($_SESSION['role'])) {
    header('Location: login.php');
    exit;
}

$errors = [];
$successMessage = null;
$user_id = $_SESSION['id'];

// Fetch user details
$sql = "SELECT * FROM users WHERE id = $user_id";
$result = $connection->query($sql);
$user = $result->fetch_assoc();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $first_name = $connection->real_escape_string($_POST['first_name']);
    $last_name = $connection->real_escape_string($_POST['last_name']);
    $email = $connection->real_escape_string($_POST['email']);
    $city = $connection->real_escape_string($_POST['city']);
    $country = $connection->real_escape_string($_POST['country']);
    $program_name = $connection->real_escape_string($_POST['program_name']);
    $institute_name = $connection->real_escape_string($_POST['institute_name']);
    $semester = $_SESSION['role'] === 'student' ? $connection->real_escape_string($_POST['semester']) : null;
    $password = !empty($_POST['password']) ? password_hash($_POST['password'], PASSWORD_BCRYPT) : null;

    // Handle profile photo upload
    $profile_photo = $user['profile_photo'];
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

    // Validation
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $errors[] = "Invalid email address.";
    } else {
        // Check for unique email
        $check_email_query = "SELECT id FROM users WHERE email = '$email' AND id != $user_id";
        $check_email_result = $connection->query($check_email_query);
        if ($check_email_result->num_rows > 0) {
            $errors[] = "This email is already taken.";
        }
    }

    if (empty($errors)) {
        // Update user profile
        $update_query = "UPDATE users SET 
            first_name = '$first_name', 
            last_name = '$last_name', 
            email = '$email', 
            city = '$city', 
            country = '$country', 
            program_name = '$program_name', 
            institute_name = '$institute_name', 
            semester = '$semester', 
            profile_photo = '$profile_photo'";
        if ($password) {
            $update_query .= ", password = '$password'";
        }
        $update_query .= " WHERE id = $user_id";

        if ($connection->query($update_query)) {
            header('Location: profile.php?successMessage=Profile updated successfully!');
            exit;
        } else {
            $errors[] = "Error updating profile: " . $connection->error;
        }
    }
}

// Handle success message
if (isset($_GET['successMessage'])) {
    $successMessage = $_GET['successMessage'];
}
?>

<div class="container">
    <h2>My Profile</h2>
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
        <div class="alert alert-success">
            <?php echo htmlspecialchars($successMessage); ?>
        </div>
    <?php endif; ?>

    <form method="POST" action="profile.php" enctype="multipart/form-data">
        <!-- Display Profile Photo -->
        <div class="mb-3 text-center">
            <?php if (!empty($user['profile_photo'])): ?>
                <img src="<?php echo htmlspecialchars($user['profile_photo']); ?>" alt="Profile Photo" class="img-thumbnail mb-3" style="max-width: 150px;">
            <?php else: ?>
                <img src="assets/uploads/profiles/default.png" alt="Default Profile Photo" class="img-thumbnail mb-3" style="max-width: 150px;">
            <?php endif; ?>
        </div>

        <!-- Read-only mode -->
        <div class="mb-3">
            <label for="first_name" class="form-label">First Name</label>
            <input type="text" name="first_name" id="first_name" class="form-control" value="<?php echo htmlspecialchars($user['first_name'] ?? ''); ?>" readonly>
        </div>
        <div class="mb-3">
            <label for="last_name" class="form-label">Last Name</label>
            <input type="text" name="last_name" id="last_name" class="form-control" value="<?php echo htmlspecialchars($user['last_name'] ?? ''); ?>" readonly>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" name="email" id="email" class="form-control" value="<?php echo htmlspecialchars($user['email'] ?? ''); ?>" readonly>
        </div>
        <div class="mb-3">
            <label for="city" class="form-label">City</label>
            <input type="text" name="city" id="city" class="form-control" value="<?php echo htmlspecialchars($user['city'] ?? ''); ?>" readonly>
        </div>
        <div class="mb-3">
            <label for="country" class="form-label">Country</label>
            <input type="text" name="country" id="country" class="form-control" value="<?php echo htmlspecialchars($user['country'] ?? ''); ?>" readonly>
        </div>
        <div class="mb-3">
            <label for="program_name" class="form-label">Program Name</label>
            <input type="text" name="program_name" id="program_name" class="form-control" value="<?php echo htmlspecialchars($user['program_name'] ?? ''); ?>" readonly>
        </div>
        <div class="mb-3">
            <label for="institute_name" class="form-label">Institute Name</label>
            <input type="text" name="institute_name" id="institute_name" class="form-control" value="<?php echo htmlspecialchars($user['institute_name'] ?? ''); ?>" readonly>
        </div>
        <?php if ($_SESSION['role'] === 'student'): ?>
            <div class="mb-3">
                <label for="semester" class="form-label">Semester</label>
                <input type="text" name="semester" id="semester" class="form-control" value="<?php echo htmlspecialchars($user['semester'] ?? ''); ?>" readonly>
            </div>
        <?php endif; ?>
        <div class="mb-3">
            <label for="profile_photo" class="form-label">Update Profile Photo (optional)</label>
            <input type="file" name="profile_photo" id="profile_photo" class="form-control" readonly>
        </div>

        <!-- Toggle Edit -->
        <button type="button" class="btn btn-secondary" id="editProfileButton">Edit Profile</button>
        <div id="editSection" style="display: none;">
            <div class="mb-3">
                <label for="password" class="form-label">New Password (optional)</label>
                <input type="password" name="password" id="password" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">Save Changes</button>
        </div>
    </form>
</div>

<script>
    // Enable editing when "Edit Profile" button is clicked
    document.getElementById('editProfileButton').addEventListener('click', function() {
        document.querySelectorAll('input[readonly]').forEach(input => input.removeAttribute('readonly'));
        document.getElementById('editSection').style.display = 'block';
        this.style.display = 'none';
    });
</script>

<?php include 'includes/footer.php'; ?>
