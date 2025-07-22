.. _ref-s3obs-event:

Object Storage Service (OBS)
=============================

.. toctree::
   :hidden:


.. note::
   OBS can only trigger FunctionGraph in the main project (e.g. eu-de), not in sub projects.

Sample event
------------

.. literalinclude:: /../../samples-events/s3obs/src/test/resources/s3obs_event.json
   :language: json
   :caption: s3obs_event.json

Class definitions
-----------------

For class definitions for S3 OBS trigger event, see:
:github_repo_master:`Classe definitions <opentelekomcloud-functiongraph-java-events/src/main/java/com/opentelekomcloud/services/functiongraph/runtime/events/s3obs>`


Sample
------

For sample on how to use trigger, see:
:github_repo_master:`Sample <samples-events/s3obs>`