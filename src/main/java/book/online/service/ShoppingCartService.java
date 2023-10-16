package book.online.service;

import book.online.dto.cart.AddBookCartRequestDto;
import book.online.dto.cart.CartResponseDto;
import book.online.dto.cart.UpdateBookCartRequestDto;
import book.online.model.ShoppingCart;

public interface ShoppingCartService {
    CartResponseDto getShoppingCart();

    CartResponseDto addBookToShoppingCart(AddBookCartRequestDto addBookCartRequestDto);

    CartResponseDto updateCartItem(UpdateBookCartRequestDto updateBookCartRequestDto, Long id);

    CartResponseDto deleteCartItem(Long id);

    ShoppingCart findShoppingCart();

    void clearCart();
}
