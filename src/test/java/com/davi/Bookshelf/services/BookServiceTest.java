package com.davi.Bookshelf.services;

import com.davi.Bookshelf.domain.dtos.BookRequestDto;
import com.davi.Bookshelf.domain.entities.Book;
import com.davi.Bookshelf.domain.entities.BookStatus;
import com.davi.Bookshelf.infra.exceptions.ResourceNotFoundException;
import com.davi.Bookshelf.modules.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    @DisplayName("Deve retornar os livros criados na lista")
    void getAll() {
        List<Book> bookList = new ArrayList<>(Arrays.asList(
                new Book(UUID.randomUUID(), "Nome 1", "Titulo 1", "12345", LocalDate.now(), BookStatus.AVAILABLE),
                new Book(UUID.randomUUID(), "Nome 2", "Titulo 2", "67890", LocalDate.now(), BookStatus.AVAILABLE)
        ));

        when(bookRepository.findAll()).thenReturn(bookList);

        List<Book> result = bookService.getAll();

        assertNotNull(result);
        assertEquals(bookList, result);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar um id igual ao pedido")
    void getById() {
        UUID id = UUID.randomUUID();
        Book book = new Book(id, "Titulo Teste", "Autor Teste", "12345", LocalDate.now(), BookStatus.AVAILABLE);

        when(bookRepository.findById(id)).thenReturn(java.util.Optional.of(book));

        Book result = bookService.getById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(bookRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException quando o ID não existir")
    void shouldThrowExceptionWhenBookNotFound() {
        UUID id = UUID.randomUUID();
        when(bookRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.getById(id);
        });

        verify(bookRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve criar um livro com sucesso")
    void createBook() {
        BookRequestDto bookDto = new BookRequestDto("Autor Teste", "Título Teste", "12345", LocalDate.now(), BookStatus.AVAILABLE);
        Book book = new Book();

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.createBook(bookDto);

        assertNotNull(result);
        verify(bookRepository, times(1)).save(any(Book.class));
    }


    @Test
    @DisplayName("Deve atualizar as informações do livro")
    void updateBook() {
        UUID id = UUID.randomUUID();
        Book book = new Book(id, "Titulo antigo", "Autor antigo", "12345", LocalDate.now(), BookStatus.AVAILABLE);
        BookRequestDto bookDto = new BookRequestDto("Autor Novo", "Título Novo", "12345", LocalDate.now(), BookStatus.AVAILABLE);

        when(bookRepository.findById(id)).thenReturn(java.util.Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenAnswer(i -> i.getArguments()[0]);

        Book result = bookService.updateBook(id, bookDto);

        assertNotNull(result);
        assertEquals(bookDto.getTitle(), result.getTitle());
        assertEquals(bookDto.getAuthor(), result.getAuthor());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    @DisplayName("Deve verificar se delete é chamado com sucesso")
    void deleteBook() {
        UUID id = UUID.randomUUID();

        when(bookRepository.existsById(id)).thenReturn(true);

        bookService.deleteBook(id);

        verify(bookRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lancar ResourceNotFoundException para id inexistente")
    void deleteBookDoesntExist() {
        UUID id = UUID.randomUUID();
        when(bookRepository.existsById(id)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, ()-> bookService.deleteBook(id));

        verify(bookRepository, times(0)).deleteById(id);
    }
}