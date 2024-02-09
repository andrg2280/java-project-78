package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {
    public final NumberSchema required() {
        Predicate<Integer> numberCondition = n -> n instanceof Integer;
        addCondition(numberCondition);
        return this;
    }
    public final BaseSchema<Integer> positive() {
        Predicate<Integer> numberCondition = n -> n > 0;
        addCondition(numberCondition);
        return this;
    }
    public final NumberSchema range(Integer min, Integer max) {
        Predicate<Integer> numberCondition = n ->  min <= n && n <= max;
        addCondition(numberCondition);
        return this;
    }
}