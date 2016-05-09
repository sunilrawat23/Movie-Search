package com.example.sunil.mymovie;

import android.content.Context;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientWritten {
	private String url;
    private HttpURLConnection con;
    private OutputStream os;
    Context context = null;
    public static final int TIME_OUT_CONNECTION = 50000;
	public static final int TIME_OUT_SOCKET_LIMIT = 120000;	
    
	private String delimiter = "--";
    private String boundary =  "SwA"+Long.toString(System.currentTimeMillis())+"SwA";

	public HttpClientWritten(String url,Context context) {		
		this.url = url;
		this.context = context;
	}


	public void connectForMultipart() throws Exception {
		con = (HttpURLConnection) ( new URL(url)).openConnection();
		con.setRequestMethod("POST");
		con.setDoInput(true);
		HttpParams httpParameters = new BasicHttpParams();	
		HttpConnectionParams.setConnectionTimeout(httpParameters, TIME_OUT_CONNECTION);
		// Set the default socket timeout (SO_TIMEOUT) 
		// in milliseconds which is the timeout for waiting for data.		
		HttpConnectionParams.setSoTimeout(httpParameters, TIME_OUT_SOCKET_LIMIT);
		con.setDoOutput(true);
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Accept-Charset", "UTF-8");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		con.connect();
		os = con.getOutputStream();
	}
	

	
	public void finishMultipart() throws Exception {
		os.write( (delimiter + boundary + delimiter + "\r\n").getBytes());
	}
	
	
	public String getResponse() throws Exception {
		
		String responseData = null;

		try {
			StringBuilder stringBuilder = new StringBuilder();

			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine())!= null) {
                stringBuilder.append(line);
            }
			responseData = stringBuilder.toString();
			con.disconnect();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseData;
	}
}
