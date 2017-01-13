package HTTP_Method;

//This does the same as the Main class in this package, but logic is broken out into methods.

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class UpdatedMain {

    private static String websiteUrl = "https://www.eventbriteapi.com/v3//events/31106522446/attendees/?token=EQ7P7JZ6V6GBPKLZI7CE";
    private static HashMap<String, Guest> guests = new HashMap<String, Guest>();
    private static Date lastUpdated = new Date();
    private static Date newUpdateDate = new Date();

    public static void main(String[] args) throws IOException, JSONException {

        HttpURLConnection conn = getConnection(websiteUrl);
        String output = getDataFromHttpConnection(conn);
        parseStringAndUpdateHashMap(output, guests, lastUpdated);
        lastUpdated = newUpdateDate;

    }


    public static HttpURLConnection getConnection(String websiteUrl) throws IOException {

        URL url = new URL(websiteUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        return conn;
    }

    public static String getDataFromHttpConnection(HttpURLConnection conn) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String output = br.readLine();
        System.out.println("Output from Server .... \n"+output);

        return output;
    }

    public static void parseStringAndUpdateHashMap(String output, HashMap<String, Guest> guests, Date lastUpdated) throws JSONException {
        JSONObject jobject = new JSONObject(output);
        JSONArray attendeesArray = jobject.getJSONArray("attendees");

        for (int i = 0; i < attendeesArray.length(); i++) {

            //Look at "changed" key, if its after date, keep
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date date = new Date();

            try {
                date = formatter.parse(attendeesArray.getJSONObject(i).getString("changed"));
            } catch (ParseException e){}

            if (date.after(lastUpdated)) {

                updateHashMap(guests, attendeesArray.getJSONObject(i), date);
                saveNewUpdateDate(date);
            }
        }
    }

    public static void updateHashMap(HashMap<String, Guest> guests, JSONObject attendee, Date date) throws JSONException {

        JSONObject profile = attendee.getJSONObject("profile");
        if (guests.containsKey(profile.getString("email"))){

            if (attendee.getString("status").equalsIgnoreCase("Attending")) {
                guests.get(profile.getString("email")).addTicket();
            } else if (attendee.getString("status").equalsIgnoreCase("Not Attending")){
                guests.get(profile.getString("email")).subtractTicket();
            }

        } else {
            Guest newGuest = new Guest();
            newGuest.setTickets(attendee.getInt("quantity"));
            newGuest.setDateTimeChanged(date);

            newGuest.setFirst_name(profile.getString("first_name"));
            newGuest.setLast_name(profile.getString("last_name"));
            newGuest.setEmail(profile.getString("email"));

            JSONObject address = profile.getJSONObject("addresses");
            newGuest.setAddress(address.getString("home"));

            guests.put(newGuest.getEmail(), newGuest);

        }

    }

    public static void saveNewUpdateDate(Date date){

        if (date.after(newUpdateDate)){
            newUpdateDate = date;
        }
    }






    //Getters and setters, used for testing
    public static String getWebsiteUrl() {
        return websiteUrl;
    }

    public static void setWebsiteUrl(String websiteUrl) {
        UpdatedMain.websiteUrl = websiteUrl;
    }

    public static HashMap<String, Guest> getGuests() {
        return guests;
    }

    public static void setGuests(HashMap<String, Guest> guests) {
        UpdatedMain.guests = guests;
    }

    public static Date getLastUpdated() {
        return lastUpdated;
    }

    public static void setLastUpdated(Date lastUpdated) {
        UpdatedMain.lastUpdated = lastUpdated;
    }

    public static Date getNewUpdateDate() {
        return newUpdateDate;
    }

    public static void setNewUpdateDate(Date newUpdateDate) {
        UpdatedMain.newUpdateDate = newUpdateDate;
    }
}
