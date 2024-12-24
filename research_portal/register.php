<?php
require_once 'includes/config.php';
include 'includes/header.php';

$first_name = $last_name = $email = $city = $country = $program_name = $institute_name = $role = "";
$errors = [];

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Fetch form data and sanitize inputs
    $first_name = $connection->real_escape_string($_POST['first_name']);
    $last_name = $connection->real_escape_string($_POST['last_name']);
    $email = $connection->real_escape_string($_POST['email']);
    $password = $_POST['password'];
    $confirm_password = $_POST['confirm_password'];
    $city = $connection->real_escape_string($_POST['city']);
    $country = $connection->real_escape_string($_POST['country']);
    $program_name = $connection->real_escape_string($_POST['program_name']);
    $institute_name = $connection->real_escape_string($_POST['institute_name']);
    $role = $connection->real_escape_string($_POST['role']);

    // Validate inputs
    if (empty($first_name) || empty($last_name) || empty($email) || empty($password) || empty($confirm_password)) {
        $errors[] = "All fields are required.";
    }
    if ($password !== $confirm_password) {
        $errors[] = "Passwords do not match.";
    }

    // Check if email already exists
    $query = "SELECT * FROM users WHERE email = '$email'";
    $result = $connection->query($query);
    if ($result && $result->num_rows > 0) {
        $errors[] = "This email is already registered.";
    }

    if (empty($errors)) {
        // Hash the password
        $hashed_password = password_hash($password, PASSWORD_BCRYPT);

        // Insert into database
        $sql = "INSERT INTO users (first_name, last_name, email, password, city, country, program_name, institute_name, role) 
                VALUES ('$first_name', '$last_name', '$email', '$hashed_password', '$city', '$country', '$program_name', '$institute_name', '$role')";

        if ($connection->query($sql)) {
            header('Location: login.php?successMessage=Registration successful! Please log in.');
            exit();
        } else {
            $errors[] = "Error: " . $connection->error;
        }
    }
}
?>

<div class="row">
    <div class="col-md-6 offset-md-3">
        <h2 class="text-center">Welcome to Research Portal</h2>
        <p class="text-center">Join our community and unlock the power of research!</p>
        <h2 class="text-center">Register An Account</h2>
        <p>Have an account? <a href="login.php">Login</a></p>

        <?php if (!empty($errors)): ?>
            <div class="alert alert-danger">
                <ul>
                    <?php foreach ($errors as $error): ?>
                        <li><?php echo htmlspecialchars($error); ?></li>
                    <?php endforeach; ?>
                </ul>
            </div>
        <?php endif; ?>

        <form method="POST" action="register.php">
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
                <label for="password" class="form-label">Password</label>
                <input type="password" name="password" id="password" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="confirm_password" class="form-label">Confirm Password</label>
                <input type="password" name="confirm_password" id="confirm_password" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="city" class="form-label">City</label>
                <input type="text" name="city" id="city" class="form-control" value="<?php echo htmlspecialchars($city); ?>" required>
            </div>
            <div class="mb-3">
                <label for="country" class="form-label">Country</label>
                <input type="text" name="country" id="country" class="form-control" value="<?php echo htmlspecialchars($country); ?>" required>
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
                <label for="role" class="form-label">Role</label>
                <select name="role" id="role" class="form-select" required>
                    <option value="student" <?php if ($role === 'student') echo 'selected'; ?>>Student</option>
                    <option value="faculty" <?php if ($role === 'faculty') echo 'selected'; ?>>Faculty</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>
</div>

<?php include 'includes/footer.php'; ?>
