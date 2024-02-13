package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class StringSchema  extends BaseSchema<String> {
    public StringSchema() {
        addCheck("required", value -> value != null);
    }
    public final StringSchema required() {
        required = true;
        Predicate<String> strCondition = s -> !(Objects.equals(s, "") || s == null) && s instanceof String;
        addCheck("required",  strCondition);
        return this;
    }
    public final StringSchema minLength(int length) {
        Predicate<String> strCondition = s ->  s.length() > length;
        addCheck("minLength", strCondition);
        return this;
    }
    public final StringSchema contains(String content) {
        Predicate<String> strCondition = s ->  s.contains(content);
        addCheck("contains", strCondition);
        return this;
    }
}
