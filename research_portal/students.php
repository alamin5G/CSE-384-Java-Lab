<?php
require_once 'includes/config.php';
include 'includes/header.php';

// Restrict access to faculty only
if (!isset($_SESSION['role']) || $_SESSION['role'] !== 'faculty') {
    header('Location: login.php');
    exit;
}

// Initialize search parameters
$search_column = $_GET['search_column'] ?? '';
$search_value = $_GET['search_value'] ?? '';

// Build the SQL query with search functionality
$sql = "SELECT id, first_name, last_name, email, program_name, institute_name, semester, profile_photo 
        FROM users 
        WHERE role = 'student'";

if (!empty($search_column) && !empty($search_value)) {
    $search_value = $connection->real_escape_string($search_value);

    if ($search_column === 'name') {
        // Search by full name
        $sql .= " AND CONCAT(first_name, ' ', last_name) LIKE '%$search_value%'";
    } else {
        // Search by other columns
        $sql .= " AND $search_column LIKE '%$search_value%'";
    }
}

$result = $connection->query($sql);
?>

<div class="container">
    <h2>All Registered Students</h2>

    <!-- Search Form -->
    <form method="GET" action="students.php" class="mb-3">
        <div class="row">
            <div class="col-md-3">
                <select name="search_column" class="form-select" required>
                    <option value="" disabled selected>Search By</option>
                    <option value="name" <?php if ($search_column === 'name') echo 'selected'; ?>>Name</option>
                    <option value="email" <?php if ($search_column === 'email') echo 'selected'; ?>>Email</option>
                    <option value="program_name" <?php if ($search_column === 'program_name') echo 'selected'; ?>>Program</option>
                    <option value="semester" <?php if ($search_column === 'semester') echo 'selected'; ?>>Semester</option>
                    <option value="institute_name" <?php if ($search_column === 'institute_name') echo 'selected'; ?>>Institute</option>
                </select>
            </div>
            <div class="col-md-6">
                <input type="text" name="search_value" class="form-control" placeholder="Enter search term" value="<?php echo htmlspecialchars($search_value); ?>" required>
            </div>
            <div class="col-md-3">
                <button type="submit" class="btn btn-primary w-100">Search</button>
            </div>
        </div>
    </form>

    <?php if ($result && $result->num_rows > 0): ?>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Photo</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Program</th>
                    <th>Semester</th>
                    <th>Institute</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <?php while ($row = $result->fetch_assoc()): ?>
                    <tr>
                        <!-- Display Profile Photo -->
                        <td>
                            <?php if (!empty($row['profile_photo'])): ?>
                                <img src="<?php echo htmlspecialchars($row['profile_photo']); ?>" alt="Profile Photo" class="img-thumbnail" style="width: 50px; height: 50px;">
                            <?php else: ?>
                                <img src="assets/uploads/profiles/default.png" alt="Default Photo" class="img-thumbnail" style="width: 50px; height: 50px;">
                            <?php endif; ?>
                        </td>
                        <td><?php echo htmlspecialchars($row['first_name'] . ' ' . $row['last_name']); ?></td>
                        <td><?php echo htmlspecialchars($row['email']); ?></td>
                        <td><?php echo htmlspecialchars($row['program_name']); ?></td>
                        <td><?php echo htmlspecialchars($row['semester'] ?? 'N/A'); ?></td>
                        <td><?php echo htmlspecialchars($row['institute_name']); ?></td>
                        <td>
                            <a href="update_student.php?id=<?php echo $row['id']; ?>" class="btn btn-primary btn-sm">Edit</a>
                        </td>
                    </tr>
                <?php endwhile; ?>
            </tbody>
        </table>
    <?php else: ?>
        <p>No students found.</p>
    <?php endif; ?>
</div>

<?php include 'includes/footer.php'; ?>
