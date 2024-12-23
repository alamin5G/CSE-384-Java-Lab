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

// Handle adding a new category
if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['add_category'])) {
    $category_name = trim($_POST['category_name']);
    if (!empty($category_name)) {
        $sql = "INSERT INTO categories (name, user_id) VALUES ('$category_name', '$user_id')";
        if (mysqli_query($conn, $sql)) {
            redirectWithMessage('categories.php', 'Category ' . $category_name . ' added successfully.');
        } else {
            echo "<div class='alert alert-danger mt-3'>Error: " . mysqli_error($conn) . "</div>";
        }
    } else {
        echo "<div class='alert alert-danger mt-3'>Category name cannot be empty.</div>";
    }
}

// Handle deleting a category
if (isset($_GET['delete_category'])) {
    $category_id = intval($_GET['delete_category']);
    $sql = "DELETE FROM categories WHERE id = $category_id AND user_id = '$user_id'";
    if (mysqli_query($conn, $sql)) {
        redirectWithMessage('categories.php', 'Category deleted successfully.');
    } else {
        echo "<div class='alert alert-danger mt-3'>Error: " . mysqli_error($conn) . "</div>";
    }
}

// Handle search functionality
$search_query = '';
$categories = [];
$search_result_message = '';

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['search_category'])) {
    $search_query = trim($_POST['search_query']);
    if (!empty($search_query)) {
        $sql = "SELECT * FROM categories WHERE user_id = '$user_id' AND name LIKE '%$search_query%' ORDER BY name ASC";
        $result = mysqli_query($conn, $sql);
        $categories = mysqli_fetch_all($result, MYSQLI_ASSOC);

        if (empty($categories)) {
            $search_result_message = '<div class="alert alert-danger mt-3">No categories found for "' . htmlspecialchars($search_query) . '".</div>';
        } else {
            $search_result_message = '<div class="alert alert-success mt-3">' . count($categories) . ' categories found for "' . htmlspecialchars($search_query) . '".</div>';
        }
    } else {
        $search_result_message = '<div class="alert alert-warning mt-3">Please enter a search term.</div>';
    }
} else {
    // Fetch all categories by default (user-specific)
    $sql = "SELECT * FROM categories WHERE user_id = '$user_id' ORDER BY name ASC";
    $result = mysqli_query($conn, $sql);
    $categories = mysqli_fetch_all($result, MYSQLI_ASSOC);
}
?>

<div class="container mt-5">
    <h2>Manage Categories</h2>

    <div class="row">
        <div class="col-md-4 offset-md-1">
            <!-- Add Category Form -->
            <form method="POST" action="categories.php" class="mt-4">
                <div class="mb-3">
                    <label for="category_name" class="form-label">Add New Category</label>
                    <input type="text" class="form-control" id="category_name" name="category_name"
                        placeholder="Category Name" required>
                </div>
                <button type="submit" name="add_category" class="btn btn-primary">Add Category</button>
            </form>
        </div>
        <div class="col-md-4 offset-md-1">
            <!-- Search Category Form -->
            <form method="POST" action="categories.php" class="mt-4">
                <div class="mb-3">
                    <label for="search_query" class="form-label">Search Categories</label>
                    <input type="text" class="form-control" id="search_query" name="search_query"
                        placeholder="Search by category name" value="<?php echo htmlspecialchars($search_query); ?>">
                </div>
                <button type="submit" name="search_category" class="btn btn-secondary">Search</button>
            </form>
        </div>
    </div>

    <!-- Display Search Result Message -->
    <?php echo $search_result_message; ?>

    <!-- Categories List -->
    <?php if (!empty($categories)): ?>
    <h3 class="mt-5">Existing Categories</h3>
    <table class="table table-bordered mt-3">
        <thead>
            <tr>
                <th>Category Name</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <?php foreach ($categories as $category): ?>
            <tr>
                <td><?php echo htmlspecialchars($category['name']); ?></td>
                <td>
                    <a href="edit_category.php?id=<?php echo $category['id']; ?>"
                        class="btn btn-warning btn-sm">Edit</a>
                    <a href="categories.php?delete_category=<?php echo $category['id']; ?>"
                        class="btn btn-danger btn-sm"
                        onclick="return confirm('Are you sure you want to delete this category?')">Delete</a>
                </td>
            </tr>
            <?php endforeach; ?>
        </tbody>
    </table>
    <?php else: ?>
    <p class="text-muted mt-4">No categories added yet.</p>
    <?php endif; ?>
</div>

<?php include 'includes/footer.php'; ?>
