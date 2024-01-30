package reviewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RegisterPageController {

	@GetMapping("/registerPage")
	public String registerpage() {
		return "registerPage";
	}
}