package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.blog.dao.UserDao;
import com.blog.model.User;
import com.blog.model.beans.RegisterBean;

@Controller
public class RegisterController {
	
	@Autowired
	private UserDao userDao;
	
	
	@GetMapping(value="/signup") 
	public String showForm(Model model){
		model.addAttribute("userRegister", new RegisterBean());
		return "register";
	}
	
	@PostMapping(value="/register")
	public String submit(@ModelAttribute("userRegister") RegisterBean r, 
			BindingResult result, Model model) {
		
		userDao.create(new User(r.getEmail(), r.getNombre(), 
				r.getCiudad(), r.getPassword()));
		return "redirect:/autores";
		
	}


}
