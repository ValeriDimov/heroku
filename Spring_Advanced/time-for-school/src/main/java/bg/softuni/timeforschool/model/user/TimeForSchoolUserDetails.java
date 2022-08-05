package bg.softuni.timeforschool.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class TimeForSchoolUserDetails implements UserDetails {

    private final Long id;
    private final String password;
    private final String username;
    private final String name;
    private final Collection<GrantedAuthority> authorities;

    public TimeForSchoolUserDetails(
            Long id,
            String password,
            String username,
            String name,
            Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.name = name;
        this.authorities = authorities;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
