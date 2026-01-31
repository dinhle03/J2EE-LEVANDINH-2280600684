package com.example.bai2.controller;

import com.example.bai2.model.Book;
import com.example.bai2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public  String listBooks(Model model){
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }
    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book", new Book());
        return "add-book";
    }
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book){
        bookService.addBook(book);
        return "redirect:/books";
    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model){
        bookService.getBookById(id).ifPresent(book -> model.addAttribute("book", book));
        return "edit-book";
    }
    @PostMapping("/edit")
    public String updateBook(@ModelAttribute Book book){
        bookService.updateBook(book);
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }
//    @Autowired
//    private BookService bookService;
//    @GetMapping
//    public List<Book> getAllBooks(){
//        return bookService.getAllBooks();
//    }
//    @GetMapping("/{id}")
//    public  Book getBooById(@PathVariable int id){
//        return bookService.getBookById(id);
//    }
//    @PostMapping
//    public String addBook(@RequestBody Book book){
//        bookService.addBook(book);
//        return "Book added successfully";
//    }
//    @PutMapping("/{id}")
//    public String updateBook(@PathVariable int id, @RequestBody Book updatedBook){
//        bookService.updateBook(id, updatedBook);
//        return "Book deleted successfully!";
//    }
//    @DeleteMapping("/{id}")
//    public String deleteBook(@PathVariable int id){
//        bookService.deleteBook(id);
//        return "Book deleted successfully";
//    }
}
