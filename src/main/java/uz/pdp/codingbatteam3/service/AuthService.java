package uz.pdp.codingbatteam3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatteam3.entity.UserEntity;
import uz.pdp.codingbatteam3.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(email);
        return optionalUserEntity.orElseThrow(() ->
                new UsernameNotFoundException(String.format("email %s not found", email))
        );
    }
}
