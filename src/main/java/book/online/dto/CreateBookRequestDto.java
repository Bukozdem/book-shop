package book.online.dto;

import book.online.validation.Isbn;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotNull(message = "Field title should not be empty")
    private String title;
    @NotNull(message = "Field author should not be empty")
    private String author;
    @NotNull(message = "Field isbn should not be empty")
    @Isbn
    private String isbn;
    @NotNull(message = "Field price should not be empty")
    @Min(value = 0, message = "Price should not be less than 0")
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
