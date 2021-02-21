package com.service1.pojo;

/**
 *
 * @author Ravindra
 */
public class PaymentDTO {
    private String merchantRefNum;
    private int amount;
    private String currencyCode;
    private boolean dupCheck;
    private boolean settleWithAuth;
    private String paymentHandleToken;
    private String customerIp;
    private String description;

    public String getMerchantRefNum() {
        return merchantRefNum;
    }

    public void setMerchantRefNum(String merchantRefNum) {
        this.merchantRefNum = merchantRefNum;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public boolean isDupCheck() {
        return dupCheck;
    }

    public void setDupCheck(boolean dupCheck) {
        this.dupCheck = dupCheck;
    }

    public boolean isSettleWithAuth() {
        return settleWithAuth;
    }

    public void setSettleWithAuth(boolean settleWithAuth) {
        this.settleWithAuth = settleWithAuth;
    }

    public String getPaymentHandleToken() {
        return paymentHandleToken;
    }

    public void setPaymentHandleToken(String paymentHandleToken) {
        this.paymentHandleToken = paymentHandleToken;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
