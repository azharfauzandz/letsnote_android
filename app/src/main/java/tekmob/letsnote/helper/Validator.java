package tekmob.letsnote.helper;

import android.support.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by azhardz on 10/4/16.
 */

public class Validator {
    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String FULLNAME_REGEX = "^[\\p{L} .'-]+$";
    private static Pattern patternEmail = Pattern.compile(EMAIL_REGEX);
    private static Pattern patternName = Pattern.compile(FULLNAME_REGEX);

    @NonNull
    public static String isValidEmail(String email){
        Matcher matcher = patternEmail.matcher(email);
        if (email.equalsIgnoreCase("")){
            return "empty";
        }else if(!matcher.matches()){
            return "not valid";
        }else{
            return "valid";
        }
    }

    @NonNull
    public static String isValidName(String name){
        Matcher matcher = patternName.matcher(name);
        if (name.equalsIgnoreCase("")){
            return "empty";
        }else if(!matcher.matches()){
            return "not valid";
        }else{
            return "valid";
        }
    }

    @NonNull
    public static String isValidPassword(String pass){
        if(pass.equalsIgnoreCase("")){
            return "empty";
        }else if(!(pass.length() >= 8)){
            return "not valid";
        }else{
            return "valid";
        }
    }

    @NonNull
    public static String isValidConfPassword(String pass){
        if(pass.equalsIgnoreCase("")){
            return "empty";
        }else if(!(pass.length() >= 8)){
            return "not valid";
        }else{
            return "valid";
        }
    }
}
