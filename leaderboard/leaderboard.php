<?php
include 'header.php';
require 'db.php';

// Fetch sorting option (default: score descending)
$sort_by = isset($_GET['sort_by']) ? $_GET['sort_by'] : 'total_score';
$sort_order = isset($_GET['order']) && strtolower($_GET['order']) === 'asc' ? 'ASC' : 'DESC';

// Build the query
$query = "SELECT u.username, u.name, u.age, SUM(s.score) AS total_score
          FROM users u
          JOIN scores s ON u.id = s.user_id
          GROUP BY u.id";

// Add sorting
if ($sort_by === 'age') {
    $query .= " ORDER BY u.age $sort_order";
} else {
    $query .= " ORDER BY total_score $sort_order";
}

// Fetch leaderboard data
$stmt = mysqli_prepare($conn, $query);
mysqli_stmt_execute($stmt);
$result = mysqli_stmt_get_result($stmt);
$leaderboard = mysqli_fetch_all($result, MYSQLI_ASSOC);

// Handle search functionality
$search_results = [];
if (isset($_GET['search'])) {
    $search_query = "%" . $_GET['search'] . "%";
    $search_stmt = mysqli_prepare($conn, "SELECT u.username, u.name, u.age, SUM(s.score) AS total_score
                                          FROM users u
                                          JOIN scores s ON u.id = s.user_id
                                          WHERE u.username LIKE ? OR u.name LIKE ?
                                          GROUP BY u.id
                                          ORDER BY total_score DESC");
    mysqli_stmt_bind_param($search_stmt, "ss", $search_query, $search_query);
    mysqli_stmt_execute($search_stmt);
    $search_result = mysqli_stmt_get_result($search_stmt);
    $search_results = mysqli_fetch_all($search_result, MYSQLI_ASSOC);
}
?>
<h1 class="text-center">Leaderboard</h1>

<div class="row mb-3">
    <div class="col-md-6">
        <form method="get" class="d-flex">
            <input type="text" name="search" class="form-control" placeholder="Search by username or full name" value="<?= htmlspecialchars($_GET['search'] ?? '') ?>">
            <button type="submit" class="btn btn-primary ms-2">Search</button>
        </form>
    </div>
    <div class="col-md-6 text-end">
        <a href="?sort_by=total_score&order=desc" class="btn btn-sm btn-outline-primary me-1">Sort by Scores ↓</a>
        <a href="?sort_by=total_score&order=asc" class="btn btn-sm btn-outline-success me-1">Sort by Score ↑</a>
        <a href="?sort_by=age&order=asc" class="btn btn-sm btn-outline-info me-1">Sort by Age ↑</a>
        <a href="?sort_by=age&order=desc" class="btn btn-sm btn-outline-warning">Sort by Age ↓</a>
    </div>
</div>

<table class="table table-bordered">
    <thead>
        <tr>
            <th>Rank</th>
            <th>Username</th>
            <th>Full Name</th>
            <th>Age</th>
            <th>Total Score</th>
        </tr>
    </thead>
    <tbody>
        <?php if (!empty($_GET['search']) && !empty($search_results)): ?>
            <?php foreach ($search_results as $rank => $row): ?>
                <tr>
                    <td><?= $rank + 1 ?></td>
                    <td><?= htmlspecialchars($row['username']) ?></td>
                    <td><?= htmlspecialchars($row['name']) ?></td>
                    <td><?= $row['age'] !== null ? $row['age'] : '-' ?></td>
                    <td><?= $row['total_score'] ?></td>
                </tr>
            <?php endforeach; ?>
        <?php elseif (!empty($_GET['search'])): ?>
            <tr>
                <td colspan="5" class="text-center text-danger">No results found for "<?= htmlspecialchars($_GET['search']) ?>"</td>
            </tr>
        <?php else: ?>
            <?php foreach ($leaderboard as $rank => $row): ?>
                <tr>
                    <td><?= $rank + 1 ?></td>
                    <td><?= htmlspecialchars($row['username']) ?></td>
                    <td><?= htmlspecialchars($row['name']) ?></td>
                    <td><?= $row['age'] !== null ? $row['age'] : '-' ?></td>
                    <td><?= $row['total_score'] ?></td>
                </tr>
            <?php endforeach; ?>
        <?php endif; ?>
    </tbody>
</table>
<?php include 'footer.php'; ?>
