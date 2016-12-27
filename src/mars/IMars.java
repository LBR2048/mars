package mars;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

public interface IMars {
	
	@GET
	@Path("mars/{instructions}")
	@Produces("text/plain")
	public String move(@PathParam("instructions") String instructions);
}
