package com.example.bai2.service;
import com.example.bai2.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L;
    public List<Book> getAllBooks(){
        return books;
    }
    public void addBook(Book book){
        book.setId(nextId++);
        books.add(book);
    }
    public Optional<Book> getBookById(Long id){
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
        
    }
    public void updateBook(Book updateBook){
        books.stream().filter(book -> book.getId().equals(updateBook.getId())).findFirst().ifPresent(book -> {book.setTitle(updateBook.getTitle());
        book.setAuthor((updateBook.getAuthor()));});
    }
    public void deleteBook(Long id){
        books.removeIf(book -> book.getId().equals(id));
    }
//    private List<Book> books = new ArrayList<>(List.of(
//            new Book(1,"Lập trình Java","Nguyễn Văn A"),
//            new Book(2,"Spring Boot","Trần Văn B"),
//            new Book(3,"Microservices","Lê Thị C")
//    ));
//    public List<Book> getAllBooks(){
//        return books;
//    }
//    public Book getBookById(int id){
//        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
//
//    }
//    public void addBook(Book book){
//        books.add(book);
//    }
//    public void updateBook(int id, Book updateBook) {
//        books.stream()
//                .filter(book -> book.getId() == id)
//                .findFirst()
//                .ifPresent(book -> {
//                    book.setTitle(updateBook.getTitle());
//                    book.setAuthor(updateBook.getAuthor());
//                });
//    }
//    public void deleteBook(int id) {
//        books.removeIf(book -> book.getId() == id);
//    }
}
