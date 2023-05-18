package com.btl.book.controller;

import com.btl.book.entity.User;
import com.btl.book.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller

public class RegisterController {
    private final UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "register";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        Optional<User> users = userRepository.findByUsername(user.getUsername());
        if (users.isPresent()){
            result.addError(new FieldError("user", "username", "username is existed"));
            return "register";
        }
        userRepository.save(user);
        return "redirect:/";
    }
}
