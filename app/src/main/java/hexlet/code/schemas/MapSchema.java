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
    public MapSchema shape(Map<String, BaseSchema> schemas) {

        for (Map.Entry<String, BaseSchema> enter : schemas.entrySet()) {
            String key = enter.getKey();
            addCondition(o -> schemas.get(key).isValid(((Map<?, ?>) o).get(key)));
        }

        return this;
    }
}
