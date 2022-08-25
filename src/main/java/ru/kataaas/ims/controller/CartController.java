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

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartMapper cartMapper;

    private final CartService cartService;

    public CartController(CartMapper cartMapper, CartService cartService) {
        this.cartMapper = cartMapper;
        this.cartService = cartService;
    }

    @GetMapping
    public CartDTO fetchCart() {
        Optional<CartEntity> cart = cartService.fetchByUserId(1L);
        return cart.map(cartMapper::toCartDTO).orElseGet(() -> null);
    }

    @GetMapping("/buy")
    public ResponseEntity<?> orderProducts(@RequestParam("id") Set<Long> ids) {
        try {
            cartService.orderProducts(ids, 1L);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }

}
