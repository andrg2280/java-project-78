package hexlet.code.schemas;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;


public abstract class BaseSchema<T> {
    protected final List<Predicate<T>> conditions;
    protected BaseSchema() {

        this.conditions = new LinkedList<>();
    }

    protected final void addCondition(Predicate<T> condition) {

        conditions.add(condition);
    }
    public final boolean isValid(T data) {

        for (Predicate<T> conditions : conditions) {
            if (!(conditions.test(data))) {
                return false;
            }
        }
        return true;
    }
}