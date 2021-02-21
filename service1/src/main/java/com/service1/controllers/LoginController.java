package com.service1.controllers;

import static com.service1.controllers.HomeController.API_PRIVATE_KEY;
import static com.service1.controllers.HomeController.API_PUBLIC_KEY;
import com.service1.model.Configs;
import com.service1.model.Customer;
import com.service1.pojo.LoginPacket;
import com.service1.service.UsersService;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ravindra
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    UsersService usersService;

    Logger logger = Logger.getLogger("loginLogger");

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String homePage(HttpServletRequest request, ModelMap model) {
        model.addAttribute("login", new LoginPacket());

        return "login";
    }

    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, Model model) {
        model.addAttribute("login", new LoginPacket());
        return "login";
    }

    @RequestMapping(value = "handleLogin", method = RequestMethod.POST)
    public String handleLogin(HttpServletRequest request, @ModelAttribute LoginPacket packet) {
        if (usersService.validateLogin(packet)) {
            logger.info("Logged in");
            logger.info("Login ke Page pr aaya");
            Configs cn = usersService.getConfig(API_PUBLIC_KEY);
            request.getSession().setAttribute("public-key", cn.getValue());
            cn = usersService.getConfig(API_PRIVATE_KEY);
            request.getSession().setAttribute("private-key", cn.getValue());
            request.getSession().setAttribute("logged_in", Boolean.toString(true));
            request.getSession().setAttribute("username", packet.getUsername());
            return "redirect:home.htm";
        }
        return "redirect:login.htm";
    }

    @RequestMapping(value = "doLogout", method = RequestMethod.GET)
    @ResponseBody
    public String handleLogout(HttpServletRequest request) {
        Customer customer = usersService.getCustomer((String) request.getSession().getAttribute("username"));
        customer.setIsLoggedIn(false);
        usersService.updateUser(customer);
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("private-key");
        request.getSession().removeAttribute("public-key");
        request.getSession().invalidate();

        return "logedOut";
    }

}
