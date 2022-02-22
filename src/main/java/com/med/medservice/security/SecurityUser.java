package com.med.medservice.security;

import com.med.medservice.domain.Doctors;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import static com.med.medservice.model.UserStatus.ACTIVE;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityUser implements UserDetails {
    String username;
    String password;
    boolean isActive;
    List<SimpleGrantedAuthority> authorities;

    public SecurityUser(List<SimpleGrantedAuthority> authorities, String username, String password, boolean isActive) {
        this.authorities = authorities;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(Doctors doctors) {
        return new User(
                doctors.getEmail(),
                doctors.getPassword(),
                doctors.getStatus().equals(ACTIVE),
                doctors.getStatus().equals(ACTIVE),
                doctors.getStatus().equals(ACTIVE),
                doctors.getStatus().equals(ACTIVE),
                doctors.getRole().getAuthorities()
        );
    }
}
