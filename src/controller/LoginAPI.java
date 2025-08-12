package controller;
import java.io.*;
import java.net.*;
import org.json.JSONObject;
import view.Dashboard;

public class LoginAPI {

    private static final String API_URL = "https://galaxyx.lk/n2nz/apii/login.php";

    public static JSONObject loginUser(String email, String password) {
        try {
            // Prepare connection
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Build JSON request
            JSONObject requestJson = new JSONObject();
            requestJson.put("email", email);
            requestJson.put("password", password);

            // Send request
            try (OutputStream os = conn.getOutputStream()) {
                os.write(requestJson.toString().getBytes());
            }

            // Read response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // Parse JSON
            return new JSONObject(response.toString());

        } catch (Exception e) {
            JSONObject errorJson = new JSONObject();
            errorJson.put("success", false);
            errorJson.put("message", "Error: " + e.getMessage());
            return errorJson;
        }
    }

    public static void main(String[] args) {
        // Example usage
        JSONObject result = loginUser("test@example.com", "mypassword");

        if (result.getBoolean("success")) {
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
            System.out.println("Token: " + result.getString("token"));
        } else {
            System.out.println("❌ Login Failed: " + result.getString("message"));
        }
    }
}
