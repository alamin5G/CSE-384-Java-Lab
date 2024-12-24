<?php
require_once 'includes/config.php';
include 'includes/header.php';

// Get thesis ID
$thesis_id = $_GET['id'] ?? null;

if (!$thesis_id) {
    echo "<div class='alert alert-danger'>Invalid thesis ID.</div>";
    include 'includes/footer.php';
    exit;
}

// Fetch thesis details
$sql = "SELECT t.title, t.summary, t.supporting_file, u.first_name, u.last_name
        FROM theses t
        JOIN users u ON t.student_id = u.id
        WHERE t.id = $thesis_id";
$result = $connection->query($sql);

if ($result && $result->num_rows === 1):
    $thesis = $result->fetch_assoc();
?>
    <div class="container">
        <h2><?php echo htmlspecialchars($thesis['title']); ?></h2>
        <p><strong>Submitted By:</strong> <?php echo htmlspecialchars($thesis['first_name'] . ' ' . $thesis['last_name']); ?></p>
        <p><?php echo nl2br(htmlspecialchars($thesis['summary'])); ?></p>
        <?php if (!empty($thesis['supporting_file'])): ?>
            <a href="<?php echo htmlspecialchars($thesis['supporting_file']); ?>" class="btn btn-success" download>Download Supporting File</a>
        <?php endif; ?>
    </div>
<?php else: ?>
    <div class="alert alert-danger">Thesis not found.</div>
<?php endif; ?>

<?php include 'includes/footer.php'; ?>
