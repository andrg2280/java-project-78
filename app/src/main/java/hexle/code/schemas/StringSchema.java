package hexle.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class StringSchema  extends BaseSchema{
    public final StringSchema required() {
        Predicate<Object> strCondition = s -> !(Objects.equals(s, "") || s == null) && s instanceof String;
        addCondition(strCondition);
        return this;
    }
    public final StringSchema minLength(int length) {
        Predicate<Object> strCondition = s -> ((String) s).length() > length;
        addCondition(strCondition);
        return this;
    }
    public final StringSchema contains(String content) {
        Predicate<Object> strCondition = s -> ((String) s).contains(content);
        addCondition(strCondition);
        return this;
    }
}
