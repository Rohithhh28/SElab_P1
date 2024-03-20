package reviewer.rest;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reviewer.model.Paper;
import reviewer.model.User;
import reviewer.repository.PaperRepository;
import reviewer.repository.ReviewRepository;
import reviewer.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class ApiUserDetails {
	
	private final UserRepository userRepository;
	private final ReviewRepository reviewRepository;
	private final PaperRepository paperRepository;
	
	public ApiUserDetails(UserRepository userRepository,ReviewRepository reviewRepository,PaperRepository paperRepository) {
		this.userRepository = userRepository;
		this.reviewRepository = reviewRepository;
		this.paperRepository = paperRepository;
	}
	
	@GetMapping("/user/{id}")
	public User getUserDetails(@PathVariable("id") String username) {
		User user = userRepository.findByEmailId(username);
		return user;
	}
	
	@GetMapping("/paperCount/{id}")
    public Long getPaperCount(@PathVariable("id") String username) {
    	User user = getUserDetails(username);
    	return reviewRepository.countByUserAndStatus(user,"Already Reviewed");
    }
	
	@GetMapping("/paper/{id}")
	public Paper getPaper(@PathVariable("id") Long paperId) {
		Paper paper = paperRepository.findBypid(paperId);
		return paper;
	}
}
