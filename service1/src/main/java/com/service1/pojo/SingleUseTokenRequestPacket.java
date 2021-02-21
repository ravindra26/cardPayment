
package com.service1.pojo;

import java.util.List;

/**
 *
 * @author Ravindra
 */
public class SingleUseTokenRequestPacket {
    private String merchantRefNum;
    private List<String> paymentTypes;

    public String getMerchantRefNum() {
        return merchantRefNum;
    }

    public void setMerchantRefNum(String merchantRefNum) {
        this.merchantRefNum = merchantRefNum;
    }

    public List<String> getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(List<String> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }
    
}
