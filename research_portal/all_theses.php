<?php
require_once 'includes/config.php';
include 'includes/header.php';

// Restrict access to faculty only
if (!isset($_SESSION['role']) || $_SESSION['role'] !== 'faculty') {
    header('Location: login.php');
    exit;
}

// Handle deletion
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['delete_id'])) {
    $delete_id = (int)$_POST['delete_id'];
    $sql_delete = "DELETE FROM theses WHERE id = $delete_id";
    if ($connection->query($sql_delete)) {
        $successMessage = "Thesis deleted successfully.";
    } else {
        $errorMessage = "Failed to delete thesis: " . $connection->error;
    }
}

// Initialize search parameters
$search_column = $_GET['search_column'] ?? '';
$search_value = $_GET['search_value'] ?? '';

// Pagination
$limit = 10;
$page = isset($_GET['page']) ? (int)$_GET['page'] : 1;
$offset = ($page - 1) * $limit;

// Build the query
$sql = "SELECT t.id, t.title, t.summary, t.supporting_file, t.submitted_at, u.first_name, u.last_name
        FROM theses t
        JOIN users u ON t.student_id = u.id";

if (!empty($search_column) && !empty($search_value)) {
    $search_value = $connection->real_escape_string($search_value);

    if ($search_column === 'submitted_by') {
        $sql .= " WHERE CONCAT(u.first_name, ' ', u.last_name) LIKE '%$search_value%'";
    } else {
        $sql .= " WHERE $search_column LIKE '%$search_value%'";
    }
}

$sql .= " ORDER BY t.submitted_at DESC LIMIT $limit OFFSET $offset";
$result = $connection->query($sql);
?>

<div class="container">
    <h2>All Submitted Theses</h2>

    <!-- Search Form -->
    <form method="GET" action="all_theses.php" class="mb-3">
        <div class="row">
            <div class="col-md-3">
                <select name="search_column" class="form-select" required>
                    <option value="" disabled selected>Search By</option>
                    <option value="title" <?php if ($search_column === 'title') echo 'selected'; ?>>Title</option>
                    <option value="summary" <?php if ($search_column === 'summary') echo 'selected'; ?>>Summary</option>
                    <option value="submitted_by" <?php if ($search_column === 'submitted_by') echo 'selected'; ?>>Submitted By</option>
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

    <!-- Success/Error Messages -->
    <?php if (isset($successMessage)): ?>
        <div class="alert alert-success"><?php echo $successMessage; ?></div>
    <?php endif; ?>
    <?php if (isset($errorMessage)): ?>
        <div class="alert alert-danger"><?php echo $errorMessage; ?></div>
    <?php endif; ?>

    <!-- Thesis List -->
    <?php if ($result && $result->num_rows > 0): ?>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Summary</th>
                    <th>Submitted By</th>
                    <th>Submitted At</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <?php while ($row = $result->fetch_assoc()): ?>
                    <tr>
                        <td><?php echo htmlspecialchars($row['title']); ?></td>
                        <td><?php echo htmlspecialchars(substr($row['summary'], 0, 100)) . '...'; ?></td>
                        <td><?php echo htmlspecialchars($row['first_name'] . ' ' . $row['last_name']); ?></td>
                        <td><?php echo htmlspecialchars($row['submitted_at']); ?></td>
                        <td>
                            <a href="thesis_details.php?id=<?php echo $row['id']; ?>" class="btn btn-primary btn-sm">View Details</a>
                            <?php if (!empty($row['supporting_file'])): ?>
                                <a href="<?php echo htmlspecialchars($row['supporting_file']); ?>" class="btn btn-success btn-sm" download>Download</a>
                            <?php endif; ?>
                            <form method="POST" action="all_theses.php" style="display: inline;">
                                <input type="hidden" name="delete_id" value="<?php echo $row['id']; ?>">
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this thesis?');">Delete</button>
                            </form>
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
