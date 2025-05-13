FunctionGraph Cold Start Optimization
=====================================

Features such as pay-as-you-go, automatic elastic scaling, and shielding
complexity make Serverless gradually become the new paradigm of next-generation
cloud computing. However, while the Serverless architecture brings great
convenience, cold start will be a real challenge in application scenarios
with high real-time requirements. When using Serverless to build a Web service,
if the total cold start and Web service initialization time exceeds 5 seconds,
it will undoubtedly greatly reduce the user experience of your website.
Therefore, how to reduce the cold start time and improve the user
experience is an urgent problem to be solved when you build a serverless
architecture.

The life cycle of a Serverless instance can be divided into three stages:

* **Initialization:**

  In this phase, FunctionGraph will try to unfreeze the previous
  execution environment.
  If there is no environment that can be unfrozen, FunctionGraph will create
  resources, download function code, initialize extensions and Runtime,
  and then start running the initialization code (code outside the main
  program).

* **Execution:**

  In this phase, the instance starts executing the function after receiving
  the event.
  After the function runs to completion, the instance waits for the next
  event to be called.

* **Shutdown:**

  This phase is triggered if a FunctionGraph function does not receive any
  calls for a period of time. In the shutdown phase, the Runtime shuts down,
  then sends a shutdown event to each extension, and finally deletes the
  environment.
  When a FunctionGraph is triggered, if there is no currently activated
  function instance available for invocation, the function code will be
  downloaded and a function execution environment will be created.
  The period from event triggering to the creation of a new FunctionGraph
  environment is usually called the "cold start time".
  In the Serverless architecture, the cold start problem is unavoidable.

Currently, FunctionGraph has made a lot of optimizations for cold start on
the system side.

For the user side, please refer to the following solutions.

Snapshot Cold Start (COMMING SOON)
----------------------------------
The slow cold start speed of Java applications is particularly prominent.
OpenTelekomCloud FunctionGraph innovatively proposed a cold start acceleration
solution based on process-level snapshots, which is committed to helping users
break through the performance bottleneck of cold start without user perception
(no/little code adaptation is required).
This optimization solution directly restores the operating environment from
the snapshot after application initialization, skipping the complex framework
and business initialization stage, thereby significantly reducing the startup
delay of Java applications, and the measured performance improvement is 90%+.

Users can use Java functions to turn on the configuration switch for cold start
snapshot acceleration.
For details, see Configuring snapshot cold start.
OpenTelekomCloud FunctionGraph will pre-execute the initialization code
corresponding to the function, obtain a snapshot of its initialization
execution context environment, and perform encrypted cache.
When the function is subsequently called and cold start expansion is triggered,
the execution environment will be restored directly from the pre-initialized
application snapshot instead of going through the initialization process again,
thereby greatly improving the startup performance.

Reduce code size and image size
-------------------------------
Since FunctionGraph will download function code during cold start, the process
of downloading code will also affect the startup time.
If the code package is too large, the download time will be longer, resulting
in an increase in the startup time of FunctionGraph;
if a custom image function is used, the larger the image, the longer the
startup time.
Therefore, in order to reduce the cold start time, you can slim down the
application, such as removing unnecessary code in the program and reducing
unnecessary third-party library dependencies.
In addition, some third-party libraries may contain test case source code,
useless binary files and data files, etc. Deleting useless files can reduce
the download and decompression time of function code.

Public dependency package acceleration
--------------------------------------
When writing applications, third-party dependency libraries are often
introduced.
During the cold start process, the required dependency packages will be
downloaded.
If the dependency packages are too large, it will directly increase the
startup time.
FunctionGraph provides two modes:
* public dependency packages and
* private dependency packages.

For public dependency packages, FunctionGraph will pre-download them
to the execution node to reduce the download time of the dependency packages.
Therefore, it is recommended to use the public dependency packages provided
by FunctionGraph first and minimize the use of private dependencies.

Preheat
-------------------
When an event triggers a function, if there is an active function instance
that can be called, cold start can be avoided and response time can be
reduced. You can use the following two methods to preheat:

* Use the timer trigger to preheat the function.

  For details, see :otc_fg_umn:`Using a Timer Trigger <creating_triggers/using_a_timer_trigger.html>`

* Use reserved instances to avoid cold starts.

  For details, see :otc_fg_umn:`Reserved Instance Management <reserved_instance_management.html>`
