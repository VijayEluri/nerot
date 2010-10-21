package nerot.task;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Performs an HTTP GET request.
 */
public class HttpGetTask extends BaseTask {

    private static final Logger LOG = LoggerFactory.getLogger(HttpGetTask.class);

    private String url;

    /**
     * Gets the page (if HTTP status code < 300), and sets the result in the Store.
     */
    public Object doExecute() throws java.io.IOException, java.net.MalformedURLException, com.sun.syndication.io.FeedException {
        Object responseBody = null;
        HttpClient httpclient = null;
        try {
            httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            // this will throw HttpResponseException if HTTP status code >= 300
            responseBody = httpclient.execute(httpget, responseHandler);
        } finally {
            if (httpclient != null) {
                httpclient.getConnectionManager().shutdown();
            }
        }
        return responseBody;
    }

    /**
     * Gets the URL.
     *
     * @return the URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL.
     *
     * @param url the URL
     */
    public void setUrl(String url) {
        this.url = url;
    }
}