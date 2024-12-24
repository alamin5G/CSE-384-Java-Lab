<?php
require_once 'includes/config.php';
include 'includes/header.php';

// Restrict access to logged-in users
if (!isset($_SESSION['role'])) {
    header('Location: login.php');
    exit;
}

// Check user role
$role = $_SESSION['role'];

// Data for Student
if ($role === 'student') {
    $student_id = $_SESSION['id'];
    $total_theses_by_student = $connection->query("
        SELECT COUNT(*) AS total 
        FROM theses 
        WHERE student_id = $student_id
    ")->fetch_assoc()['total'];

    $recent_student_theses = $connection->query("
        SELECT title, summary, submitted_at 
        FROM theses 
        WHERE student_id = $student_id
        ORDER BY submitted_at DESC
        LIMIT 5
    ");
}

// Data for Faculty
if ($role === 'faculty') {
    // Total Students
    $total_students = $connection->query("
        SELECT COUNT(*) AS total 
        FROM users 
        WHERE role = 'student'
    ")->fetch_assoc()['total'];

    // Total Theses
    $total_theses = $connection->query("
        SELECT COUNT(*) AS total 
        FROM theses
    ")->fetch_assoc()['total'];

    // Most Active Student
    $most_active_student = $connection->query("
        SELECT u.first_name, u.last_name, COUNT(t.id) AS thesis_count
        FROM users u
        JOIN theses t ON u.id = t.student_id
        WHERE u.role = 'student'
        GROUP BY u.id
        ORDER BY thesis_count DESC
        LIMIT 1
    ")->fetch_assoc();

    // Recent Theses
    $recent_theses = $connection->query("
        SELECT t.title, t.summary, t.submitted_at, u.first_name, u.last_name
        FROM theses t
        JOIN users u ON t.student_id = u.id
        ORDER BY t.submitted_at DESC
        LIMIT 5
    ");

    // Recent Students
    $recent_students = $connection->query("
        SELECT first_name, last_name, email, registered_at 
        FROM users 
        WHERE role = 'student' 
        ORDER BY registered_at DESC 
        LIMIT 5
    ");
}
?>

<div class="container">
    <h2><?php echo ucfirst($role); ?> Dashboard</h2>

    <?php if ($role === 'student'): ?>
        <!-- Student Dashboard -->
        <div class="row mt-4">
            <div class="col-md-4">
                <div class="card text-white bg-primary mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Your Thesis Submissions</h5>
                        <p class="card-text"><?php echo $total_theses_by_student; ?></p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Quick Access Section -->
        <div class="row mt-4">
            <div class="col-md-4">
                <a href="submit_thesis.php" class="btn btn-success btn-block">Submit Thesis</a>
            </div>
            <div class="col-md-4">
                <a href="thesis_list.php" class="btn btn-primary btn-block">View All Theses</a>
            </div>
            <div class="col-md-4">
                <a href="profile.php" class="btn btn-warning btn-block">View Profile</a>
            </div>
        </div>

        <!-- Recent Submissions -->
        <h3 class="mt-5">Your Recent Submissions</h3>
        <?php if ($recent_student_theses && $recent_student_theses->num_rows > 0): ?>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Summary</th>
                        <th>Submitted At</th>
                    </tr>
                </thead>
                <tbody>
                    <?php while ($row = $recent_student_theses->fetch_assoc()): ?>
                        <tr>
                            <td><?php echo htmlspecialchars($row['title']); ?></td>
                            <td><?php echo htmlspecialchars(substr($row['summary'], 0, 100)) . '...'; ?></td>
                            <td><?php echo htmlspecialchars($row['submitted_at']); ?></td>
                        </tr>
                    <?php endwhile; ?>
                </tbody>
            </table>
        <?php else: ?>
            <p>No recent thesis submissions.</p>
        <?php endif; ?>
    <?php endif; ?>

    <?php if ($role === 'faculty'): ?>
        <!-- Faculty Dashboard -->
        <div class="row mt-4">
            <div class="col-md-4">
                <div class="card text-white bg-primary mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Total Students</h5>
                        <p class="card-text"><?php echo $total_students; ?></p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-white bg-success mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Total Theses</h5>
                        <p class="card-text"><?php echo $total_theses; ?></p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-white bg-info mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Most Active Student</h5>
                        <p class="card-text">
                            <?php if ($most_active_student): ?>
                                <?php echo htmlspecialchars($most_active_student['first_name'] . ' ' . $most_active_student['last_name']); ?>
                                (<?php echo $most_active_student['thesis_count']; ?> submissions)
                            <?php else: ?>
                                No submissions yet.
                            <?php endif; ?>
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Quick Access Links -->
        <div class="row mt-4">
            <div class="col-md-4">
                <a href="students.php" class="btn btn-primary btn-block">View All Students</a>
            </div>
            <div class="col-md-4">
                <a href="all_theses.php" class="btn btn-success btn-block">View All Theses</a>
            </div>
            <div class="col-md-4">
                <a href="add_student.php" class="btn btn-warning btn-block">Add New Student</a>
            </div>
        </div>

        <!-- Recent Thesis Submissions -->
        <h3 class="mt-5">Recent Thesis Submissions</h3>
        <?php if ($recent_theses && $recent_theses->num_rows > 0): ?>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Summary</th>
                        <th>Submitted By</th>
                        <th>Submitted At</th>
                    </tr>
                </thead>
                <tbody>
                    <?php while ($row = $recent_theses->fetch_assoc()): ?>
                        <tr>
                            <td><?php echo htmlspecialchars($row['title']); ?></td>
                            <td><?php echo htmlspecialchars(substr($row['summary'], 0, 100)) . '...'; ?></td>
                            <td><?php echo htmlspecialchars($row['first_name'] . ' ' . $row['last_name']); ?></td>
                            <td><?php echo htmlspecialchars($row['submitted_at']); ?></td>
                        </tr>
                    <?php endwhile; ?>
                </tbody>
            </table>
        <?php else: ?>
            <p>No recent thesis submissions.</p>
        <?php endif; ?>

        <!-- Recent Student Registrations -->
        <h3 class="mt-5">Recent Student Registrations</h3>
        <?php if ($recent_students && $recent_students->num_rows > 0): ?>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Registered At</th>
                    </tr>
                </thead>
                <tbody>
                    <?php while ($row = $recent_students->fetch_assoc()): ?>
                        <tr>
                            <td><?php echo htmlspecialchars($row['first_name'] . ' ' . $row['last_name']); ?></td>
                            <td><?php echo htmlspecialchars($row['email']); ?></td>
                            <td><?php echo htmlspecialchars($row['registered_at']); ?></td>
                        </tr>
                    <?php endwhile; ?>
                </tbody>
            </table>
        <?php else: ?>
            <p>No recent student registrations.</p>
        <?php endif; ?>
    <?php endif; ?>
</div>

<?php include 'includes/footer.php'; ?>
