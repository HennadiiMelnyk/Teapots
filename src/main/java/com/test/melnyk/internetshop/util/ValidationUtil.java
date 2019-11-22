package com.test.melnyk.internetshop.util;

import java.util.regex.Pattern;

public final class ValidationUtil {

    private static final String EMAIL_REG_EXP = "/^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$/";
    private static final String PASSWORD_REG_EXP = "/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/";

    private ValidationUtil() {
    }

    public static boolean emailValidator(final String email) throws Exception {
        final Pattern pattern = Pattern.compile(EMAIL_REG_EXP);
        if (!pattern.matcher(email).matches()) {
            throw new Exception("Invalid email");
        }
        return true;
    }

    public static boolean passwordValidator(final String password) throws Exception {
        final Pattern pattern = Pattern.compile(PASSWORD_REG_EXP);
        if (!pattern.matcher(password).matches()) {
            throw new Exception("Invalid password");
        }
        return true;
    }

   // select item.id, item.name,item.price,item.weight, item.age,location.id,location.country,material.id,material.material,item_type.id,item_type.type,color.id,color.color
    // from item
    // INNER JOIN  location on location.id=item.location_idlocation
    // INNER JOIN material on material.id=item.material_idmaterial
    // INNER JOIN item_type on item_type.id=item.itemtype_iditemtype
    //  INNER JOIN color on color.id=item.color_idcolor LIMIT ?,?
}
