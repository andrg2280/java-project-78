package schemas;

public class NumberSchema {
    public NumberSchema required() {
        return this;
    }

    public NumberSchema positive() {
        return this;
    }

    public NumberSchema range(int lowerBound, int upperBound) {
        return this;
    }
    public boolean isValid(Object data) {
        return true;
    }
}
