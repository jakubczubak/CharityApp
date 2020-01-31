package pl.czubak.charityapp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Sender {
  @NotNull
  @Size(min = 3, message = "Wprowadź imię")
  private String firstName;

  @NotNull
  @Size(min = 3, message = "Wprowadź nazwisko")
  private String lastName;

  @NotNull
  @Size(min = 3, message = "Wprowadź treść wiadomości")
  private String message;

  public Sender(String firstName, String lastName, String message) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.message = message;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
