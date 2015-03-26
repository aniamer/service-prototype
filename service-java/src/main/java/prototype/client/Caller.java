package prototype.client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.management.RuntimeErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.utils.Exceptions;

public class Caller {
	private Client client;
	private final String ASYNC = "async";
	private final String SYNC = "sync";
	ExecutorService executorService = Executors.newFixedThreadPool(5);

	public String call(String action, String address) {
		client = ClientBuilder.newClient();

		String openSession = " player is not allowed";
		Entity<String> entity = Entity
				.entity(openSession, MediaType.TEXT_PLAIN);
		String url = String.format("http://%s/session", address);
		Response response = null;
		try {
			Future<Response> future = client.target(url)
					.request(MediaType.TEXT_PLAIN).async().get();

			switch (action) {
			case ASYNC:

				break;
			case SYNC:

				response = future.get();

				break;

			default:
				RuntimeErrorException ex = new RuntimeErrorException(new Error(
						"unsupported action"));
				response = Response.status(200)
						.entity(Exceptions.getStackTraceAsString(ex))
						.type("text/plain").build();
				break;
			}
		} catch (InterruptedException ex) {
			response = Response.status(200)
					.entity(Exceptions.getStackTraceAsString(ex))
					.type("text/plain").build();
		} catch (Exception ex) {
			response = Response.status(200)
					.entity(Exceptions.getStackTraceAsString(ex))
					.type("text/plain").build();
		}
		
		String result = response.readEntity(String.class);
		return result;
	}

}
