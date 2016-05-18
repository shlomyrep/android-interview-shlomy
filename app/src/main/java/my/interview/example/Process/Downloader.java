package my.interview.example.Process;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Downloader {
    public Downloader() {
    }

    public String getJsonDataFromUrl(String url) {
        HttpClient client = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(url);
        try {
            HttpResponse httpResponse = client.execute(getRequest);
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode != 200) {
                return null;
            }
            InputStream jsonStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jsonStream));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            //Create the json text
            return builder.toString();
        } catch (ClientProtocolException e) {
            AppHelper.Logger(e.toString());
        } catch (IOException e) {
            AppHelper.Logger(e.toString());
        }
        return null;
    }
}
