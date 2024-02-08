package hexlet.code.schemas;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;


public abstract class BaseSchema {
    protected final List<Predicate<Object>> conditions;
    protected BaseSchema() {

        this.conditions = new LinkedList<>();
    }

    protected final void addCondition(Predicate<Object> condition) {

        conditions.add(condition);
    }
    public final boolean isValid(Object data) {

        for (Predicate<Object> conditions : conditions) {
            if (!conditions.test(data)) {
                return false;
            }
        }
        return true;
    }
}