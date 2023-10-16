package book.online.service.impl;

import book.online.dto.order.OrderItemDto;
import book.online.dto.order.OrderRequestDto;
import book.online.dto.order.OrderResponseDto;
import book.online.dto.order.StatusRequestDto;
import book.online.exception.EntityNotFoundException;
import book.online.mapper.OrderMapper;
import book.online.model.Order;
import book.online.model.Order.Status;
import book.online.model.ShoppingCart;
import book.online.repository.order.OrderItemRepository;
import book.online.repository.order.OrderRepository;
import book.online.service.OrderService;
import book.online.service.ShoppingCartService;
import book.online.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ShoppingCartService shoppingCartService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto dto) {
        ShoppingCart shoppingCart = shoppingCartService.findShoppingCart();
        Order order = orderRepository.save(orderMapper.toOrderModel(shoppingCart, dto));
        order.setOrderItems(shoppingCart.getCartItems().stream()
                .map(cartItem -> orderItemRepository.save(orderMapper.toOrderItem(cartItem, order)))
                .collect(Collectors.toSet()));
        shoppingCartService.clearCart();
        return orderMapper.toOrderResponseDto(order);
    }

    @Override
    public List<OrderResponseDto> getAllOrders(Pageable pageable) {
        return orderRepository.findAllByUser(pageable, userService.getUser()).stream()
                .map(orderMapper::toOrderResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto updateOrdersStatus(StatusRequestDto dto, Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Couldn't find order by id " + id));
        order.setStatus(dto.status());
        return orderMapper.toOrderResponseDto(orderRepository.save(order));
    }

    @Override
    public List<OrderItemDto> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findAllByOrderId(orderId).stream()
                .map(orderMapper::toOrderItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDto getOrderItemsByOrderIdAndId(Long orderId, Long itemId) {
        return orderMapper.toOrderItemDto(orderItemRepository.findByOrderIdAndId(orderId, itemId)
                .orElseThrow(() -> new EntityNotFoundException("Couldn't find order item by id "
                        + itemId)));
    }
}
