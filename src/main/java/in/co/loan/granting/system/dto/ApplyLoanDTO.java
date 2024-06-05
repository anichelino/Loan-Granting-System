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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="APPLY_LOAN")
@Getter
@Setter
public class ApplyLoanDTO extends BaseDTO {

	@Column(name="NAME",length = 225)
	private String name;
	
	@Column(name="ADDRESS",length = 1500)
	private String address;
	
	@Column(name="PHONE_NO",length = 225)
	private String phoneNo;
	
	@Column(name="EMAIL_ID",length = 225)
	private String emailId;
	
	@Column(name="RESUME",columnDefinition = "LONGBLOB")
	private byte[] resume;
	
	@Column(name="PROJECT_PLAN",columnDefinition = "LONGBLOB")
	private byte[] projectPlan;

	@Column(name="PERSONAL_CREDIT_REPORT",columnDefinition = "LONGBLOB")
	private byte[] personalCreditReport;
	
	@Column(name="BUSINESS_CREDIT_REPORT",columnDefinition = "LONGBLOB")
	private byte[] businessCreditReport;
	
	@Column(name="FINANCIAL_STATEMENT",columnDefinition = "LONGBLOB")
	private byte[] financialStatement;
	
	@Column(name="BANK_STATEMENT",columnDefinition = "LONGBLOB")
	private byte[] bankStatement;
	
	@Column(name="LOAN_AMOUNT",length = 225)
	private String loanAmount;
	
	@Column(name="LOAN_TENURE",length = 225)
	private String loanTenure;
	
	@Column(name="EMI_OPTION",length = 225)
	private String EMIOption;
	
	private long userId;
	
	@Column(name="APPLY_DATE")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="STATUS",length = 225)
	private String status;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "loan")
	private Set<AssignBGVerificationDTO> assignBGVerifications;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "loan")
	private Set<AssignLoanVerificationDTO> assignLoanVerifications;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "loan")
	private Set<UpdateBGVerificationDTO> updateBGVerifications;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "loan")
	private Set<UpdateLoanVerificationDTO> updateLoanVerifications;
	
	@Column(name="REQUEST_LOAN_ID")
	private long requestLoanId;
	
	@Override
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		return String.valueOf(requestLoanId);
	}

}
