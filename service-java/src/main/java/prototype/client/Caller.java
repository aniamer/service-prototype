package prototype.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.jadestone.arena.wallet.adapter.api.OpenSession;
import se.jadestone.arena.wallet.adapter.api.SessionResponse;

public class Caller {
	private Client client;
	
	private String serverHost;
	public SessionResponse call() {
	
		client = ClientBuilder.newClient();
		OpenSession openSession = new OpenSession();
		openSession.arenaPlayerId = 1121;
		openSession.partnerId=12;
		Entity<OpenSession> entity = Entity.entity(openSession, MediaType.APPLICATION_JSON);
		
		Response response = client.target("http://localhost:8080/session").request().buildPost(entity).invoke();
		SessionResponse result = response.readEntity(SessionResponse.class);
		return result;
	}
	
	
}
