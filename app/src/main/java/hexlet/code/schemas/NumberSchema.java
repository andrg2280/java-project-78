package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {

    /*public final NumberSchema required() {
        Predicate<Integer> numberCondition = n -> n instanceof Integer;
        addCondition(numberCondition);
        return this;
    }
    public final NumberSchema positive() {
        Predicate<Integer> numberCondition = n -> (n == null || n > 0);
        addCondition(numberCondition);
        return this;
    }
    public final NumberSchema range(Integer min, Integer max) {
        Predicate<Integer> numberCondition = n ->  min <= n && n <= max;
        addCondition(numberCondition);
        return this;
    }*/
    public NumberSchema() {
        addCheck(
                "required",
                value -> value != null
        );
    }

    public NumberSchema required() {
        required = true;
        return this;
    }

    public NumberSchema positive() {
        addCheck(
                "positive",
                value -> value > 0
        );
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck(
                "range",
                value -> value >= min && value <= max
        );
        return this;
    }
}
