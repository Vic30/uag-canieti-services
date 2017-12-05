package mx.uag.canieti.Canieti.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	private Object example(@RequestBody RegistryModel registry){
		
		try {
			validateRegistry(registry);
		}catch(Exception e) {
			return "{ \"error_code:\" : 1, \"error\": \"Already registered\" }";
		}
		
		try {
			
			clientDB.save(registry);
		} catch (Exception e) {
			return "{ \"error_code:\" : 2, \"error\": \"Error saving the data.\" }";
		}
		return registry;
	}
	
	private void validateRegistry(RegistryModel registry) throws Exception {
		if(clientDB.exists(registry)) {
			throw new Exception("This element already exist.");
		}
	}
}
