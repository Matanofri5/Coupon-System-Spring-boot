package spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.CouponClientFacade;
import spring.Session;
import spring.exceptions.CompanyAlreadyExistsException;
import spring.exceptions.CustomerAlreadyExistsException;
import spring.exceptions.DoesntExistException;
import spring.models.ClientType;
import spring.models.Company;
import spring.models.Coupon;
import spring.models.Customer;
import spring.models.Income;
import spring.repository.CompanyRepository;
import spring.repository.CouponRepository;
import spring.repository.CustomerRepository;
import spring.repository.IncomeRepository;

@Service
public class AdminServiceImpl implements AdminService, CouponClientFacade {

	@Autowired
	private CouponRepository couponRepository;
		
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private IncomeRepository incomeRepository;
	
	public AdminServiceImpl() {
	}
	
	
	
	@Override
	public boolean checkIfCompanyNameAlreadyExists(String companyName) {
		if (companyRepository.findByCompanyName(companyName) != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public Company createCompany(Company company) throws CompanyAlreadyExistsException  {
		if (checkIfCompanyNameAlreadyExists(company.getCompanyName())==false) {
			companyRepository.save(company);
		}else {
			throw new CompanyAlreadyExistsException("The company " + company.getCompanyName() +" already exist, please try another name");
		}
		return company;
    }
	
	@Override
	public void deleteCompany(long id) {
		try {
			if (!companyRepository.existsById(id)) {
				throw new DoesntExistException("This company doesn't exist, please try another one");
			}
			long i = 0;
			Company company = companyRepository.getOne(id);
			List<Coupon> coupons = new ArrayList<Coupon>(company.getCoupons());
			companyRepository.deleteById(id);
			for(i=0; i<coupons.size(); i++) {
				couponRepository.delete(coupons.get((int) i));
			}
		}catch (Exception e) {
			System.out.println("Failed to delete company " + id + e.getMessage());
		}
	}
	
	@Override
	public void updateCompany(Company company, String password, String email) {
		company.setPassword(password);
		company.setEmail(email);
		companyRepository.save(company);
	}
	
	@Override
	public List<Company> allCompanies(){
		return companyRepository.findAll();
	}
	
	@Override
	public Company companyById(long id) {
		return companyRepository.findById(id).get();
	}
	
	
	
	@Override
	public boolean checkIfCustomerNameAlreadyExists(String customerName) {
		if (customerRepository.findByCustomerName(customerName) != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public Customer createCustomer(Customer customer) throws CustomerAlreadyExistsException  {
		if (checkIfCustomerNameAlreadyExists(customer.getCustomerName())==false) {
			customerRepository.save(customer);
		}else {
			throw new CustomerAlreadyExistsException("The customer " + customer.getCustomerName() +" already exist, please try another name");
		}
		return customer;
	}
	
	@Override
	public void updateCustomer(Customer customer, String password) {
		customer.setPassword(password);
		customerRepository.save(customer);
	}
	
	@Override
	public void deleteCustomer(long id) {
		try {
			if (!customerRepository.existsById(id)) {
				throw new DoesntExistException("This customer doesn't exist, please try another one");
			}
			long i = 0;
			Customer customer = customerRepository.getOne(id);
			List<Coupon> coupons = new ArrayList<Coupon>(customer.getCoupons());
			customerRepository.deleteById(id);
			for(i=0; i<coupons.size(); i++) {
				couponRepository.delete(coupons.get((int) i));
			}
		}catch (Exception e) {
			System.out.println("Failed to delete customer " + id + e.getMessage());
		}
	}
	
	@Override
	public List<Customer> allCustomers(){
		return customerRepository.findAll();
	}
	
	@Override
	public Customer customerById(long id) {
		return customerRepository.findById(id).get();
		
	}
	
	@Override
	public List<Income> allIncome(){
		return incomeRepository.findAll();
	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return new AdminServiceImpl();
	}

	@Override
	public List<Coupon> allCoupons(){
		return couponRepository.findAll();
	}
}
