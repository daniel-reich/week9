package HTTP_Method;

/**
 * Created by Daniel on 1/13/17.
 */
public class Guest {
    private int guestId;
    private String dateTimeChanged;
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private int tickets;

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getDateTimeChanged() {
        return dateTimeChanged;
    }

    public void setDateTimeChanged(String dateTimeChanged) {
        this.dateTimeChanged = dateTimeChanged;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }
}
