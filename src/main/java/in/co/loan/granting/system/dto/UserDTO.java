package in.co.loan.granting.system.dto;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class UserDTO extends BaseDTO {

	@Column(name = "FIRST_NAME", length = 225)
	private String firstName;

	@Column(name = "LAST_NAME", length = 225)
	private String lastName;

	@Column(name = "USER_ID", length = 225)
	private String userId;

	@Column(name = "PASSWORD", length = 225)
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@Column(name="DOB")
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(name = "GENDER", length = 225)
	private String gender;
	
	@Column(name = "CONTACT_NO", length = 225)
	private String contactNo;
	
	@Column(name = "EMAIL", length = 225)
	private String email;
	
	@Column(name = "ROLE_ID")
	private long roleId;

	@Column(name = "ROLE_NAME", length = 225)
	private String roleName;
	
	@Column(name="STATUS",length = 225)
	private String status;
	
	@Column(name="PAN",length = 225)
	private String pan;

	@Column(name="USER_CATEGORY",length = 225)
	private String userCategory;
	
	@Column(name="DESIGNATION",length = 225)
	private String designation;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "fieldOfficer")
	private Set<AssignBGVerificationDTO> assignBGVerifications;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "loanOfficer")
	private Set<AssignLoanVerificationDTO> assignLoanVerifications;
	
	public String getKey() {
		return id + "";
	}

	public String getValue() {

		return firstName + " " + lastName;
	}


}
