package in.co.loan.granting.system.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="USER_FEED_BACK")
@Getter
@Setter
public class UserFeedBackDTO extends BaseDTO{
	
	@Column(name="QUESTION",length = 225)
	private String question;	
	
	
	@Column(name="FEED_BACK",length = 1500)
	private String feedBack;	
	
	@Override
	public String getKey() {
		return null;
	}
	
	@Override
	public String getValue() {
		return null;
	}

}
