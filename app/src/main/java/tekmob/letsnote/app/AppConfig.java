package tekmob.letsnote.app;

/**
 * Created by azhardz on 10/6/16.
 */

public class AppConfig {
    // URL BASE
    public static String URL_BASE = "http://ec2-52-207-209-250.compute-1.amazonaws.com/letsnote";

    //Login
    public static String URL_LOGIN = URL_BASE + "/login.php";

    //Create Notes
    public static String URL_REGISTER = URL_BASE + "/register.php";

    //Register
    public static String URL_CREATE_NOTES = URL_BASE + "/create_notes.php";

    //timeline
    public static String URL_TIMELINE = URL_BASE + "/getTimeline.php";

}
