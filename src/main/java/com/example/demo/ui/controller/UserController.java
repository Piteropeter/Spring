package com.example.demo.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;
import com.example.demo.shared.UserDto;
import com.example.demo.ui.model.request.UserDetailsRequestModel;
import com.example.demo.ui.model.response.UserRest;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUser() {
		return "<!-- #######  YAY, I AM THE SOURCE EDITOR! #########-->\n" + 
				"<p><button type=\"button\">Click Me!</button></p>\n" + 
				"<h1 style=\"color: #5e9ca0;\">You can edit <span style=\"color: #2b2301;\">this demo</span> text!</h1>";
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue = new UserRest();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
	}
	
	@PutMapping
	public String updateUser() {
		return "update user was called";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "delete user was called";
	}

	@GetMapping({"/", "/index"})
    public String index() {
		return"<div class=\"container\">"+
		  "<h1>Pizzeria PANDA3.0</h1>  <p>Aby zlożyc zamówienie, zaloguj się</p>"+
		  "</div> <div class=\"container signin\"><p>Nie masz konta? " + 
		  "<a href=\"registration\">Zarejestruj sie</a>.</p></div>"+
		  "</div> <div class=\"container signin\"><p>Masz konto? " + 
		  "<a href=\"login\">Logowanie</a>.</p></div>";

	}
	@GetMapping("/login")
    public String login() {

    	String data = "<form action=\"login\" method=\"get\" target=\"_blank\">" + 
  		"Email: <input type=\"text\" name=\"email\"><br>" +
  		"Password: <input type=\"password\" name=\"password\"><br>"+
  		"<button type=\"submit\" formmethod=\"post\">Zatwierdź</button></form>" +
		"</div> <div class=\"container signin\"><p>Nie masz jeszcze konta? " + 
		"<a href=\"registration\">Zarejestruj się</a>.</p></div>";

  		return data;
	}

	@PostMapping("/login")
	public String login(@RequestBody String dane) {
		String[] arguments = dane.split("&");
		String email = arguments[0];
		String password = arguments[1]; 


		return dane;
	}

	@GetMapping("/registration")
    public String registration() {
		String data = "<form action=\"login\" method=\"get\" target=\"_blank\">" + 
		"<br><label for=\"firstName\"><b>First Name</b></label>"+ 
		"<input type=\"text\" placeholder=\"Enter Your first name\" name=\"firstName\" required>"+	
		"<br><label for=\"lastName\"><b>Last Name</b></label>"+ 
		"<input type=\"text\" placeholder=\"Enter Your last name\" name=\"lastName\" required>"+		  
		"<br><label for=\"email\"><b>Email</b></label>"+ 
		"<input type=\"text\" placeholder=\"Enter Email\" name=\"email\" required>"+	  
		"<br><label for=\"psw\"><b>Password</b></label>" +
		"<input type=\"password\" placeholder=\"Enter Password\" name=\"psw\" required>" +
		"<br><label for=\"psw-repeat\"><b>Repeat Password</b></label>" + 
		"<input type=\"password\" placeholder=\"Repeat Password\" name=\"psw-repeat\" required>"+
  		"<button type=\"submit\" formmethod=\"post\">Zatwierdź</button></form>" +
		"</div> <div class=\"container signin\"><p>Masz już konto? " + 
		"<a href=\"login\">Zaloguj się</a>.</p></div>";

		return data;
	}

	@PostMapping("/registration")
	public String registration(@RequestBody String dane) {
		String[] arguments = dane.split("&");

		return dane;
	}
	

	
	
}