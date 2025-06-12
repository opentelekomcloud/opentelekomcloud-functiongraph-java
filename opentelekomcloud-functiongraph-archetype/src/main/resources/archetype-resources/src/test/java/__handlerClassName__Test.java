#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opentelekomcloud.services.functiongraph.runtime.test.TestContext;


public class ${handlerClassName}Test {

    @Test
    public void handleRequest_shouldReturnConstantValue() {
        ${handlerClassName} function = new ${handlerClassName}();

        String json = "{ \"key\": \"Hello World\" }";

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

        TestContext context = new TestContext();

        Object result = function.handleRequest(jsonObject, context);
        assertEquals("ok", result);
    }
}