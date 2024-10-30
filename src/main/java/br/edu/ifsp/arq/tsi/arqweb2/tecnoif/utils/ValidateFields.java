package br.edu.ifsp.arq.tsi.arqweb2.tecnoif.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ValidateFields {

    public static Boolean validate(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.getName();
            String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

            try {
                Method getter = obj.getClass().getMethod(getterName);
                Object value = getter.invoke(obj);

                if (value instanceof String) {
                    if (((String) value).isEmpty()) {
                        return false;
                    }
                } else if (value instanceof Double) {
                    if ((Double) value < 0.0) {
                        return false;
                    }
                }
            } catch (NoSuchMethodException e) {
                continue;
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }

}
