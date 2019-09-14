package spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.Session;
import spring.models.Company;
import spring.models.Customer;
import spring.models.Income;
import spring.service.AdminService;
import spring.service.AdminServiceImpl;
import spring.service.IncomeService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private IncomeService incomeService;

	@Autowired
	private Map<String, Session> tokens;

	private Session exists(String token) {
		return LoginController.tokens.get(token);
	}

	@PostMapping("/createCompany/{token}")
	public ResponseEntity<String> createCompany(@RequestBody Company company, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				((AdminServiceImpl) session.getFacade()).createCompany(company);
				return new ResponseEntity<>("company created  " + company, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage() + e.getStackTrace(), HttpStatus.UNAUTHORIZED);
			}
		}
		return null;
	}

	@DeleteMapping("/deleteCompany/{id}/{token}")
	public void deleteCompany(@PathVariable long id, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Company company = null;
				company = adminService.companyById(id);
				if (company != null) {
					((AdminServiceImpl) session.getFacade()).deleteCompany(id);
				}
			} catch (Exception e) {
				System.err.println("Failed to delete company, please insert another id");
			}
		}
	}

	@PostMapping("/updateCompany/{token}")
	public ResponseEntity<String> updateCompany(@PathVariable String token, @RequestParam long id,
			@RequestParam String password, @RequestParam String email) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Company company = null;
				company = adminService.companyById(id);
				if (company != null) {
					((AdminServiceImpl) session.getFacade()).updateCompany(company, password, email);
					return new ResponseEntity<>("company " + company.getCompanyName() + " was updated", HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} catch (Exception e) {
				System.out.println("Failed to update company !!");
			}
		}
		return null;
	}

	@GetMapping("/getAllCompanies/{token}")
	public ResponseEntity<List<Company>> allCompanies(@PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				if (((AdminServiceImpl) session.getFacade()).allCompanies() != null) {
					ResponseEntity<List<Company>> result = new ResponseEntity<List<Company>>(
							adminService.allCompanies(), HttpStatus.OK);
					return result;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@GetMapping("/companyById/{id}/{token}")
	public Company companyById(@PathVariable long id, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((AdminServiceImpl) session.getFacade()).companyById(id);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	
	
	@PostMapping("/createCustomer/{token}")
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				((AdminServiceImpl) session.getFacade()).createCustomer(customer);
				return new ResponseEntity<>("customer created  " + customer, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage() + e.getStackTrace(), HttpStatus.UNAUTHORIZED);
			}
		}
		return null;
	}

	@DeleteMapping("/deleteCustomer/{id}/{token}")
	public void deleteCustomer(@PathVariable long id, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Customer customer = null;
				customer = adminService.customerById(id);
				if (customer != null) {
					((AdminServiceImpl) session.getFacade()).deleteCustomer(id);
				}
			} catch (Exception e) {
				System.err.println("Failed to delete customer, please insert another id");
			}
		}
	}

	@PostMapping("/updateCustomer/{token}")
	public ResponseEntity<String> updateCustomer(@PathVariable String token, @RequestParam long id,
			@RequestParam String password) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Customer customer = null;
				customer = adminService.customerById(id);
				if (customer != null) {
					((AdminServiceImpl) session.getFacade()).updateCustomer(customer, password);
					return new ResponseEntity<>("customer " + customer.getCustomerName() + " was updated", HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} catch (Exception e) {
				System.out.println("Failed to update company !!");
			}
		}
		return null;
	}

	@GetMapping("/getAllCustomers/{token}")
	public ResponseEntity<List<Customer>> allcustomers(@PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				if (((AdminServiceImpl) session.getFacade()).allCustomers() != null) {
					ResponseEntity<List<Customer>> result = new ResponseEntity<List<Customer>>(
							adminService.allCustomers(), HttpStatus.OK);
					return result;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@GetMapping("/customerById/{id}/{token}")
	public Customer customerById(@PathVariable long id, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((AdminServiceImpl) session.getFacade()).customerById(id);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@GetMapping("/allIncome")
	public ResponseEntity<List<Income>> allIncome() {
		ResponseEntity<List<Income>> allIncome = new ResponseEntity<List<Income>>(incomeService.allIncome(),
				HttpStatus.OK);
		return allIncome;
	}
	
	@GetMapping("/viewAllIncome/{token}")
	public ResponseEntity<List<Income>> viewAllIncome(@PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				if (incomeService.allIncome() != null) {
					ResponseEntity<List<Income>> result = new ResponseEntity<List<Income>>(incomeService.allIncome(),HttpStatus.OK);
					return result;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	
	@GetMapping("/viewIncomeByCompanyId/{companyId}/{token}")
	public List<Income> viewIncomeByCompanyId(@PathVariable long companyId, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return incomeService.viewIncomeByCompany(companyId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	
	@GetMapping("/viewIncomeByCustomerId/{customerId}/{token}")
	public List<Income> viewIncomeByCustomerId(@PathVariable long customerId, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return incomeService.viewIncomeByCustomer(customerId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
}
