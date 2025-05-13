:original_name: functiongraph_02_0532.html

Initializer
===========

Overview
--------

An initializer is a logic entry for initializing functions.
For a function with an initializer, FunctionGraph invokes the
initializer to initialize the function and then invokes the
handler to process function requests. For a function without an
initializer, FunctionGraph only invokes the handler to process
function requests.


Applicable Scenario
-------------------

FunctionGraph executes a function in the following steps:

1. Allocate container resources to the function.
2. Download function code.
3. Use the runtime to load the function code.
4. Initialize the function.
5. Process the function request and return the result.

Steps **1**, **2**, and **3** are performed during a systematic cold start,
ensuring a stable latency through proper resource scheduling and
process optimization.

Step **4** is performed during an application-layer cold start in
complex scenarios, such as loading large models for deep learning,
building database connection pools, and loading function dependencies.

To reduce the latency caused by an application-layer cold start,
FunctionGraph provides the initializer to identify function
initialization logic for proper resource scheduling.


Benefits of the Initializer
---------------------------

* Isolate function initialization and request processing to enable
  clearer program logic and better structured and higher-performance code.

* Ensure a smooth function upgrade to prevent performance loss during
  the application layer's cold start initialization.
  Enable new function instances to automatically execute initialization
  logic before processing requests.

* Identify the overhead of application layer initialization, and
  accurately determine the time for resource scaling and the quantity
  of required resources. This feature makes request latency more
  stable when the application load increases and more function
  instances are required.

* If there are continuous requests and the function is not updated,
  the system may still reclaim or update existing containers.
  Although no code starts on the platform side, there are cold starts
  on the service side.
  The initializer can be used to ensure that requests can be processed
  properly.

Constraints and Restrictions
----------------------------

* The function initialization entry needs to be in the same file
  as the function execution entry.
* Custom image event functions do not currently support initialization
  configuration.

Features of the Initializer
---------------------------

The initializer of each runtime has the following features:

* **No custom parameters:**

  The initializer does not support custom parameters and only uses
  the variables in context for logic processing.

* **No return values:**

  No values will be returned for initializer invocation.

* **Initialization timeout:**

  You can set an initialization timeout (≤ 300s) different from the
  timeout for invoking the handler.

* **Execution duration:**

  Function instances are processes that execute function logic in a
  container and automatically scale if the number of requests changes.
  When a new function instance is generated, the system invokes the
  initializer and then executes the handler logic if the invocation
  is successful.

*  **One-time execution:**

   After each function instance starts, the initializer can only be
   executed once. If an instance fails to execute the initializer,
   the instance is abandoned and another instance starts to execute
   the initializer.
   A maximum of three attempts are allowed.
   If the initializer is executed successfully, the instance will
   only process requests upon invocation and will no longer execute
   the initializer again within its lifecycle.

* **Naming rule:**

  The initializer for Java has to be named as:
  **[Package name].[Class name].handleRequest**

* **Billing:**

  The initializer execution duration will be billed at the same rate
  as the function execution duration.


Configuring Initialization
---------------------------

Prerequisites
*************

You have created a function.

Initializing a Function
***********************

#. Log in to the `FunctionGraph console <https://console.otc.t-systems.com/functiongraph>`_.

   In the navigation pane, choose **Functions** > **Function List**.
#. Click the function to be configured to go to the function details page.
#. Choose **Configuration** > **Advanced Settings** and enable
   **Initialization**


.. list-table:: **Table 2** Parameter configuration
   :widths: 25 25
   :header-rows: 1

   * - Parameter
     - Description

   * - Initialization
     - Enable initialization if needed.

   * - Initialization Timeout (s)
     - Maximum duration the function can be initialized. Set this parameter if you enable function initialization.

       The value ranges from 1s to 300s.

   * - Initializer
     - You can enable function initialization on the Configuration tab page.
       The initializer must be named in the same way as the handler.
       Set an initializer name in the format of **[Package name].[Class name].[Initializer function name]**

       .. note::
         * This parameter is not required if function initialization is disabled.
         * Ensure that the function initializer and handler are in the same file.

.. note::
  * Set the initializer in the same way as the handler.

    **[Package name].[Class name].[Initializer function name]**
