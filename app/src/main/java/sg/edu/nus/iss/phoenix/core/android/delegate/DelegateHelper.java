package sg.edu.nus.iss.phoenix.core.android.delegate;

public class DelegateHelper {
    final static String HOST = "192.168.1.22:8080";

    // RESTful parameters.
    public final static String PRMS_BASE_URL_AUTHENTICATE = String.format("http://%s/phoenix/rest/Login/doLogin?", HOST);
    public final static String PRMS_BASE_URL_RADIO_PROGRAM = String.format("http://%s/phoenix/rest/radioprogram", HOST);
    public final static String PRMS_BASE_URL_USER = String.format("http://%s/phoenix/rest/user", HOST);
    public final static String PRMS_BASE_URL_PROGRAM_SLOT = String.format("http://%s/phoenix/rest/schedule", HOST);
    public final static String PRMS_BASE_URL_PRODUCER = String.format("http://%s/phoenix/rest/producer", HOST);
    public final static String PRMS_BASE_URL_PRESENTER = String.format("http://%s/phoenix/rest/presentor", HOST);
}
