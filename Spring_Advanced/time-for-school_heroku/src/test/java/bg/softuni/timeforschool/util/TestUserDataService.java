package bg.softuni.timeforschool.util;

import bg.softuni.timeforschool.model.user.TimeForSchoolUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TestUserDataService implements UserDetailsService {
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    return new TimeForSchoolUserDetails(1L,
            "topsecret",
            username,
            "Admin Adminov",
            Collections.emptyList());

  }
}
