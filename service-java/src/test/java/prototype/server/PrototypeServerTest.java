package prototype.server;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import prototype.Main;


public class PrototypeServerTest {
	Logger logger = LoggerFactory.getLogger(getClass());
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
	public void testSync() {
		Response response = client.target(Main.SERVER_URI).path("/getData/"+"sync/"+"localhost:8080").request(MediaType.TEXT_PLAIN).get();
		String readEntity = response.readEntity(String.class);
		logger.info(readEntity);
	}
	@Test
	public void testAsync() throws InterruptedException, ExecutionException {
		Response response = client.target(Main.SERVER_URI).path("/getData/"+"async/"+"localhost:8080").request(MediaType.TEXT_PLAIN).async().get().get();
		String readEntity = response.readEntity(String.class);
		logger.info(readEntity);
	}

}
