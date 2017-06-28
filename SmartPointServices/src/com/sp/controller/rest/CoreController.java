package com.sp.controller.rest;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.sp.model.spmodels.BusLinhasPontoClienteRelationship;
import com.sp.services.spservice.SPService;



@Path("/sp")

public class CoreController {
	/**
	 * @author dan
	 */
	 

	 
			@Path("/busStop/lat/{lt}/long/{lg}/linha/{ln}")

		  @GET
		  @Produces("application/json")
		  public Response bustStopController(@PathParam("lt") String latitude, @PathParam("lg")
		  String longitude, @PathParam("ln") String linha) throws JSONException {
				Gson gson = new Gson();

				BusLinhasPontoClienteRelationship bestChoice = SPService.distanceMatrixAPI(latitude,longitude, linha);
				System.out.println(bestChoice.toString());
				System.out.println(bestChoice.getPonto().getId());
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("idLinha", bestChoice.getPonto().getId());
				jsonObject.put("latitude", bestChoice.getPonto().getLatitude());
				jsonObject.put("longitude", bestChoice.getPonto().getLongitude());

	 
				String result = jsonObject.toString();
			
			return Response.status(200).entity(result).build();
		  }
	 
		  @Path("{f}")
		  @GET
		  @Produces("application/json")
		  public Response convertFtoCfromInput(@PathParam("f") float f) throws JSONException {
	 
			JSONObject jsonObject = new JSONObject();
			float celsius;
			celsius =  (f - 32)*5/9; 
			jsonObject.put("F Value", f); 
			jsonObject.put("C Value", celsius);
	 
			String result = jsonObject.toString();
			return Response.status(200).entity(result).build();
		  }
		  
//		  @GET
//			@Produces("application/json")
//			public List<Player> getBandas() {
//				return new ArrayList<Player>(playerMap.values());
//			}
//
//			@Path("{id}")
//			@GET
//			@Produces("text/xml")
//			public Player getPlayer(@PathParam("id") int id) {
//				return playerMap.get(id);
//			}
//			@POST
//			@Consumes("text/xml")
//			@Produces("text/plain")
//			public String adicionaBanda(Player player) {
//				player.setId(playerMap.size() + 1);
//				playerMap.put(player.getId(), player);
//				return player.getNome() + " adicionado.";
//			}
//
//			@Path("{id}")
//			@PUT
//			@Consumes("text/xml")
//			@Produces("text/plain")
//			public String atualizaPlayer(Player player, @PathParam("id") int id) {
//				Player atual = playerMap.get(id);
//				atual.setNome(player.getNome());
//				atual.setScore(player.getScore());
//				return player.getNome() + " atualizada.";
//			}
//
//			@Path("{id}")
//			@DELETE
//			@Produces("text/plain")
//			public String removePlayer(@PathParam("id") int id) {
//				playerMap.remove(id);
//				return "Banda removida.";
//			}
	}

