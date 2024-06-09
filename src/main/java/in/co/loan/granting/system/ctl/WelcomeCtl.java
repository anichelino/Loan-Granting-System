package in.co.loan.granting.system.ctl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class WelcomeCtl {
	private static final Logger logger = LoggerFactory.getLogger(WelcomeCtl.class);

	@GetMapping(path={"/welcome"})
	public String display() {
		return "welcome";
	}
	
	@GetMapping(path={"/home","/"})
	public String homeDisplay() {
		String viewName = "home";
		logger.info("Returning view: {}", viewName);
		return viewName;
	}
	@GetMapping("/aboutUs")
	public String aboutUs() {
		return "aboutUs";
	}
	
	@GetMapping("/contactUs")
	public String contactUs() {
		return "contactUs";
	}
	
	@GetMapping("/FAQ")
	public String FAQ() {
		return "faq";
	}
	
}
