package com.oyedost.contactapp.controller;

import com.oyedost.contactapp.commands.AddContactCommand;
import com.oyedost.contactapp.commands.LoginCommand;
import com.oyedost.contactapp.commands.UpdateContactCommand;
import com.oyedost.contactapp.domain.Contact;
import com.oyedost.contactapp.services.ContactServices;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @Autowired
    private ContactServices cs;

    @RequestMapping(value = "/add_contact")
    public String addContactForm(Model m) {
        m.addAttribute("add_contact_command", new AddContactCommand());

        return "add_contact"; //--- jsp page--> /WEB-INF/view/add_contact/.jsp

    }

    @RequestMapping(value = "/save_contact")
    public String saveContact(@ModelAttribute("add_contact_command") AddContactCommand cmd, Model m, HttpSession session) {
        Contact contact = cmd.getContact();
        contact.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));

        if (contact.getName().equals("") || contact.getAddress().equals("")
                || contact.getPhone().equals("") || contact.getEmail().equals("") || contact.getRemark().equals("")) {
            return "redirect:/add_contact?act=missing_field";
        }

        if (contact.getPhone().length() > 16) {
            return "redirect:/add_contact?act=invalid_contact";
        }

        /**
         * In this method, Contact data will be verified, hint : two or more
         * user can not have same contact
         */
        Contact c = cs.validateContact(contact);
        if (c == null) {
            cs.saveContact(contact);
            return "redirect:/add_contact?act=saved";
        } else {
            return "redirect:/add_contact?act=already_saved";
        }
    }

    @RequestMapping(value = "user/contact_list")
    public String contactList(Model m, HttpSession session) {
        if(session.getAttribute("userId")!=null){
        
        int userId = Integer.parseInt(session.getAttribute("userId").toString());
        System.out.println("************************************ user id " + userId);
        List<Contact> contacts = cs.searchContact(userId);
        m.addAttribute("contactLists", contacts);
        int x = 0;
        System.out.println("********************************* contact lists are displaying -----");
        for (Contact c : contacts) {
            System.out.println("------------ data: " + (++x) + " --------------");
            System.out.println("------" + c.getName());
        }
        return "contact_list"; //--- JSP PAGE --> contact_list.jsp
        }
        else{
            //             m.addAttribute("login_command", new LoginCommand());
            //            return "index"; //--- jsp page--> /WEB-INF/view/index/.jsp
        //------------- OR ---
        return "contact_list";
        //-- return simply 'contact_list.jsp' page without any data, since on contact_list.jsp page 
        //already condition is written that if userId will null then it will transfer to the login page
        }
    }

    @RequestMapping(value = "user/del_contact")
    public String deleteContact(@RequestParam("cid") Integer contactId) {
        cs.deleteContact(contactId);
        return "redirect:contact_list?del=ok";
    }

    @RequestMapping(value = "user/update_contact")
    public String updateContact(@RequestParam("cid") Integer contact_id, Model m, HttpSession session) {
        session.setAttribute("contactId", contact_id);
        Contact c = cs.findContactByContactId(contact_id);
        m.addAttribute("update_contact_command", c);
        return "update_contact"; //-------- JSP PAGE--> update_contact.jsp
    }

    @RequestMapping(value = "user/save_updated_contact")
    public String saveOrUpdatedContact(@ModelAttribute("update_contact_command") Contact c, Model m, HttpSession session) {
        Integer contactId = (Integer) session.getAttribute("contactId");

        if (contactId == null) {
            //-------- SAVE TASK ---------------
            try {
                Integer userId = (Integer) session.getAttribute("userId"); //-- getting current logged In userId through session
                c.setUserId(userId); //---- userId will be store as foreign key for a new contact entering
                cs.saveContact(c);
                return "redirect:contact_list?act=saved";
                //--- after saving the new entered data into the redirected to contact list and message--> data saved successfully
            } catch (Exception e) {
                return "update_contact?act=failed"; //-- if data will not save then redirect to the againg same page that is--> update_contact.jsp
            }
        } else {
            //------------------ UPDATE TASK -----------------
            System.out.println("------------ contact id : " + contactId + " ------------");
            c.setContactId(contactId); //--------- here contactId works as foreign key 
            cs.updateContact(c);
            session.removeAttribute("contactId");
            return "redirect:contact_list?act=updated";
        }

    }

    @RequestMapping(value = "user/search_contact")
    public String searchContact(Model m, HttpSession session, @RequestParam("searchText") String searchText) {
        int userId = Integer.parseInt(session.getAttribute("userId").toString());
        System.out.println("************************************ user id " + userId);
        List<Contact> contacts = cs.searchContact(userId, searchText);
        m.addAttribute("contactLists", contacts);
        int x = 0;
        System.out.println("********************************* contact lists are displaying -----");
        for (Contact c : contacts) {
            System.out.println("------------ data: " + (++x) + " --------------");
            System.out.println("------" + c.getName());
        }
        return "contact_list"; //--- JSP PAGE --> contact_list.jsp
    }
    
    /**
     * deleting bulk of contacts at a time
     * @param contactIds
     * @return 
     */
 @RequestMapping(value = "user/delete_bulk_contact")
    public String deleteBulContact(@RequestParam("cids") Integer[] contactIds) {
        cs.deleteContact(contactIds);
        return "redirect:contact_list?act=all_deleted";
    }
    
}
