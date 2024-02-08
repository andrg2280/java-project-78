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
        actual = schema.isValid(5);
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
        actual = schema.positive().isValid(null);
        assertTrue(actual);
        schema.required();
        actual = schema.isValid(null);
        assertFalse(actual);
        actual = schema.isValid("1");
        assertFalse(actual);
        actual = schema.isValid(-1);
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
    public void testValidMapShapeSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        boolean actual;
        schemas.put("name", v.string().minLength(4));
        schemas.put("age", v.number().positive());
        schema.shape(schemas);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "Name1");
        map1.put("age", 1);
        actual = schema.isValid(map1);
        assertTrue(actual);
        Map<Object, Object> map2 = new HashMap<>();
        map2.put("name", "Jeff");
        map2.put("age", null);
        actual = schema.isValid(map2);
        assertFalse(actual);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "");
        map3.put("age", null);
        actual = schema.isValid(map3);
        assertFalse(actual);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("name", "Molly");
        map4.put("age", -5);
        actual = schema.isValid(map4);
        assertFalse(actual);
        Map<String, Object> map5 = new HashMap<>();
        map5.put("name", "Nil");
        map5.put("age", 5);
        actual = schema.isValid(map5);
        assertFalse(actual);
    }
}
