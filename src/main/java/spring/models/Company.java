package spring.models;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String companyName;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String password;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String email;

	
}