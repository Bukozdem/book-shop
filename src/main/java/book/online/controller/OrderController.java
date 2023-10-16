package book.online.controller;

import book.online.dto.order.OrderItemDto;
import book.online.dto.order.OrderRequestDto;
import book.online.dto.order.OrderResponseDto;
import book.online.dto.order.StatusRequestDto;
import book.online.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order management")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Create an order from current shopping cart")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiResponse(responseCode = "201",
            description = "Order was created successfully")
    public OrderResponseDto createOrder(@RequestBody @Valid OrderRequestDto dto) {
        return orderService.createOrder(dto);
    }

    @GetMapping
    @Operation(summary = "Get all orders")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<OrderResponseDto> getOrdersHistory(Pageable pageable) {
        return orderService.getAllOrders(pageable);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update status of order")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiResponse(responseCode = "202",
            description = "Order status was updated successfully")
    public OrderResponseDto updateOrdersStatus(@PathVariable Long id,
                                               @RequestBody StatusRequestDto dto) {
        return orderService.updateOrdersStatus(dto, id);
    }

    @GetMapping("/{orderId}/items")
    @Operation(summary = "Get list of items from order by id")
    public List<OrderItemDto> getOrderItemsByOrderId(@PathVariable Long orderId) {
        return orderService.getOrderItemsByOrderId(orderId);
    }

    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Get certain item from order by ids")
    public OrderItemDto getOrderById(@PathVariable Long orderId,
                                     @PathVariable Long itemId) {
        return orderService.getOrderItemsByOrderIdAndId(orderId, itemId);
    }
}
