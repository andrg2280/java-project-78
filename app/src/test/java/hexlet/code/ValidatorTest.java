package hexlet.code;

import org.junit.jupiter.api.Test;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    private MapSchema schema;
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 100;
    private static final int IN_RANGE = 25;
    private static final int NEGATIVE = -5;
    private static final int MIN = 18;
    private static final int MAX = 35;
    @Test
    public void testValidStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        boolean actual;
        actual = schema.isValid("");
        assertTrue(actual);
        actual = schema.isValid(null);
        assertTrue(actual);
        schema.required().contains("ab").minLength(5);
        actual = schema.required().contains("ab").minLength(5).isValid("abcdef");
        assertTrue(actual);
        actual = schema.required().isValid(null);
        assertFalse(actual);
        actual = schema.required().isValid("");
        assertFalse(actual);
        actual = schema.isValid(String.valueOf(5));
        assertFalse(actual);
        actual = schema.minLength(6).isValid("abcdefgh");
        assertTrue(actual);
        actual = schema.minLength(10).isValid("ab");
        assertFalse(actual);
    }

    @Test
    public void testNumberValidator() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        boolean actual;
        actual = schema.isValid(1);
        assertTrue(actual);
        actual = schema.isValid(null);
        assertTrue(actual);
        actual = schema.required().positive().isValid(null);
        assertTrue(actual);
        actual = schema.required().isValid(null);
        assertTrue(actual);
        actual = schema.isValid(1);
        assertTrue(actual);
        actual = schema.positive().isValid(-1);
        assertFalse(actual);
        actual = schema.isValid(-0);
        assertFalse(actual);
        actual = schema.isValid(10);
        assertTrue(actual);
        schema.range(1, 3);
        actual = schema.isValid(1);
        assertTrue(actual);
        actual = schema.isValid(3);
        assertTrue(actual);
        actual = schema.isValid(-2);
        assertFalse(actual);
        actual = schema.isValid(5);
        assertFalse(actual);
        schema.range(6, 9);
        actual = schema.isValid(5);
        assertFalse(actual);
        actual = schema.isValid(10);
        assertFalse(actual);
    }

    @Test
    public void testValidMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        boolean actual;
        actual = schema.isValid(null);
        assertTrue(actual);
        schema.required();
        actual = schema.isValid(null);
        assertFalse(actual);
        actual = schema.isValid(new HashMap<>());
        assertTrue(actual);
        Map<String, String>data = new HashMap<>();
        data.put("key1", "value1");
        actual = schema.isValid(data);
        assertTrue(actual);
        schema.sizeof(2);
        data.put("key2", "value2");
        actual = schema.isValid(data);
        assertTrue(actual);
        schema.sizeof(1);
        actual = schema.isValid(data);
        assertFalse(actual);

    }


    @Test
    public <T> void testValidMapShapeSchema() {
        schema = Validator.map();
        Map<String, BaseSchema<T>> schemas = new HashMap<>();
        schemas.put("name", (BaseSchema<T>) Validator.string().required());
        schemas.put("age", (BaseSchema<T>) Validator.number().required().range(MIN, MAX));

        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertFalse(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertFalse(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", NEGATIVE);
        assertFalse(schema.isValid(human4));

        Map<String, Object> human5 = new HashMap<>();
        human5.put("name", " ");
        human5.put("age", IN_RANGE);
        assertTrue(schema.isValid(human5));
    }
}
