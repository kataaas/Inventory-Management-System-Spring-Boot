package ru.kataaas.ims.service;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.dto.OrderDTO;
import ru.kataaas.ims.entity.OrderEntity;
import ru.kataaas.ims.entity.ProductEntity;
import ru.kataaas.ims.entity.UserEntity;
import ru.kataaas.ims.mapper.OrderMapper;
import ru.kataaas.ims.repository.OrderRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderMapper orderMapper;

    private final UserService userService;

    private final ProductService productService;

    private final OrderRepository orderRepository;

    public OrderService(OrderMapper orderMapper,
                        UserService userService,
                        ProductService productService,
                        OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.userService = userService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> findOrderListByUserPhoneNumber(String phoneNumber) {
        return orderRepository.findAllByUser_PhoneNumber(phoneNumber).stream()
                .map(orderMapper::toOrderDTO).collect(Collectors.toList());
    }

    public void create(Long userId, Long productId, int quantity) throws RuntimeException {
        OrderEntity order = new OrderEntity();
        Optional<UserEntity> user = userService.findById(userId);
        Optional<ProductEntity> product = productService.findById(productId);
        if (user.isPresent() && product.isPresent()) {
            order.setUser(user.get());
            order.setProduct(product.get());
            order.setQuantity(quantity);
            order.setOrderTime(new Date(System.currentTimeMillis() + 259200)); // + 3 days
            orderRepository.save(order);
            return;
        }
        throw new RuntimeException();
    }

}
