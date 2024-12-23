<?php
include 'includes/header.php';
include 'includes/db.php';
include 'includes/functions.php';

if (!isset($_SESSION['user_id'])) {
    header("Location: login.php");
    exit();
}

$user_id = $_SESSION['user_id'];
$expense_id = $_GET['id'] ?? null;

if (!$expense_id) {
    redirectWithMessage('dashboard.php', 'Invalid expense ID.');
}

// Fetch expense details (user-specific)
$sql = "SELECT * FROM expenses WHERE id = '$expense_id' AND user_id = '$user_id'";
$result = mysqli_query($conn, $sql);
$expense = mysqli_fetch_assoc($result);

if (!$expense) {
    redirectWithMessage('dashboard.php', 'Expense not found.');
}

// Handle update
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $category_id = $_POST['category'];
    $amount = $_POST['amount'];
    $expense_date = $_POST['expense_date'];
    $description = $_POST['description'];

    $sql = "UPDATE expenses SET 
                category_id = '$category_id',
                amount = '$amount',
                expense_date = '$expense_date',
                description = '$description'
            WHERE id = '$expense_id' AND user_id = '$user_id'";

    if (mysqli_query($conn, $sql)) {
        redirectWithMessage('dashboard.php', 'Expense updated successfully.');
    } else {
        echo "<div class='alert alert-danger mt-3'>Error: " . mysqli_error($conn) . "</div>";
    }
}

// Fetch categories for dropdown (user-specific)
$sql = "SELECT * FROM categories WHERE user_id = '$user_id' ORDER BY name ASC";
$result = mysqli_query($conn, $sql);
if ($result) {
    $categories = mysqli_fetch_all($result, MYSQLI_ASSOC);
}
?>

<div class="container mt-5">
    <h2>Edit Expense</h2>
    <form method="POST" action="edit_expense.php?id=<?php echo $expense_id; ?>">
        <div class="mb-3">
            <label for="category" class="form-label">Category</label>
            <select class="form-select" id="category" name="category" required>
                <?php foreach ($categories as $category): ?>
                    <option value="<?php echo $category['id']; ?>" <?php echo ($category['id'] == $expense['category_id']) ? 'selected' : ''; ?>>
                        <?php echo $category['name']; ?>
                    </option>
                <?php endforeach; ?>
            </select>
        </div>
        <div class="mb-3">
            <label for="amount" class="form-label">Amount</label>
            <input type="number" step="0.01" class="form-control" id="amount" name="amount" value="<?php echo $expense['amount']; ?>" required>
        </div>
        <div class="mb-3">
            <label for="expense_date" class="form-label">Date</label>
            <input type="date" class="form-control" id="expense_date" name="expense_date" value="<?php echo $expense['expense_date']; ?>" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3"><?php echo $expense['description']; ?></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Update Expense</button>
    </form>
</div>

<?php include 'includes/footer.php'; ?>
