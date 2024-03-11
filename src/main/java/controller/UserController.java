package controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Model.User;
import service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@PostMapping("/login")
	public Optional<User> login(@RequestBody Map<String, String> requestBody) {
	    String email = requestBody.get("email");
	    String mdp = requestBody.get("mdp");
	    
	    return service.loginEmployee(email, mdp);
	}

	
	
	
	@PostMapping("/signup")
    public User signup(@RequestBody User user) {
        System.out.println("signup succeed " + user);
        return service.signup(user);
    }
	
	
	@PostMapping("/add")
	public User addUser(@RequestBody User user ) {
	    System.out.println("Received request to add employe: " + user);
	    return service.saveUser(user);
	}
	

	@GetMapping("/users")
	public List<User> findAllUsers() { 
		return service.getUsers();
	}	

	@PutMapping("/update/{id}")
	public User updateUser (@PathVariable(name = "id") int userId, @RequestBody User user) { 
		return service.updateUser(userId,user);
}


	@DeleteMapping("/delete/{id}")
	public String deleteUser (@PathVariable(name = "id") int userId) { 
		return service.deleteUser(userId);
	}


	@GetMapping("/user/{id}")
	public User findUserById(@PathVariable(name = "id") int userId) { 
		return service.getUserById(userId);
}
	
	@GetMapping("/mail/{mail}")
	public Optional<User> find(@PathVariable(name = "mail") String email) { 
		return service.findByEmail(email);}

}
