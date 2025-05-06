package ${package};

import com.google.gson.JsonObject;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;
import com.opentelekomcloud.services.functiongraph.runtime.core.RequestHandler;

public class ${handlerClassName} implements RequestHandler<JsonObject , String> {

  public ${handlerClassName}() {
    // Initialize stuff here, so that it can be reused for subsequent invocations.
  }

  @Override
  public String handleRequest(final JsonObject event, Context context)  {
    
    RuntimeLogger log = context.getLogger();

    log.log(String.format("class name: %s", event.getClass().getName()));
    
    return "ok";
  }
}