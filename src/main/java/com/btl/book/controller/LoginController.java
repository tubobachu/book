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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String showSignUpForm(User user) {
        return "login";
    }

    @PostMapping("/login")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        Optional<User> users = userRepository.findByUsername(user.getUsername());
        if (users.isPresent()){
            if(!users.get().getPassword().equals(user.getPassword()) ){
                model.addAttribute("error","Mật khẩu không đúng");
                return "login";
            }

        }else{
            model.addAttribute("error","tài khoản không tồn tại!");
            return "login";
        }
        return "redirect:/book";
    }
}
