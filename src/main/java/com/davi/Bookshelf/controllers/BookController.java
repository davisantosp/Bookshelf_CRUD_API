package com.davi.Bookshelf.controllers;

import com.davi.Bookshelf.domain.dtos.BookRequestDto;
import com.davi.Bookshelf.domain.entities.Book;
import com.davi.Bookshelf.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> index() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book show(@PathVariable UUID id) {
        return bookService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody @Valid BookRequestDto bookDto) {
        Book book = bookService.createBook(bookDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable UUID id, @RequestBody BookRequestDto bookDto) {
        return bookService.updateBook(id, bookDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        bookService.deleteBook(id);
    }
}