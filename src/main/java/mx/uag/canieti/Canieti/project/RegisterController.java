package mx.uag.canieti.Canieti.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
	
	@GetMapping
	private JsonNode example(){
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jNode = mapper.createObjectNode();
		jNode.put("Text", "Hello World!");
		return jNode;
	}
}
