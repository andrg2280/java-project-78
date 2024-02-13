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
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 100;
    private static final int IN_RANGE = 25;
    private static final int NEGATIVE = -5;
    private static final int MIN = 18;
    private static final int MAX = 35;
    @Test
    public void testValidStringSchema() {
        StringSchema schema = Validator.string();
        boolean actual;
        actual = schema.isValid("");
        assertTrue(actual);
        actual = schema.isValid(null);
        assertTrue(actual);

        assertTrue(schema.isValid(""));

        schema.required();
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));

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
        NumberSchema schema = Validator.number();
        boolean actual;
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));

        schema.positive();
        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));

        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));

        schema.range(6, 9);
        assertFalse(schema.isValid(5));
        assertFalse(schema.isValid(10));

        actual = schema.isValid(1);
        assertFalse(actual);
        actual = schema.isValid(null);
        assertFalse(actual);
        actual = schema.required().isValid(null);
        assertFalse(actual);
        actual = schema.required().positive().isValid(null);
        assertFalse(actual);
        actual = schema.required().isValid(null);
        assertFalse(actual);
    }

    @Test
    public void testValidMapSchema() {
        MapSchema schema = Validator.map();
        boolean actual;
        actual = schema.isValid(null);
        assertTrue(actual);
        schema.required();
        actual = schema.isValid(null);
        assertFalse(actual);
        actual = schema.isValid(new HashMap<>());
        assertTrue(actual);
        Map<String, String> data = new HashMap<>();
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
        MapSchema schema = Validator.map();
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
