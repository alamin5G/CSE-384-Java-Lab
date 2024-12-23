<?php
function redirectWithMessage($url, $message) {
    session_start();
    $_SESSION['successMessage'] = $message;
    header("Location: $url");
    exit();
}
?>
