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
	private RegistryModel example(@RequestBody RegistryModel registry){
		validateRegistry(registry);
		try {
			clientDB.save(registry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registry;
	}
	
	private void validateRegistry(RegistryModel registry) {
		//check if exists in DB
		//If yes return an exception
	}
}
