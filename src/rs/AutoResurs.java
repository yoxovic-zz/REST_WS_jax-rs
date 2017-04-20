package rs;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import rs.Auto;
import rs.AutoDAO;

@Path("/auto")
public class AutoResurs {

	// http://localhost:8080/REST_WS_Java/rest_api/auto - realizovan URI web servisa

	@GET
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	public Response getAutoList10() {

		AutoDAO auto_dao = new AutoDAO();
		ArrayList<Auto> lista_auto = new ArrayList<Auto>();

		lista_auto = auto_dao.getAutoList10();

		if (lista_auto.isEmpty() || lista_auto == null) {
			return Response.status(Status.NOT_FOUND).entity("GET: Lista automobila nije pronađena!").type(MediaType.TEXT_PLAIN).build();
		}

		return Response.ok().entity(new GenericEntity<ArrayList<Auto>>(lista_auto) { }).build();
	}

	// http://localhost:8080/REST_WS_Java/rest_api/auto/id/{auto_id}

	@GET
	@Path("/id/{auto_id}")
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	public Response getAuto(@PathParam("auto_id") int auto_id) {

		AutoDAO auto_dao = new AutoDAO();
		Auto auto = new Auto();
		
		auto = auto_dao.getAutoById(auto_id);

		if (auto == null) {
			return Response.status(Status.NOT_FOUND).entity("GET: Traženi auto nije pronađen!").type(MediaType.TEXT_PLAIN).build();
		}

		return Response.ok().entity(auto).build();
	}

	// http://localhost:8080/REST_WS_Java/rest_api/auto - realizovan URI web servisa

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response postAuto(@FormParam("marka") String marka, @FormParam("model") String model,
			@FormParam("godiste") int godiste, @FormParam("kubikaza") float kubikaza, @FormParam("boja") String boja,
			@FormParam("cena") float cena, @Context UriInfo uriInfo) {

		Auto auto = new Auto();
		AutoDAO auto_dao = new AutoDAO();
		String marka_resp;

		try {

			if (marka.isEmpty() || marka == null || 
				model.isEmpty() || model == null || 
				godiste < 0 || kubikaza < 0 || 
				boja.isEmpty() || boja == null || cena < 0) {

				return Response.status(Status.BAD_REQUEST).entity("POST: Prosleđene vrednosti nisu dozvoljene!").type(MediaType.TEXT_PLAIN).build();
			}

			auto.setMarka(marka);
			auto.setModel(model);
			auto.setGodiste(godiste);
			auto.setKubikaza(kubikaza);
			auto.setBoja(boja);
			auto.setCena(cena);

			auto_dao.insertAuto(auto);
			marka_resp = auto.getMarka();

		} catch (Exception e) {
			
			return Response.status(Status.BAD_REQUEST).entity("POST: Greška u prosleđenom zahtevu!").type(MediaType.TEXT_PLAIN).build();
		
		}

		URI uri = uriInfo.getAbsolutePathBuilder().path(marka_resp).build();

		return Response.created(uri).entity("POST: Uspešno realizovan zahtev!").type(MediaType.TEXT_PLAIN).build();

	}

	// http://localhost:8080/REST_WS_Java/rest_api/auto/id/{auto_id}/marka/{marka} - realizovan URI web servisa

	@PUT
	@Path("/id/{auto_id}/marka/{marka:[a-zA-Z]*}")
	public Response putAuto(@PathParam("auto_id") int auto_id, @PathParam("marka") String marka) {

		Auto auto = new Auto();
		AutoDAO auto_dao = new AutoDAO();

		auto.setAuto_id(auto_id);
		auto.setMarka(marka);
		auto_dao.updateAuto(auto);

		return Response.accepted().entity("PUT: Uspešno prosleđen zahtev!").type(MediaType.TEXT_PLAIN).build();
	}

	// http://localhost:8080/REST_WS_Java/rest_api/auto/marka/{marka}

	@DELETE
	@Path("/marka/{marka:[a-zA-Z]*}")
	public Response deleteAuto(@PathParam("marka") String marka) {

		Auto auto = new Auto();
		AutoDAO auto_dao = new AutoDAO();

		auto.setMarka(marka);
		auto_dao.deleteAuto(auto);

		return Response.accepted().entity("DELETE: Uspešno realizovan zahtev!").type(MediaType.TEXT_PLAIN).build();
	}

}