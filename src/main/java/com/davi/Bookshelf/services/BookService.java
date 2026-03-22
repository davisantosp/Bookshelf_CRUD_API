package com.davi.Bookshelf.services;

import com.davi.Bookshelf.domain.dtos.BookRequestDto;
import com.davi.Bookshelf.domain.entities.Book;
import com.davi.Bookshelf.infra.exceptions.ResourceNotFoundException;
import com.davi.Bookshelf.modules.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Book getById(UUID id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado."));
        return book;
    }

    public Book createBook(BookRequestDto bookDto){
        Book newBook = new Book();

        newBook.setAuthor(bookDto.getAuthor());
        newBook.setIsbn(bookDto.getIsbn());
        newBook.setTitle(bookDto.getTitle());
        newBook.setPublicationDate(bookDto.getPublicationDate());
        newBook.setStatus(bookDto.getStatus());

        return bookRepository.save(newBook);
    }

    public Book updateBook(UUID id, BookRequestDto updatedBook){
        Book oldBook = this.getById(id);

        oldBook.setAuthor(updatedBook.getAuthor());
        oldBook.setIsbn(updatedBook.getIsbn());
        oldBook.setTitle(updatedBook.getTitle());
        oldBook.setPublicationDate(updatedBook.getPublicationDate());
        oldBook.setStatus(updatedBook.getStatus());

        return bookRepository.save(oldBook);
    }

    public void deleteBook(UUID id){
        if (!bookRepository.existsById(id))
            throw new ResourceNotFoundException("Livro não encontrado");

        bookRepository.deleteById(id);
    }
}
