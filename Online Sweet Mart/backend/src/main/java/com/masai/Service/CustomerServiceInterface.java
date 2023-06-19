package com.masai.Service;

import com.masai.exception.CustomerException;
import com.masai.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CustomerServiceInterface {
    public Customer addCustomer(Customer customer) throws CustomerException;

    public Customer updateCustomer(Integer customerId, Customer customer)throws CustomerException;

    public Customer deleteCustomer(Integer customerId)throws CustomerException;

    public Cart addOrdersToCart(Integer customerId, Integer orderId)throws CustomerException;

    public Cart deleteOrdersFromCart(Integer customerId, Integer orderId)throws CustomerException;

    public List<Orders> showAllOrdersInCart(Integer customerId)throws CustomerException;

    public Orders createOrder()throws CustomerException;

    public Orders addProductInOrder(Integer orderId, Integer productId)throws CustomerException;

    public Orders deleteProductFromOrders(Integer orderId, Integer productId)throws CustomerException;

    public Orders deleteOrders(Integer orderId)throws CustomerException;

    public OrderBill addOrderBill(Integer orderId)throws CustomerException;

    public List<OrderBill> showAllOrderBill(Integer customerId)throws CustomerException;

    public Boolean customerLogin(String email, String password)throws CustomerException;
}
