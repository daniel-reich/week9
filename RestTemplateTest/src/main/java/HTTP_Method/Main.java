package HTTP_Method;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class Main {


    public static void main(String args[]) {




        try {

            URL url = new URL("https://www.eventbriteapi.com/v3//events/31106522446/attendees/?token=EQ7P7JZ6V6GBPKLZI7CE");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");

            while ((output = br.readLine()) != null) {
                System.out.println(output);

                try {
                    JSONObject jobject = new JSONObject(output);
                    JSONArray attendeesArray =jobject.getJSONArray("attendees");

                    for (int i = 0; i<attendeesArray.length(); i++){
                        JSONObject profile = attendeesArray.getJSONObject(i).getJSONObject("profile");
                        JSONObject address = profile.getJSONObject("addresses");
                        String test = "";
                        test = profile.getString("first_name");
                            System.out.println(test);
                        test = profile.getString("last_name");
                            System.out.println(test);
                        test = profile.getString("email");
                            System.out.println(test);
                        test = address.getString("home");
                            System.out.println(test);

                    }

                } catch (Exception E) {

                }
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    String websiteUrl = "https://www.eventbriteapi.com/v3//events/31106522446/attendees/?token=EQ7P7JZ6V6GBPKLZI7CE";

    public HttpURLConnection getConnection(String websiteUrl) throws IOException{

        URL url = new URL(websiteUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        return conn;
    }

    public String getDatafromHttpConnection(HttpURLConnection conn) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String output = br.readLine();
        System.out.println("Output from Server .... \n"+output);

        return output;
    }

    public void parseStringGetJsonData(String output) throws JSONException {
        JSONObject jobject = new JSONObject(output);
        JSONArray attendeesArray = jobject.getJSONArray("attendees");

        for (int i = 0; i < attendeesArray.length(); i++) {
            //Look at "changed" key, if its after date, keep

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            Date()



            int guestId = attendeesArray.getJSONObject(i).getInt("id");
            JSONObject profile = attendeesArray.getJSONObject(i).getJSONObject("profile");
            JSONObject address = profile.getJSONObject("addresses");
            String test = "";
            test = profile.getString("first_name");
            System.out.println(test);
            test = profile.getString("last_name");
            System.out.println(test);
            test = profile.getString("email");
            System.out.println(test);
            test = address.getString("home");
            System.out.println(test);

        }
    }

}
