package prototype.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	
	
}
