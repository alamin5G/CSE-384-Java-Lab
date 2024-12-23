<?php
include 'header.php';
require 'db.php';

if (!isset($_SESSION['user_id'])) {
    header("Location: login.php");
    exit();
}

$user_id = $_SESSION['user_id'];
$username = $_SESSION['username'];

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Calculate the score based on submitted answers
    $score = 0;
    foreach ($_POST as $question_id => $selected_option) {
        if ($question_id !== 'submit') {
            $stmt = mysqli_prepare($conn, "SELECT correct_option FROM questions WHERE id = ?");
            mysqli_stmt_bind_param($stmt, "i", $question_id);
            mysqli_stmt_execute($stmt);
            $result = mysqli_stmt_get_result($stmt);
            $question = mysqli_fetch_assoc($result);

            if ($question && (int)$question['correct_option'] === (int)$selected_option) {
                $score++;
            }
        }
    }

    // Insert current game score into the scores table
    $stmt = mysqli_prepare($conn, "INSERT INTO scores (user_id, score) VALUES (?, ?)");
    mysqli_stmt_bind_param($stmt, "ii", $user_id, $score);
    mysqli_stmt_execute($stmt);

    // Calculate total score for the user
    $stmt = mysqli_prepare($conn, "SELECT SUM(score) AS total_score FROM scores WHERE user_id = ?");
    mysqli_stmt_bind_param($stmt, "i", $user_id);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);
    $total_score = mysqli_fetch_assoc($result)['total_score'];

    // Provide feedback based on the session score
    if ($score >= 8) {
        $feedback = "Excellent";
    } elseif ($score >= 5) {
        $feedback = "Pass";
    } else {
        $feedback = "Failed";
    }

    // Display results
    echo "<div class='alert alert-info text-center'>Hi <strong>" . htmlspecialchars($username) . "</strong>, you scored <strong>$score/10</strong> in this session. Feedback: <strong>$feedback</strong></div>";
    echo "<div class='alert alert-success text-center'>Your total score: <strong>$total_score</strong></div>";
    echo "<div class='text-center'><a href='leaderboard.php' class='btn btn-primary'>View Leaderboard</a></div>";
} else {
    // Fetch 10 random questions
    $stmt = mysqli_prepare($conn, "SELECT * FROM questions ORDER BY RAND() LIMIT 10");
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);
    $questions = mysqli_fetch_all($result, MYSQLI_ASSOC);
    ?>
    <h1 class="text-center">Play Quiz</h1>
    <form method="post">
        <?php foreach ($questions as $index => $question): ?>
            <div class="mb-3">
                <h5>Q<?= $index + 1 ?>: <?= htmlspecialchars($question['question']) ?></h5>
                <?php for ($i = 1; $i <= 4; $i++): ?>
                    <div class="form-check">
                        <label class="form-check-label" for="<?= $question['id'] ?>"></label>
                        <input class="form-check-input" id="<?= $question['id'] ?>" type="radio" name="<?= $question['id'] ?>" 
                               value="<?= $i ?>" required>
                        <label class="form-check-label">
                            <?= htmlspecialchars($question["option$i"]) ?>
                        </label>
                    </div>
                <?php endfor; ?>
            </div>
        <?php endforeach; ?>
        <button type="submit" name="submit" class="btn btn-primary">Submit</button>
    </form>
    <?php
}
include 'footer.php';
?>
