<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Bill</title>
</head>
<body>

<h2>Add Bill Record</h2>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="newRecord">

    Customer Name: <input type="text" name="customerName" required><br><br>
    Item Name: <input type="text" name="itemName" required><br><br>
    Purchase Date: <input type="date" name="purchaseDate" required><br><br>
    Quantity: <input type="number" name="quantity" required><br><br>
    Price: <input type="number" step="0.01" name="price" required><br><br>
    Remarks: <input type="text" name="remarks"><br><br>

    <input type="submit" value="Add Record">

</form>

<br>
<a href="menu.html">Back to Menu</a>

</body>
</html>