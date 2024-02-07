package schemas;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import static java.lang.Boolean.TRUE;

public abstract class BaseSchema<Object> {
    protected final List<Predicate<Object>> conditions;
    private Boolean isRequired = false;

    protected BaseSchema() {
        this.conditions = new LinkedList<>();
    }

    //Indicates if the specified attribute is required
    protected final void setIsRequired() {
        this.isRequired = TRUE;
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