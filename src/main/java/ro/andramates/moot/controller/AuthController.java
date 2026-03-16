package ro.andramates.moot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.andramates.moot.service.UserService;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password
    ){
        userService.register(username,password);
        return "redirect:/login";
    }
}