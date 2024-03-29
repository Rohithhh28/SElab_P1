package reviewer.rest;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reviewer.model.Paper;
import reviewer.model.Review;
import reviewer.model.User;
import reviewer.repository.PaperRepository;
import reviewer.repository.ReviewRepository;
import reviewer.repository.UserRepository;
import reviewer.util.Rkey;

@RestController
@RequestMapping(path = "/api/review",produces="application/json")
public class ApiReviewController {
	private final UserRepository userRepository;
    private final PaperRepository paperRepository;
    private final ReviewRepository reviewRepository;

    public ApiReviewController(UserRepository userRepository, PaperRepository paperRepository,
            ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.paperRepository = paperRepository;
        this.reviewRepository = reviewRepository;
    }
    
	@GetMapping("/{id}/{username}")
	public Review getReviewFormPage(@PathVariable("id") Long paperId, @PathVariable("username") String username) {
//		Rkey rkey = new Rkey(paperId,username).get();
		User user = userRepository.findByEmailId(username);
		Paper paper = paperRepository.findBypid(paperId);
		Review review = reviewRepository.findByUserAndPaper(user,paper);
		return review;
	}
	
	
	@PostMapping(path="/save" , consumes="application/json")
	public Review setReviewFormPage(@RequestBody Review review) {
		review.setStatus("Already Reviewed");
		reviewRepository.save(review);
//		System.out.println(review);
		return review;
	}
}
