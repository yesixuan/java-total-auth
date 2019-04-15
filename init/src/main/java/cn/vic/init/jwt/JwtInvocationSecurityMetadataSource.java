package cn.vic.init.jwt;

import cn.vic.init.model.Permission;
import cn.vic.init.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by tomoya at 2018/8/12
 */
@Component
public class JwtInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

  @Autowired
  PermissionRepository permissionRepository;

  private Map<String, Collection<ConfigAttribute>> map;

  private void loadPermissions() {
    map = new HashMap<>();
//    map.put("/topic/list", Collections.singletonList(new SecurityConfig("topic:list")));
//    map.put("/user/list", Collections.singletonList(new SecurityConfig("user:list")));
//    map.put("/user/info", Collections.singletonList(new SecurityConfig("user:info")));
    List<Permission> permissions = permissionRepository.findAll();
    for (Permission permission : permissions) {
      System.out.println("999" + permission.getName() + permission.getUrl());
      map.put(permission.getUrl(), Collections.singletonList(new SecurityConfig(permission.getName())));
    }
  }

  @Override
  public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
    if (map == null) this.loadPermissions();
    FilterInvocation filterInvocation = (FilterInvocation) object;
    HttpServletRequest request = filterInvocation.getRequest();
    for (String key : map.keySet()) {
      AntPathRequestMatcher matcher = new AntPathRequestMatcher(key);
      if (matcher.matches(request)) return map.get(key);
    }
    return null;
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    return Collections.emptyList();
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return FilterInvocation.class.isAssignableFrom(clazz);
  }
}
