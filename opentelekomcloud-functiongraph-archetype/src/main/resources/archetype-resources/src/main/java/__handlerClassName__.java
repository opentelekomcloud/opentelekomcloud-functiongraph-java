package ${package};

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;
import com.opentelekomcloud.services.functiongraph.runtime.core.RequestHandler;

public class ${handlerClassName} implements RequestHandler<JsonObject , String> {

  @Override
  public void initializer(Context context) {
   // put initializer functionality here
  }

  @Override
  public boolean heartbeat() {
    // put heart beat functionality here
    return true;
  }

  @Override
  public void prestop() {
    // put pre-stop functionality here
  }

  @Override
  public String handleRequest(final JsonObject event, Context context)  {
    
    RuntimeLogger log = context.getLogger();

    log.log("Hello world");

    Gson gson = new Gson();

    log.log(gson.toJson(event));
    
    return "ok";
  }

}