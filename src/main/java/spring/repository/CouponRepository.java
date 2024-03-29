package spring.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.models.Coupon;
import spring.models.CouponType;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Coupon findByTitle(String title);

	List<Coupon> findByEndDateBefore(Date date);

	List<Coupon> findByType(CouponType couponType);

	List<Coupon> findByPriceLessThan(double price);

	List<Coupon> findAllById(long id);
	

	

}
