package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema{
    public final NumberSchema required() {
        Predicate<Object> numberCondition = n -> (n instanceof Integer);
        addCondition(numberCondition);
        return this;
    }
    public final NumberSchema positive() {
        Predicate<Object> numberCondition = n -> (n instanceof Integer i && i > 0 || n == null);
        addCondition(numberCondition);
        return this;
    }
    public final NumberSchema range(Integer min, Integer max) {
        Predicate<Object> numberCondition = n ->  min <= (Integer) n && (Integer) n <= max;
        addCondition(numberCondition);
        return this;
    }
}
