Pre-stop function
=================

Overview
--------

The Pre-stop function is a callback function beeing called by FunctionGraph
before the current function instance stops.

Constraints and Restrictions
----------------------------

* Currently, only Java language functions support configuring pre-stop
  functions.
* The pre-stop function entry must be in the same file as the function
  execution entry.

Configuring Pre-stop function
-----------------------------

#. Log in to the :fg_console:`FunctionGraph console <>`.

   In the navigation pane, choose **Functions** > **Function List**.
#. Click the function to be configured to go to the function details page.
#. Choose **Configuration** > **Lifecycle** and enable
   **Pre-stop**

    .. list-table:: **Table 2** Parameter configuration
      :widths: 25 25
      :header-rows: 1

      * - Parameter
        - Description

      * - Pre-stop
        - Enable ``Pre-stop`` if needed.

      * - Pre-stop Timeout (s)
        - Maximum duration the function can be Pre-stoped.
          Set this parameter if you enable function Pre-stop.

          The value ranges from 1s to 90s.

      * - Pre-stop Handler
        - The Pre-stop handler must be named in the same way as the handler.
          Set an initializer name in the format of

          **[Package name].[Class name].[Pre-Stop function name]**

#. Save the configuration

