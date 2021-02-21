package com.service1.service;

import com.google.gson.Gson;
import com.service1.dao.ConfigsDAOImpl;
import com.service1.dao.CustomerPaySafeIDDAOImpl;
import com.service1.dao.UsersDAOImpl;
import com.service1.model.Configs;
import com.service1.pojo.CustomerDTO;
import com.service1.model.Customer;
import com.service1.model.CustomerPaysafeID;
import com.service1.pojo.DateOfBirth;
import com.service1.pojo.LoginPacket;
import com.service1.pojo.PaymentDTO;
import com.service1.pojo.PaysafeCustomer;
import com.service1.pojo.SingleUseTokenRequestPacket;
import com.service1.utils.RestCallUtils;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ravindra
 */
@Service
public class UsersService {
    
    public static String DEFAULT_REGISTER_CUSTOMER_URL = "https://api.test.paysafe.com/paymenthub/v1/customers";
    public static String DEFAULT_GET_SINGLE_USE_TOKEN = "https://api.test.paysafe.com/paymenthub/v1/customers/customer_id/singleusecustomertokens";
    public static String DEFAULT_PAYMENT_URL = "https://api.test.paysafe.com/paymenthub/v1/payments";
    
    @Autowired
    UsersDAOImpl usersDao;
    @Autowired
    CustomerPaySafeIDDAOImpl cpIDDao;
    @Autowired
    ConfigsDAOImpl configsDAO;
    
    Logger logger = Logger.getLogger("myLogger");
    RestCallUtils util = new RestCallUtils();
    
    @Transactional
    public int addUser(CustomerDTO customer) {
        Customer cus = new Customer();
        cus.setFirstName(customer.getFirstName());
        cus.setMiddleName(customer.getMiddleName());
        cus.setLastName(customer.getLastName());
        cus.setNationality(customer.getNationality());
        cus.setEmail(customer.getEmail());
        cus.setPhone(customer.getPhone());
        cus.setUsername(customer.getUsername());
        cus.setPassword(customer.getPassword());
        cus.setDay(customer.getDay());
        cus.setMonth(customer.getMonth());
        cus.setYear(customer.getYear());
        cus.setState(customer.getState());
        cus.setNickname(customer.getNickname());
        cus.setStreet(customer.getStreet());
        cus.setStreet2(customer.getStreet2());
        cus.setCountry(customer.getCountry());
        cus.setCity(customer.getCity());
        cus.setZip(customer.getZip());
        int addedPk = usersDao.addUser(cus);
        logger.info("Added user with pk " + Integer.toString(addedPk));
        return addedPk;
    }
    
    @Transactional
    public Customer getCustomer(String username) {
        return usersDao.getUserByUsername(username);
    }
    
    @Transactional
    public boolean validateLogin(LoginPacket packet) {
        Customer customer = usersDao.getUserByUsername(packet.getUsername());
        if (customer == null) {
            return false;
        }
        if (customer.getPassword().equals(packet.getPassword())) {
            customer.setIsLoggedIn(true);
            updateUser(customer);
            return true;
        }
        return false;
    }
    
    @Transactional
    public Customer getUser(String username) {
        return usersDao.getUserByUsername(username);
    }
    
    @Transactional
    public void craetePaySafeUser(CustomerDTO customer, int pk, HttpServletRequest request) throws UnknownHostException, MalformedURLException, IOException {
        String apiKey = (String) request.getSession().getAttribute("private-key");
        PaysafeCustomer obj = new PaysafeCustomer();
        obj.setEmail(customer.getEmail());
        obj.setFirstName(customer.getFirstName());
        obj.setLastName(customer.getLastName());
        obj.setMiddleName(obj.getMiddleName());
        obj.setMerchantCustomerId("customer_" + Integer.toString(pk));
        obj.setEmail(customer.getEmail());
        InetAddress addr = InetAddress.getLocalHost();
        obj.setIp(addr.getHostAddress());
        obj.setNationality(customer.getNationality());
        obj.setPhone(customer.getPhone());
        DateOfBirth dob = new DateOfBirth();
        dob.setDay(1);
        dob.setMonth(1);
        dob.setYear(1990);
        obj.setDateOfBirth(dob);
        logger.info(new Gson().toJson(obj));
        
        PaysafeCustomer obj1 = new PaysafeCustomer();
        
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Simulator", "\"External\"");
        headers.put("Authorization", "Basic " + apiKey);
        
        String response = util.makePOSTJSONRequest(DEFAULT_REGISTER_CUSTOMER_URL, new Gson().toJson(obj), headers);
        logger.info(response);
        obj1 = new Gson().fromJson(response.toString(), PaysafeCustomer.class);
        cpIDDao.addID(usersDao.getUserByUsername(customer.getUsername()), obj1.getId());
    }
    
    @Transactional
    public void updateUser(Customer cus) {
        usersDao.updateUser(cus);
    }
    
    @Transactional
    public Customer getCustomerData(HttpServletRequest request) {
        return usersDao.getUserByUsername((String) request.getSession().getAttribute("username"));
    }
    
    @Transactional
    public String getSingleUseToken(HttpServletRequest request) throws ProtocolException, IOException {
        String apiKey = (String) request.getSession().getAttribute("private-key");
        Customer customer = usersDao.getUserByUsername((String) request.getSession().getAttribute("username"));
        CustomerPaysafeID idObj = cpIDDao.getPaymentIDByCustomer(customer);
        String url = DEFAULT_GET_SINGLE_USE_TOKEN.replace("customer_id", idObj.getPaysafeCustomerID());
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Basic " + apiKey);
        headers.put("Content-Type", "application/json");
        headers.put("Simulator", "\"External\"");
        SingleUseTokenRequestPacket obj = new SingleUseTokenRequestPacket();
        List<String> paymentMethods = new ArrayList<>();
        paymentMethods.add("CARD");
        obj.setMerchantRefNum("customer_" + customer.getId());
        obj.setPaymentTypes(paymentMethods);
        String response = util.makePOSTJSONRequest(url, new Gson().toJson(obj), headers);
        logger.info("Response:" + response);
        return response;
    }
    
    @Transactional
    public Configs getConfig(String key) {
        return configsDAO.getConfigByKey(key);
    }
    
    @Transactional
    public String makePayment(HttpServletRequest request, String paymentHandleToken, String description, int amount, String curr) throws UnknownHostException, ProtocolException, IOException {
        Customer customer = usersDao.getUserByUsername((String) request.getSession().getAttribute("username"));
        String apiKey = (String) request.getSession().getAttribute("private-key");
        PaymentDTO obj = new PaymentDTO();
        obj.setAmount(amount);
        obj.setCurrencyCode(curr);
        InetAddress addr = InetAddress.getLocalHost();
        obj.setCustomerIp(addr.getHostAddress());
        obj.setDescription(description);
        obj.setDupCheck(true);
        obj.setSettleWithAuth(false);
        System.out.println(paymentHandleToken);
        obj.setPaymentHandleToken(paymentHandleToken);
        UUID uuid = UUID.randomUUID();
        obj.setMerchantRefNum(uuid.toString());
        
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Basic " + apiKey);
        headers.put("Content-Type", "application/json");
        headers.put("Simulator", "\"External\"");
        String response = util.makePOSTJSONRequest(DEFAULT_PAYMENT_URL, new Gson().toJson(obj), headers);
        logger.info("Response:" + response);
        return response;
    }
}
