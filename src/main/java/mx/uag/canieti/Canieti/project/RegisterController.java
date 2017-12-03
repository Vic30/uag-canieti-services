package mx.uag.canieti.Canieti.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import mx.uag.canieti.Canieti.model.RegistryModel;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
	
	@PostMapping
	private RegistryModel example(@RequestBody RegistryModel registry){
		validateRegistry(registry);
		//Store in DB
		return registry;
	}
	
	private void validateRegistry(RegistryModel registry) {
		//check if exists in DB
		//If yes return an exception
	}
}
