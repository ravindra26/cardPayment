package com.service1.controllers;

import com.google.gson.Gson;
import com.service1.model.Configs;
import com.service1.service.UsersService;
import com.service1.utils.UserAccessUtil;
import java.io.IOException;
import java.net.ProtocolException;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ravindra
 */
@Controller
public class HomeController {

    public static String API_PUBLIC_KEY = "api-public-key";
    public static String API_PRIVATE_KEY = "api-private-key";

    @Autowired
    UsersService service;
    @Autowired
    UserAccessUtil accessUtil;

    Logger logger = Logger.getLogger("myLogger");

    @RequestMapping(value = "home.htm", method = RequestMethod.GET)
    public String showHomePage(HttpServletRequest request, Model model) {
        if (accessUtil.checkLoginStatus(request)) {
            Configs cn = service.getConfig(API_PUBLIC_KEY);
            model.addAttribute("apiKey", cn.getValue());
            return "home";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "getSingleUseToken", method = RequestMethod.GET)
    @ResponseBody
    public String getSingleUseToken(HttpServletRequest request) throws IOException, IOException {
        return service.getSingleUseToken(request);
    }

    @RequestMapping(value = "getCustomerData", method = RequestMethod.GET)
    @ResponseBody
    public String getCustomerData(HttpServletRequest request) {
        return new Gson().toJson(service.getCustomerData(request));
    }

    @RequestMapping(value = "makePaymentRequest", method = RequestMethod.GET)
    @ResponseBody
    public String makePayment(HttpServletRequest request, @RequestParam(value = "paymentHandleToken") String token, @RequestParam(value = "description") String description,
            @RequestParam(value = "amount") int amount, @RequestParam(value = "currCode") String curr) throws ProtocolException, IOException {
        return new Gson().toJson(service.makePayment(request, token, description, amount, curr));
    }
}
