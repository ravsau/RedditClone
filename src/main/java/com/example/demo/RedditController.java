package com.example.demo;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Reddit;
import com.example.demo.RedditRepository;



@Controller 
public class RedditController {


	@Autowired
	private RedditRepository redRepository;
	
	


	@RequestMapping("/front")
	public String listPosts(Model model){
		model.addAttribute("reddit", redRepository.findAll());
		return "allPosts";

	}


	@GetMapping("/add")
	public String addPost(Model model){
		model.addAttribute("reddit", new Reddit());
		return "addPost";
	}
	@PostMapping("/add")
	public String addedList(@ModelAttribute Reddit reddit , BindingResult bindingResult, Principal p, Model model){
		if (bindingResult.hasErrors()){
			return "addPost";
		}
		
		
	    String  username=p.getName();
	     reddit.setUsername(username);
	    
		redRepository.save(reddit);
		return "redirect:/front";

	}

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    
    @RequestMapping("/userpost/{username}")
    public String userPost(@ModelAttribute Reddit post,Model m){
    	
    	m.addAttribute("reddit",redRepository.findByUsername(post.getUsername()));
    	return "userPosts";
    	
    }
}
