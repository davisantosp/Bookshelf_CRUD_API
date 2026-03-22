package com.davi.Bookshelf.domain.dtos;

import com.davi.Bookshelf.domain.entities.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {
    @NotNull(message = "O autor é obrigatório")
    private String author;

    @NotNull(message = "O título é obrigatório")
    private String title;

    @NotNull(message = "O ISBN é obrigatório")
    private String isbn;

    @NotNull(message = "A data de publicação é obrigatória")
    private LocalDate publicationDate;

    @NotNull(message = "O status é obrigatório")
    private BookStatus status;
}
