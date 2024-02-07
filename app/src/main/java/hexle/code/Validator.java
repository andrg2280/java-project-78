package hexle.code;

import hexle.code.schemas.MapSchema;
import hexle.code.schemas.NumberSchema;
import hexle.code.schemas.StringSchema;

public class Validator {
    public static StringSchema string() {
        return new StringSchema();
    }
    public static NumberSchema number() {
        return new NumberSchema();
    }
    public MapSchema map() {
        return new MapSchema();
    }
}
