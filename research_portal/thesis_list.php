<?php
require_once 'includes/config.php';
include 'includes/header.php';

// Restrict access to students only
if (!isset($_SESSION['role']) || $_SESSION['role'] !== 'student') {
    header('Location: login.php');
    exit;
}

// Fetch all theses
$sql = "SELECT t.id, t.title, t.summary, t.supporting_file, u.first_name, u.last_name
        FROM theses t
        JOIN users u ON t.student_id = u.id
        WHERE t.student_id = {$_SESSION['id']}"; // Only fetch the logged-in student's theses
$result = $connection->query($sql);

// Check for success message
$successMessage = $_GET['successMessage'] ?? null;
?>

<div class="container">
    <h2>Thesis List</h2>

    <!-- Display success message -->
    <?php if ($successMessage): ?>
        <div class="alert alert-success">
            <?php echo htmlspecialchars($successMessage); ?>
        </div>
    <?php endif; ?>

    <?php if ($result && $result->num_rows > 0): ?>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Summary</th>
                    <th>Submitted By</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <?php while ($row = $result->fetch_assoc()): ?>
                    <tr>
                        <td><?php echo htmlspecialchars($row['title']); ?></td>
                        <td><?php echo htmlspecialchars(substr($row['summary'], 0, 100)) . '...'; ?></td>
                        <td><?php echo htmlspecialchars($row['first_name'] . ' ' . $row['last_name']); ?></td>
                        <td>
                            <a href="thesis_details.php?id=<?php echo $row['id']; ?>" class="btn btn-primary btn-sm">View Details</a>
                            <a href="edit_thesis.php?id=<?php echo $row['id']; ?>" class="btn btn-warning btn-sm">Edit</a>
                            <?php if (!empty($row['supporting_file'])): ?>
                                <a href="<?php echo htmlspecialchars($row['supporting_file']); ?>" class="btn btn-success btn-sm" download>Download</a>
                            <?php endif; ?>
                        </td>
                    </tr>
                <?php endwhile; ?>
            </tbody>
        </table>
    <?php else: ?>
        <p>No theses found.</p>
    <?php endif; ?>
</div>

<?php include 'includes/footer.php'; ?>
