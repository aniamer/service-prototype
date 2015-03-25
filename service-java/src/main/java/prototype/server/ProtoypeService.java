package prototype.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/server")
public class ProtoypeService {

	@GET
	@Path("/echo")
	@Produces(MediaType.TEXT_PLAIN)
	public String echo() throws InterruptedException{
		Thread.sleep(1000);
		return "hey handsome !!";
	}
}
