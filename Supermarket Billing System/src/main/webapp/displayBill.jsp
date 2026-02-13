<%@ page import="com.wipro.market.bean.MarketBillBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Bill Details</title>
</head>
<body>
<h2>Bill Details</h2>
<%
    MarketBillBean bill = (MarketBillBean) request.getAttribute("bill");
    String message = (String) request.getAttribute("message");
    if (bill != null) {
%>
        Bill ID: <%= bill.getBillId() %><br><br>
        Customer Name: <%= bill.getCustomerName() %><br><br>
        Item Name: <%= bill.getItemName() %><br><br>
        Purchase Date: <%= bill.getPurchaseDate() %><br><br>
        Quantity: <%= bill.getQuantity() %><br><br>
        Price: <%= bill.getPrice() %><br><br>
        Total Amount: <%= bill.getTotalAmount() %><br><br>
        Remarks: <%= bill.getRemarks() %><br><br>
<%
    } else {
        if (message == null) message = "No matching records exists! Please try again!";
%>
        <h3><%= message %></h3>
<%
    }
%>
<br>
<a href="menu.html">Back to Menu</a>
</body>
</html>