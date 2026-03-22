package com.davi.Bookshelf.modules;

import com.davi.Bookshelf.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
