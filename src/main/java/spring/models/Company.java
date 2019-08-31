package spring.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Basic(optional = false)
	@Column(nullable = false, name="companyName")
	private String companyName;
	
	@Basic(optional = false)
	@Column(nullable = false, name="password")
	private String password;
	
	@Basic(optional = false)
	@Column(nullable = false, name="email")
	private String email;
	
	
	private List<Coupon> coupons;

	@Access(AccessType.PROPERTY)
	@OneToMany///(cascade=CascadeType.REMOVE)
//	@JoinTable(name="company_coupons", joinColumns= {@JoinColumn(name="company_id", referencedColumnName="id")},
//	inverseJoinColumns= {@JoinColumn(name="coupons_id", referencedColumnName="id")})
	public List<Coupon> getCoupons(){
		return coupons;
	}
	
	
	public void setCoupons(List<Coupon>coupons) {
		this.coupons = coupons;
	}

	
}
