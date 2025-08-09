package model;

public class Validator {

    public static boolean isEmailValid(String value) {
         if (!value.matches(Validation.EMAIL.validate())) {
            return false;
        }
        return true;
    }

    public static boolean isMobileValid(String value) {
    if (!value.matches(Validation.MOBILE.validate())) {
            return false;
        }
        return true;
    }

    public static boolean isPasswordValid(String value) {
     if (!value.matches(Validation.PASSWORD.validate())) {
                  return false;
        }
        return true;
    }
}
