package book.online.dto.cart;

import lombok.Data;

@Data
public class CartItemResponseDto {
    private Long id;
    private Long bookId;
    private String bookTitle;
    private Long quantity;
}
