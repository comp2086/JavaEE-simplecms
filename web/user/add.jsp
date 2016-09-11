<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>simplecms</title>
    </head>
    <body>
        <h1>Add a New User</h1>
        <form action="users" method="post">
            <input type="hidden" name="action" value="add">
            <label>First Name: </label>
            <input type="text" name="firstName" required><br /><br />
            <input type="submit" value="Add">
        </form>
    </body>
</html>
