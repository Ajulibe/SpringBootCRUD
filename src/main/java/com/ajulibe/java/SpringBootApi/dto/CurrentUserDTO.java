package com.ajulibe.java.SpringBootApi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/*
 * This class extends the basic spring User details class to enable
 * us build a more robust class containing all user required properties
 * */

@Getter
@Setter
public class CurrentUserDTO extends User {
    public CurrentUserDTO(String username, String password, boolean enabled, boolean accountNonExpired,
                          boolean credentialsNonExpired, boolean accountNonLocked,
                          Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    private String Email;
}
