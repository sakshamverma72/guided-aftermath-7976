package com.masai.controller;

import com.masai.Service.CustomerServiceInterfaceImplementation;
import com.masai.exception.CustomerException;
import com.masai.model.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerServiceInterfaceImplementation customerService;

    @PostMapping("/customer/signup")
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid Customer customer) {
        log.info("adding customer controller");
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);

    }


    @PutMapping("/customerUpdate/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@Valid @PathVariable Integer customerId, @RequestBody Customer customer) {
        log.info("Updating customer controller");

        return new ResponseEntity<>(customerService.updateCustomer(customerId, customer), HttpStatus.CREATED);
    }


    @DeleteMapping("/customerDelete/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@Valid @PathVariable Integer customerId) {
        log.info("deleting customer controller");
        return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.OK);
    }

//customer finished


//    cart started


    @PutMapping("/customer/cart/addOrder/{customerId}/{orderId}")
    public ResponseEntity<Cart> addOrdersToCart(@Valid @PathVariable Integer customerId, @PathVariable Integer orderId) {
        log.info("adding orders to the cart controller");
        return new ResponseEntity<>(customerService.addOrdersToCart(customerId, orderId), HttpStatus.CREATED);

    }


    @DeleteMapping("/customer/cart/deleteOrder/{customerId}/{orderId}")
    public ResponseEntity<Cart> deleteOrdersFromCart(@Valid @PathVariable Integer customerId, @PathVariable Integer orderId) {
        log.info("removing orders from the cart controller");
        return new ResponseEntity<>(customerService.deleteOrdersFromCart(customerId, orderId), HttpStatus.CREATED);
    }


    //    admin side .......................................

    @GetMapping("/customer/showAllOrdersOfCustomer/{customerId}")
    public ResponseEntity<List<Orders>> showAllOrdersInCart(@Valid @PathVariable Integer customerId) {
        log.info("showing all orders in particular customer's cart controller");
        return new ResponseEntity<>(customerService.showAllOrdersInCart(customerId), HttpStatus.CREATED);

    }


//cart finished

    @PostMapping("/customer/createOrder")
    public ResponseEntity<Orders> createOrder() {
        log.info("creating order controller");
        return new ResponseEntity<>(customerService.createOrder(), HttpStatus.CREATED);
    }


    //    orders started
    @PutMapping("/customer/addProductInOrder/{orderId}/{productId}")
    public ResponseEntity<Orders> addProductInOrder(@Valid @PathVariable Integer orderId, @PathVariable Integer productId) {
        log.info("adding product into order controller");

        return new ResponseEntity<>(customerService.addProductInOrder(orderId, productId), HttpStatus.CREATED);
    }


    @DeleteMapping("/customer/deleteProductFromOrders/{orderId}/{productId}")
    public ResponseEntity<Orders> deleteProductFromOrders(@Valid @PathVariable Integer orderId, @PathVariable Integer productId) {
        log.info("removing product from the orders controller");
        return new ResponseEntity<>(customerService.deleteProductFromOrders(orderId, productId), HttpStatus.CREATED);

    }


    @DeleteMapping("/customer/deleteOrder/{orderId}")
    public ResponseEntity<Orders> deleteOrders(@Valid @PathVariable Integer orderId) {
        log.info("deleting order controller");


        return new ResponseEntity<>(customerService.deleteOrders(orderId), HttpStatus.CREATED);

    }


//orders finished

    @PostMapping("/customer/addOrderBill/{orderId}")
    public ResponseEntity<OrderBill> addOrderBill(@Valid @PathVariable Integer orderId) {
        log.info("adding order bill controller");

        return new ResponseEntity<>(customerService.addOrderBill(orderId), HttpStatus.CREATED);
    }


    @GetMapping("/customer/showAllOrderBill/{customerId}")
    public ResponseEntity<List<OrderBill>> showAllOrderBill(@Valid @PathVariable Integer customerId) {
        log.info("fetching all order bill controller");

        return new ResponseEntity<>(customerService.showAllOrderBill(customerId), HttpStatus.CREATED);
    }

    @GetMapping("/customer/login/{email}/{password}")
    public ResponseEntity<Boolean> customerLogin(String email, String password) {

        log.info("customer login controller");
        return new ResponseEntity<>(customerService.customerLogin(email, password), HttpStatus.CREATED);
    }















    @GetMapping("/customer/allOOrders")
    public ResponseEntity<List<Orders>> getOrderList(){
        return new ResponseEntity<>(customerService.getOrderList(), HttpStatus.CREATED);
    }
}
