package io.github.mrzepisko.varianteditor.security;

import io.github.mrzepisko.varianteditor.dao.UserRepository;
import io.github.mrzepisko.varianteditor.model.Roles;
import io.github.mrzepisko.varianteditor.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByIdentifier(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found %s", username)));

        return new org.springframework.security.core.userdetails.User(
                user.getIdentifier(),
                user.getPassword(),
                Stream.of(Roles.USER)
                        .map(this::getAuthority)
                        .collect(Collectors.toList()));
    }

    private GrantedAuthority getAuthority(Roles role) {
        return new SimpleGrantedAuthority(role.getValue());
    }


}
