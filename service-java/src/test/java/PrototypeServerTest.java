import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import prototype.Main;


public class PrototypeServerTest {
	HttpServer server ;
	Client client ;
	@Before
	public void init() {
		server = Main.startServer();
		client =ClientBuilder.newClient(); 
	}
	
	@After
	public void tearDown() {
		server.shutdownNow();
	}
	
	@Test
	public void test() {
		Response response = client.target(Main.SERVER_URI).path("/getData").request(MediaType.TEXT_PLAIN).get();
		String readEntity = response.readEntity(String.class);
		System.out.println(readEntity);
	}

}
