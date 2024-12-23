<?php
include 'includes/header.php';
include 'includes/db.php';
include 'includes/functions.php';

if (!isset($_SESSION['user_id'])) {
    header("Location: login.php");
    exit();
}

$user_id = $_SESSION['user_id'];
$filters = [];
$results = [];
$limit = 20; // Number of results per page
$page = isset($_GET['page']) ? intval($_GET['page']) : 1;
$offset = ($page - 1) * $limit;
$total_pages = 0;

// Handle search
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $category = $_POST['category'];
    $start_date = $_POST['start_date'];
    $end_date = $_POST['end_date'];

    $sql = "SELECT expenses.*, categories.name as category_name 
        FROM expenses 
        JOIN categories ON expenses.category_id = categories.id
        WHERE expenses.user_id = '$user_id'";

if (!empty($category)) {
    $sql .= " AND expenses.category_id = '$category'";
}
if (!empty($start_date) && !empty($end_date)) {
    $sql .= " AND expenses.expense_date BETWEEN '$start_date' AND '$end_date'";
}


    $sql .= " ORDER BY expenses.expense_date DESC LIMIT $limit OFFSET $offset";

    $result = mysqli_query($conn, $sql);
    if ($result) {
        $results = mysqli_fetch_all($result, MYSQLI_ASSOC);
    }
}

// Fetch total expenses for pagination
$count_sql = "SELECT COUNT(*) as total FROM expenses WHERE user_id = '$user_id'";
$count_result = mysqli_query($conn, $count_sql);
$total_records = mysqli_fetch_assoc($count_result)['total'] ?? 0;
$total_pages = ceil($total_records / $limit);

// Fetch categories for dropdown
$categories = [];
$sql = "SELECT * FROM categories";
$result = mysqli_query($conn, $sql);
if ($result) {
    $categories = mysqli_fetch_all($result, MYSQLI_ASSOC);
}
?>

<div class="container mt-5">
    <h2>Search Expenses</h2>
    <a href="add_expense.php" class="btn btn-primary mb-4">Add Expense</a>
    <form method="POST" action="search_expenses.php">
        <div class="mb-3">
            <label for="category" class="form-label">Category</label>
            <select class="form-select" id="category" name="category">
                <option value="">All Categories</option>
                <?php foreach ($categories as $category): ?>
                    <option value="<?php echo $category['id']; ?>"><?php echo $category['name']; ?></option>
                <?php endforeach; ?>
            </select>
        </div>
        <div class="mb-3">
            <label for="start_date" class="form-label">Start Date</label>
            <input type="date" class="form-control" id="start_date" name="start_date" max="<?php echo date('Y-m-d'); ?>">
        </div>
        <div class="mb-3">
            <label for="end_date" class="form-label">End Date</label>
            <input type="date" class="form-control" id="end_date" name="end_date" max="<?php echo date('Y-m-d'); ?>">
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>

    <?php if (!empty($results)): ?>
        <h3 class="mt-4">Search Results</h3>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Category</th>
                    <th>Amount</th>
                    <th>Date</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <?php foreach ($results as $expense): ?>
                    <tr>
                        <td><?php echo $expense['category_name']; ?></td>
                        <td>$<?php echo number_format($expense['amount'], 2); ?></td>
                        <td><?php echo $expense['expense_date']; ?></td>
                        <td><?php echo $expense['description']; ?></td>
                        <td>
                            <a href="edit_expense.php?id=<?php echo $expense['id']; ?>" class="btn btn-warning btn-sm">Edit</a>
                            <a href="delete_expense.php?id=<?php echo $expense['id']; ?>" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this expense?')">Delete</a>
                        </td>
                    </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
        <!-- Pagination -->
        <nav>
            <ul class="pagination">
                <?php for ($i = 1; $i <= $total_pages; $i++): ?>
                    <li class="page-item <?php echo ($i == $page) ? 'active' : ''; ?>">
                        <a class="page-link" href="?page=<?php echo $i; ?>"><?php echo $i; ?></a>
                    </li>
                <?php endfor; ?>
            </ul>
        </nav>
    <?php else: ?>
        <p class="alert alert-warning mt-4">No expenses found for the selected criteria.</p>
    <?php endif; ?>
</div>

<?php include 'includes/footer.php'; ?>
