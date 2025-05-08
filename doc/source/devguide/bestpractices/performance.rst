FunctionGraph Performance Optimization
======================================

As Serverless technology becomes increasingly popular, performance optimization has become the key to improving 
application efficiency and user experience.

This article aims to explore the latest practices of FunctionGraph performance optimization. 
It comprehensively analyzes how to achieve optimal performance in different scenarios from aspects such as cold 
start optimization and function execution optimization, and provides you with practical guidance to help you build 
more efficient and stable applications on FunctionGraph.

Code Optimization
-----------------------------------

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

* **Write idempotent code.**
  
  Writing idempotent code for your function ensures that the function handles repeated events in the same way.

* **Use the connection pool**
  
  Use the connection pool properly and reuse connections to reduce the cold start overhead of new connections 
  (such as HTTP connection pool, database connection pool, Redis connection pool, etc.).

* **Avoid reinitializing variable objects**
  
  Avoid reinitializing variable objects each time a call is made (using global static variables, singletons, etc.).
  When a function calls middleware (such as Redis, Kafka, etc.), avoid repeatedly initializing the client in the handler 
  method. Initialize the client through the init method or global variables to reduce the cold start overhead of the client.

* **Strengthen the client exception retry mechanism.**
  
  When the calling function returns a non-200 status code (such as 500, 429, 504, etc.), 
  the client adds retry logic according to specific business needs to further ensure 
  the reliability of the business.

* **Use appropriate logging**
  When accessing third-party services, OpenTelekomCloud services, and performing related 
  operations in FunctionGraph functions, logs should be recorded to facilitate subsequent 
  abnormal location, performance optimization, and business analysis.

Performance stress testing
-----------------------------------

Performance testing of functions is a key step in ensuring the selection of the optimal configuration. 
During the function stress testing process, you can use the indicators, logs, call chains and other means provided 
by the platform to further analyze the function performance data, thereby optimizing the function configuration 
selection. For details on the specific observable indicators, please refer to the function monitoring overview 
`<https://docs.otc.t-systems.com/function-graph/umn/monitoring/index.html>`_ .

Simplify code and reduce image size
-----------------------------------

Since FunctionGraph will download function code during cold start, the code download process will affect the 
startup time. If the code file is too large, the download time will be extended, resulting in an increase in 
FunctionGraph startup time.

If you use a custom image function, the larger the image, the longer the startup time. 
Therefore, in order to reduce the cold start time, you should optimize the application, such as removing 
unnecessary code in the program and reducing the dependency on third-party libraries. 

In addition, some third-party libraries may include test case source code, useless binary files, and data files. 
Cleaning these files can reduce the download and decompression time of function code.

Use more memory
-----------------------------------
Configuring a larger memory for a function can improve CPU performance, thereby speeding up function startup 
and execution. You can monitor the function execution time to evaluate the impact of different memory 
configurations on function performance and select the optimal memory size.

For detailed monitoring information, please refer to View Monitoring Data 
`<https://docs.otc.t-systems.com/function-graph/umn/monitoring/metrics/function_monitoring.html>`_ . 
For the steps to configure memory, please refer to Configure Function Information 
`<https://docs.otc.t-systems.com/function-graph/umn/configuring_functions/index.html>`_ .

Use public dependency packages to speed up
------------------------------------------
When writing function code, third-party dependency libraries are usually introduced. 
During the cold start process, the system will download the required dependency packages. 
If the size of the dependency packages is too large, the startup time will be extended.

FunctionGraph provides two types of dependency packages: public dependency packages and 
private dependency packages. When using a public dependency package, FunctionGraph will 
download it to the execution node in advance to reduce the download time of the dependency package. 
Therefore, it is recommended to use the public dependency packages provided by FunctionGraph first 
and minimize the use of private dependencies. For an introduction to dependency packages, please 
refer to Function Dependency Packages `<https://docs.otc.t-systems.com/function-graph/umn/dependency_management/index.html>`_ .

Configuring Reserved Instances
-----------------------------------
After the reserved instance is created, the function's code, dependent packages, and initialization 
entry function will be automatically loaded and kept in the environment. Therefore, configuring a 
reserved instance for a function can avoid latency issues caused by cold start. 
For more information about configuring a function's reserved instance, 
see Configuring a Reserved Instance for a Function `<https://docs.otc.t-systems.com/function-graph/umn/reserved_instance_management.html>`_ .

Use function to initialize entry
-----------------------------------
For functions that need to be called frequently, placing the initialization logic at the initialization entry can 
significantly reduce the execution time each time, such as initializing HTTP connections, initializing database 
connections, etc. For configuration operations of the function initialization entry, please refer to Configuring Function Initialization 
`<https://docs.otc.t-systems.com/function-graph/umn/configuring_functions/configuring_initialization.html>`_.

