
package javafxcuponex.modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ConsumirServiciosWeb {
     public static String get(String url) throws IOException  {
        URL accessUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) accessUrl.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK) {
            return bytesToString(connection.getInputStream());
        }
        return "Error en la peticion GET con código: " + responseCode;
    }
    
    public static String post(String url, String parametros) throws IOException {
        URL accessUrl = new URL(url);
        HttpURLConnection connection =  (HttpURLConnection) accessUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        
        OutputStream oStream = connection.getOutputStream();
        oStream.write(parametros.getBytes());
        oStream.flush();
        oStream.close();
        
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK) {
            return bytesToString(connection.getInputStream());
        }
        return "Error en la peticion POST con código: " + responseCode;
    }
    
    public static String put(String url, String parametros) throws IOException {
        URL accessUrl = new URL(url);
        HttpURLConnection connection =  (HttpURLConnection) accessUrl.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        
        OutputStream oStream = connection.getOutputStream();
        oStream.write(parametros.getBytes());
        oStream.flush();
        oStream.close();
        
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK) {
            return bytesToString(connection.getInputStream());
        }
        return "Error en la peticion PUT con código: " + responseCode;
    }
    
    public static String delete(String url, String parametros) throws IOException {
        URL accessUrl = new URL(url);
        HttpURLConnection connection =  (HttpURLConnection) accessUrl.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);
        
        OutputStream oStream = connection.getOutputStream();
        oStream.write(parametros.getBytes());
        oStream.flush();
        oStream.close();
        
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK) {
            return bytesToString(connection.getInputStream());
        }
        return "Error en la peticion DELETE con código: " + responseCode;
    }
    
    private static String bytesToString(InputStream byteStream) throws IOException {
        InputStreamReader isr = new InputStreamReader(byteStream);
        BufferedReader br = new BufferedReader(isr);
        String inputLine;
        StringBuilder response = new StringBuilder();
        while((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();
        return response.toString();
    }
}
    
    
    
    
    
    

