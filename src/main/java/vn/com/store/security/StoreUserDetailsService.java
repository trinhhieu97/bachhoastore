package vn.com.store.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.store.repository.UserRepo;
import vn.com.store.repository.documents.User;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
@Transactional
public class StoreUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        // TODO set role and authority
        User user = userRepo.findByEmail(email);
        if (user==null){
            // TODO: throw exception
            return new org.springframework.security.core.userdetails.User("", "", authorities);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
