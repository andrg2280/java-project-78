package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


public abstract class BaseSchema<T> {
   // protected final List<Predicate<T>> conditions;
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean required = false;
    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }
   /* protected BaseSchema() {

        this.conditions = new LinkedList<>();
    }*/

   /* protected final void addCondition(Predicate<T> condition) {

        conditions.add(condition);
    }*/
    /*public final boolean isValid(T data) {

        for (Predicate<T> conditions : conditions) {
            if (!(conditions.test(data))) {
                return false;
            }
        }
        return true;
    }*/
   public final boolean isValid(T value) {
       if (!required) {
           var validate = checks.get("required");
           if (!validate.test(value)) {
               return true;
           }
       }

       for (var validate : checks.values()) {
           if (!validate.test(value)) {
               return false;
           }
       }

       return true;
   }
}