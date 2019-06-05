package com.example.demo.ui.controller;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.UserRepository;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.shared.UserDto;
import com.example.demo.shared.Utils;
import com.example.demo.ui.model.request.UserDetailsRequestModel;
import com.example.demo.ui.model.request.UserLoginRequestModel;
import com.example.demo.ui.model.response.UserRest;

@RestController
@RequestMapping("") // http://localhost:8080/users
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

    	String data = "<form>" + 
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
		String email = arguments[0].split("=")[1];
		String password = arguments[0].split("=")[1];
		UserLoginRequestModel userLoginData = new UserLoginRequestModel();
		userLoginData.setEmail(email);
		userLoginData.setPassword(password);
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(userLoginData, userDto);
		
		UserDto loggedUser = userService.logInUser(userDto);
		

		if(loggedUser == null) return "Błędne dane";
			return "Zalogowano poprawnie\n"+
		"<h1><a href=\"http://localhost:8080/menu\">MENU</a></h1>";
	}


	@GetMapping("/registration")
    public String registration() {
		String data = "<form>" + 
		"<br><label for=\"firstName\"><b>First Name</b></label>"+ 
		"<input type=\"text\" placeholder=\"Enter Your first name\" name=\"firstName\" required>"+	
		"<br><label for=\"lastName\"><b>Last Name</b></label>"+ 
		"<input type=\"text\" placeholder=\"Enter Your last name\" name=\"lastName\" required>"+		  
		"<br><label for=\"email\"><b>Email</b></label>"+ 
		"<input type=\"text\" placeholder=\"Enter Email\" name=\"email\" required>"+	  
		"<br><label for=\"psw\"><b>Password</b></label>" +
		"<input type=\"password\" placeholder=\"Enter Password\" name=\"psw\" required>" +
		"<br><label for=\"psw-repeat\"><b>Repeat Password</b></label>" + 
		"<input type=\"password\" placeholder=\"Repeat Password\" name=\"psw_repeat\" required>"+
  		"<button type=\"submit\" formmethod=\"post\">Zatwierdź</button></form>" +
		"</div> <div class=\"container signin\"><p>Masz już konto? " + 
		"<a href=\"login\">Zaloguj się</a>.</p></div>";

		return data;
	}

	@PostMapping("/registration")
	public String registration(@RequestBody String dane) {
		String[] arguments = dane.split("&");
		String firstName = arguments[0].split("=")[1];
		String lastName = arguments[1].split("=")[1]; 
		String email = arguments[2].split("=")[1]; 
		String psw = arguments[3].split("=")[1]; 
		String psw_repeat = arguments[4].split("=")[1];
		
		email = java.net.URLDecoder.decode(email, StandardCharsets.UTF_8);
		
		if(!(psw.equals(psw_repeat)))
			return "Hasła różnią się";
		
		UserDetailsRequestModel userDetailsRequestModel = new UserDetailsRequestModel();
		userDetailsRequestModel.setFirstName(firstName);
		userDetailsRequestModel.setLastName(lastName);
		userDetailsRequestModel.setEmail(email);
		userDetailsRequestModel.setPassword(psw);

		UserRest userRest = new UserRest();
		userRest = createUser(userDetailsRequestModel);
		if(userRest==null) 
			return "Błąd rejestracji";
		
		return "Zarejestrowano poprawnie\n"
				+ "<h1><a href=\"http://localhost:8080/login\">LOGIN</a></h1>";
	}
	
}