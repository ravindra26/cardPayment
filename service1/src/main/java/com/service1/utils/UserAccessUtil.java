package com.service1.utils;

import com.service1.model.Customer;
import com.service1.service.UsersService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ravindra
 */
@Component
public class UserAccessUtil {

    @Autowired
    UsersService service;

    public boolean checkLoginStatus(HttpServletRequest request) {
        if ((String) request.getSession().getAttribute("username") != null) {
            Customer cus = service.getCustomer((String) request.getSession().getAttribute("username"));
            return (cus != null && cus.isIsLoggedIn());
        } else {
            return false;
        }
    }
}
