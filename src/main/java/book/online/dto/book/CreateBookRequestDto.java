package book.online.dto.book;

import book.online.validation.Isbn;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
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
    private List<Long> categoryIds;
}
