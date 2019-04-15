package cn.vic.init.jwt;

import cn.vic.init.model.Permission;
import cn.vic.init.model.Role;
import cn.vic.init.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tomoya at 2018/8/10
 */
public class JwtUserFactory {

  private JwtUserFactory() {
  }

  public static JwtUser create(User user) {
    return new JwtUser(
        user.getId(),
        user.getUsername(),
        user.getPassword(),
        user.getEmail(),
        mapToGrantedAuthorities(user.getRoles()),
        user.getLastPasswordResetDate()
    );
  }

  private static Collection<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    for (Role role : roles) {
      for (Permission permission : role.getPermissions()) {
        grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
      }
    }
    return grantedAuthorities;
  }
}
