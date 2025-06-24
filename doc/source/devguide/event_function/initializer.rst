:original_name: functiongraph_02_0532.html

Initializer funcion
===================

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

* Separating the initialization logic and request processing logic makes
  the program logic clearer, allowing users to more easily write
  well-structured and high-performance code.

* When the user function code is updated, the system can ensure the
  smooth upgrade of the user function and avoid the performance loss
  caused by the cold start of the application layer initialization.
  After the new function instance is started, it can automatically
  execute the user's initialization logic and process the request
  after the initialization is completed.

* When the application load increases and more function instances
  need to be added, the system can identify the initialization
  overhead of the function application layer, more accurately
  calculate the timing of resource scaling and the amount of
  resources required, and make request delays more stable.

* Even if the user has continuous requests and does not update the
  function, the system may still recycle or update the existing
  container. In this case, there will be no cold start on the
  platform side, but there will be a cold start on the business 
  side. Initializer can minimize this situation.

Constraints and Restrictions
----------------------------

* The function initialization entry needs to be in the same file
  as the function execution entry.
* Custom image event functions do not currently support initialization
  configuration.

Example of function initializer
-------------------------------

.. code-block:: java
  :caption: initializer

    public void initializer(Context context) {
      RuntimeLogger log = context.getLogger();
      log.log(String.format("ak:%s", context.getAccessKey()));
    }

Initializer Interface Specification
-----------------------------------

The initializer of each runtime has the following features:

* **No custom parameters:**

  The initializer does not support custom parameters and only uses
  the variables in context for logic processing.

* **No return value:**

  No values will be returned for initializer invocation.

* **Initialization timeout:**

  You can set an initialization timeout (≤ 300s) different from the
  timeout for invoking the handler.

* **Execution time:**

  The process that runs the function logic is called a function instance,
  which runs in a container. FunctionGraph will scale the function
  instance based on the user load. Whenever a new function instance is
  created, the system will first call the initializer.
  The system ensures that the handler logic will be executed only after
  the initializer is successfully executed.

* **One-time execution:**

  FunctionGraph ensures that the initializer will only be successfully
  executed once after each function instance is started.
  If the execution fails, the function instance fails to execute, and
  the next instance is selected for re-execution, with a maximum of 3
  retries.
  Once the execution succeeds, the initializer will not be executed
  again during the life cycle of the instance, and only the request
  processing function will be executed after receiving the Invoke request.

* **Naming rule:**

  The initializer for Java has to be named as:
  **[Package name].[Class name].[Initializer function name]**

* **Metering and Billing:**

  The execution time of the Initializer will also be measured, and the
  user needs to pay for it. The billing method is the same as the
  execution function.


Configuring Initialization
---------------------------

#. Log in to the :fg_console:`FunctionGraph console <>`.

   In the navigation pane, choose **Functions** > **Function List**.
#. Click the function to be configured to go to the function details page.
#. Choose **Configuration** > **Lifecycle** and enable
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
     - The initializer must be named in the same way as the handler.
       Set an initializer name in the format of **[Package name].[Class name].[Initializer function name]**

#. Save the configuration


