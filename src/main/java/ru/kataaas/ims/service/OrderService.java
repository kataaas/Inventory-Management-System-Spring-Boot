package ru.kataaas.ims.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.kataaas.ims.entity.OrderEntity;
import ru.kataaas.ims.entity.ProductEntity;
import ru.kataaas.ims.entity.UserEntity;
import ru.kataaas.ims.repository.OrderRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    private final UserService userService;

    private final CartService cartService;

    private final ProductService productService;

    private final OrderRepository orderRepository;

    public OrderService(UserService userService,
                        @Lazy CartService cartService,
                        ProductService productService,
                        OrderRepository orderRepository) {
        this.userService = userService;
        this.cartService = cartService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    public void create(Long userId, Long productId, int quantity) {
        OrderEntity order = new OrderEntity();
        Optional<UserEntity> user = userService.findById(userId);
        Optional<ProductEntity> product = productService.findById(productId);
        if (user.isPresent() && product.isPresent()) {
            order.setUser(user.get());
            order.setProduct(product.get());
            order.setQuantity(quantity);
            order.setOrderTime(new Date(System.currentTimeMillis() + 604800000)); // 7 days
            orderRepository.save(order);
        }
    }

}
