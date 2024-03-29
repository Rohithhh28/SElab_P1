package reviewer.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reviewer.model.Tag;
import reviewer.model.User;

import reviewer.repository.PaperRepository;
import reviewer.repository.ReviewRepository;
import reviewer.repository.TagRepository;
import reviewer.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class ApiTagController {
   
	
	@Autowired
	 private UserRepository userRepository;
	private TagRepository tagRepository;


	    public ApiTagController(UserRepository userRepository, TagRepository tagRepository,PaperRepository paperRepo,ReviewRepository reviewRepo) {
	        this.userRepository = userRepository;
	        this.tagRepository=tagRepository;
	        
	    }
	    @GetMapping("/tags/{id}")
	    public List<String> getTags(@PathVariable("id") String username) 
	    {  
	         User user = userRepository.findByEmailId(username); 
	         
	          List <Tag> list1 = tagRepository.findByUser(user);
    		 List<String> list = new ArrayList<String>(); 
    		 for(int i=0;i<list1.size();i++)
    		 {
    			  list.add(list1.get(i).getTag());
    		 }
	        return list;
	    }
	    
	@PostMapping("/tagsadd/{id}/{username}")
	public List<String> addTags(@PathVariable(name = "id",required =false)String tag,
			@PathVariable("username") String username)
	{
		System.out.println("1"+tag);
		User user1=userRepository.findByEmailId(username);
		List <Tag> list1 = tagRepository.findByUser(user1);
		int repeat=0;
		for(int i=0;i<list1.size();i++)
		{
			boolean k1=list1.get(i).getTag().equals(tag);
			//boolean  k2=list1.get(i).getUser().equals(user1);
			if(k1)
			{
				repeat=1;
			}
			
			else
			{
				repeat=0;
			}
		}
		Tag tag1=tagRepository.findByUserAndTag(user1,tag);
		System.out.println(tag1);
		if(tag!="")
		{

		if(repeat==0)
		{
			tag1=new Tag();
			 tag1.setTag(tag);
			// if(tag1!=null)
			// {
			 tag1.setUser(user1);
			// }
			 System.out.println(tag1.toString());
			tagRepository.save(tag1);
			list1.add(tag1);
		}
         
		else
		{
			System.out.println("tag exist");
		}
		
		}
		
		
		list1 = tagRepository.findByUser(user1);
		 List<String> list = new ArrayList<String>(); 
		 for(int i=0;i<list1.size();i++)
		 {
			  list.add(list1.get(i).getTag());
		 }
		

		return list;
    }

	@PostMapping("/tagsdelete/{id}")
	public List<String> tagDeletes(@RequestBody List<String> values ,@PathVariable("id") String username)
	{
		User user1=userRepository.findByEmailId(username);
		List <Tag> list1 = tagRepository.findByUser(user1);
		List<String> reflist = new ArrayList<String>();
		for (int i = 0; i < list1.size(); i++) {
			reflist.add(list1.get(i).getTag());
		}
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < list1.size(); i++) {
			list.add(list1.get(i).getTag());
		}
		
		reflist.removeAll(values);
		
		if (values.size()>=5) {
			for(String rmtag : reflist) 
			{
				
					Tag rmtag1 = tagRepository.findByUserAndTag(user1, rmtag);
					
						tagRepository.delete(rmtag1);
					

				
			}
			list.removeAll(reflist);
			
		}
		
		return list;
    }
	
}