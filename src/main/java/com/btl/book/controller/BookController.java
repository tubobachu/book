package com.btl.book.controller;

import com.btl.book.entity.Book;
import com.btl.book.entity.User;
import com.btl.book.repository.BookRepository;
import com.btl.book.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping(value = "book")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(UserRepository userRepository, BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String showSignUpForm() {
        return "book";
    }
    @GetMapping("/addBook")
    public String showAddBoowForm(Book book) {
        return "addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book, @RequestParam("image") MultipartFile image) {

        if (!image.isEmpty()) {
            try {
                // Lưu ảnh vào thư mục lưu trữ (ví dụ: /uploads)
                String imageName = UUID.randomUUID().toString() + "-" + image.getOriginalFilename();
                String uploadDir = "D:\\upload\\" + imageName;
                image.transferTo(new File(uploadDir));

                book.setImageUrl(uploadDir);
            } catch (IOException e) {
                // Xử lý ngoại lệ khi xảy ra lỗi upload ảnh
                e.printStackTrace();
            }
        }
        bookRepository.save(book);
        return "redirect:/book/";
    }
}
