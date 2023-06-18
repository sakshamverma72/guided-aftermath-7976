package com.masai.Service;

import java.util.List;

import com.masai.exception.ApplicationException;
import com.masai.model.Admin;
import com.masai.model.Category;
import com.masai.model.Customer;
import com.masai.model.Product;


public interface AdminServicesInterface {

	void addAdmin(Admin admin) throws ApplicationException;

	Product addProduct(int cateId,Product product) throws ApplicationException;

	void addCategory(Category category) throws ApplicationException;

	void deleteAdmin(Integer adminId) throws ApplicationException;

	void updateAdmin(Integer adminId, Admin admin) throws ApplicationException;

	void deleteCustomer(Integer customerId) throws ApplicationException;

	void updateProduct(Integer productId, Product product) throws ApplicationException;

	void deleteProduct(Integer productId) throws ApplicationException;

	void updateCategory(Integer categoryId, Category category) throws ApplicationException;

	List<Admin> getAllAdmin() throws ApplicationException;

	List<Product> getAllProduct() throws ApplicationException;

	List<Category> getAllCategory() throws ApplicationException;

	List<Product> getAllOrders() throws ApplicationException;

	List<Product> getAllOrdersOfCustomer(Integer customerId) throws ApplicationException;

	void deleteOrder(Integer orderId) throws ApplicationException;

	List<Customer> getAllCustomers() throws ApplicationException;

//	Admin getCustomerDetailsByEmail(String email) throws ApplicationException;

}
