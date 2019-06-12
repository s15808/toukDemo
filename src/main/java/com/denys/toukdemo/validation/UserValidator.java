package com.denys.toukdemo.validation;

import com.denys.toukdemo.dto.User;

import java.util.regex.Pattern;

public class UserValidator {

    private static final String DASH = "-";
    private static final Pattern SURNAME_START_CAPITAL = Pattern.compile("[A-Z].*");
    private static final Pattern SECOND_SURNAME_START_CAPITAL = Pattern.compile(".*-[A-Z].*");

    public static boolean isValid(User user){
        String surname = user.getSurname();
        if(user.getName().length() < 3)
            return false;
        else if(surname.length() < 3)
            return false;
        else if(!SURNAME_START_CAPITAL.matcher(surname).matches())
            return false;
        else if(surname.contains(DASH) && !SECOND_SURNAME_START_CAPITAL.matcher(surname).matches())
            return false;
        return true;
    };
}
