package prototype.server;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ManagedAsync;

import prototype.client.Caller;

@Path("/getData")
public class ProtoypeService {
	private Caller caller = new Caller();
	@GET
	@Path("{action}/{address}")
	@Produces(MediaType.TEXT_PLAIN)
	public String exec(@PathParam("action") String action, @PathParam("address") String address) throws InterruptedException{
		String response = caller.call(action, address);
		System.out.println(response);
		return response;
	}
	
	@GET
	@Path("/testData")
	@ManagedAsync
	@Produces(MediaType.TEXT_PLAIN)
	public void test(@Suspended final AsyncResponse asyncResponse){
		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		asyncResponse.resume("hallo tested service");
	}
	
	
}
