package pl.czubak.charityapp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PasswordDTO {
  @NotNull
  @Size(min = 3, message = "Podaj stare hasło!")
  private String oldPassword;

  @NotNull
  @Size(min = 3, message = "Podaj nowe hasło, min. 3 znaki!")
  private String password;

  @NotNull
  @Size(min = 3, message = "Powtórz nowe hasło, min. 3 znaki!")
  private String rePassword;

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRePassword() {
    return rePassword;
  }

  public void setRePassword(String rePassword) {
    this.rePassword = rePassword;
  }
}
