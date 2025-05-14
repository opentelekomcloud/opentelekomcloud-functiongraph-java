Heartbeat function
==================

Overview
--------

The heartbeat function is used to detect abnormal scenarios when user functions
are running, such as function deadlock, function memory overflow, function
network abnormality, etc.

After configuring the heartbeat function, when the function is running,
FunctionGraph sends a heartbeat request to the function instance every 5
seconds to trigger the heartbeat function.
If the heartbeat request returns an exception, FunctionGraph will consider the
function instance abnormal and terminate the function instance.

The timeout period for FunctionGraph heartbeat requests is 3 seconds.

If there is no response to six consecutive heartbeat requests, the function
instance will be terminated.

Constraints and Restrictions
----------------------------

* Currently, only Java language functions support configuring heartbeat
  functions.
* The heartbeat function entry must be in the same file as the function
  execution entry.
* The heartbeat function currently has no input parameters and the return
  value is of boolean type.

Example of Heartbeat function
-----------------------------

.. code-block:: java
  :caption: heartbeat

    public boolean heartbeat() {
      // Custom detection logic
      return true;
    }

Configuring Heartbeat Function
------------------------------

#. Log in to the :fg_console:`FunctionGraph console <>`.

   In the navigation pane, choose **Functions** > **Function List**.
#. Click the function to be configured to go to the function details page.
#. Choose **Configuration** > **Advanced Settings** and enable
   **Heartbeat Function**


.. list-table:: **Table 2** Parameter configuration
   :widths: 25 25
   :header-rows: 1

   * - Parameter
     - Description

   * - Heartbeat Function
     - Enable heatbeat if needed.

   * - Heartbeat Function Entry
     - The Heartbeat Function Entry must be named in the same way as the handler.
       Set an HeatBeat function name in the format of **[Package name].[Class name].[Heartbeat function name]**

       .. note::
         * This parameter is not required if function Heartbeat Function is disabled.
         * Ensure that the heartbeat function and handler are in the same file.

#. Save the configuration

.. note::
  * Set the Heartbeat Function in the same way as the handler.

    **[Package name].[Class name].[Heartbeat function name]**
