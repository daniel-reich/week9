package HTTP_Method;

import java.util.Date;

/**
 * Created by Daniel on 1/13/17.
 */
public class Guest {
    private Date dateTimeChanged;
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private int tickets;


    public void addTicket() {
        this.tickets = this.tickets+1;
    }

    public void subtractTicket() {
        this.tickets = this.tickets-1;
    }






    public Date getDateTimeChanged() {
        return dateTimeChanged;
    }

    public void setDateTimeChanged(Date dateTimeChanged) {
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
