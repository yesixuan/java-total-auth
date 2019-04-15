package cn.vic.init.controller;

import cn.vic.init.service.UserService;
import cn.vic.init.util.Result;
import cn.vic.init.util.SecurityUtil;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tomoya at 2018/8/10
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private SecurityUtil securityUtil;

  @GetMapping("/list")
  public Result list(@RequestParam(defaultValue = "1") Integer pageNo) {
    return Result.success(userService.findAll(pageNo, 20));
  }

  @GetMapping("/info")
  public Result info(String username) {
    String principal = securityUtil.getPrincipal();
    Assert.isTrue(principal.equals(username), "不能查看别人的个人信息");
    return Result.success(userService.findByUsername(username));
  }

}
