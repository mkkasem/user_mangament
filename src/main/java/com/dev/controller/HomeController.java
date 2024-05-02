package com.dev.controller;

import com.dev.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @Resource
    private UserService userService;

    @GetMapping
    public String getHomePage(Model model, HttpSession httpSession) {
        String currentUser = (String) httpSession.getAttribute("username");
        model.addAttribute("userList", userService.getAllUsersExceptCurrentOne(currentUser));

        return "index";
    }

    @GetMapping("/errorPage")
    public String getLoginPage() {
        return "errorPage";
    }

    @GetMapping("/change-language")
    public String changeLanguage(@RequestParam("lang") String lang, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        Cookie cookie = new Cookie("lang", lang);
        httpServletResponse.addCookie(cookie);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
