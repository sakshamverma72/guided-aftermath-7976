package com.masai.Service;

import com.masai.exception.ApplicationException;
import com.masai.exception.CustomerException;
import com.masai.model.*;
import com.masai.repository.*;
import jakarta.persistence.criteria.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceInterfaceImplementation {


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private OrderBillRepository orderBillRepository;

    //    customer started
    public Customer addCustomer(Customer customer) {
        log.info("adding customer");
        Optional<Customer> opt = customerRepository.findByEmail(customer.getEmail());
        if (opt.isPresent()) {
            if (opt.get().getActive()) {
                throw new CustomerException("Already Exists with these credentials...");
            }

            if (!opt.get().getActive()) {

                opt.get().setName(customer.getName());
                opt.get().setPassword(customer.getPassword());
                opt.get().setActive(true);


                opt.get().getCart().setActive(true);
                cartRepository.save(opt.get().getCart());


                opt.get().setActive(true);
                return customerRepository.save(opt.get());

            }
        }


        if (customer == null) throw new CustomerException("Fields don't have any data");
//		admin.setPassword(pass.enCode(admin.getPassword()));


        Cart cart = new Cart();
        customer.setCart(cart);
        cart.setCustomer(customer);


        return customerRepository.save(customer);


    }


    public Customer updateCustomer(Integer customerId, Customer customer) {
        log.info("Updating customer");
        Optional<Customer> opt = customerRepository.findById(customerId);
        if (opt.isEmpty() || !opt.get().getActive()) {
            throw new CustomerException("No customer found");
        }

        if (customer == null) throw new CustomerException("Fields don't have any data");
        Customer c = opt.get();

        c.setName(customer.getName());
        c.setPassword(customer.getPassword());
        c.setEmail(customer.getEmail());

        return customerRepository.save(c);
    }

    public Customer deleteCustomer(Integer customerId) {
        log.info("deleting customer");
        Optional<Customer> opt = customerRepository.findById(customerId);
        if (opt.isEmpty() || !opt.get().getActive()) {
            throw new CustomerException("No Customer Found with these Details...");
        }


        opt.get().getCart().setActive(false);
        cartRepository.save(opt.get().getCart());

        opt.get().setActive(false);
        return customerRepository.save(opt.get());

    }

//customer finished


//    cart started


    public Cart addOrdersToCart(Integer customerId, Integer orderId) {
        log.info("adding orders to the cart");
        Optional<Customer> opt1 = customerRepository.findById(customerId);


        Optional<Orders> opt2 = orderRepository.findById(orderId);


        if (opt1.isEmpty() || !opt1.get().getActive()) {
            throw new CustomerException("No Customer Found with these Details...");
        }

        if (opt2.isEmpty()) {
            throw new CustomerException("No Orders Found with these Details...");
        }

        Customer c = opt1.get();

        Orders o = opt2.get();
        o.setCart(c.getCart());
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(o);

        c.getCart().setOrders(ordersList);
        customerRepository.save(c);
        return cartRepository.save(c.getCart());

    }


    public Cart deleteOrdersFromCart(Integer customerId, Integer orderId) {
        log.info("removing orders from the cart");
        Optional<Customer> opt1 = customerRepository.findById(customerId);


        Optional<Orders> opt2 = orderRepository.findById(orderId);


        if (opt1.isEmpty() || !opt1.get().getActive()) {
            throw new CustomerException("No Customer Found with these Details...");
        }

        if (opt2.isEmpty()) {
            throw new CustomerException("No Orders Found with these Details...");
        }

        Customer c = opt1.get();

        Orders o = opt2.get();

        Cart cart = c.getCart();
        List<Orders> ordersList = cart.getOrders();

        if (ordersList.isEmpty()) throw new CustomerException("No any Orders found");

        Integer count = 0;

        for (Orders or : ordersList) {
            count++;
            if (or.getOrderId() == orderId) {
                break;
            }
        }

        ordersList.remove(count-1);

        o.setCart(null);
        orderRepository.save(o);
        Cart cart1 = cartRepository.save(cart);
        customerRepository.save(c);

        return cart1;
    }


    //    admin side .......................................
    public List<Orders> showAllOrdersInCart(Integer customerId) {
        log.info("showing all orders in particular customer's cart");
        Optional<Customer> opt = customerRepository.findById(customerId);


        if (opt.isEmpty() || !opt.get().getActive()) {
            throw new CustomerException("No Customer Found with these Details...");
        }


        Customer c = opt.get();


        Cart cart = c.getCart();
        List<Orders> ordersList = cart.getOrders();

        if (ordersList.isEmpty()) throw new CustomerException("No any Orders found");

        return ordersList;

    }


//cart finished


    public Orders createOrder() {
        log.info("creating order");
//        Optional<Orders> opt = orderRepository.findById(order.getOrderId());

//        Orders o = opt.get();
//        if (opt.isPresent()) {
//            if (opt.get().getActive()) {
//                throw new CustomerException("Order already exists");
//            }
//            o.setActive(true);
//
//            return orderRepository.save(o);
//        }

//        if (order == null) throw new CustomerException("orders field cannot be null");

        Orders order = new Orders();
        return orderRepository.save(order);
    }


    //    orders started
    public Orders addProductInOrder(Integer orderId, Integer productId) {
        log.info("adding product into order");

        Optional<Product> opt1 = productRepository.findById(productId);

        Optional<Orders> opt2 = orderRepository.findById(orderId);


        if (opt1.isEmpty()) {
            throw new CustomerException("No Product Found with given Id");
        }


        if (!opt1.get().getActive() || !opt1.get().getAvailablility()) {
            throw new CustomerException("Product is not available ");
        }

        if (opt2.isEmpty()) {
            throw new CustomerException("No Orders Found with these Details...");
        }

        if (!opt2.get().getActive()) {
            throw new CustomerException("Order is not available ");
        }

        Product p = opt1.get();


        Orders o = opt2.get();


        List<Product> pList = o.getProducts();

//        if(pList.isEmpty()){
//            List<Product> productList = new ArrayList<>();
//
//
//            productList.add(p);
//            o.setProducts(productList);
//
//            p.setOrder(o);
//
//            productRepository.save(p);
//            return orderRepository.save(o);
//
//        }


        List<Product> productList = new ArrayList<>();

        productList.add(p);
        p.setOrder(o);
        o.setProducts(productList);

        productRepository.save(p);
        return orderRepository.save(o);


    }


    public Orders deleteProductFromOrders(Integer orderId, Integer productId) {
        log.info("removing product from the orders");
        Optional<Product> opt1 = productRepository.findById(productId);


        Optional<Orders> opt2 = orderRepository.findById(orderId);


        if (opt1.isEmpty()) {
            throw new CustomerException("No Product Found with these Details...");
        }

        if (opt2.isEmpty()) {
            throw new CustomerException("No Orders Found with these Details...");
        }

        Product p = opt1.get();

        Orders o = opt2.get();


        List<Product> productList = o.getProducts();

        if (productList.isEmpty()) throw new CustomerException("No any products found");

        Integer count = 0;
        for (Product or : productList) {
            count++;
            if (or.getProductId() == productId) {
                break;

            }
        }

        p.setOrder(null);
        productList.remove(count-1);

        return orderRepository.save(o);

    }


    public Orders deleteOrders(Integer orderId) {
        log.info("deleting order");


        Optional<Orders> opt = orderRepository.findById(orderId);


        if (opt.isEmpty()) {
            throw new CustomerException("No order found with these Details...");
        }


        Orders o = opt.get();

        if (!o.getActive()) throw new CustomerException("order already deleted");

        o.setActive(false);
        o.getOrderBill().setActive(false);
        orderBillRepository.save(o.getOrderBill());

        return orderRepository.save(o);

    }


//orders finished


    public OrderBill addOrderBill(Integer orderId) {
        log.info("adding order bill");

        Optional<Orders> opt = orderRepository.findById(orderId);


        if (opt.isEmpty()) {
            throw new CustomerException("No Order Found with these Details...");
        }
        if (!opt.get().getActive()) {
            throw new CustomerException("order is not present ");
        }


        List<OrderBill> orderBillList = orderBillRepository.findAll();
        OrderBill orderBill = null;

        Optional<OrderBill> orderBillOpt = orderBillRepository.findById(orderId);

        if(orderBillOpt.isEmpty()){
            orderBill = new OrderBill();
        }
        else{
            orderBill = orderBillOpt.get();
        }

        Orders o = opt.get();

        List<Product> productList = o.getProducts();

        Integer count = 0;
        Double sum = 0.00;
        for (Product p : productList) {
            if (p.getActive()) {
                count++;
                sum += p.getPrice();
            }

        }



        orderBill.setOrders(o);
        orderBill.setTotalPrice(sum);
        orderBill.setTotalProductCount(count);


        return orderBillRepository.save(orderBill);
    }


    public List<OrderBill> showAllOrderBill(Integer customerId) {
        log.info("fetching all order bill");

        Optional<Customer> opt = customerRepository.findById(customerId);


        if (opt.isEmpty() || !opt.get().getActive()) {
            throw new CustomerException("No customer Found with these Details...");
        }


        Customer c = opt.get();

        List<Orders> ordersList = c.getCart().getOrders();


        List<OrderBill> orderBillList = new ArrayList<OrderBill>();


        for (Orders order : ordersList) {
            if (order.getActive()) {
                orderBillList.add(order.getOrderBill());
            }
        }

        for(OrderBill orderBill : orderBillList){

        }
        return orderBillList;
    }


    public Boolean customerLogin(String email, String password) {

        log.info("customer login");
        Optional<Customer> opt = customerRepository.findByEmail(email);
        if (opt.isPresent() && opt.get().getActive()) {
            return true;
        }

        return false;
    }






//    helping

    public List<Orders> getOrderList(){
        return orderRepository.findAll();
    }
}
