package ru.kataaas.ims.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kataaas.ims.dto.OrderDTO;
import ru.kataaas.ims.service.OrderService;
import ru.kataaas.ims.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final UserService userService;

    private final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping
    private List<OrderDTO> fetchOrderList(HttpServletRequest request) {
        String phoneNumber = request.getRemoteUser();
        return orderService.findOrderListByUserPhoneNumber(phoneNumber);
    }

}
