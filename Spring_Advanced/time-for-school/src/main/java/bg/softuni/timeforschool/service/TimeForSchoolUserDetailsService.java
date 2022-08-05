package bg.softuni.timeforschool.service;

import bg.softuni.timeforschool.model.entity.UserEntity;
import bg.softuni.timeforschool.model.entity.UserRoleEntity;
import bg.softuni.timeforschool.model.user.TimeForSchoolUserDetails;
import bg.softuni.timeforschool.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class TimeForSchoolUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public TimeForSchoolUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    return userRepository.
        findByEmail(username).
        map(this::map).
        orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
  }

  private UserDetails map(UserEntity userEntity) {

    return new TimeForSchoolUserDetails(
        userEntity.getId(),
        userEntity.getPassword(),
        userEntity.getEmail(),
        userEntity.getName(),
        userEntity.
            getUserRoles().
            stream().
            map(this::map).
            toList()
    );
  }

  private GrantedAuthority map(UserRoleEntity userRole) {
    return new SimpleGrantedAuthority("ROLE_" +
        userRole.
            getUserRole().name());
  }
}
