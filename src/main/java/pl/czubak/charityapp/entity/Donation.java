package pl.czubak.charityapp.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "donations")
public class Donation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Min(1)
  private int quantity;

  @ManyToMany(fetch = FetchType.EAGER)
  @NotNull(message = "Zaznacz co chcesz oddać!")
  private List<Category> categories;
  @NotNull(message = "Wybierz instytucję którą chcesz wesprzeć!")
  @ManyToOne private Institution institution;
  @NotEmpty(message = "Brak nazwy ulicy")
  private String street;
  @NotEmpty(message = "Brak miejsca zamieszkania")
  private String city;
  @NotEmpty(message = "Podaj kod pocztowy")
  private String zipCode;

  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Future(message = "Niepoprawna data odbioru darowizny")
  @NotNull(message = "Podaj datę odbioru darowizny przez kuriera")
  private Date pickUpDate;

  @DateTimeFormat(pattern = "HH:mm")
  @Temporal(TemporalType.TIME)
  @NotNull(message = "Podaj czas odebrania darowizny")
  private Date pickUpTime;

  private String pickUpComment;
  @NotEmpty(message = "Podaj numer telefonu")
  @Pattern(regexp = "[0-9]{9}", message = "Niepoprawny nr tel.")
  private String phoneNumber;
  @ManyToOne private User user;
  @ManyToOne private Status status;
  private boolean isArchived;
  private LocalDateTime created;
  private LocalDateTime updated;

  @PreUpdate
  public void preUpdate() {
    updated = LocalDateTime.now();
  }

  public LocalDateTime getUpdated() {
    return updated;
  }

  @PrePersist
  public void prePersist() {
    created = LocalDateTime.now();
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public boolean isArchived() {
    return isArchived;
  }

  public void setArchived(boolean archived) {
    isArchived = archived;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public Institution getInstitution() {
    return institution;
  }

  public void setInstitution(Institution institution) {
    this.institution = institution;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public Date getPickUpDate() {
    return pickUpDate;
  }

  public void setPickUpDate(Date pickUpDate) {
    this.pickUpDate = pickUpDate;
  }

  public Date getPickUpTime() {
    return pickUpTime;
  }

  public void setPickUpTime(Date pickUpTime) {
    this.pickUpTime = pickUpTime;
  }

  public String getPickUpComment() {
    return pickUpComment;
  }

  public void setPickUpComment(String pickUpComment) {
    this.pickUpComment = pickUpComment;
  }
}
