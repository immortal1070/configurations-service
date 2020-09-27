package com.immortal.configurations.api;

import com.immortal.configurations.api.constants.ConfigurationsMessages;
import com.immortal.configurations.api.dto.ConfigMetadataDto;
import com.immortal.configurations.api.dto.ConfigMetadataPersistDto;
import com.webcohesion.enunciate.metadata.rs.ResponseCode;
import com.webcohesion.enunciate.metadata.rs.StatusCodes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.HttpURLConnection;
import java.util.List;

import static com.immortal.configurations.api.ConfigMetadataResource.Routing.Endpoints.ID_PATH;
import static com.immortal.configurations.api.ConfigMetadataResource.Routing.SERVICE_PREFIX;

/**
 * Service for UI languages (ones with en-US format).
 * <p>
 * language can get the SELF_TRANSLATE value and ru-RU will be translated with ru, en-US - with en, etc.
 * For not authenticated user the GDPR related data is not shown.
 */
@Path(SERVICE_PREFIX)
//@SecurityDomain(SecurityConstants.Domain.IMMORTAL)
@Produces(MediaType.APPLICATION_JSON)
//@ServiceContextRoot(ConfigurationsConstants.Rest.RootContexts.CONFIGURATIONS)
//@RestContextRoot(ConfigurationsConstants.Rest.RootContexts.CONFIGURATIONS)
//@RolesAllowed(JaasRights.MANAGE_CONFIGURATION_METADATA)
public interface ConfigMetadataResource {
    interface Routing {
        String SERVICE_PREFIX = "/metadata";

        interface Endpoints {
            String ID_PATH = "/{" + Params.ID + "}";
        }
    }

    interface Params {
        String ID = "ID";
    }

    /**
     * Returns ConfigMetadata by id
     */
    @GET
    @Path(ID_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @StatusCodes({@ResponseCode(code = HttpURLConnection.HTTP_OK, condition = ConfigurationsMessages.SUCCESS),
        @ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = "entity not found")})
    ConfigMetadataDto findById(@PathParam(Params.ID) String id);

    /**
     * Returns all ConfigMetadata
     * <p>
     * TODO add paging to find method
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @StatusCodes(@ResponseCode(code = HttpURLConnection.HTTP_OK, condition = ConfigurationsMessages.SUCCESS))
    List<ConfigMetadataDto> find();

    /**
     * Creates new ConfigMetadata.
     */
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @StatusCodes({@ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = ConfigurationsMessages.SUCCESS),
        @ResponseCode(code = HttpURLConnection.HTTP_CONFLICT, condition = "input validation failed")})
    ConfigMetadataDto register(ConfigMetadataPersistDto configMetadataPersistDto);

    /**
     * Deletes ConfigMetadata.
     */
    @DELETE
    @Path(ID_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    @StatusCodes({@ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = ConfigurationsMessages.SUCCESS),
        @ResponseCode(code = HttpURLConnection.HTTP_CONFLICT, condition = "input validation failed")})
    void delete(@PathParam(Params.ID) String id);
}
