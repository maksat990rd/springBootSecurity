package peaksoft.springbootsecurity.serviceImple;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import peaksoft.springbootsecurity.model.modelUsers.SecurityUser;
import peaksoft.springbootsecurity.model.modelUsers.User;
import peaksoft.springbootsecurity.repositoryImpl.repository.UserRepository;

public class UserDetailServicImpl implements UserDetailsService {


@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        return new SecurityUser(user);
    }
}
