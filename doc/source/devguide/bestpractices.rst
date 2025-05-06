Code best practices for Java FunctionGraph functions
====================================================

Adhere to the guidelines in the following lists to use best coding practices when building your FunctionGraph functions:


* **Separate the FunctionGraph handler from your core logic.**  

  This allows a more unit-testable function.

* **Write idempotent code.**

   Writing idempotent code for your functions ensures that duplicate events are handled the same way. 
   Your code should properly validate events and gracefully handle duplicate events.

* **Connection Pool**

  Use the connection pool properly and reuse connections to reduce the cold start overhead of new connections 
  (such as HTTP connection pool, database connection pool, Redis connection pool, etc.).

* **Variable reinitialization**
  
  Avoid reinitializing variable objects each time a call is made (using global static variables, singletons, etc.). 
  When a function calls middleware (such as Redis, Kafka, etc.), avoid repeatedly initializing the client in the handler method. 
  Initialize the client through the init method or global variables to reduce the cold start overhead of the client.

* **Retry mechanism**
  
  Strengthen the client exception retry mechanism. When the calling function returns a non-200 status code (such as 500, 429, 504, etc.), 
  the client adds retry logic according to specific business needs to further ensure the reliability of the business.

* **Logging**

  Use appropriate logging. When accessing third-party services, OpenTelekomCloud services, and performing related operations in 
  FunctionGraph functions, logs should be recorded to facilitate subsequent abnormal location, performance optimization, and business analysis.

* **Simplify code and reduce image size**
  
  Since FunctionGraph will download function code during cold start, the code download process will affect the startup time. 
  If the code file is too large, the download time will be extended, resulting in an increase in FunctionGraph startup time.

  If you use a custom image function, the larger the image, the longer the startup time. Therefore, in order to reduce the 
  cold start time, you should optimize the application, such as removing unnecessary code in the program and reducing the 
  dependency on third-party libraries.

  In addition, some third-party libraries may include test case source code, useless binary files, and data files. 
  Cleaning these files can reduce the download and decompression time of function code.
 
* **Use more memory**
  
  Configuring a larger memory for a function can improve CPU performance, thereby speeding up function startup and execution. 
  You can monitor the function execution time to evaluate the impact of different memory configurations on function performance 
  and select the optimal memory size.

* **Control the dependencies in your function's deployment package.** 

  The FunctionGraph execution environment contains a number of libraries. To enable the latest set of features
  and security updates, FunctionGraph will periodically update these libraries. 
  These updates may introduce subtle changes to the behavior of your FunctionGraph function.
  To have full control of the dependencies your function uses, package all of your dependencies 
  with your deployment package.


* **Minimize your deployment package size to its runtime necessities.**

  This will reduce the amount of time that it takes for your deployment package to be downloaded and unpacked ahead of invocation.


* **Use environment variables to pass operational parameters to your function.**
  
  For example, if you are writing to an OBS bucket, instead of hard-coding the bucket name you are writing to,
  configure the bucket name as an environment variable.

* **Avoid using recursive invocations** 

  in your FunctionGraph function, where the function invokes itself or initiates a process that may invoke
  the function again. This could lead to unintended volume of function invocations and escalated costs. 
  If you see an unintended volume of invocations, set the "Max. Instances per Function" to 0 immediately 
  to throttle all invocations to the function, while you update the code.
 
   
* **Reduce the time it takes FunctionGraph to unpack deployment packages** 

  authored in Java by putting your dependency .jar files in a separate ``/lib`` directory. 
  This is faster than putting all your function’s code in a single jar with a large number of .class files.
  See :doc:`Deploy FunctionGraph with .zip <./deploy>` for instructions.

* **Configuring reserved instances**

  After the reserved instance is created, the function's code, dependent packages, and initialization entry 
  function will be automatically loaded and kept in the environment. Therefore, configuring a reserved 
  instance for a function can avoid latency issues caused by cold start. 
  

