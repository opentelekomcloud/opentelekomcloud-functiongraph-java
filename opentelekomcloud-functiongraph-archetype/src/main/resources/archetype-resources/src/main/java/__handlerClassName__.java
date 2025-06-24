package ${package};

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class ${handlerClassName} {

  public void initializer(Context context) {
   // put initializer functionality here
  }

  public boolean heartbeat() {
    // put heart beat functionality here
    return true;
  }

  public void prestop() {
    // put pre-stop functionality here
  }

  public String handleRequest(final JsonObject event, final  Context context)  {
    
    RuntimeLogger log = context.getLogger();

    log.log("Hello world");

    Gson gson = new Gson();

    log.log(gson.toJson(event));
    
    return "ok";
  }

}