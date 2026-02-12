package com.wipro.market.service;

import java.util.Date;
import java.util.List;

import com.wipro.market.bean.MarketBillBean;
import com.wipro.market.dao.MarketBillDAO;
import com.wipro.market.util.InvalidInputException;

public class Administrator {

    public String addRecord(MarketBillBean bean) {

        try {
            if (bean == null || bean.getCustomerName() == null
                    || bean.getItemName() == null
                    || bean.getPurchaseDate() == null) {
                throw new InvalidInputException();
            }

            if (bean.getCustomerName().length() < 2)
                return "INVALID CUSTOMER NAME";

            if (bean.getItemName().length() < 2)
                return "INVALID ITEM NAME";

            if (bean.getQuantity() < 1 || bean.getPrice() <= 0)
                return "INVALID BILL DETAILS";

            MarketBillDAO dao = new MarketBillDAO();

            if (dao.recordExists(bean.getCustomerName(), bean.getPurchaseDate()))
                return "ALREADY EXISTS";

            String billId = dao.generateBillID(
                bean.getCustomerName(), bean.getPurchaseDate());

            bean.setBillId(billId);
            bean.setTotalAmount(bean.getQuantity() * bean.getPrice());

            return dao.createRecord(bean);

        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        }
    }

    public MarketBillBean viewRecord(String customerName, Date purchaseDate) {
        return new MarketBillDAO().fetchRecord(customerName, purchaseDate);
    }

    public List<MarketBillBean> viewAllRecords() {
        return new MarketBillDAO().fetchAllRecords();
    }
}