package interpretation.value;

public interface Value {
    Value clone();
    
    Object getValue();
}
