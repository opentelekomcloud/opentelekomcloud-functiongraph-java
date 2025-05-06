Using the FunctionGraph context interface to retrieve Java function information
===============================================================================

When FunctionGraph runs your function, it passes a context object to the handler.
This object provides methods and properties that provide information about the invocation, function, and execution environment.

Context interface
-----------------

.. list-table:: **Table 1** Context interface methods
   :widths: 25 25
   :header-rows: 1

   * - Method Name
     - Description

   * - getRequestID( )
     - Get the request ID.

   * - getRemainingTimeInMilliSeconds ( )
     - Get the remaining running time of a function.

   * - getAccessKey( )
     - Get the AccessKey delegated by the user (valid for 24 hours). 

       To use this method, you need to configure agency for the function.

       **The current function workflow has stopped maintaining the getAccessKey interface in the Runtime SDK. 
       You will not be able to use getAccessKey to obtain a temporary AK.**

   * - getSecretKey( )
     - Get the SecretKey of the user's agency (valid for 24 hours). 

       To use this method, you need to configure the agency for the function.

       **The current function workflow has stopped maintaining the getSecretKey interface in the Runtime SDK.
       You will not be able to use getSecretKey to obtain a temporary SK.**

   * - getSecurityAccessKey( )
     - Get the SecurityAccessKey delegated by the user (valid for 24 hours). 

       To use this method, you need to configure a agency for the function.

   * - getSecuritySecretKey( )
     - Get the SecuritySecretKey (valid for 24 hours) delegated by the user. 

       To use this method, you need to configure the agency for the function.

   * - getSecurityToken( )
     - Get the SecurityToken delegated by the user (valid for 24 hours). 

       To use this method, you need to configure the agency for the function.

   * - getUserData(string key)
     - Get the value passed by the user through the environment variable through key.

   * - getFunctionName( )
     - Get the function name.

   * - getRunningTimeInSeconds ( )
     - Get the function timeout.

   * - getVersion( )
     - Get the version of the function.

   * - getMemorySize( )
     - Allocated memory.

   * - getCPUNumber( )
     - Get the CPU resources used by the function.

   * - getPackage( )
     - Gets a function group.

   * - getToken( )
     - Get the user's delegated token (valid for 24 hours).

       To use this method, you need to configure the agency for the function.

   * - getLogger( )
     - Get the logger method provided by the context (by default, it will output information such as time and request ID).

   * - getAlias
     - Get the alias of a function
  
   * - getInvokeProperty()
     - ??
  
   * - getWorkflowRunID()
     - ??

   * - getWorkflowStateID()
     - ??

   * - getTraceID()
     - ??
   
   * - getInvokeID()
     - ??

   * - getState()
     - ??

   * - setState()
     - ??
  
Log interface
-------------

The following table shows the log interface description.

.. list-table:: **Table 2** Log interface description
   :widths: 25 25
   :header-rows: 1

   * - Method Name
     - Description

   * - RuntimeLogger()
     - Record user input logs. See  :doc:`Logging<./logging/logging>` for details
  
To obtain the logger from the context use:

.. code-block:: java
  :caption: Example: FGEventHandler.java

    RuntimeLogger log = context.getLogger();
    log.log("Hello world!");
  