package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?,?>> {
    public final MapSchema required() {
        Predicate<Map<?,?>> strCondition = m -> m instanceof Map;

        addCondition(strCondition);
        return this;
    }
    public final MapSchema sizeof(Integer size) {
        Predicate<Map<?,?>> strCondition = m ->  m.size() == size;
        addCondition(strCondition);
        return this;
    }
    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        schemas.keySet().forEach(key -> super.addCondition(s -> schemas.get(key).isValid((T) s.get(key))));
        return this;
    }
}
