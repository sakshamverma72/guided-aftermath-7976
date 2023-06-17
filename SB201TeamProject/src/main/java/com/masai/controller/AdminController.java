package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.masai.model.Product;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
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
	public ResponseEntity<List<Product>> getAllOrders() throws ApplicationException{
		log.info("Admin is fetching orders");
		List<Product>orders=adminServices.getAllOrders();
		return new ResponseEntity<List<Product>>(orders,HttpStatus.OK);
	}
	@GetMapping("/admin/customer/{customerId}/orders")
	public ResponseEntity<List<Product>> getAllOrdersOfCustomer(@PathVariable("customerId") Integer customerId ) throws ApplicationException{
		log.info("Admin is fetching orders of a customer");
		List<Product>orders=adminServices.getAllOrdersOfCustomer(customerId);
		return new ResponseEntity<List<Product>>(orders,HttpStatus.OK);
	}
	
	
	
//	:::ORDER:::
	
	@DeleteMapping("/admin/delete/order/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Integer orderId ) throws ApplicationException{
		log.info("Admin is Deleting orders");
		adminServices.deleteOrder(orderId);
		return new ResponseEntity<String>("Order has been deleted",HttpStatus.OK);
	}
	
	
	
//	:::ADMIN:::
	
	@PostMapping("/admin/add/admins")
	public ResponseEntity<String> addAdmin(@RequestBody @Valid Admin admin) throws ApplicationException{
		log.info("Admin is adding another Admin");
		adminServices.addAdmin(admin);
		return new ResponseEntity<String>("Added Succefully...",HttpStatus.ACCEPTED);
	}
	@PutMapping("/admin/update/admin/{adminId}")
	public ResponseEntity<String> updateAdmin(@PathVariable("adminId") Integer adminId,@RequestBody @Valid Admin admin) throws ApplicationException{
		log.info("Admin is Deleteing another Admin");
		adminServices.updateAdmin(adminId,admin);
		return new ResponseEntity<String>("Updated Succefully...",HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/admin/delete/admin/{adminId}")
	public ResponseEntity<String> deleteAdmin(@PathVariable("adminId") Integer adminId) throws ApplicationException{
		log.info("Admin is Deleteing another Admin");
		adminServices.deleteAdmin(adminId);
		return new ResponseEntity<String>("Deleted Succefully...",HttpStatus.OK);
	}
	
	
	
//	:::PRODUTC:::
	
	@PostMapping("/admin/add/category/{categoryId}/products")
	public ResponseEntity<String> addProduct(@PathVariable("categoryId") Integer cateId,@RequestBody @Valid Product product) throws ApplicationException{
		log.info("Admin is adding another Product");
		adminServices.addProduct(cateId,product);
		return new ResponseEntity<String>("Added Succefully...",HttpStatus.ACCEPTED);
	}
	@PutMapping("/admin/update/product/{productId}")
	public ResponseEntity<String> updateProduct(@PathVariable("productId") Integer productId,@RequestBody @Valid Product product) throws ApplicationException{
		log.info("Admin is Updating a Product");
		adminServices.updateProduct(productId,product);
		return new ResponseEntity<String>("updated Succefully...",HttpStatus.OK);
	}
	@DeleteMapping("/admin/delete/product/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") Integer productId) throws ApplicationException{
		log.info("Admin is Deleteing a Product");
		adminServices.deleteProduct(productId);
		return new ResponseEntity<String>("Deleted Succefully...",HttpStatus.OK);
	}
	
	
	
//	:::CATEGORY:::
	
	@PostMapping("/admin/add/categories")
	public ResponseEntity<String> addCategory(@RequestBody @Valid Category category) throws ApplicationException{
		log.info("Admin is adding another Category");
		adminServices.addCategory(category);
		return new ResponseEntity<String>("Added Succefully...",HttpStatus.ACCEPTED);
	}
	@PutMapping("/admin/update/category/{categoryId}")
	public ResponseEntity<String> updateCategory(@PathVariable("categoryId") Integer categoryId,@RequestBody @Valid Category category) throws ApplicationException{
		log.info("Admin is Updating a Category");
		adminServices.updateCategory(categoryId,category);
		return new ResponseEntity<String>("updated Succefully...",HttpStatus.OK);
	}
	@DeleteMapping("/admin/delete/category/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Integer categoryId) throws ApplicationException{
		log.info("Admin is Deleteing a Category");
		adminServices.deleteCategory(categoryId);
		return new ResponseEntity<String>("Deleted Succefully...",HttpStatus.OK);
	}
	
	
	
//	:::CUSTOMER:::
	
	@DeleteMapping("/admin/delete/customer/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Integer customerId) throws ApplicationException{
		log.info("Admin is Deleteing a Customer");
		adminServices.deleteCustomer(customerId);
		return new ResponseEntity<String>("Deleted Succefully...",HttpStatus.OK);
	}
	
	
//	:::SIGNIN:::
	
//	@GetMapping("/signIn")
//	public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth){
//		 Admin admin= adminServices.getDetails(auth.getName());
//		 return new ResponseEntity<>(admin.getName()+"Logged In Successfully", HttpStatus.ACCEPTED);	
//	}
	
	
	
}
