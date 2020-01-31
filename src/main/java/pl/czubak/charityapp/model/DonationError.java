package pl.czubak.charityapp.model;

public class DonationError {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DonationError(String name) {
        this.name = name;
    }
}
