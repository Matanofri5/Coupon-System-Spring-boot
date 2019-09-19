package spring;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.repository.CompanyRepository;
import spring.repository.CouponRepository;
import spring.repository.CustomerRepository;

@Component
public class CouponsThread {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	CouponRepository couponRepository;

	private boolean exit = false;
	private boolean running = true;

	public void removeExpiredCoupons(Date date) {
		couponRepository.deleteAll(couponRepository.findByEndDateBefore(date));
	}

	// @Override
	// public void run() {
	// while (running) {
	// removeExpiredCoupons(new Date(System.currentTimeMillis()));
	// try {
	// Thread.sleep(1000 * 60 * 60 * 24);
	// } catch (InterruptedException e) {
	// System.out.println("Eroor " + e.getMessage());
	// }
	// }
	//
	// }

	// private boolean exit = false;
	// private boolean running = true;
	//
	//
	// public void deleteCoupon(Coupon coupon) {
	// List<Company> companies =
	// companyRepository.findCompanyByCoupons(coupon.getId());
	// List<Customer> customers =
	// customerRepository.findCustomerByCoupons(coupon.getId());
	// couponRepository.delete(coupon);
	// }
	//
	//
	public void startThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (running) {
					removeExpiredCoupons(new Date(System.currentTimeMillis()));
					try {
						Thread.sleep(1000 * 60 * 60 * 24);
					} catch (InterruptedException e) {
						System.out.println("Eroor " + e.getMessage());
					}
				}
			}
		}).start();
	}
	
	public void stopThread() {
		this.running = false;
	}

}
