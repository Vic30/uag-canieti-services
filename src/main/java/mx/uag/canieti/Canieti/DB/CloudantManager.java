package mx.uag.canieti.Canieti.DB;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;

import mx.uag.canieti.Canieti.model.RegistryModel;

@Component
public class CloudantManager {
	  @Value("${gacl.cloudant.user:}")
	  private String user;
	  @Value("${gacl.cloudant.password:}")
	  private String password;
	  @Value("${gacl.cloudant.host:}")
	  private String host;	  	 
	  
	  public Database getDB(String dataBaseName) {
		    CloudantClient client = getClient();
		    try {
		      dataBaseName = URLEncoder.encode(dataBaseName, "UTF-8");
		    } catch (UnsupportedEncodingException e) {
		      e.printStackTrace();
		    }
		    Database db = client.database(dataBaseName, true);		        
		    return db;
	  }
	  
	  private CloudantClient getClient() {
		  CloudantClient client = ClientBuilder.account(user)
	              .username(user)
	              .password(password)
	              .build();
		  return client;
	  }
	  
	  public Response save(RegistryModel registry){
		  Database db= getDB("users");
		  return db.save(registry);
	  }
}
