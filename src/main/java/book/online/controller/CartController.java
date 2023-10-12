package book.online.controller;

import book.online.dto.cart.AddBookCartRequestDto;
import book.online.dto.cart.CartResponseDto;
import book.online.dto.cart.UpdateBookCartRequestDto;
import book.online.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management",
        description = "Endpoints for managing shopping cart")
@RestController
@RequestMapping(value = "/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final ShoppingCartService shoppingCartService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(summary = "Get all item from shopping cart")
    public CartResponseDto getShoppingCart() {
        return shoppingCartService.getShoppingCart();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @Operation(summary = "Add book to shopping cart")
    public CartResponseDto addBookToShoppingCart(@RequestBody @Valid AddBookCartRequestDto dto) {
        return shoppingCartService.addBookToShoppingCart(dto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping(value = "/cart-items/{id}")
    @Operation(summary = "Update quantity of books in shopping cart")
    public CartResponseDto updateCartItem(@RequestBody @Valid UpdateBookCartRequestDto dto,
                                                 @PathVariable Long id) {
        return shoppingCartService.updateCartItem(dto, id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping(value = "/cart-items/{id}")
    @Operation(summary = "Delete item in shopping cart")
    public CartResponseDto deleteCartItem(@PathVariable Long id) {
        return shoppingCartService.deleteCartItem(id);
    }
}
