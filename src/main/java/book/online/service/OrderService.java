package book.online.service;

import book.online.dto.order.OrderItemDto;
import book.online.dto.order.OrderRequestDto;
import book.online.dto.order.OrderResponseDto;
import book.online.dto.order.StatusRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto dto);

    List<OrderResponseDto> getAllOrders(Pageable pageable);

    OrderResponseDto updateOrdersStatus(StatusRequestDto dto, Long id);

    List<OrderItemDto> getOrderItemsByOrderId(Long orderId);

    OrderItemDto getOrderItemsByOrderIdAndId(Long orderId, Long itemId);
}
