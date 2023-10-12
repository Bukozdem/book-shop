package book.online.mapper;

import book.online.config.MapperConfig;
import book.online.dto.cart.AddBookCartRequestDto;
import book.online.dto.cart.CartItemResponseDto;
import book.online.dto.cart.CartResponseDto;
import book.online.model.Book;
import book.online.model.CartItem;
import book.online.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartMapper {
    @Mapping(target = "userId", source = "shoppingCart.id")
    CartResponseDto toDto(ShoppingCart shoppingCart);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    CartItem toCartItem(AddBookCartRequestDto dto, Book book, ShoppingCart shoppingCart);

    @Mapping(target = "bookId", source = "cartItem.book.id")
    @Mapping(target = "bookTitle", source = "cartItem.book.title")
    CartItemResponseDto toCartItemResponse(CartItem cartItem);
}
