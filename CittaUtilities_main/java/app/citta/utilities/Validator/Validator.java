package app.citta.utilities.Validator;

import android.content.Context;
import android.widget.EditText;
import java.util.regex.Pattern;

import app.citta.utilities.utilities.Config;
import app.citta.utilities.R;


public class Validator {
    private static Validator validator;
    private final Pattern emailPattern = Pattern.compile(Config.REGEX_EMAIL_PATTERN);
    private final Pattern namePattern = Pattern.compile(Config.REGEX_NAME_PATTERN);
    //  public final Pattern passwordPattern = Pattern.compile(Const.REGEX_PASS_PATTERN);
    public final int passwordLength = 6;

    public static Validator getInstance() {
        if (validator == null) {
            validator = new Validator();
        }
        return validator;
    }

    private boolean validate(String value, Pattern pattern) {
        return pattern.matcher(value).matches();
    }


    public boolean validateEmail(EditText email, Context context) {
        boolean valid = true;
        String strEmail = email.getText().toString().trim();

        if (strEmail.length() == 0) {
            email.setError(context.getResources().getString(R.string.null_field));
            email.requestFocus();
            valid = false;
        } else if (!(validate(strEmail, emailPattern))) {
            email.setError(context.getResources().getString(R.string.invalid_email));
            email.requestFocus();
            valid = false;
        } else {
            email.setError(null);
            email.requestFocus();
        }
        return valid;
    }


    public boolean validateName(EditText firstname, Context context) {
        boolean valid = true;
        String strName = firstname.getText().toString().trim();
        if (strName.length() == 0) {
            firstname.setError(context.getResources().getString(R.string.null_field));
            firstname.requestFocus();
            valid = false;
        } else if (!(validate(strName, namePattern))) {
            firstname.setError(context.getResources().getString(R.string.invalid_name));
            firstname.requestFocus();
            valid = false;
        } else {
            firstname.setError(null);
            firstname.requestFocus();
        }
        return valid;
    }


    public boolean validateMobile(EditText mobile, Context context) {
        boolean valid = true;
        String strmobile = mobile.getText().toString().trim();
        if (strmobile.length() == 0) {
            mobile.setError(context.getResources().getString(R.string.null_field));
            mobile.requestFocus();
            valid = false;
        } else if (strmobile.length() < 10) {
            mobile.setError(context.getResources().getString(R.string.invalid_contact));
            mobile.requestFocus();
            valid = false;
        } else {
            mobile.setError(null);
            mobile.requestFocus();
        }
        return valid;
    }

    public boolean validateAddress(EditText address, Context context) {
        boolean valid = true;
        String strName = address.getText().toString().trim();
        if (strName.length() == 0) {
            address.setError(context.getResources().getString(R.string.null_field));
            address.requestFocus();
            valid = false;
        } else {
            address.setError(null);
            address.requestFocus();
        }
        return valid;
    }


    public boolean validateConformPassword(EditText password, EditText Cpassword, Context context) {
        boolean valid = true;
        String strPass = password.getText().toString().trim();
        String strCpass = Cpassword.getText().toString().trim();

        if (strPass.length() == 0) {

            password.setError(context.getResources().getString(R.string.null_field));
            password.requestFocus();
            valid = false;
        } else if (strPass.length() < passwordLength) {
            password.setError(context.getResources().getString(R.string.Passwordlength));
            password.requestFocus();
            valid = false;
        } else if (strCpass.length() == 0) {

            Cpassword.setError(context.getResources().getString(R.string.entersamepassword));
            Cpassword.requestFocus();
            valid = false;

        } else if (strCpass.length() < passwordLength) {
            Cpassword.setError(context.getResources().getString(R.string.Passwordlength));
            Cpassword.requestFocus();
            valid = false;
        } else if (!strPass.matches(strCpass)) {
            Cpassword.setError(context.getResources().getString(R.string.invalid_Cpassword));
            Cpassword.requestFocus();
            valid = false;
        } else {
            Cpassword.setError(null);
            Cpassword.requestFocus();
        }

        return valid;
    }

    public boolean validatePassword(EditText password, Context context) {
        boolean valid = true;
        String strPass = password.getText().toString().trim();
        if (strPass.length() == 0) {
            password.setError(context.getResources().getString(R.string.null_field));
            password.requestFocus();
            valid = false;
        } else if (strPass.length() < passwordLength) {
            password.setError(context.getResources().getString(R.string.Passwordlength));
            password.requestFocus();
            valid = false;
        } else {
            password.setError(null);
            password.requestFocus();
        }
        return valid;
    }
}

