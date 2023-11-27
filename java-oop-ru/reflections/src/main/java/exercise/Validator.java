package exercise;

import java.lang.reflect.Field;
import java.util.*;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> result = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            NotNull nn = field.getAnnotation(NotNull.class);
            if (nn != null) {
                try {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        result.add(field.getName());
                    }
                } catch (IllegalAccessException | NoSuchFieldError e) {
                    e.printStackTrace();
                }

            }

        }
        return result;
    }

    public static Map<String, List<String>> advanceValidate(Address address) {
        Map<String, List<String>> result = new LinkedHashMap<>();
        Field[] filelds = Address.class.getDeclaredFields();
        for (Field fileld : filelds) {
            try {
                List<String> errList = new ArrayList<>();
                NotNull nn = fileld.getAnnotation(NotNull.class);
                MinLength ml = fileld.getAnnotation(MinLength.class);
                fileld.setAccessible(true);
                Object mean = fileld.get(address);
                if (nn != null) {
                    if (mean == null) {
                        errList.add("Should be not Null"); //address.getClass().getDeclaredField(fileld.getName());
                    }
                }
                if (ml != null & fileld.get(address) != null) {
                    fileld.setAccessible(true);
                    if (mean != null & ml.minLength() > (int) mean.toString().length()) {
                        errList.add("Field Length less than MinLength:" + ml.minLength()); //address.getClass().getDeclaredField(fileld.getName());
                    }
                }
                if (!errList.isEmpty()) {
                    result.put(fileld.getName(), errList);
                }

            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


}
// END
