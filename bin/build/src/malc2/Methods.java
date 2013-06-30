package malc2;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Methods {
	// http://stackoverflow.com/questions/1378199/how-to-check-if-a-url-exists-or-returns-404-with-java
	public int getResponseCode(String sit) throws MalformedURLException,
			IOException {
		URL u = new URL(sit);
		HttpURLConnection huc = (HttpURLConnection) u.openConnection();
		huc.setRequestMethod("HEAD");
		huc.connect();
		return huc.getResponseCode();
	}

	public String getImp(int papa) {
		if (papa == 404) {
			return "wrong";
		} else {
			return "good";
		}
	}

}
