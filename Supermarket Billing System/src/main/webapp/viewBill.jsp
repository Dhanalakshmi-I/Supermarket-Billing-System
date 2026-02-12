<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Bill</title>
</head>
<body>

<h2>View Bill Record</h2>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="viewRecord">

    Customer Name: <input type="text" name="customerName" required><br><br>
    Purchase Date: <input type="date" name="purchaseDate" required><br><br>

    <input type="submit" value="View Bill">

</form>

<br>
<a href="menu.html">Back to Menu</a>

</body>
</html>