package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class StringSchema  extends BaseSchema<String> {
    public final StringSchema required() {
        Predicate<String> strCondition = s -> !(Objects.equals(s, "") || s == null) && s instanceof String;
        addCondition(strCondition);
        return this;
    }
    public final StringSchema minLength(int length) {
        Predicate<String> strCondition = s -> ((String) s).length() > length;
        addCondition(strCondition);
        return this;
    }
    public final StringSchema contains(String content) {
        Predicate<String> strCondition = s -> ((String) s).contains(content);
        addCondition(strCondition);
        return this;
    }
}
