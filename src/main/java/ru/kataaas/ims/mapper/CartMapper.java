package ru.kataaas.ims.mapper;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.dto.CartDTO;
import ru.kataaas.ims.dto.ProductDTO;
import ru.kataaas.ims.entity.CartEntity;
import ru.kataaas.ims.service.CartService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartMapper {

    private final CartService cartService;

    private final ProductMapper productMapper;

    public CartMapper(CartService cartService, ProductMapper productMapper) {
        this.cartService = cartService;
        this.productMapper = productMapper;
    }

    public CartDTO toCartDTO(CartEntity cart) {
        CartDTO cartDTO = new CartDTO();
        List<ProductDTO> productsDTO = new ArrayList<>();
        cartService.findProductsByCartId(cart.getId())
                .forEach(cartProductsEntity ->
                        productsDTO.add(productMapper.toProductDTO(cartProductsEntity.getProduct())));
        cartDTO.setId(cart.getId());
        cartDTO.setProducts(productsDTO);

        return cartDTO;
    }

}
