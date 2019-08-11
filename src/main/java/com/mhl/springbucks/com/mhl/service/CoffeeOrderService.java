package com.mhl.springbucks.com.mhl.service;

import com.mhl.springbucks.com.mhl.model.Coffee;
import com.mhl.springbucks.com.mhl.model.CoffeeOrder;
import com.mhl.springbucks.com.mhl.model.OrderState;
import com.mhl.springbucks.com.mhl.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@Slf4j
public class CoffeeOrderService {
    private CoffeeOrderRepository coffeeOrderRepository;

    public CoffeeOrderService(final CoffeeOrderRepository coffeeOrderRepository) {
        this.coffeeOrderRepository = coffeeOrderRepository;
    }

    public CoffeeOrder createOrder(final String customer, Coffee... coffees) {
        final CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffees)))
                .state(OrderState.INIT)
                .build();

        final CoffeeOrder savedOrder = coffeeOrderRepository.save(order);
        log.info("New Order: {}", savedOrder);
        return savedOrder;
    }

    public Boolean updateState(final CoffeeOrder coffeeOrder, final OrderState orderState) {
        if (orderState.compareTo(coffeeOrder.getState()) <= 0) {
            log.warn("Wrong State order:{},{}", orderState, coffeeOrder.getState());
            return false;
        }
        coffeeOrder.setState(orderState);
        coffeeOrderRepository.save(coffeeOrder);
        log.info("Update Order:{}", coffeeOrder);
        return true;
    }
}
