package tekmob.letsnote.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by feaza on 9/27/2016.
 */
public class DummyModel {
    @SerializedName("Accept-Language")
    private String acceptLanguage;
    @SerializedName("Host")
    private String host;
    @SerializedName("Accept-Charset")
    private String acceptCharset;
    @SerializedName("Accept")
    private String accept;
    @SerializedName("User-Agent")
    private String userAgent;

    public String getUserAgent() {
        return userAgent;
    }

    public String getAccept() {
        return accept;
    }

    public String getAcceptCharset() {
        return acceptCharset;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public String getHost() {
        return host;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public void setAcceptCharset(String acceptCharset) {
        this.acceptCharset = acceptCharset;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
