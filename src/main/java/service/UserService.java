package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Model.User;
import repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository repository;

    public User signup(User user) {
        BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
        String hashe=bcrypt . encode(user.getMdp());
        user.setMdp(hashe);
        user.setType("patient");
        return repository.save(user);
    }
    
    
    
    
    public User saveUser(User user) {
    	BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
        String hashe=bcrypt . encode(user.getMdp());
        user.setMdp(hashe);
        user.setType("Employe");
        user.setDisponibilite("True");
		return repository.save(user);
	}
	
    
    
    
	public List<User> getUsers(){
		return repository.findAll();
	}
	
	
	
	
	public String deleteUser(int id) {
		repository.deleteById(id);
		return "utilisateur supprimé avec succés !"+id ;
	}
	
	


	public User getUserById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	
	
	
	 public Optional<User> findByEmail(String email) {
	        return repository.findByEmail(email);
	    }
	 
	 
	 
	 

		public Optional<User> loginEmployee(String email, String mdp) {
		    

		    Optional<User> userOptional = repository.findByEmail(email);
		
		    
		        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		        if (bcrypt.matches(mdp, userOptional.get().getMdp())) {
		            return userOptional ;
		        
		
		
		}return userOptional ;}

		        
		 
	
	public User updateUser(int id, User newEmploye) {
	    // Assuming "repository" is an instance of some JpaRepository or similar interface
	    java.util.Optional<User> optionalOldEmploye = repository.findById(id);

	    if (optionalOldEmploye.isPresent()) {
	    	User oldEmploye = optionalOldEmploye.get();

	        // Update the properties of the old employee with the new employee's values
	        oldEmploye.setNom(newEmploye.getNom());
	        oldEmploye.setPrenom(newEmploye.getPrenom());
	        oldEmploye.setTelephone(newEmploye.getTelephone());
	        oldEmploye.setEmail(newEmploye.getEmail());
	        oldEmploye.setMdp(newEmploye.getMdp());
	        oldEmploye.setType(newEmploye.getType());
	        oldEmploye.setAge(newEmploye.getAge());
	        oldEmploye.setLocalisation(newEmploye.getLocalisation());
	        oldEmploye.setSpecialite(newEmploye.getSpecialite());
	        oldEmploye.setDisponibilite(newEmploye.getDisponibilite());

	        // Save the updated employee
	        return repository.save(oldEmploye);
	    } else {
	        // Handle the case where the old employee with the given id is not found
	        // You might want to throw an exception or return null, depending on your use case
	        return null;
	    }
	}




	


}

