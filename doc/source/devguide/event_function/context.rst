Using the FunctionGraph context interface to retrieve Java function information
===============================================================================

When FunctionGraph runs your function, it passes a context object to the
handler.
This object provides methods and properties that provide information about
the invocation, function, and execution environment.

Context interface
-----------------

.. list-table:: **Table 1** Context interface methods
   :widths: 25 25
   :header-rows: 1

   * - Method Name
     - Description

   * - getRequestID()
     - Get the request ID.

   * - getRemainingTimeInMilliSeconds()
     - Get the remaining running time of a function.

   * - getAccessKey()
     - Get the AccessKey (valid for 24 hours) with an agency.

       To use this method, you need to configure **agency** for the function.

       .. note::

         FunctionGraph has stopped maintaining the **getAccessKey()** API in the Runtime
         SDK. You cannot use this API to obtain a temporary AK.

   * - getSecretKey()
     - Get the SecretKey (valid for 24 hours) with an agency.

       To use this method, you need to configure the **agency** for the function.

       .. note::

         FunctionGraph has stopped maintaining the **getSecretKey()** API in the Runtime
         SDK. You cannot use this API to obtain a temporary SK.

   * - getSecurityAccessKey()
     - Get the SecurityAccessKey (valid for 24 hours) with an agency.

       To use this method, you need to configure a **agency** for the function.

   * - getSecuritySecretKey()
     - Get the SecuritySecretKey (valid for 24 hours) with an agency.

       To use this method, you need to configure the **agency** for the function.

   * - getSecurityToken()
     - Get the SecuritySecretToken (valid for 24 hours) with an agency.

       To use this method, you need to configure the **agency** for the function.

   * - getUserData(String key)
     - Uses keys to obtain the values passed by environment variables.

   * - getFunctionName()
     - Get the function name.

   * - getRunningTimeInSeconds()
     - Get the function timeout.

   * - getVersion()
     - Get the version of the function.

   * - getMemorySize()
     - Allocated memory.

   * - getCPUNumber()
     - Get the CPU resources used by the function.

   * - getPackage()
     - Gets a function group.

   * - getToken()
     - Get the token (valid for 24 hours) with an agency.

       To use this method, you need to configure the **agency** for the function.

   * - getLogger()
     - Get the logger method provided by the context (by default, it will output information such as time and request ID).

   * - getAlias()
     - Get the alias of a function

   * - getInvokeProperty()
     - Get the invoke property

   * - getWorkflowID()
     - Get the workflow run id (not yet available)

   * - getWorkflowRunID()
     - Get the workflow run id (not yet available)

   * - getWorkflowStateID()
     - Get the workflow state id (not yet available)

   * - getTraceID()
     - Get the trace id (not yet available)

   * - getInvokeID()
     - Get the invoke id

   * - getState()
     - Get the state

.. warning::
  Results returned by using the ``getToken()``, ``getAccessKey()``, and
  ``getSecretKey()`` methods contain sensitive information.
  Exercise caution when using these methods

Log interface
-------------

The following table shows the log interface description.

.. list-table:: **Table 2** Log interface description
   :widths: 25 25
   :header-rows: 1

   * - Method Name
     - Description

   * - RuntimeLogger()
     - | Records user input logs using the method ``log(String string)``.
       | See :doc:`Logging<./logging/logging>` for details.

To obtain the logger from the context use:

.. code-block:: java
  :caption: Example: FGEventHandler.java

    RuntimeLogger log = context.getLogger();
    log.log("Hello world!");
