package com.service1.controllers;

import com.google.gson.Gson;
import com.service1.pojo.CustomerDTO;
import com.service1.service.UsersService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Ravindra
 */
@Controller
public class CreateCustomerController {

    @Autowired
    UsersService usersService;
    
    Logger logger = Logger.getLogger("myLogger");

    @RequestMapping(value = "createUser.htm", method = RequestMethod.GET)
    public String showCreateCustomer(HttpServletRequest request, Model model) {
        model.addAttribute("customer", new CustomerDTO());
        return "createCustomer";
    }

    @RequestMapping(value = "submitForm", method = RequestMethod.POST)
    public String submitForm(HttpServletRequest request, @ModelAttribute("customer") CustomerDTO customer) throws MalformedURLException, IOException {
        logger.info(new Gson().toJson(customer));
        int pk = usersService.addUser(customer);
        usersService.craetePaySafeUser(customer, pk,request);
        return "redirect:login.htm";

    }
}
