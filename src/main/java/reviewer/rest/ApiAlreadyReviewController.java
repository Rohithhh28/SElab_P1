package reviewer.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reviewer.model.Paper;
import reviewer.model.Review;
import reviewer.model.User;
import reviewer.repository.PaperRepository;
import reviewer.repository.ReviewRepository;
import reviewer.repository.UserRepository;

@RestController
@RequestMapping("/api/AlreadyReviewed")
public class ApiAlreadyReviewController {
	 	private final UserRepository userRepository;
	    private final PaperRepository paperRepository;
	    private final ReviewRepository reviewRepository;

	    public ApiAlreadyReviewController(UserRepository userRepository, PaperRepository paperRepository,
	            ReviewRepository reviewRepository) {
	        this.userRepository = userRepository;
	        this.paperRepository = paperRepository;
	        this.reviewRepository = reviewRepository;
	    }
	    
	    @GetMapping("/{id}")
	    public List<Paper> getAlreadyReviewedPapers(@PathVariable("id") String username) {
			User user = userRepository.findByEmailId(username);
	        List<Review> reviews = reviewRepository.findAllByuserAndStatus(user, "Already Reviewed");
	        List<Paper> alreadyReviewedPapers = new ArrayList<>();

	        for (Review review : reviews) {
	            Paper paper = review.getPaper();
	            alreadyReviewedPapers.add(paper);
	        }
	        return alreadyReviewedPapers;
	    }
	    
}
