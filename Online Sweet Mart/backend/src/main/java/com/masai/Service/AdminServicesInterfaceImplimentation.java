package com.masai.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.exception.ApplicationException;
import com.masai.model.Admin;
import com.masai.model.Category;
import com.masai.model.Customer;
import com.masai.model.Orders;
import com.masai.model.Product;
import com.masai.repository.AdminRepository;
import com.masai.repository.CategoryRepository;
import com.masai.repository.CustomerRepository;
import com.masai.repository.OrdersRepository;
import com.masai.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServicesInterfaceImplimentation implements AdminServicesInterface {
	@Autowired
	private AdminRepository aRepo;
	@Autowired
	private ProductRepository pRepo;
	@Autowired
	private CategoryRepository cRepo;
	@Autowired
	private CustomerRepository cuRepo;
	@Autowired
	private OrdersRepository oRepo;
//	@Autowired
//	private PasswordEncoder pass;
	
	@Override
	public Admin addAdmin(Admin admin) throws ApplicationException {
		log.info("Admin is Adding customer in Service Layer");
		Optional<Admin> opt = aRepo.findById(admin.getAdminId());
		if(opt.isPresent() && opt.get().getActive()) {
			throw new ApplicationException("Already Exists with these credentials...");
		}
		if(admin.getAdminId()!=0) {
			throw new ApplicationException("Id is AutoGenerated Type Don't mention it...");
		}
		if(admin==null) throw new ApplicationException("Fields don't have any data");
//		admin.setPassword(pass.enCode(admin.getPassword()));
		aRepo.save(admin);
		return admin;
		
	}
	@Override
	public Admin updateAdmin(Integer adminId,Admin admin) throws ApplicationException{
		log.info("Admin is Updating an Admin Details in Service Layer");
		Optional<Admin> opt = aRepo.findById(adminId);
		if(opt.isEmpty() || !opt.get().getActive()) {
			throw new ApplicationException("No Admin Found with this ID...");
		}
		if(admin.getAdminId()!=0) {
			throw new ApplicationException("Id is AutoGenerated Type Don't mention it...");
		}
		if(admin==null) throw new ApplicationException("Fields don't have any data");
		opt.get().setName(admin.getName());
		opt.get().setEmail(admin.getEmail());
//		opt.get().setPassword(pass.enCode(admin.getPassword()));
		opt.get().setPassword(admin.getPassword());
		aRepo.save(opt.get());
		return opt.get();
	}
	@Override
	public Admin deleteAdmin(Integer adminId)throws ApplicationException {
		log.info("Admin is Deleting an Admin in Service Layer");
		Optional<Admin> opt = aRepo.findById(adminId);
		if(opt.isEmpty()  ||  !opt.get().getActive()) {
			throw new ApplicationException("No Admin Found with these Details...");
		}
		opt.get().setActive(false);
		aRepo.save(opt.get());
		return opt.get();
	}
	@Override
	public Customer deleteCustomer(Integer customerId) throws ApplicationException {
		log.info("Admin is Deleting a Customer in Service Layer");
		Optional<Customer> opt = cuRepo.findById(customerId);
		if(opt.isEmpty() || !opt.get().getActive()) {
			throw new ApplicationException("No Customer Found with these Details...");
		}
		opt.get().setActive(false);
		cuRepo.save(opt.get());
		return opt.get();
	}
	
	
	
	
	@Override
	public Product addProduct(int cateId,Product product) throws ApplicationException {
		log.info("Admin is Adding Product in Service Layer");
		Optional<Product> opt = pRepo.findById(product.getProductId());
		if(opt.isPresent() && opt.get().getActive()) {
			throw new ApplicationException("Already Exists with these Details...");
		}
		if(product.getProductId()!=0) {
			throw new ApplicationException("Id is AutoGenerated Type Don't mention it...");
		}
		if(product==null) throw new ApplicationException("Fields don't have any data");
		Optional<Category> optt = cRepo.findById(cateId);
		if(optt.isEmpty() || !optt.get().getActive()) {
			throw new ApplicationException("No Category Found with this ID...");
		}
		optt.get().getProducts().add(product);
		product.setCategory(optt.get());
		pRepo.save(product);
		return product;
	}
	@Override
	public Product updateProduct(Integer productId, Product product) throws ApplicationException {
		log.info("Admin is Updating Product in Service Layer");
		Optional<Product> opt = pRepo.findById(productId);
		if(opt.isEmpty() || !opt.get().getActive()){
			throw new ApplicationException("No Product Found with this ID...");
		}
		if(product.getProductId()!=0) {
			throw new ApplicationException("Id is AutoGenerated Type Don't mention it...");
		}
		if(product==null) throw new ApplicationException("Fields don't have any data");
		opt.get().setAvailablility(product.getAvailablility());
		opt.get().setDescription(product.getDescription());
		opt.get().setImageUrl(product.getImageUrl());
		opt.get().setName(product.getName());
		opt.get().setPrice(product.getPrice());
//		opt.get().setCategory(opt.get().getCategory());
//		opt.get().setActive(opt.get().getActive());
//		opt.get().setOrder(opt.get().getOrder());
		pRepo.save(opt.get());
		return opt.get();
	}
	@Override
	public Product deleteProduct(Integer productId) throws ApplicationException{
		log.info("Admin is Deleting Product in Service Layer");
		Optional<Product> opt = pRepo.findById(productId);
		if(opt.isEmpty() || !opt.get().getActive()){
			throw new ApplicationException("No Product Found with this ID...");
		}
		opt.get().setActive(false);
		pRepo.save(opt.get());
		return opt.get();
	}
	
	
	
	@Override
	public Category addCategory(Category category) throws ApplicationException{
		log.info("Admin is Adding Category in Service Layer");
		Optional<Category> opt = cRepo.findById(category.getCategoryId());
		if(opt.isPresent() && opt.get().getActive()) {
			throw new ApplicationException("Already Exists with these Details...");
		}
		if(category.getCategoryId()!=0) {
			throw new ApplicationException("Id is AutoGenerated Type Don't mention it...");
		}
		if(category==null) throw new ApplicationException("Fields don't have any data");
		cRepo.save(category);
		return category;
	}
	@Override
	public Category deleteCategory(Integer categoryId) throws ApplicationException{
		log.info("Admin is Deleting a category in Service Layer");
		Optional<Category> opt = cRepo.findById(categoryId);
		if(opt.isEmpty() || !opt.get().getActive()) {
			throw new ApplicationException("No Category Found with this ID...");
		}
		opt.get().setActive(false);
		for(Product product:opt.get().getProducts()) {
			product.setCategory(null);
			product.setActive(false);
		}
		cRepo.save(opt.get());
		return opt.get();
	}
	@Override
	public Category updateCategory(Integer categoryId, Category category)throws ApplicationException{
		log.info("Admin is Updating a category in Service Layer");
		Optional<Category> opt = cRepo.findById(categoryId);
		if(opt.isEmpty() || !opt.get().getActive()) {
			throw new ApplicationException("No Category Found with this ID...");
		}
		if(category.getCategoryId()!=0) {
			throw new ApplicationException("Id is AutoGenerated Type Don't mention it...");
		}
		if(category==null) throw new ApplicationException("Fields don't have any data");
		opt.get().setName(category.getName());
//		opt.get().setActive(opt.get().getActive());
//		opt.get().setProducts(opt.get().getProducts());
		cRepo.save(opt.get());
		return opt.get();
	}
	@Override
	public List<Admin> getAllAdmin()throws ApplicationException{
		List<Admin>admins = aRepo.findAll();
		if(admins.size()==0) {
			throw new ApplicationException("No Admin Found");
		}
		ArrayList<Admin>ans = new ArrayList<>();
		for(Admin ad:admins) {
			if(ad.getActive()) {
				ans.add(ad);
			}
		}
		if(ans.size()==0) {
			throw new ApplicationException("No Admin Found");
		}
		return ans;
	}
	@Override
	public List<Product> getAllProduct()throws ApplicationException{
		List<Product>products = pRepo.findAll();
		if(products.size()==0) {
			throw new ApplicationException("No Product Found");
		}
		ArrayList<Product>ans = new ArrayList<>();
		for(Product ad:products) {
			if(ad.getActive()) {
				ans.add(ad);
			}
		}
		if(ans.size()==0) {
			throw new ApplicationException("No Product Found");
		}
		return ans;
	}
	@Override
	public List<Category> getAllCategory()throws ApplicationException{
		List<Category>categories = cRepo.findAll();
		if(categories.size()==0) {
			throw new ApplicationException("No Category Found");
		}
		ArrayList<Category>ans = new ArrayList<>();
		for(Category ad:categories) {
			if(ad.getActive()) {
				ans.add(ad);
			}
		}
		if(ans.size()==0) {
			throw new ApplicationException("No Category Found");
		}
		return ans;
	}
	@Override
	public List<Product> getAllOrders() throws ApplicationException{
		
		List<Orders>orders = oRepo.findAll();
		if(orders.size()==0) {
			throw new ApplicationException("No Order Found");
		}
		ArrayList<Product>ans = new ArrayList<>();
		for(Orders ad:orders) {
			for(Product pro: ad.getProducts()) {
				ans.add(pro);
			}
		}
		if(ans.size()==0) {
			throw new ApplicationException("No ordered Product Found");
		}
		return ans;
	}
	@Override
	public List<Product> getAllOrdersOfCustomer(Integer customerId) throws ApplicationException{
		// TODO Auto-generated method stub
		Optional<Customer> opt = cuRepo.findById(customerId);
		if(opt.isEmpty() || !opt.get().getActive()) {
			throw new ApplicationException("No Customer Found with these Details...");
		}
		if(opt.get().getCart()==null) {
			throw new ApplicationException("Cart of customer don't have any Order...");
		}
		List<Orders>orders = opt.get().getCart().getOrders();
		if(orders.size()==0) {
			throw new ApplicationException("No Order Found");
		}
		ArrayList<Product>ans = new ArrayList<>();
		for(Orders ad:orders) {
			for(Product pro: ad.getProducts()) {
				ans.add(pro);
			}
		}
		if(ans.size()==0) {
			throw new ApplicationException("No Product Found in customer's Orders");
		}
		return ans;
	}
	@Override
	public Orders deleteOrder(Integer orderId)throws ApplicationException{
		Optional<Orders>opt = oRepo.findById(orderId);
		if(opt.isEmpty() || !opt.get().getActive()) {
			throw new ApplicationException("No Order Found with this Id...");
		}
		opt.get().setActive(false);
		oRepo.save(opt.get());
		return opt.get();
	}
	
//	@Override
//	public Admin getDetails(String email)throws ApplicationException {
//		
//		return aRepo.findByEmail(email).orElseThrow(() -> new ApplicationException("Customer Not found with Email: "+email));
//	}
	
	@Override
	public List<Customer> getAllCustomers()throws ApplicationException {
		List<Customer>customers = cuRepo.findAll();
		if(customers.size()==0) {
			throw new ApplicationException("No Customer Found");
		}
		return customers;
	}
	
	@Override
	public Customer getCustomerByEmail(String email)throws ApplicationException {
		Optional<Customer> customer = cuRepo.findByEmail(email);
		if(customer.isEmpty()) {
			throw new ApplicationException("No Customer Found");
		}
		return customer.get();
	}
	@Override
	public Customer ChangeRoleCustomer(Integer customerId, Customer cust) throws ApplicationException {
		Optional<Customer> customerr = cuRepo.findById(customerId);
		if(customerr.isEmpty() || !customerr.get().getActive()) {
			throw new ApplicationException("No Customer Found");
		}
//		if(!customer.getRole().toUpperCase().equals("ADMIN") && !customer.getRole().toUpperCase().equals("CUSTOMER")) {
//			System.out.println(customer.getRole().toUpperCase());
//			throw new ApplicationException("Role isn't a Valid one");
//		}
		
//		System.out.println(customerr.get().getRole());
//		customerr.get().setRole(null);
//		cuRepo.save(customerr.get());
//		System.out.println(cust.getRole());
//		
		customerr.get().setRole(cust.getRole().toUpperCase());
		System.out.println(customerr.get().getRole());
		cuRepo.save(customerr.get());
		return customerr.get();

	}
	@Override
	public List<Category> getListOfCategory() throws ApplicationException {
		List<Category> cList=cRepo.findAll();
		if(cList.size()==0) {
			throw new ApplicationException("No Category Found");
		}
		return cList;

	}
	
}
