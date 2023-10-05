package book.online.dto;

import book.online.validation.Isbn;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    @Isbn
    private String isbn;
    @NotNull
    @Min(0)
    private BigDecimal price;
    private String description;
    private String coverImage;

    public CreateBookRequestDto(String title, String author, String isbn,
                BigDecimal price, String description, String coverImage) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.description = description;
        this.coverImage = coverImage;
    }
}
