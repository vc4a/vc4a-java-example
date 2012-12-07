package biz.vc4africa.java;

import java.net.URLEncoder;
import java.util.ArrayList;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author ebot tabi <ebot.tabi@gmail.com>
 */
public class Rest implements Constants{

    private ArrayList<NameValuePair> params;
    private ArrayList<NameValuePair> headers;
    private int responseCode;
    private String message;
    private String response;

    public String getResponse() {
        return response;
    }

    public String getErrorMessage() {
        return message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public Rest() {
        
        params = new ArrayList<NameValuePair>();
        headers = new ArrayList<NameValuePair>();
    }

    public void AddParam(String name, String value) {
        params.add(new BasicNameValuePair(name, value));
    }

    public String Execute(Methods method, String url) throws Exception {
        String results = null;
        switch (method) {
            case GET: {
                // add parameters
                String combinedParams = "";
                if (!params.isEmpty()) {
                    combinedParams += "?";
                    for (NameValuePair p : params) {
                        String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(), "UTF-8");
                        if (combinedParams.length() > 1) {
                            combinedParams += "&" + paramString;
                        } else {
                            combinedParams += paramString;
                        }
                    }
                }
                results = executeRequest(url + combinedParams);
                break;
            }

        }
        
        return results;
    }

    private String executeRequest(String url) {
        String responseBody = null;
        try {

            HttpClient client = new HttpClient();

            GetMethod getMethod = new GetMethod(url);

            int status = client.executeMethod(getMethod);
            System.out.println("status: " + status);
            responseBody = getMethod.getResponseBodyAsString();
            System.out.println("responseBody: " + responseBody);

            Header wwAuthHeader = getMethod.getResponseHeader("WWW-Authenticate");
            for (HeaderElement element : wwAuthHeader.getElements()) {
                System.out.println(element.getName() + ":" + element.getValue());
            }

            UsernamePasswordCredentials upc = new UsernamePasswordCredentials(PARTNER_ID, API_KEY);
            AuthScope as = new AuthScope(API_HOST, HOST_PORT);
            client.getState().setCredentials(as, upc);
            status = client.executeMethod(getMethod);
            System.out.println("status: " + status);
            responseBody = getMethod.getResponseBodyAsString();
            //System.out.println("responseBody: " + responseBody);

            getMethod.releaseConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return responseBody;
    }
}
