<?php
include 'includes/header.php';
include 'includes/functions.php';
include 'includes/db.php';

if (!isset($_SESSION['user_id'])) {
    header("Location: login.php");
    exit();
}

$user_id = $_SESSION['user_id'];

// Fetch total expenses (user-specific)
$sql = "SELECT SUM(amount) as total FROM expenses WHERE user_id = '$user_id'";
$result = mysqli_query($conn, $sql);
$total_expenses = mysqli_fetch_assoc($result)['total'] ?? 0;

// Fetch total categories (user-specific)
$sql = "SELECT COUNT(*) as total_categories FROM categories WHERE user_id = '$user_id'";
$result = mysqli_query($conn, $sql);
$total_categories = mysqli_fetch_assoc($result)['total_categories'] ?? 0;

// Fetch top expense category with amount (user-specific)
$sql = "SELECT categories.name, SUM(expenses.amount) as total 
        FROM expenses 
        JOIN categories ON expenses.category_id = categories.id 
        WHERE expenses.user_id = '$user_id' 
        GROUP BY categories.name 
        ORDER BY total DESC 
        LIMIT 1";
$result = mysqli_query($conn, $sql);
$top_category_data = mysqli_fetch_assoc($result);
$top_category = $top_category_data['name'] ?? 'None';
$top_category_amount = $top_category_data['total'] ?? 0;


// Fetch lowest expense category with amount
$sql = "SELECT categories.name, SUM(expenses.amount) as total 
        FROM expenses 
        JOIN categories ON expenses.category_id = categories.id 
        WHERE expenses.user_id = '$user_id' 
        GROUP BY categories.name 
        ORDER BY total ASC 
        LIMIT 1";
$result = mysqli_query($conn, $sql);
$lowest_category_data = mysqli_fetch_assoc($result);
$lowest_category = $lowest_category_data['name'] ?? 'None';
$lowest_category_amount = $lowest_category_data['total'] ?? 0;

// Fetch top month for expenses
$sql = "SELECT MONTHNAME(expense_date) as month, SUM(amount) as total 
        FROM expenses 
        WHERE user_id = '$user_id' 
        GROUP BY MONTH(expense_date) 
        ORDER BY total DESC 
        LIMIT 1";
$result = mysqli_query($conn, $sql);
$top_month_data = mysqli_fetch_assoc($result);
$top_month = $top_month_data['month'] ?? 'None';
$top_month_amount = $top_month_data['total'] ?? 0;

// Fetch lowest month for expenses
$sql = "SELECT MONTHNAME(expense_date) as month, SUM(amount) as total 
        FROM expenses 
        WHERE user_id = '$user_id' 
        GROUP BY MONTH(expense_date) 
        ORDER BY total ASC 
        LIMIT 1";
$result = mysqli_query($conn, $sql);
$lowest_month_data = mysqli_fetch_assoc($result);
$lowest_month = $lowest_month_data['month'] ?? 'None';
$lowest_month_amount = $lowest_month_data['total'] ?? 0;
?>

<div class="container mt-5">
    <h2>Dashboard</h2>
    <div class="row mt-4">
        <div class="col-md-4">
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title">Total Expenses</h5>
                    <p class="card-text fs-4">$<?php echo number_format($total_expenses, 2); ?></p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title">Total Categories</h5>
                    <p class="card-text fs-4"><?php echo $total_categories; ?></p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title">Top Expense Category</h5>
                    <p class="card-text fs-4"><?php echo $top_category; ?>: $<?php echo number_format($top_category_amount, 2); ?></p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mt-4">
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title">Lowest Expense Category</h5>
                    <p class="card-text fs-4"><?php echo $lowest_category; ?>: $<?php echo number_format($lowest_category_amount, 2); ?></p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mt-4">
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title">Top Expense Month</h5>
                    <p class="card-text fs-4"><?php echo $top_month; ?>: $<?php echo number_format($top_month_amount, 2); ?></p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mt-4">
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title">Lowest Expense Month</h5>
                    <p class="card-text fs-4"><?php echo $lowest_month; ?>: $<?php echo number_format($lowest_month_amount, 2); ?></p>
                </div>
            </div>
        </div>
    </div>
    <div class="mt-4">
        <a href="add_expense.php" class="btn btn-primary">Add Expense</a>
        <a href="search_expenses.php" class="btn btn-secondary">Search Expenses</a>
    </div>
</div>

<?php include 'includes/footer.php'; ?>
