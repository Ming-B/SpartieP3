import java.util.HashMap;
import java.util.Map;

public class Environment {
    Environment enclosing = null;

    public Environment() {
    }

    public Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    private Map<String, Object> variables = new HashMap<>();

    // Define - Create a variable
    void define(String name, Object value) {
        variables.put(name, value);
    }

    Object get(String name) {
        if(variables.containsKey(name)) {
            return variables.get(name);
        }
        else if(enclosing != null) { //checks enclosing if variable is not in table
            return enclosing.get(name);
        }
        else return null;
    }

    // Assign - Replace the value of an existing variable
    void assign(Token name, Object value) {
        if(variables.containsKey(name.text)) {
            variables.put(name.text, value);
        }
        else if(enclosing != null) {
            enclosing.assign(name, value);
        }
        else {
            // Exit on error if we get this far since the variable is undefined
            System.err.println("Undefined variable: " + name.text);
            System.exit(ErrorCode.INTERPRET_ERROR);
        }
    }
}
