<?php
include '../header.php';
require '../db.php';

if (!isset($_SESSION['is_admin']) || !$_SESSION['is_admin']) {
    header("Location: ../login.php");
    exit();
}

// Pagination settings
$limit = 10; // Number of questions per page
$page = isset($_GET['page']) && is_numeric($_GET['page']) ? $_GET['page'] : 1;
$offset = ($page - 1) * $limit;

// Handle search functionality
$search_query = isset($_GET['query']) ? "%" . $_GET['query'] . "%" : "%";
$stmt = mysqli_prepare($conn, "SELECT SQL_CALC_FOUND_ROWS * FROM questions 
                                WHERE question LIKE ? 
                                LIMIT ? OFFSET ?");
mysqli_stmt_bind_param($stmt, "sii", $search_query, $limit, $offset);
mysqli_stmt_execute($stmt);
$result = mysqli_stmt_get_result($stmt);
$questions = mysqli_fetch_all($result, MYSQLI_ASSOC);

// Get total number of results for pagination
$total_result_stmt = mysqli_query($conn, "SELECT FOUND_ROWS() AS total");
$total_result = mysqli_fetch_assoc($total_result_stmt)['total'];
$total_pages = ceil($total_result / $limit);

// Success message after editing or deleting
$success_message = isset($_GET['success']) ? $_GET['success'] : "";
?>

<h1>Search Questions</h1>

<!-- Display Success Message -->
<?php if ($success_message): ?>
<div class="alert alert-success"><?= htmlspecialchars($success_message) ?></div>
<?php endif; ?>

<form method="get">
    <div class="mb-3">
        <input type="text" name="query" class="form-control" placeholder="Search questions..."
            value="<?= htmlspecialchars($_GET['query'] ?? '') ?>">
    </div>
    <button type="submit" class="btn btn-primary">Search</button>
</form>

<?php if (!empty($questions)): ?>
<table class="table mt-4">
    <thead>
        <tr>
            <th>ID</th>
            <th>Question</th>
            <th>Correct Answer</th>
            <th>Created At</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <?php foreach ($questions as $question): ?>
        <tr>
            <td><?= $question['id'] ?></td>
            <td><?= htmlspecialchars($question['question']) ?></td>
            <td><?= htmlspecialchars($question["option{$question['correct_option']}"]) ?></td>
            <td><?= $question['created_at'] ?></td>
            <td>
                <a href="edit_question.php?id=<?= $question['id'] ?>" class="btn btn-sm btn-outline-primary">Edit</a>
                <form method="post" action="delete_question.php" style="display:inline;">
                    <input type="hidden" name="delete_id" value="<?= $question['id'] ?>">
                    <form method="post" action="delete_question.php" style="display:inline;">
                        <input type="hidden" name="delete_id" value="<?= $question['id'] ?>">
                        <button type="submit" class="btn btn-sm btn-outline-danger"
                            onclick="return confirm('Are you sure you want to delete this question?');">Delete</button>
                    </form>

                </form>
            </td>
        </tr>
        <?php endforeach; ?>
    </tbody>
</table>

<!-- Pagination Controls -->
<nav>
    <ul class="pagination justify-content-center">
        <?php for ($i = 1; $i <= $total_pages; $i++): ?>
        <li class="page-item <?= ($i == $page) ? 'active' : '' ?>">
            <a class="page-link" href="?query=<?= urlencode($_GET['query'] ?? '') ?>&page=<?= $i ?>">
                <?= $i ?>
            </a>
        </li>
        <?php endfor; ?>
    </ul>
</nav>
<?php else: ?>
<div class="alert alert-warning text-center mt-4">No results found for "<?= htmlspecialchars($_GET['query']) ?>"</div>
<?php endif; ?>
<?php include '../footer.php'; ?>