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
import java.util.Set;

@Service
public class CartService {

    private final UserService userService;

    private final OrderService orderService;

    private final CartRepository cartRepository;

    private final ProductService productService;

    private final CartProductsRepository cartProductsRepository;

    public CartService(UserService userService,
                       OrderService orderService,
                       CartRepository cartRepository,
                       ProductService productService,
                       CartProductsRepository cartProductsRepository) {
        this.userService = userService;
        this.orderService = orderService;
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartProductsRepository = cartProductsRepository;
    }

    public Optional<CartEntity> fetchByUserId(Long userId) {
        return cartRepository.getByUserId(userId);
    }

    public List<CartProductsEntity> findProductsByCartId(Long id) {
        return cartProductsRepository.findProductsByCart_Id(id);
    }

    public void addProductToCart(Long userId, Long productId, int quantity) {
        Optional<ProductEntity> product = productService.findById(productId);
        if (product.isPresent()) {
            CartEntity cart = cartRepository.getByUserId(userId).orElseGet(() -> null);
            assert cart != null;
            Optional<CartProductsEntity> optionalCartProducts
                    = cartProductsRepository.findByCartIdAndProductId(cart.getId(), productId);
            CartProductsEntity cartProducts = null;
            // if cartProducts is empty, create new object
            if (optionalCartProducts.isEmpty()) {
                cartProducts = new CartProductsEntity();
                cartProducts.setCartId(cart.getId());
                cartProducts.setProductId(productId);
                cartProducts.setCart(cart);
                cartProducts.setProduct(product.get());
                cartProducts.setQuantity(quantity);
            } else {
                cartProducts = optionalCartProducts.get();
                if (productService.getQuantityById(productId) >= cartProducts.getQuantity() + quantity)
                    cartProducts.setQuantity(cartProducts.getQuantity() + quantity);
            }
            cartProductsRepository.save(cartProducts);
        }
    }

    public void orderProducts(Set<Long> ids, Long userId) {
        List<CartProductsEntity> cartProducts = cartProductsRepository.findByCartIdAndProductIdIsIn(userId, ids);
        cartProducts.forEach(cartProduct -> {
            // subtraction of ordered products from the products from the warehouse
            int productLeft = productService.getQuantityById(cartProduct.getProductId()) - cartProduct.getQuantity();
            if (productLeft >= 0) {
                orderService.create(userId, cartProduct.getProductId(), cartProduct.getQuantity());
                productService.setQuantityProduct(cartProduct.getProductId(), productLeft);
                cartProductsRepository.delete(cartProduct);
            }
        });
    }

    public void create(Long userId) {
        CartEntity cart = new CartEntity();
        UserEntity user = userService.findById(userId).orElseGet(() -> null);
        cart.setUser(user);
        cartRepository.save(cart);
    }

}
