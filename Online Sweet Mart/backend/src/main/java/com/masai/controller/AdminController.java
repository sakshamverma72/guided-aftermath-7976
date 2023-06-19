package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Service.AdminServicesInterfaceImplimentation;
import com.masai.exception.ApplicationException;
import com.masai.model.Admin;
import com.masai.model.Category;
import com.masai.model.Customer;
import com.masai.model.Orders;
import com.masai.model.Product;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins= "*")
public class AdminController {
	@Autowired
	private AdminServicesInterfaceImplimentation adminServices;
	
	
//	:::GETTING:::
	
	@GetMapping("/admin/admins")
	public ResponseEntity<List<Admin>> getAllAdmin() throws ApplicationException{
		log.info("Admin is fetching Admins");
		List<Admin>admins=adminServices.getAllAdmin();
		return new ResponseEntity<List<Admin>>(admins,HttpStatus.OK);
	}
	@GetMapping("/admin/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() throws ApplicationException{
		log.info("Admin is fetching customers");
		List<Customer>customers=adminServices.getAllCustomers();
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
	@GetMapping("/admin/customer/{email}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable("email") String email) throws ApplicationException{
		log.info("Admin is fetching customer by email");
		Customer customer=adminServices.getCustomerByEmail(email);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	@GetMapping("/admin/categories")
	public ResponseEntity<List<Category>> getAllCategory() throws ApplicationException{
		log.info("Admin is fetching categories");
		List<Category>categories=adminServices.getAllCategory();
		return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
	}
	@GetMapping("/admin/products")
	public ResponseEntity<List<Product>> getAllProducts() throws ApplicationException{
		log.info("Admin is fetching categories");
		List<Product>products=adminServices.getAllProduct();
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	@GetMapping("/admin/orders")
	public ResponseEntity<List<Orders>> getAllOrders() throws ApplicationException{
		log.info("Admin is fetching orders");
		List<Orders>orders=adminServices.getAllOrders();
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}
	@GetMapping("/admin/customer/{customerId}/orders")
	public ResponseEntity<List<Orders>> getAllOrdersOfCustomer(@PathVariable("customerId") Integer customerId ) throws ApplicationException{
		log.info("Admin is fetching orders of a customer");
		List<Orders>orders=adminServices.getAllOrdersOfCustomer(customerId);
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}
	
	
	
//	:::ORDER:::
	
	@DeleteMapping("/admin/delete/order/{orderId}")
	public ResponseEntity<Orders> deleteOrder(@PathVariable("orderId") Integer orderId ) throws ApplicationException{
		log.info("Admin is Deleting orders");
		Orders order = adminServices.deleteOrder(orderId);
		return new ResponseEntity<Orders>(order,HttpStatus.OK);
	}
	
	
	
//	:::ADMIN:::
	
	@PostMapping("/admin/add/admins")
	public ResponseEntity<Admin> addAdmin(@RequestBody @Valid Admin admin) throws ApplicationException{
		log.info("Admin is adding another Admin");
		Admin adminn=adminServices.addAdmin(admin);
		return new ResponseEntity<Admin>(adminn,HttpStatus.ACCEPTED);
	}
	@PutMapping("/admin/update/admin/{adminId}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable("adminId") Integer adminId,@RequestBody @Valid Admin admin) throws ApplicationException{
		log.info("Admin is Deleteing another Admin");
		Admin adminn=adminServices.updateAdmin(adminId,admin);
		return new ResponseEntity<Admin>(adminn,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/admin/delete/admin/{adminId}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable("adminId") Integer adminId) throws ApplicationException{
		log.info("Admin is Deleteing another Admin");
		Admin admin=adminServices.deleteAdmin(adminId);
		return new ResponseEntity<Admin>(admin,HttpStatus.OK);
	}
	
	
	
//	:::PRODUTC:::
	
	@PostMapping("/admin/add/category/{categoryId}/products")
	public ResponseEntity<Product> addProduct(@PathVariable("categoryId") Integer cateId,@RequestBody @Valid Product product) throws ApplicationException{
		log.info("Admin is adding another Product");
		Product productt = adminServices.addProduct(cateId,product);
		return new ResponseEntity<Product>(productt,HttpStatus.ACCEPTED);
	}
	@PutMapping("/admin/update/product/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable("productId") Integer productId,@RequestBody @Valid Product product) throws ApplicationException{
		log.info("Admin is Updating a Product");
		Product productt = adminServices.updateProduct(productId,product);
		return new ResponseEntity<Product>(productt,HttpStatus.OK);
	}
	@DeleteMapping("/admin/delete/product/{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Integer productId) throws ApplicationException{
		log.info("Admin is Deleteing a Product");
		Product productt = adminServices.deleteProduct(productId);
		return new ResponseEntity<Product>(productt,HttpStatus.OK);
	}
	
	
	
//	:::CATEGORY:::
	
	@PostMapping("/admin/add/categories")
	public ResponseEntity<Category> addCategory(@RequestBody @Valid Category category) throws ApplicationException{
		log.info("Admin is adding another Category");
		Category categoryy = adminServices.addCategory(category);
		return new ResponseEntity<Category>(categoryy,HttpStatus.ACCEPTED);
	}
	@PutMapping("/admin/update/category/{categoryId}")
	public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") Integer categoryId,@RequestBody @Valid Category category) throws ApplicationException{
		log.info("Admin is Updating a Category");
		Category categoryy = adminServices.updateCategory(categoryId,category);
		return new ResponseEntity<Category>(categoryy,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/admin/delete/category/{categoryId}")
	public ResponseEntity<Category> deleteCategory(@PathVariable("categoryId") Integer categoryId) throws ApplicationException{
		log.info("Admin is Deleteing a Category");
		Category categoryy = adminServices.deleteCategory(categoryId);
		return new ResponseEntity<Category>(categoryy,HttpStatus.OK);
	}
	
	@GetMapping("/admin/category")
	public ResponseEntity<List<Category>> getAllCategories() throws ApplicationException{
		log.info("Admin is Getting All Categories");
		List<Category> category=adminServices.getListOfCategory();
		return new ResponseEntity<>(category,HttpStatus.OK);
	}
	
	
//	:::CUSTOMER:::
	@PostMapping("/admin/updaterole/customer/{customerId}")
	public ResponseEntity<Customer> ChangeRoleCustomer(@PathVariable("customerId") Integer customerId , @RequestBody Customer cutomerr) throws ApplicationException{
		log.info("Admin is Changing role of a Customer");
		Customer customer = adminServices.ChangeRoleCustomer(customerId,cutomerr);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	@DeleteMapping("/admin/delete/customer/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerId") Integer customerId) throws ApplicationException{
		log.info("Admin is Deleteing a Customer");
		Customer customer = adminServices.deleteCustomer(customerId);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	
//	:::SIGNIN:::
	
//	@GetMapping("/signIn")
//	public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth){
//		 Admin admin= adminServices.getDetails(auth.getName());
//		 return new ResponseEntity<>(admin.getName()+"Logged In Successfully", HttpStatus.ACCEPTED);	
//	}
	
	
	
}
