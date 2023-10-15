package book.online.service;

import book.online.dto.cart.AddBookCartRequestDto;
import book.online.dto.cart.CartResponseDto;
import book.online.dto.cart.UpdateBookCartRequestDto;

public interface ShoppingCartService {
    CartResponseDto getShoppingCart();

    CartResponseDto addBookToShoppingCart(AddBookCartRequestDto addBookCartRequestDto);

    CartResponseDto updateCartItem(UpdateBookCartRequestDto updateBookCartRequestDto, Long id);

    CartResponseDto deleteCartItem(Long id);
}
