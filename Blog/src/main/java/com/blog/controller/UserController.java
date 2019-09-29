package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blog.dao.UserDao;
import com.blog.model.User;

@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping(value="/autores")
	public String listaAutores(Model model) {
		
		List<User> autores = userDao.getAll();
		
		model.addAttribute("autores", autores);
		
		return "userlist";
	}
	


}
