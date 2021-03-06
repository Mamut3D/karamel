/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.karamel.webservice.calls.definition;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import se.kth.karamel.client.api.KaramelApi;
import se.kth.karamel.common.exception.KaramelException;
import se.kth.karamel.webservice.calls.AbstractCall;
import se.kth.karamel.webservicemodel.KaramelBoardYaml;

/**
 *
 * @author kamal
 */
@Path("/definition/yaml2json")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class YamlToJson extends AbstractCall{

  public YamlToJson(KaramelApi karamelApi) {
    super(karamelApi);
  }

  @PUT
  public Response getJSONForYaml(KaramelBoardYaml cluster) {

    Response response = null;
    try {
      String jsonClusterString = karamelApi.yamlToJson(cluster.getYml());
      response = Response.status(Response.Status.OK).entity(jsonClusterString).build();
    } catch (KaramelException e) {
      response = buildExceptionResponse(e);
    }
    return response;
  }
}
