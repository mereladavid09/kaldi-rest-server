package org.acme.auth;

import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.IdentityProvider;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.models.Support;
import org.acme.models.User;
import org.acme.services.AuthService;
import org.acme.services.SupportService;
import org.acme.services.UserService;

import java.util.Set;

@ApplicationScoped
public class RoleIdentityProvider implements IdentityProvider<UsernamePasswordAuthenticationRequest> {
    @Inject
    SupportService supportService;

    @Inject
    AuthService authService;

    @Inject
    UserService userService;
    @Override
    public Class<UsernamePasswordAuthenticationRequest> getRequestType() {
        return UsernamePasswordAuthenticationRequest.class;
    }

    @Override
    public Uni<SecurityIdentity> authenticate(UsernamePasswordAuthenticationRequest authenticationRequest, AuthenticationRequestContext authenticationRequestContext) {
        if (authenticationRequest instanceof UsernamePasswordAuthenticationRequest) {
            UsernamePasswordAuthenticationRequest upRequest = (UsernamePasswordAuthenticationRequest) authenticationRequest;
            if (authService.validateSupport(upRequest.getUsername(),new String(upRequest.getPassword().getPassword()))) {
                Support support = supportService.getSupportByUsername(upRequest.getUsername());
                SecurityIdentity supportIdentity = new RoleSecurityIdentity(
                        () -> upRequest.getUsername(),
                        Set.of(support.getRole())
                );
                return Uni.createFrom().item(supportIdentity);
            }
            else if(authService.validateUser(upRequest.getUsername(),new String(upRequest.getPassword().getPassword()))){
                User user = userService.getUserByUsername(upRequest.getUsername());
                SecurityIdentity userIdentity = new RoleSecurityIdentity(
                        () -> upRequest.getUsername(),
                        Set.of(user.getRole())
                );
                return Uni.createFrom().item(userIdentity);
            }
        }
        return Uni.createFrom().failure(new RuntimeException("Authentication failed"));
    }

    @Override
    public int priority() {
        return IdentityProvider.super.priority();
    }


}
