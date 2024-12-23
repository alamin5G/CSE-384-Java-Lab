<?php
include 'includes/header.php';
include 'includes/functions.php';
include 'includes/db.php';

if (!isset($_SESSION['user_id'])) {
    header("Location: login.php");
    exit();
}

// Define $user_id at the top
$user_id = $_SESSION['user_id'];

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $category_id = $_POST['category'];
    $amount = $_POST['amount'];
    $expense_date = $_POST['expense_date'];
    $description = $_POST['description'];

    $sql = "INSERT INTO expenses (user_id, category_id, amount, expense_date, description) 
            VALUES ('$user_id', '$category_id', '$amount', '$expense_date', '$description')";

    if (mysqli_query($conn, $sql)) {
        redirectWithMessage('dashboard.php', 'Expense added successfully.');
    } else {
        echo "<div class='alert alert-danger mt-3'>Error: " . mysqli_error($conn) . "</div>";
    }
}

// Fetch categories for dropdown (user-specific)
$categories = [];
$sql = "SELECT * FROM categories WHERE user_id = '$user_id' ORDER BY name ASC";
$result = mysqli_query($conn, $sql);
if ($result) {
    $categories = mysqli_fetch_all($result, MYSQLI_ASSOC);
}
?>

<div class="container mt-5">
    <h2>Add Expense</h2>
    <form method="POST" action="add_expense.php">
        <div class="mb-3">
            <label for="category" class="form-label">Category</label>
            <select class="form-select" id="category" name="category" required>
                <option value="" selected disabled>Select a category</option>
                <?php foreach ($categories as $category): ?>
                    <option value="<?php echo $category['id']; ?>"><?php echo $category['name']; ?></option>
                <?php endforeach; ?>
            </select>
        </div>
        <div class="mb-3">
            <label for="amount" class="form-label">Amount</label>
            <input type="number" step="0.01" class="form-control" id="amount" name="amount" required>
        </div>
        <div class="mb-3">
            <label for="expense_date" class="form-label">Date</label>
            <input type="date" class="form-control" id="expense_date" name="expense_date" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Add Expense</button>
    </form>
</div>

<?php include 'includes/footer.php'; ?>
