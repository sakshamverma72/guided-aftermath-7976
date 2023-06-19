package com.masai.Service;

import java.util.List;

import com.masai.exception.AdminException;
import com.masai.model.Admin;
import com.masai.model.Category;
import com.masai.model.Customer;
import com.masai.model.Orders;
import com.masai.model.Product;


public interface AdminServicesInterface {

	Admin addAdmin(Admin admin) throws AdminException;

	Product addProduct(int cateId,Product product) throws AdminException;

	Category addCategory(Category category) throws AdminException;

	Admin deleteAdmin(Integer adminId) throws AdminException;

	Admin updateAdmin(Integer adminId, Admin admin) throws AdminException;

	Customer deleteCustomer(Integer customerId) throws AdminException;

	Product updateProduct(Integer productId, Product product) throws AdminException;

	Product deleteProduct(Integer productId) throws AdminException;

	Category updateCategory(Integer categoryId, Category category) throws AdminException;

	List<Admin> getAllAdmin() throws AdminException;

	List<Product> getAllProduct() throws AdminException;

	List<Category> getAllCategory() throws AdminException;

	List<Orders> getAllOrders() throws AdminException;

	List<Orders> getAllOrdersOfCustomer(Integer customerId) throws AdminException;

	Orders deleteOrder(Integer orderId) throws AdminException;

	List<Customer> getAllCustomers() throws AdminException;

	Customer getCustomerByEmail(String email) throws AdminException;

	Category deleteCategory(Integer categoryId) throws AdminException;

	Customer ChangeRoleCustomer(Integer customerId, Customer customer) throws AdminException;

	List<Category> getListOfCategory() throws AdminException;


//	Admin getCustomerDetailsByEmail(String email) throws ApplicationException;

}
