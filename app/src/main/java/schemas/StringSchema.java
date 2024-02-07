package schemas;

public class StringSchema {
    public StringSchema required() {
        return this;
    }

    public StringSchema minLength(int length) {
        return this;
    }

    public StringSchema contains(String str) {
        return this;
    }
}
