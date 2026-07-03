    package net.java.Springbt_restapi.service.impl;

    import lombok.AllArgsConstructor;
    import net.java.Springbt_restapi.entity.UserEntity;
    import net.java.Springbt_restapi.entity.Users;
    import net.java.Springbt_restapi.enums.CredentialStatus;
    import net.java.Springbt_restapi.repository.UserCredentialRepository;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    @AllArgsConstructor
    public class DatabaseUserDetailsService implements UserDetailsService {

        private final UserCredentialRepository userCredentialRepository;

        @Override
        public UserDetails loadUserByUsername(String eeid) throws UsernameNotFoundException {
            Users credential = userCredentialRepository.findByUserEntity_Eeid(eeid)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found for employee ID: " + eeid));

            UserEntity user = credential.getUserEntity();

            List<GrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority(user.getRole().name()));

            System.out.println(authorities);

            boolean enabled = credential.getStatus() == CredentialStatus.ACTIVE;

            System.out.println("Loading" +eeid);

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEeid())
                    .password(credential.getPasswordHash())
                    .authorities(authorities)
                    .accountLocked(!user.isAccountNonLocked())
                    .accountExpired(!user.isAccountNonExpired())
                    .credentialsExpired(!user.isCredentialsNonExpired())
                    .disabled(!enabled)
                    .build();
        }
    }
