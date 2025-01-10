package com.anw.order.service.dataaccess.order.adapter;

import com.anw.order.service.dataaccess.order.mapper.OrderDataAccessMapper;
import com.anw.order.service.dataaccess.order.repository.OrderJpaRepository;
import com.anw.order.service.domain.entity.Order;
import com.anw.order.service.domain.exception.OrderDomainException;
import com.anw.order.service.domain.ports.output.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    @Override
    public List<Order> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderJpaRepository.findAll(pageable).getContent()
                .stream()
                .map(orderDataAccessMapper::orderEntityToOrder)
                .collect(Collectors.toList());
    }

    @Override
    public Order save(Order order) {
        return orderDataAccessMapper.orderEntityToOrder(
                orderJpaRepository.save(orderDataAccessMapper.orderToOrderEntity(order)));
    }

    @Override
    public Order getById(Order order) {
        try {
            return orderDataAccessMapper.orderEntityToOrder(
                    orderJpaRepository.getReferenceById(order.getId().getValue()));
        } catch (EntityNotFoundException ex) {
            log.error("order with id {} is not found", order.getId().getValue());
            throw new OrderDomainException("order with id " + order.getId().getValue() + " is not found");
        }
    }

    @Override
    public Optional<Order> findById(String orderId) {
        return orderJpaRepository.findById(UUID.fromString(orderId))
                .map(orderDataAccessMapper::orderEntityToOrder);
    }
}
