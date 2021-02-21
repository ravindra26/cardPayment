package com.service1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Ravindra
 */
@Entity
@Table(name = "customer_payment_id")
public class CustomerPaysafeID {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "fk_customer")
    private Customer customer;
    @Column(name = "paysafe_id")
    private String paysafeCustomerID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPaysafeCustomerID() {
        return paysafeCustomerID;
    }

    public void setPaysafeCustomerID(String paysafeCustomerID) {
        this.paysafeCustomerID = paysafeCustomerID;
    }

}
