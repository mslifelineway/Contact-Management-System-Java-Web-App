package com.oyedost.contactapp.controller;

import com.oyedost.contactapp.commands.LoginCommand;
import com.oyedost.contactapp.commands.RegisterCommand;
import com.oyedost.contactapp.domain.User;
import com.oyedost.contactapp.exceptions.UserBlockedException;
import com.oyedost.contactapp.services.UserServices;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author janeman
 */
@Controller
public class UserController {

    @Autowired
    private UserServices us;

    @RequestMapping(value = {"/", "/index"})
    public String index(Model m) {
        m.addAttribute("login_command", new LoginCommand());
        return "index"; //--- jsp page--> /WEB-INF/view/index/.jsp
    }

    /**
     * when user click on submit button in login form then this method will call
     * by the front controller that is dispatcherController
     *
     * @param lcmd
     * @param m
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(@ModelAttribute("login_command") LoginCommand lcmd, Model m, HttpSession session) {
        try {
            User u = us.loginUser(lcmd.getLoginName(), lcmd.getLoginPassword());

            if (u == null) {
                //----- FAILED
                //-- Add error to the modal and send back to login-form page
                m.addAttribute("err", "Login Failed! Enter valid credintials.");
                return "index"; //--- jsp page--> /WEB-INF/view/index/.jsp
            } else //------ SUCCESS
            //-- now check user role and send or redirect it to the appropriate dashboard or page
            if (u.getUserRole() == UserServices.ADMIN_ROLE) {

                //--- add user details to the HttpSession --> session
                this.addUserInSession(u, session);
                System.out.println("--------- current user : " + u + " and user role : " + u.getUserRole());
                return "redirect:admin/dashboard";
            } else if (u.getUserRole() == UserServices.USER_ROLE) {
                //--- add user details to the HttpSession --> session
                //--- add user details to the HttpSession --> session
                this.addUserInSession(u, session);
                System.out.println("--------- current user : " + u + " and user role : " + u.getUserRole());
                return "redirect:user/dashboard";
            } else {
                //-- Add error to the modal and send back to login-form page
                m.addAttribute("err", "invalid user role");
                return "index";
            }
        } catch (UserBlockedException ex) {
            //-- Add error to the modal and send back to login-form page
            m.addAttribute("err", ex.getMessage());
            return "index"; //--- jsp page--> /WEB-INF/view/index/.jsp
        }

    }

    @RequestMapping("/user/dashboard")
    public String userDashboard() {
        return "user_dashboard"; //--- jsp page--> /WEB-INF/view/user_dashboard/.jsp
    }

    @RequestMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin_dashboard"; //--- jsp page--> /WEB-INF/view/admin_dashboard/.jsp
    }

    private void addUserInSession(User u, HttpSession session) {
        //--- binding complete user to the session, this my use somewhere
        System.out.println("------------- adding user details to the session ------------");
        session.setAttribute("user", u);
        //----- binding user details to the session
        session.setAttribute("userId", u.getUserId());
        session.setAttribute("userName", u.getUserName());
        session.setAttribute("userRole", u.getUserRole());
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index?act=lo"; //--- after invalidating the user sessions sending back to the index page that is user-login page
    }

    @RequestMapping("/reg_form")
    private String handleRegForm(Model m) {
        RegisterCommand rc = new RegisterCommand();
        m.addAttribute("reg_command", rc);
        return "reg_form";// JSP FILE --> reg_form.jsp
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleRegistration(@ModelAttribute("reg_command") RegisterCommand rcmd, Model m, HttpSession session) {
        User user = rcmd.getUser();
        if (user.getUserName().equals("")) {
            m.addAttribute("empty_field", "please enter your Name.");
            return "redirect:/reg_form";
        }
        if (user.getUserPhone().equals("")) {
            m.addAttribute("empty_field", "please enter your phone number.");
            return "redirect:/reg_form";
        }
        if (user.getUserEmail().equals("")) {
            m.addAttribute("empty_field", "please enter your email address.");
            return "redirect:/reg_form";
        }
        if (user.getUserAddress().equals("")) {
            m.addAttribute("empty_field", "please enter your Address.");
            return "redirect:/reg_form";
        }
        if (user.getUserLoginName().equals("")) {
            m.addAttribute("empty_field", "please enter your username.");
            return "redirect:/reg_form";
        }
        if (user.getUserPassword().equals("")) {
            m.addAttribute("empty_field", "please enter your password.");
            return "redirect:/reg_form";
        }

        user.setUserRole(UserServices.USER_ROLE);
        user.setUserLoginStatus(UserServices.LOGIN_STATUS_ACTIVE);
        // TODO : users fileds must not be empty
        User u = us.isUserExists(user);
        if (u == null) {
            us.registerUser(user);
            return "redirect:/index?act=reg";
        } else {
            return "redirect:/index?act=exists";
        }
    }

    @RequestMapping(value = "admin/user_list")
    public String userList(Model m, HttpSession session) {
        //int userId = Integer.parseInt(session.getAttribute("userId").toString());
//        System.out.println("************************************ user id " + userId);
        List<User> users = us.getAllUsers();
        m.addAttribute("userLists", users);
        int x = 0;
        System.out.println("********************************* contact lists are displaying -----");
        for (User u : users) {
            System.out.println("------------ data: " + (++x) + " --------------");
            System.out.println("------" + u.getUserName());
        }
        return "user_list"; //--- JSP PAGE --> contact_list.js 
    }

    @RequestMapping(value = "/admin/change_login_status")
    @ResponseBody
    public String changeLoginStatus(@RequestParam Integer userId, @RequestParam Integer loginStatus) {
        try {
            us.blockUser(userId, loginStatus);
            return "SUCCESS: login status changed!";
        } catch (Exception e) {
            return "ERROR: failed to changed login status!";
        }
    }
}
