package  com.lotus.uccp.auth.web;

import com.lotus.uccp.authentication.model.User;
import com.lotus.uccp.authentication.model.Users;
import com.lotus.uccp.auth.security.service.SecurityService;
import com.lotus.uccp.auth.security.service.UserService;
import com.lotus.uccp.auth.validator.UserValidator;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

//    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

//    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") Users userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPassword());

        return "redirect:/welcome";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout, HttpServletResponse response,HttpServletRequest request) throws IOException {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        
        
        
//       response.sendRedirect("/login");

       return "login";
    }

//    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public void welcome(Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
    	response.sendRedirect("/Connect360/index.html");
//        return "welcome";
    }
    
    @RequestMapping("/user")
    public Principal user(Principal user) {
      return user;
    }
}
