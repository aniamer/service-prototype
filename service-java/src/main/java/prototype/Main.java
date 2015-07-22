package prototype;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import prototype.server.ProtoypeService;

public class Main {
	public final static URI SERVER_URI=URI.create("http://0.0.0.0:8080/reactive-service/rest");
	
	public static void main(String[] args) throws IOException {
		HttpServer httpServer = startServer();
		System.out.println("application started at "+SERVER_URI);
		
		while(true){
			
		}
	}

	public static HttpServer startServer() {
		ResourceConfig resourceConfig = new ResourceConfig(ProtoypeService.class); 
		HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(SERVER_URI, resourceConfig);
		return httpServer;
	}

}
