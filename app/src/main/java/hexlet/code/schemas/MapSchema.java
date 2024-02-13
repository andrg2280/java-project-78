package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema() {
        addCheck("required", value -> value != null);
    }
    public final MapSchema required() {
        required =  true;
        Predicate<Map<?, ?>> strCondition = m -> m instanceof Map;
        addCheck("required", strCondition);
        return this;
    }
    public final MapSchema sizeof(Integer size) {
        Predicate<Map<?, ?>> strCondition = m ->  m.size() == size;
        addCheck("sizeof", strCondition);
        return this;
    }
    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        schemas.keySet().forEach(key -> super.addCheck("shape",  s -> schemas.get(key).isValid((T) s.get(key))));
        return this;
  }
}
