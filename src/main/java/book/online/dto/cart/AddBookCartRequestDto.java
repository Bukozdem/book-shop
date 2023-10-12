package book.online.dto.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddBookCartRequestDto {
    @NotNull(message = "Field bookId should not be empty")
    private Long bookId;
    @NotNull(message = "Field quantity should not be empty")
    @Min(value = 1, message = "You should add at least one book to the cart")
    private Long quantity;
}
