package vn.com.store.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.com.store.dtos.UserChangePassword;
import vn.com.store.repository.documents.User;
import vn.com.store.services.IUserService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/store")
@AllArgsConstructor
public class StoreController {
    private IUserService userService;
    private SessionRegistry sessionRegistry;

    @PostMapping(path = "/register")
    public @ResponseBody
    ModelAndView register(@ModelAttribute User user, ModelMap model) {
        User respond = userService.register(user);
        String redirectUrl = "login";
        if(respond == null){
            redirectUrl = "register";
        }
        return new ModelAndView("redirect:/"+ redirectUrl, model);
    }

    @PostMapping("/update")
    public @ResponseBody
    User update(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping("/change-password")
    public @ResponseBody
    User changePassword(@ModelAttribute UserChangePassword userChangepassword, Principal principal) {
        return userService.changePassword(principal.getName(), userChangepassword.getOldPassword(), userChangepassword.getNewPassword());
    }

    @PostMapping("/update-avatar")
    public @ResponseBody
    User updateAvatar(@RequestBody String avatarUrl, Principal principal) {
        return userService.updateAvatar(principal.getName(), avatarUrl);
    }

    @PostMapping("/forget-password")
    public @ResponseBody
    User forgetPassword(@RequestBody User user) {
        return userService.forgetPassword(user.getEmail());
    }
}
