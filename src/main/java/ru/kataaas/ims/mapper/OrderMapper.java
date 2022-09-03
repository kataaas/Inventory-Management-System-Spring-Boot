package ru.kataaas.ims.mapper;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.dto.OrderDTO;
import ru.kataaas.ims.dto.ProductDTO;
import ru.kataaas.ims.entity.OrderEntity;

@Service
public class OrderMapper {

    private final ProductMapper productMapper;

    public OrderMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public OrderDTO toOrderDTO(OrderEntity order) {
        OrderDTO orderDTO = new OrderDTO();
        ProductDTO productDTO = productMapper.toProductDTO(order.getProduct());

        orderDTO.setId(order.getId());
        orderDTO.setProduct(productDTO);
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setCreatedAt(order.getCreatedAt());
        orderDTO.setOrderTime(order.getOrderTime());
        return orderDTO;
    }

}
