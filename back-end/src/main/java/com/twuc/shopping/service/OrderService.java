package com.twuc.shopping.service;

import com.twuc.shopping.dto.Order;
import com.twuc.shopping.dto.Product;
import com.twuc.shopping.entity.OrderEntity;
import com.twuc.shopping.entity.ProductEntity;
import com.twuc.shopping.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream().map(OrderService::mapFromOrderEntitiesToOrder).collect(Collectors.toList());
    }

    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    private static Order mapFromOrderEntitiesToOrder(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }
        return Order.builder()
                .id(orderEntity.getId())
                .amount(orderEntity.getAmount())
                .product(mapFromProductEntitiesToProduct(orderEntity.getProduct()))
                .build();
    }

    private static Product mapFromProductEntitiesToProduct(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        return Product.builder()
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .unit(productEntity.getUnit())
                .url(productEntity.getUrl())
                .build();
    }
}
