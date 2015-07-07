package prototype.client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.management.RuntimeErrorException;
import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
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
		String url = String.format("http://%s/reactive-service/rest/getData/testData", address);
		Response response = null;
		AsyncInvoker asyncInvoker = client.target(url)
				.request(MediaType.TEXT_PLAIN).async();
		Future<Response> future=null;
		try {
			switch (action) {
			case ASYNC:
				
				future = asyncInvoker.get(new InvocationCallback<Response>() {

					@Override
					public void completed(Response arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void failed(Throwable arg0) {
						arg0.printStackTrace();
					}
				});
				break;
			case SYNC:
				 future = asyncInvoker.get();

				break;

			default:
				RuntimeErrorException ex = new RuntimeErrorException(new Error(
						"unsupported action"));
				response = Response.status(200)
						.entity(Exceptions.getStackTraceAsString(ex))
						.type("text/plain").build();
				break;
			}
		} catch (Exception ex) {
			response = Response.status(200)
					.entity(Exceptions.getStackTraceAsString(ex))
					.type("text/plain").build();
		}
		
		String result = null;
		while(!future.isDone()){
			
		}
		try {
			response = future.get();
		} catch (InterruptedException e) {
			response = Response.status(200)
					.entity(Exceptions.getStackTraceAsString(e))
					.type("text/plain").build();
		} catch (ExecutionException e) {
			response = Response.status(200)
					.entity(Exceptions.getStackTraceAsString(e))
					.type("text/plain").build();
		}
		result = response.readEntity(String.class);
		return result;
	}

}
