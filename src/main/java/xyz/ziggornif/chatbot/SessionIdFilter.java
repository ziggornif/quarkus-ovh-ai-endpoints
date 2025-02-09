package xyz.ziggornif.chatbot;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;
import org.jboss.resteasy.reactive.server.spi.ServerRequestContext;


import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Provider
public class SessionIdFilter implements ContainerResponseFilter {
  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
    // Check for the X-Session-ID in the request headers
    String sessionId = requestContext.getHeaderString("X-Session-ID");

    // If it doesn't exist, create a new UUID
    if (sessionId == null || sessionId.isEmpty()) {
      sessionId = UUID.randomUUID().toString();
    }

    // Set the X-Session-ID in the response headers
    responseContext.getHeaders().add("X-Session-ID", sessionId);
  }
}
