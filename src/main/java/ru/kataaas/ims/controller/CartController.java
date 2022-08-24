package ru.kataaas.ims.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kataaas.ims.dto.CartDTO;
import ru.kataaas.ims.entity.CartEntity;
import ru.kataaas.ims.mapper.CartMapper;
import ru.kataaas.ims.service.CartService;

import java.util.Optional;

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
        return cart.map(cartMapper::toCartDTO).orElse(null);
    }

}
