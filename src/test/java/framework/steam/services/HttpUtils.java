package framework.steam.services;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;

/**
 * Created by User on 15.06.2017.
 */
public class HttpUtils {
    public HttpURLConnection getConnection(String href)  {
        URL url = null;
        try {
            url = new URL(href);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String loginAndPassword = "login" + ":" + "password";

        String encoding = Base64.getEncoder().encodeToString(
                loginAndPassword.getBytes());

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url
                    .openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic " + encoding);
        return  connection;
    }

    public long getSizeOfContent(String href,String path)  {
        HttpURLConnection connection=getConnection(href);
        long size = connection.getContentLength();
        downLoadFile(connection,path);
        return size;
    }

    public  void downLoadFile(HttpURLConnection connection,String path) {
        InputStream content = null;
        try {
            content = (InputStream) connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(path);
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            IOUtils.copy(content, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            content.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
