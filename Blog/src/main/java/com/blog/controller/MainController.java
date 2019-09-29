package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blog.dao.PostDao;
import com.blog.model.Post;

@Controller
public class MainController {
	
	@Autowired
	private PostDao postDao;
	
	@GetMapping(value="/")
	public String welcome(Model model) {
		List<Post> posts = postDao.getAll();
		model.addAttribute("postList", posts);
		
		return "index";
	}
	
	

}
