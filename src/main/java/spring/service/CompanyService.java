package spring.service;

import java.sql.Date;
import java.util.List;

import spring.exceptions.CouponNotAvailableException;
import spring.models.Company;
import spring.models.Coupon;
import spring.models.CouponType;

public interface CompanyService {

	Coupon createCoupon(Coupon coupon) throws Exception;

	boolean checkIfTitleAlreadyExists(String title);

	void updateCoupon(Coupon coupon, Date endDate, double price);

	Company companyById(long id);

	void deleteCoupon(long coupon_id) throws CouponNotAvailableException, Exception;

	void setCompany(Company company);

	List<Coupon> getAllCompanyCoupons(long company_id) throws Exception;

	List<Coupon> getCouponsByCouponType(CouponType couponType) throws Exception;

	List<Coupon> getCouponsByPrice(double price) throws Exception;

	List<Coupon> getCouponsByEndDate(Date endDate) throws Exception;

}