package in.co.loan.granting.system.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="HELP")
@Setter
@Getter
public class HelpDTO extends BaseDTO {
	
	@Column(name="REQUEST_ID")
	private long requestId;
	@Column(name="ISSUE",length = 225)
	private String issue;
	@Column(name="DESCRIPTION",length = 225)
	private String description;
	@Column(name="COMMENT",length = 225)
	private String comment;
	@Column(name="DATE",length = 225)
	private Date date;

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
