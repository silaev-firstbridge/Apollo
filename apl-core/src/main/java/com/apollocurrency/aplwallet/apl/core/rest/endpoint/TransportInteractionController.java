/*
 * Copyright © 2018-2019 Apollo Foundation
 */

package com.apollocurrency.aplwallet.apl.core.rest.endpoint;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.apollocurrency.aplwallet.api.response.ResponseDone;
import com.apollocurrency.aplwallet.api.response.TransportStatusResponse;
import com.apollocurrency.aplwallet.apl.core.http.AdminPasswordVerifier;
import com.apollocurrency.aplwallet.apl.core.rest.service.TransportInteractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import lombok.Setter;
import org.jboss.resteasy.annotations.jaxrs.FormParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Path("/transport")
public class TransportInteractionController {

    private static final Logger log = LoggerFactory.getLogger(TransportInteractionController.class);

    @Inject @Setter
    private TransportInteractionService  tiService;
    
    @Inject @Setter
    private AdminPasswordVerifier adminPasswordVerifier;
     
    public TransportInteractionController( ) {        
    }

    @Path("/connectionstatus")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns transport status",
            description = "Returns transport status in JSON format.",
            tags = {"securetransport"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful execution",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TransportStatusResponse.class)))
            }
    )    
    public TransportStatusResponse getTransportStatusResponse(){                                
        TransportStatusResponse transportStatusResponse = tiService.getTransportStatusResponse();                
        return transportStatusResponse;        
    }
           
    @GET
    @Path("/connect")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(tags = {"securetransport"}, summary = "connect to remote", description = "initiate connection process")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful exchage"),
            @ApiResponse(responseCode = "200", description = "Unexpected error") })
    public ResponseDone connect(    @Parameter(description = "adminPassword.") @QueryParam("adminPassword") String adminPassword,                               
                                @Context HttpServletRequest req) throws NotFoundException {
        
        ResponseDone response = new ResponseDone();
        if (adminPasswordVerifier.checkPassword(req)) {          
            if ( tiService.startSecureTransport()) {                
                response.setDone(Boolean.TRUE);                                
                return response;   
            } else {
                response.setDone(Boolean.FALSE);
                response.errorCode = -2L;
                response.errorDescription = "Transport hasn't been started";   
                return response;
            }
        }         
        response.setDone(Boolean.FALSE);
        response.errorCode = -1L;
        response.errorDescription = "wrong admin password";
        return response;        
    }

    @GET
    @Path("/disconnect")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(tags = {"securetransport"}, summary = "connect to remote", description = "initiate connection process")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful exchage"),
            @ApiResponse(responseCode = "200", description = "Unexpected error") })
    public ResponseDone disconnect(   @Parameter(description = "adminPassword.") @QueryParam("adminPassword") String adminPassword,                               
                                @Context HttpServletRequest req) throws NotFoundException {
        
        ResponseDone response = new ResponseDone();
        if (adminPasswordVerifier.checkPassword(req)) {          
            if ( tiService.stopSecureTransport()) {                
                response.setDone(Boolean.TRUE);                                
                return response;   
            } else {
                response.setDone(Boolean.FALSE);
                response.errorCode = -2L;
                response.errorDescription = "Transport service hasn't been started";              
            }
        }         
        response.setDone(Boolean.FALSE);
        response.errorCode = -1L;
        response.errorDescription = "wrong admin password";
        return response;        
    }

    
/*    
    @Path("/connect")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "connect to remote",
            description = "Returns transport status in JSON format.",
            tags = {"securetransport"})
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Successful execution",
//                            content = @Content(mediaType = "application/json",
//                                    schema = @Schema(implementation = ResponseDone.class)))
 //           }
            @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction hash"),
            @ApiResponse(responseCode = "200", description = "Unexpected error") })
     
    public ResponseDone startSecureTransport( @NotNull @Parameter(description = "administrator's password") @FormParam("adminPassword") String adminPassword, 
            @Context HttpServletRequest req) {       
        ResponseDone response = new ResponseDone();
        if (adminPasswordVerifier.checkPassword(req)) {          
            tiService.startSecureTransport();
            response.setDone(Boolean.TRUE);        
            return response;   
        }         
        response.setDone(Boolean.FALSE);
        response.errorCode = -1L;
        response.errorDescription = "wrong admin password";
        return response;
     
    }

    @Path("/disconnect")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "connect to remote",
            description = "Returns transport status in JSON format.",
            tags = {"securetransport"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful execution",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDone.class)))
            }
    ) 
    
    public ResponseDone stopSecureTransport( @NotNull @Parameter(description = "administrator's password") @FormParam("adminPassword") String adminPassword, 
            @Context HttpServletRequest req ) {   
                
        ResponseDone response = new ResponseDone();
        if (adminPasswordVerifier.checkPassword(req)) {          
            tiService.stopSecureTransport();
            response.setDone(Boolean.TRUE);        
            return response;   
        }         
        response.setDone(Boolean.FALSE);
        response.errorCode = -1L;
        response.errorDescription = "wrong admin password";
        return response;
    }
*/

}