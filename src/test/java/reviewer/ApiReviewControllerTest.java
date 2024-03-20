
package reviewer;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;

import reviewer.model.Paper;
import reviewer.model.Review;
import reviewer.model.User;
import reviewer.repository.PaperRepository;
import reviewer.repository.ReviewRepository;
import reviewer.repository.UserRepository;
import reviewer.rest.ApiReviewController;
import reviewer.util.Rkey;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;


@ExtendWith(MockitoExtension.class)
public class ApiReviewControllerTest {

    @Mock
    private ReviewRepository reviewRepository;
    
    @Mock
    private UserRepository userRepository;
    
    
    @Mock
    private PaperRepository paperRepository;

    @InjectMocks
    private ApiReviewController apiReviewController;

    private User user;
    

    private Paper paper1;
    private Paper paper2;
    private Paper paper3;

    private Review review1;
    private Review review2;
    private Review review3;
  


    @BeforeEach
    public void setup() {
    	user = new User("aishwaryaisback1234@gmail.com","ATTANTI","AISHWARYA","8790373183","$2a$10$xAkiDuhqEQKsQV4aJ39Yeu3aVhlIT3hyy827NPITOO.d70ZH0FeEC",5L,",A,S,D,F,G");
    	LocalDateTime now = LocalDateTime.now();
    	paper1 = new Paper();
    	//1L, "title1", "author1a, author1b", now, "link1", now);
    	paper1.setPid(1L);
    	paper1.setAuthors("aishwarya,chaitanya");
    	paper1.setDateallocated(now);
    	paper1.setDeadlinedate(now);
    	paper1.setPaperlink("link");
    	paper1.setTitle("CD");
    	paper2 = new Paper();
    	paper2.setPid(2L);
    	//2L, "title2", "author2a, author2b", now, "link2", now);
    	paper3 = new Paper(3L, "title3", "author3a, author3b", now, "link3", now);
    	

       Rkey rid1= new Rkey(1L,"aishwaryaisback1234@gmail.com");
    	Rkey rid2 = new Rkey(2L, user.getEmailId());
    	Rkey rid3 = new Rkey(3L, user.getEmailId());

     review1 = new Review(rid1, 9L, 8L, 7L, 6L, 5L, 4L, 3L, 2L, 1L, "yes", "no",
              "accept as it is","Already Reviewed");
    
    	
      	review2 = new Review(rid2, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, "decent comment", "decent confidential comment",
                "accept with minor revisions","Already Reviewed");
      	review3 = new Review(rid3, 5L, 9L, 8L, 4L, 6L, 8L, 7L, 9L, 6L, "bad comment", "bad confidential comment", "reject","Already Reviewed");
      }
    
    @Test
    public void findbyRid() throws Exception 
    {
        when(reviewRepository.findByUserAndPaper(1L,"aishwaryaisback1234@gmail.com")).thenReturn(review1);
        Review review = apiReviewController.getReviewFormPage(paper1.getPid(), user.getUsername());
        assertEquals(review, review1);
    }
    


@Test
public void cannotfindbyRid() throws Exception 
{
   Review review = apiReviewController.getReviewFormPage(paper1.getPid(), user.getUsername());
    assertNotEquals(review3, review1);
}

@Test
public void setbyRid() throws Exception 
{

    when(reviewRepository.save(review1)).thenReturn(review1);
   Review review = apiReviewController.setReviewFormPage(review1);
    assertEquals(review, review1);
}
   
}
    
    
//just checking merge conflicts




  