<?php
include 'includes/header.php';
include 'includes/db.php';
include 'includes/functions.php'; // Include the file with redirectWithMessage function

if (!isset($_SESSION['user_id'])) {
    header("Location: login.php");
    exit();
}

// Define $user_id
$user_id = $_SESSION['user_id'];

// Fetch category ID
$category_id = $_GET['id'] ?? null;

// Validate category ID
if (!$category_id) {
    redirectWithMessage('categories.php', 'Invalid category ID.');
    exit();
}

// Fetch category details (user-specific)
$sql = "SELECT * FROM categories WHERE id = $category_id AND user_id = '$user_id'";
$result = mysqli_query($conn, $sql);
$category = mysqli_fetch_assoc($result);

if (!$category) {
    redirectWithMessage('categories.php', 'Category not found.');
    exit();
}

// Handle category update
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $category_name = trim($_POST['category_name']);

    if (!empty($category_name)) {
        $sql = "UPDATE categories SET name = '$category_name' WHERE id = $category_id AND user_id = '$user_id'";
        if (mysqli_query($conn, $sql)) {
            redirectWithMessage('categories.php', 'Category updated successfully.');
        } else {
            echo "<div class='alert alert-danger mt-3'>Error: " . mysqli_error($conn) . "</div>";
        }
    } else {
        echo "<div class='alert alert-danger mt-3'>Category name cannot be empty.</div>";
    }
}
?>

<div class="container mt-5">
    <h2>Edit Category</h2>

    <form method="POST" action="edit_category.php?id=<?php echo $category_id; ?>" class="mt-4">
        <div class="mb-3">
            <label for="category_name" class="form-label">Category Name</label>
            <input type="text" class="form-control" id="category_name" name="category_name" value="<?php echo htmlspecialchars($category['name']); ?>" required>
        </div>
        <button type="submit" class="btn btn-primary">Update Category</button>
    </form>
</div>

<?php include 'includes/footer.php'; ?>
