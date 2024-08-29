package org.acme.auth;

import io.quarkus.security.credential.Credential;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.mutiny.Uni;

import java.security.Permission;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class RoleSecurityIdentity implements SecurityIdentity {

    private final Principal principal;
    private final Set<String> roles;

    public RoleSecurityIdentity(Principal principal, Set<String> roles) {
        this.principal = principal;
        this.roles = roles;
    }

    @Override
    public Principal getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAnonymous() {
        return principal == null;
    }

    @Override
    public Set<String> getRoles() {
        return roles;
    }

    @Override
    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    @Override
    public <T extends Credential> T getCredential(Class<T> credentialClass) {
        return null;
    }

    @Override
    public Set<Credential> getCredentials() {
        return Collections.emptySet();
    }

    @Override
    public <T> T getAttribute(String name) {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.emptyMap();
    }

    @Override
    public Uni<Boolean> checkPermission(Permission permission) {
        return Uni.createFrom().item(true);
    }
}


