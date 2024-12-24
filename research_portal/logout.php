<?php
session_start(); // Start the session if not already started

// Destroy all session data
session_unset();
session_destroy();

// Clear session cookies
if (ini_get("session.use_cookies")) {
    $params = session_get_cookie_params();
    setcookie(session_name(), '', time() - 42000, $params["path"], $params["domain"], $params["secure"], $params["httponly"]);
}

// Redirect to login page
header('Location: login.php');
exit;
?>
