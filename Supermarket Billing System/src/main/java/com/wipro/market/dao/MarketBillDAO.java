package com.wipro.market.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wipro.market.bean.MarketBillBean;
import com.wipro.market.util.DBUtil;

public class MarketBillDAO {
public String generateBillID(String customerName, Date purchaseDate) {
	       String billId = "";
        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement("SELECT MARKET_BILL_SEQ.NEXTVAL FROM DUAL");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int seq = rs.getInt(1);
                SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
                String datePart = f.format(purchaseDate);
                String namePart = customerName.substring(0, 2).toUpperCase();
                billId = datePart + namePart + seq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return billId;
    }

    public String createRecord(MarketBillBean bean) {
        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO MARKET_BILL_TB VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, bean.getBillId());
            ps.setString(2, bean.getCustomerName());
            ps.setString(3, bean.getItemName());
            ps.setDate(4, new java.sql.Date(bean.getPurchaseDate().getTime()));
            ps.setInt(5, bean.getQuantity());
            ps.setDouble(6, bean.getPrice());
            ps.setDouble(7, bean.getTotalAmount());
            ps.setString(8, bean.getRemarks());
            int rows = ps.executeUpdate();
            if (rows > 0)
                return bean.getBillId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "FAIL";
    }
    public MarketBillBean fetchRecord(String customerName, Date purchaseDate) {
        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM MARKET_BILL_TB WHERE CUSTOMERNAME=? AND PURCHASE_DATE=?");
            ps.setString(1, customerName);
            ps.setDate(2, new java.sql.Date(purchaseDate.getTime()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                MarketBillBean b = new MarketBillBean();
                b.setBillId(rs.getString(1));
                b.setCustomerName(rs.getString(2));
                b.setItemName(rs.getString(3));
                b.setPurchaseDate(rs.getDate(4));
                b.setQuantity(rs.getInt(5));
                b.setPrice(rs.getDouble(6));
                b.setTotalAmount(rs.getDouble(7));
                b.setRemarks(rs.getString(8));
                return b;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean recordExists(String customerName, Date purchaseDate) {
        return fetchRecord(customerName, purchaseDate) != null;
    }
    public List<MarketBillBean> fetchAllRecords() {
        List<MarketBillBean> list = new ArrayList<>();
        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM MARKET_BILL_TB");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MarketBillBean b = new MarketBillBean();
                b.setBillId(rs.getString(1));
                b.setCustomerName(rs.getString(2));
                b.setItemName(rs.getString(3));
                b.setPurchaseDate(rs.getDate(4));
                b.setQuantity(rs.getInt(5));
                b.setPrice(rs.getDouble(6));
                b.setTotalAmount(rs.getDouble(7));
                b.setRemarks(rs.getString(8));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}