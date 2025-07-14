Event trigger
=============

.. toctree::
   :maxdepth: 1
   :hidden:

   API Gateway  (APIG) <apig_event>
   Cloud Trace Service (CTS)  <cts_event>
   Document Database Service (DDS)  <dds_event>
   DMS (for Kafka) (DMS4Kafka)<dms4kafka_event>
   Kafka (OPENSOURCEKAFKA)  <kafka_event>
   Log Tank Service (LTS) <lts_event>
   S3 Object Storage Service (S3OBS) <s3obs_event>
   Simple Message Notification (SMN) <smn_event>
   Timer  (Timer) <timer_event>


.. _ref-otc-events:

Event Types of OpenTelekomCloud services
----------------------------------------

See following table for all available event types on Open Telekom Cloud:

.. list-table:: **Table 1** Events
   :widths: 20 8 5 
   :header-rows: 1

   * - Service
     - Short
     - Calling method

   * - :ref:`ref-apig-event`
     - APIG
     - sync

   * - :ref:`ref-cts-event`
     - CTS
     - async

   * - :ref:`ref-dds-event`
     - DDS
     - async

   * - :ref:`ref-dms4kafka-event`
     - DMS4Kafka
     - sync

   * - :ref:`ref-kafka-event`
     - KAFKA
     - async

   * - :ref:`ref-lts-event`
     - LTS
     - async

   * - :ref:`ref-s3obs-event`
     - S3OBS
     - async

   * - :ref:`ref-smn-event`
     - SMN
     - async

   * - :ref:`ref-timer-event`
     - Timer
     - async


Creating triggers
-----------------

On how to create triggers using console, see :otc_fg_umn:`Create trigger <creating_triggers/index.html>` in User Guide.

Permissions required to use trigger events
------------------------------------------

For using event triggers, you need to assign permissions to
the FunctionGraph function,
see :otc_fg_umn:`Permissions required to use triggers and relevant functions <service_overview/permissions_management.html#id2>`
in User Guide.
