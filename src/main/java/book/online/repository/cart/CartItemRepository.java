package book.online.repository.cart;

import book.online.model.CartItem;
import book.online.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteByShoppingCart(ShoppingCart cart);
}
