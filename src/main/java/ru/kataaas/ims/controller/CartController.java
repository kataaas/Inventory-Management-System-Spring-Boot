package ru.kataaas.ims.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kataaas.ims.dto.CartDTO;
import ru.kataaas.ims.entity.CartEntity;
import ru.kataaas.ims.mapper.CartMapper;
import ru.kataaas.ims.service.CartService;
import ru.kataaas.ims.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartMapper cartMapper;

    private final CartService cartService;

    private final UserService userService;

    public CartController(CartMapper cartMapper,
                          CartService cartService,
                          UserService userService) {
        this.cartMapper = cartMapper;
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping
    public CartDTO fetchCart(HttpServletRequest request) {
        String phoneNumber = request.getRemoteUser();
        Long userId = userService.findIdByPhoneNumber(phoneNumber);
        Optional<CartEntity> cart = cartService.fetchByUserId(userId);
        return cart.map(cartMapper::toCartDTO).orElseGet(() -> null);
    }

    @GetMapping("/buy")
    public ResponseEntity<?> orderProducts(@RequestParam("id") Set<Long> ids, HttpServletRequest request) {
        try {
            String phoneNumber = request.getRemoteUser();
            Long userId = userService.findIdByPhoneNumber(phoneNumber);
            cartService.orderProducts(ids, userId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }

}
