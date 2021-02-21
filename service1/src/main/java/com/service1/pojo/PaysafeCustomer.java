package com.service1.pojo;

/**
 *
 * @author Ravindra
 */
public class PaysafeCustomer {

    private String id;
    private String merchantCustomerId;
    private String locale;
    private String firstName;
    private String middleName;
    private String lastName;
    private DateOfBirth dateOfBirth;
    private String email;
    private String ip;
    private String phone;
    private String nationality;
    private String status;

    public String getMerchantCustomerId() {
        return merchantCustomerId;
    }

    public void setMerchantCustomerId(String merchantCustomerId) {
        this.merchantCustomerId = merchantCustomerId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
