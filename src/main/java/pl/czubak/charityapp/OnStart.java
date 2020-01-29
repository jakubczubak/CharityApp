package pl.czubak.charityapp;

import org.springframework.stereotype.Component;
import pl.czubak.charityapp.entity.User;
import pl.czubak.charityapp.service.UserService;

@Component
public class OnStart {
  private UserService userService;

  public OnStart(UserService userService) {
    this.userService = userService;
    userService.createDefaultAdminAccount();
    userService.createDefaultUserAccount();
  }
}
