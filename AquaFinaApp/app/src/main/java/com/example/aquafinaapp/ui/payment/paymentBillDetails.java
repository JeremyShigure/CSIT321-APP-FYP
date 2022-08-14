package com.example.aquafinaapp.ui.payment;

public class paymentBillDetails {

    String invoiceID;
    String accountID;
    String cID;
    String startDate;
    String endDate;
    String nettCost;
    String consTax;
    String borneFee;
    String subTotal;
    String GST;
    String totalCost;
    String dateIssued;
    String dueDate;
    String Status;

    public paymentBillDetails(String invoiceID, String accountID, String cID, String startDate, String endDate, String nettCost, String consTax, String borneFee, String subTotal, String GST, String totalCost, String dateIssued, String dueDate, String status) {
        this.invoiceID = invoiceID;
        this.accountID = accountID;
        this.cID = cID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nettCost = nettCost;
        this.consTax = consTax;
        this.borneFee = borneFee;
        this.subTotal = subTotal;
        this.GST = GST;
        this.totalCost = totalCost;
        this.dateIssued = dateIssued;
        this.dueDate = dueDate;
        Status = status;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNettCost() {
        return nettCost;
    }

    public void setNettCost(String nettCost) {
        this.nettCost = nettCost;
    }

    public String getConsTax() {
        return consTax;
    }

    public void setConsTax(String consTax) {
        this.consTax = consTax;
    }

    public String getBorneFee() {
        return borneFee;
    }

    public void setBorneFee(String borneFee) {
        this.borneFee = borneFee;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
