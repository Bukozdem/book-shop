package book.online.service.impl;

import book.online.dto.cart.AddBookCartRequestDto;
import book.online.dto.cart.CartResponseDto;
import book.online.dto.cart.UpdateBookCartRequestDto;
import book.online.exception.EntityNotFoundException;
import book.online.mapper.CartMapper;
import book.online.model.Book;
import book.online.model.CartItem;
import book.online.model.ShoppingCart;
import book.online.repository.book.BookRepository;
import book.online.repository.cart.CartItemRepository;
import book.online.repository.cart.ShoppingCartRepository;
import book.online.service.ShoppingCartService;
import book.online.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final UserService userService;
    private final CartMapper cartMapper;

    @Override
    public CartResponseDto getShoppingCart() {
        return cartMapper.toDto(findShoppingCart());
    }

    @Override
    public CartResponseDto addBookToShoppingCart(AddBookCartRequestDto addBookCartRequestDto) {
        Book book = bookRepository.findById(addBookCartRequestDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Couldn't find item in cart by id "
                        + addBookCartRequestDto.getBookId()));
        ShoppingCart shoppingCart = findShoppingCart();
        CartItem cartItem = cartItemRepository.save(cartMapper
                .toCartItem(addBookCartRequestDto, book, shoppingCart));
        shoppingCart.getCartItems().add(cartItem);
        shoppingCartRepository.save(shoppingCart);
        return getShoppingCart();
    }

    @Override
    public CartResponseDto updateCartItem(UpdateBookCartRequestDto updateBookCartRequestDto,
                                          Long id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Couldn't find item in cart by id "
                + id));
        cartItem.setQuantity(updateBookCartRequestDto.getQuantity());
        cartItemRepository.save(cartItem);
        return getShoppingCart();
    }

    @Override
    public CartResponseDto deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
        return getShoppingCart();
    }

    @Override
    public ShoppingCart findShoppingCart() {
        return shoppingCartRepository.findById(userService.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Couldn't find cart for user by id "
                        + userService.getUserId()));
    }

    @Transactional
    @Override
    public void clearCart() {
        cartItemRepository.deleteByShoppingCart(findShoppingCart());
    }
}
