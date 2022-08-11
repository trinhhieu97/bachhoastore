package vn.com.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping(value = { "", "/", "login" })
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = { "", "/", "register" })
    public String register(Model model) {
        return "register";
    }

    @RequestMapping(value = { "", "/", "change-password" })
    public String changePassword(Model model) {
        return "change-password";
    }
}
