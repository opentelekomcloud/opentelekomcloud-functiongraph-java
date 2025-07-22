.. _class-isolation-ref:

Class isolation
===============

Class isolation means using separate class loaders to load user
code and dependencies to resolve conflicts between user
dependencies and runtime dependencies.
If there is no dependency conflict, there is no need to
use class isolation.

Configuring class isolation
---------------------------

#. Log in to the :fg_console:`FunctionGraph console <>`.

   In the navigation pane, choose **Functions** > **Function List**.
#. Click the function to be configured to go to the function details page.
#. Choose **Configuration** > **Advanced Settings** and enable
   **Class Isolation**

#. Save the configuration
