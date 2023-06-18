package com.masai.Service;

import java.util.List;

import com.masai.exception.ApplicationException;
import com.masai.model.Admin;
import com.masai.model.Category;
import com.masai.model.Customer;
import com.masai.model.Orders;
import com.masai.model.Product;


public interface AdminServicesInterface {

	Admin addAdmin(Admin admin) throws ApplicationException;

	Product addProduct(int cateId,Product product) throws ApplicationException;

	Category addCategory(Category category) throws ApplicationException;

	Admin deleteAdmin(Integer adminId) throws ApplicationException;

	Admin updateAdmin(Integer adminId, Admin admin) throws ApplicationException;

	Customer deleteCustomer(Integer customerId) throws ApplicationException;

	Product updateProduct(Integer productId, Product product) throws ApplicationException;

	Product deleteProduct(Integer productId) throws ApplicationException;

	Category updateCategory(Integer categoryId, Category category) throws ApplicationException;

	List<Admin> getAllAdmin() throws ApplicationException;

	List<Product> getAllProduct() throws ApplicationException;

	List<Category> getAllCategory() throws ApplicationException;

	List<Product> getAllOrders() throws ApplicationException;

	List<Product> getAllOrdersOfCustomer(Integer customerId) throws ApplicationException;

	Orders deleteOrder(Integer orderId) throws ApplicationException;

	List<Customer> getAllCustomers() throws ApplicationException;

	Customer getCustomerByEmail(String email) throws ApplicationException;

	Category deleteCategory(Integer categoryId) throws ApplicationException;

	Customer ChangeRoleCustomer(Integer customerId, Customer customer) throws ApplicationException;

//	Admin getCustomerDetailsByEmail(String email) throws ApplicationException;

}
