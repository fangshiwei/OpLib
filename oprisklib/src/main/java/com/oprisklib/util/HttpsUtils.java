package com.oprisklib.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONObject;
import org.json.JSONTokener;

public class HttpsUtils {

		
	private static class TrustAnyTrustManager implements X509TrustManager {
		 
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
 
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
 
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }
 
    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
    
    
    public static JSONObject get(String url) throws IOException, KeyManagementException, NoSuchAlgorithmException{
    	JSONObject response = null;  
    	
    	 SSLContext sc = SSLContext.getInstance("SSL");
         sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
                 new java.security.SecureRandom());
  
         URL console = new URL(url);
         HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
         conn.setSSLSocketFactory(sc.getSocketFactory());
         conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
         conn.setDoOutput(true);
         conn.connect();
         DataOutputStream out = new DataOutputStream(conn.getOutputStream());
         out.flush();
         out.close();
         
         conn.disconnect();
         
         if(conn.getResponseCode() == 200){
        	 InputStream is = conn.getInputStream();
             response = new JSONObject(new JSONTokener(is));
             response.put("msg", "success");
             return response;
         }else{
        	 InputStream is = conn.getErrorStream();
        	 response = new JSONObject(new JSONTokener(is));
        	 return response;
         }
        
         
    	
    }
    
    public static JSONObject postJson(String url, String content) throws Exception{
    	JSONObject response = null;  
    	
   	 SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
                new java.security.SecureRandom());
 
        URL console = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
        conn.setSSLSocketFactory(sc.getSocketFactory());
        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
        conn.setDoOutput(true);
        conn.connect();
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.write(content.getBytes());
        out.flush();
        out.close();
        
        InputStream is = conn.getInputStream();
        
        response = new JSONObject(new JSONTokener(is));  
        
        return response;
    }
    
    public static byte[] post(String url, String content) throws KeyManagementException, NoSuchAlgorithmException, IOException{
    	return post(url, content, "UTF-8");
    }
    
    public static byte[] post(String url, String content, String charset)
            throws NoSuchAlgorithmException, KeyManagementException,
            IOException {
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
                new java.security.SecureRandom());
 
        URL console = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
        conn.setSSLSocketFactory(sc.getSocketFactory());
        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
        conn.setDoOutput(true);
        conn.connect();
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.write(content.getBytes(charset));
        out.flush();
        out.close();
        InputStream is = conn.getInputStream();
        if (is != null) {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            is.close();
            return outStream.toByteArray();
        }
        return null;
    }
    

}
