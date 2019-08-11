package com.mhl.springbucks;

import com.mhl.springbucks.com.mhl.model.Coffee;
import com.mhl.springbucks.com.mhl.model.CoffeeOrder;
import com.mhl.springbucks.com.mhl.model.OrderState;
import com.mhl.springbucks.com.mhl.repository.CoffeeRepository;
import com.mhl.springbucks.com.mhl.service.CoffeeOrderService;
import com.mhl.springbucks.com.mhl.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
@EnableJpaRepositories
public class SpringBucksApplication implements ApplicationRunner {
    private CoffeeRepository coffeeRepository;
    private CoffeeService coffeeService;
    private CoffeeOrderService coffeeOrderService;

    public SpringBucksApplication(final CoffeeRepository coffeeRepository,
                                  final CoffeeService coffeeService,
                                  final CoffeeOrderService coffeeOrderService) {
        this.coffeeRepository = coffeeRepository;
        this.coffeeService = coffeeService;
        this.coffeeOrderService = coffeeOrderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBucksApplication.class, args);
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        log.info("All coffees:{}", coffeeRepository.findAll());

        final Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");
        if (latte.isPresent()) {
            final CoffeeOrder order = coffeeOrderService.createOrder("Li Lei", latte.get());
            log.info("Update INIT to PAID", coffeeOrderService.updateState(order, OrderState.PAID));
            log.info("Update PAID to INIT", coffeeOrderService.updateState(order, OrderState.INIT));
        }
    }
}
