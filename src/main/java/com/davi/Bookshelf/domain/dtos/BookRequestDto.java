package com.davi.Bookshelf.domain.dtos;

import com.davi.Bookshelf.domain.entities.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {
    @NotBlank(message = "O autor é obrigatório")
    private String author;

    @NotBlank(message = "O título é obrigatório")
    private String title;

    @NotBlank(message = "O ISBN é obrigatório")
    private String ISBN;

    @NotNull(message = "A data de publicação é obrigatória")
    private LocalDate publicationDate;

    @NotNull(message = "O status é obrigatório")
    private BookStatus status;
}
