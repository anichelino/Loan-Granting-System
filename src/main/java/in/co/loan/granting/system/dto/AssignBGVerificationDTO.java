package in.co.loan.granting.system.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ASSIGN_BG_VERIFICATION")
@Getter
@Setter
public class AssignBGVerificationDTO  extends BaseDTO{

	@Column(name="FIELD_OFFICER_ID")
	private long fieldOfficerId;
	
	@Column(name="FIELD_OFFICER_NAME",length = 225)
	private String fieldOfficerName;
	
	@Column(name="LOAN_ID")
	private long loanId;
	
	@Column(name="LOAN_REQUEST_ID")
	private long loanRequestId;
	
	@ManyToOne
	@JoinColumn(name = "FIELD_OFFICER",nullable = false)
	private UserDTO fieldOfficer;
	
	@ManyToOne
	@JoinColumn(name = "LOAN",nullable = false)
	private ApplyLoanDTO loan;
	
	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
