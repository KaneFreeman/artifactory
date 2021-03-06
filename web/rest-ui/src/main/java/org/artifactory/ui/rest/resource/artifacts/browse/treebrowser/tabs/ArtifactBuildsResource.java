package org.artifactory.ui.rest.resource.artifacts.browse.treebrowser.tabs;

import org.artifactory.api.security.AuthorizationService;
import org.artifactory.rest.common.service.RestResponse;
import org.artifactory.ui.rest.resource.BaseResource;
import org.artifactory.ui.rest.service.artifacts.browse.BrowseServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Chen Keinan
 */
@Path("artifactbuilds")
@RolesAllowed({AuthorizationService.ROLE_ADMIN,AuthorizationService.ROLE_USER})
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ArtifactBuildsResource extends BaseResource {

    @Autowired
    BrowseServiceFactory browseFactory;

    @Autowired
    @Qualifier("streamingRestResponse")
    public void setArtifactoryResponse(RestResponse artifactoryResponse) {
        this.artifactoryResponse = artifactoryResponse;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArtifactBuild()
            throws Exception {
        return runService(browseFactory.getArtifactBuildsService());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("json{id:(/[^/]+?)?}")
    public Response getArtifactBuildJson()
            throws Exception {
        return runService(browseFactory.getArtifactBuildJsonService());
    }
}
