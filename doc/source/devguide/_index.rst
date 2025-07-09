Building with Java
==========================================
.. toctree::
   :hidden:


You can run Java code in OpenTelekomCloud FunctionGraph.
FunctionGraph provides runtimes for Java that run your code
to process events.

Supported Java Runtimes
-----------------------

FunctionGraph supports following Java runtimes:

.. list-table:: **Table 1** Java Runtimes
   :widths: 25 25
   :header-rows: 1

   * - Name
     - Identifier

   * - Java 8
     - Java8

   * - Java 11
     - Java11

   * - Java 17
     - Java17

   * - Java 21
     - Java21


FunctionGraph Types
-------------------

FunctionGraph provides 2 types of functions:

* **Event Functions**

  Event functions can be configured with event triggers and integrate
  a variety of OpenTelekomCloud products
  (such as object storage service OBS, distributed messaging service
  RabbitMQ version, cloud log service LTS, etc.).

  See :doc:`Event Function <event_function/_index>`

* **HTTP Functions**

  HTTP functions support mainstream Web application frameworks and can
  be accessed through a browser or called directly by a URL.

  See :doc:`HTTP Functions <http_function/_index>`
