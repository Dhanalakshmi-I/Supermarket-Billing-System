<%@ page import="java.util.List" %>
<%@ page import="com.wipro.market.bean.MarketBillBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>All Bills</title>
</head>
<body>

<h2>All Bill Records</h2>

<%
    List<MarketBillBean> list = (List<MarketBillBean>) request.getAttribute("billList");
    String message = (String) request.getAttribute("message");

    if (list != null && !list.isEmpty()) {
%>

<table border="1">
    <tr>
        <th>Bill ID</th>
        <th>Customer Name</th>
        <th>Item Name</th>
        <th>Purchase Date</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Total Amount</th>
        <th>Remarks</th>
    </tr>

<%
        for (MarketBillBean bill : list) {
%>
    <tr>
        <td><%= bill.getBillId() %></td>
        <td><%= bill.getCustomerName() %></td>
        <td><%= bill.getItemName() %></td>
        <td><%= bill.getPurchaseDate() %></td>
        <td><%= bill.getQuantity() %></td>
        <td><%= bill.getPrice() %></td>
        <td><%= bill.getTotalAmount() %></td>
        <td><%= bill.getRemarks() %></td>
    </tr>
<%
        }
%>

</table>

<%
    } else {
        if (message == null) message = "No records available!";
%>
    <h3><%= message %></h3>
<%
    }
%>

<br>
<a href="menu.html">Back to Menu</a>

</body>
</html>