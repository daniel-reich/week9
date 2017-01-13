import HTTP_Method.Guest;
import HTTP_Method.UpdatedMain;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class UpdatedMainTest {

    @Test
    public void whenTicketIsRecievedTicketCountIncreasesByOne() {
        //Arrange
        Guest guest = new Guest();

        //Act
        guest.addTicket();

        //Assert
        assertThat(guest.getTickets(), equalTo(1));

    }

    @Test
    public void whenTicketIsSubtractedTicketCountDecreasesByOne() {
        //Arrange
        Guest guest = new Guest();

        //Act
        guest.subtractTicket();

        //Assert
        assertThat(guest.getTickets(), equalTo(-1));

    }

    @Test
    public void whenNewUpdateDateMeetsLaterDateItChangesToMatchLaterDate() throws ParseException {
        //Arrange
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        UpdatedMain updatedMain = new UpdatedMain();
        Date newDate = formatter.parse("2017-01-13T18:34:32Z");
        updatedMain.setLastUpdated(formatter.parse("2017-01-12T18:34:32Z"));

        //Act
        updatedMain.saveNewUpdateDate(newDate);

        //Assert
        assertThat(updatedMain.getLastUpdated(), equalTo(newDate));


    }

    @Test
    public void whenNewUpdateDateMeetsEarlierDateItDoesNotChange() throws ParseException {
        //Arrange
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        UpdatedMain updatedMain = new UpdatedMain();
        Date newDate = formatter.parse("2017-01-11T18:34:32Z");
        updatedMain.setLastUpdated(formatter.parse("2017-01-12T18:34:32Z"));

        //Act
        updatedMain.saveNewUpdateDate(newDate);

        //Assert
        assertThat(updatedMain.getLastUpdated(), equalTo(formatter.parse("2017-01-12T18:34:32Z")));


    }


}
