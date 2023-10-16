package book.online.mapper;

import book.online.config.MapperConfig;
import book.online.dto.order.OrderItemDto;
import book.online.dto.order.OrderRequestDto;
import book.online.dto.order.OrderResponseDto;
import book.online.model.CartItem;
import book.online.model.Order;
import book.online.model.OrderItem;
import book.online.model.ShoppingCart;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    @Mapping(target = "userId", source = "order.user.id")
    OrderResponseDto toOrderResponseDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "shippingAddress", source = "dto.shippingAddress")
    Order toOrderModel(ShoppingCart shoppingCart, OrderRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "price", source = "cartItem.book.price")
    OrderItem toOrderItem(CartItem cartItem, Order order);

    @Mapping(target = "bookId", source = "orderItem.book.id")
    OrderItemDto toOrderItemDto(OrderItem orderItem);

    @AfterMapping
    default void setStatusTimeAndTotal(@MappingTarget Order order, ShoppingCart shoppingCart) {
        order.setStatus(Order.Status.PENDING);
        order.setOrderDate(LocalDateTime.now());
        order.setTotal(shoppingCart.getCartItems().stream()
                .map(cartItem -> cartItem.getBook().getPrice().intValue() * cartItem.getQuantity())
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    @AfterMapping
    default void setOrder(@MappingTarget OrderItem orderItem, Order order) {
        orderItem.setOrder(order);
    }
}
