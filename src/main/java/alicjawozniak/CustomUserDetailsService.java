package alicjawozniak;

import alicjawozniak.model.Admin;
import alicjawozniak.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Optional<Admin> userFromDataBase = adminRepository.findByLogin(username);
        return userFromDataBase.map(admin -> {
            return new org.springframework.security.core.userdetails.User(admin.getLogin(), admin.getPassword(), true,
                    true, true, true, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }).orElseThrow(() -> new UsernameNotFoundException("UsernameNotFound"));
    }
}

