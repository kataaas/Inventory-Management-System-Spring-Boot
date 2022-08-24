package ru.kataaas.ims.service;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.entity.CartEntity;
import ru.kataaas.ims.entity.CartProductsEntity;
import ru.kataaas.ims.entity.ProductEntity;
import ru.kataaas.ims.entity.UserEntity;
import ru.kataaas.ims.repository.CartProductsRepository;
import ru.kataaas.ims.repository.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final UserService userService;

    private final CartRepository cartRepository;

    private final ProductService productService;

    private final CartProductsRepository cartProductsRepository;

    public CartService(UserService userService,
                       CartRepository cartRepository,
                       ProductService productService,
                       CartProductsRepository cartProductsRepository) {
        this.userService = userService;
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartProductsRepository = cartProductsRepository;
    }

    public Optional<CartEntity> fetchByUserId(Long userId) {
        return cartRepository.getByUserIdAndOrderedFalse(userId);
    }

    public List<CartProductsEntity> findProductsByCartId(Long id) {
        return cartProductsRepository.findProductsByCart_Id(id);
    }

    public void addProductToCart(Long userId, Long productId, int quantity) {
        CartProductsEntity cartProducts = new CartProductsEntity();
        Optional<ProductEntity> product = productService.findById(productId);
        CartEntity cart = cartRepository.getByUserIdAndOrderedFalse(userId).orElse(null);;
        if (product.isPresent()) {
            if (cart == null) cart = create(userId);
            cartProducts.setCartId(cart.getId());
            cartProducts.setProductId(productId);
            cartProducts.setCart(cart);
            cartProducts.setProduct(product.get());
            cartProducts.setQuantity(quantity);
            cartProductsRepository.save(cartProducts);
        }
    }

    private CartEntity create(Long userId) {
        CartEntity cart = new CartEntity();
        UserEntity user = userService.findById(userId);
        cart.setUser(user);
        return cartRepository.save(cart);
    }

}
