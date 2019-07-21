package com.task.notes.api.facade.security.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {
    private long id;
    private String email;
    private String passwordHash;

    public UserPrincipal() {
        super();
    }

    public UserPrincipal(final long id, final String email, final String passwordHash) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptySet();
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // There's no need to implement complex authentication mechanisms.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // There's no need to implement complex authentication mechanisms.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // There's no need to implement complex authentication mechanisms.
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof UserPrincipal)) {
            return false;
        }

        final UserPrincipal that = (UserPrincipal) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(email, that.email)
                .append(passwordHash, that.passwordHash)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(email)
                .append(passwordHash)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("email", email)
                .append("passwordHash", passwordHash)
                .toString();
    }
}
