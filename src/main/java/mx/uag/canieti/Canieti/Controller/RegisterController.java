package mx.uag.canieti.Canieti.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uag.canieti.Canieti.DB.CloudantManager;
import mx.uag.canieti.Canieti.model.RegistryModel;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
	@Autowired
	private CloudantManager clientDB;
	
	@PostMapping
	private ResponseEntity<String> example(@RequestBody RegistryModel registry){
		
		try {
			validateRegistry(registry);
		}catch(Exception e) {
			String json = String.format("{\"error\": \"%s\" }", e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
		}
		
		try {
			
			clientDB.save(registry);
		} catch (Exception e) {
			String json = "{ \"error\": \"Internal error when try to save the data.\" }";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
		}
		
		String json = String.format("{ \"name\": \"%s\", \"email\": \"%s\", \"phone\": \"%s\"}", registry.getName(), registry.getEmail(), registry.getPhone());
		
		return ResponseEntity.ok(json);
	}
	
	private void validateRegistry(RegistryModel registry) throws Exception {
		
		if(registry.getName().isEmpty()) {
			throw new Exception("Name is empty");
		}
		
		if(registry.getEmail().isEmpty()) {
			throw new Exception("Email is empty");
		}
		
		if(registry.getPhone().isEmpty()) {
			throw new Exception("Phone is empty");
		}
		
		if(clientDB.exists(registry)) {
			throw new Exception("This element already exist.");
		}
	}
}
