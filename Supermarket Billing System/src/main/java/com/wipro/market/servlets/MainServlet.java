package com.wipro.market.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.wipro.market.bean.MarketBillBean;
import com.wipro.market.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    public String addRecord(HttpServletRequest req) {
        try {
            MarketBillBean bean = new MarketBillBean();
            bean.setCustomerName(req.getParameter("customerName"));
            bean.setItemName(req.getParameter("itemName"));
            bean.setPurchaseDate(new SimpleDateFormat("yyyy-MM-dd")
                    .parse(req.getParameter("purchaseDate")));
            bean.setQuantity(Integer.parseInt(req.getParameter("quantity")));
            bean.setPrice(Double.parseDouble(req.getParameter("price")));
            bean.setRemarks(req.getParameter("remarks"));
            return new Administrator().addRecord(bean);
        } catch (Exception e) {
            return "FAIL";
        }
    }

    public MarketBillBean viewRecord(HttpServletRequest req) {
        try {
            return new Administrator().viewRecord(
                req.getParameter("customerName"),
                new SimpleDateFormat("yyyy-MM-dd")
                    .parse(req.getParameter("purchaseDate")));
        } catch (Exception e) {
            return null;
        }
    }

    public List<MarketBillBean> viewAllRecords(HttpServletRequest req) {
        return new Administrator().viewAllRecords();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String op = req.getParameter("operation");

        if ("newRecord".equals(op)) {
            String result = addRecord(req);
            if ("FAIL".equals(result) || result.contains("INVALID")
                    || "ALREADY EXISTS".equals(result))
                resp.sendRedirect("error.html");
            else
                resp.sendRedirect("success.html");
        }

        else if ("viewRecord".equals(op)) {
            MarketBillBean b = viewRecord(req);
            if (b == null)
                req.setAttribute("message",
                    "No matching records exists! Please try again!");
            else
                req.setAttribute("bill", b);
            req.getRequestDispatcher("displayBill.jsp")
               .forward(req, resp);
        }

        else if ("viewAllRecords".equals(op)) {
            List<MarketBillBean> list = viewAllRecords(req);
            if (list.isEmpty())
                req.setAttribute("message", "No records available!");
            else
                req.setAttribute("billList", list);
            req.getRequestDispatcher("displayAllBills.jsp")
               .forward(req, resp);
        }
    }
}