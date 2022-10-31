package com.ajulibe.java.SpringBootApi.service.jwt;


import com.ajulibe.java.SpringBootApi.dto.CurrentUserDTO;
import com.ajulibe.java.SpringBootApi.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final JwtUserService jwtUserService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = jwtUserService.getJwtUserByEmail(email);
        CurrentUserDTO currentUserDTO = new CurrentUserDTO(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, user.getAuthorities());
        //manually setting the email into the current user details to include email
        currentUserDTO.setEmail(email);
        return currentUserDTO;
    }
}
