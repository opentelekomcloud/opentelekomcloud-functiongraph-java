OpenTelekomCloud FunctionGraph Java libraries
=============================================

################### BETA Version, subject to change ###################

..
  .. image:: https://zuul.otc-service.com/api/tenant/eco/badge?project=opentelekomcloud/opentelekomcloud-functiongraph-java&pipeline=check&branch=master
      :target: https://zuul.otc-service.com/t/eco/builds?project=opentelekomcloud%2Fopentelekomcloud-functiongraph-java

This Repository provides key libraries for running FunctionGraph on OpenTelekomCloud (OTC).

Documentation
-------------

`For documentation and usage overview see:  <https://docs.otc.t-systems.com/opentelekomcloud-functiongraph-java/>`_

:otc_fg_umn:`FunctionGraph User Guides <umn/>`

.. note::
  To run documentation localy run:

  .. code-block:: bash
    tox -e docs-auto


Repository Overview
-------------------

.. code-block:: text
  .
  ├── doc                                         # Project documentation
  ├── opentelekomcloud-functiongraph-archetype    # Maven archtype
  ├── opentelekomcloud-functiongraph-java-core    # FunctionGraph core classes
  ├── opentelekomcloud-functiongraph-java-events  # FunctionGraph event definitions
  ├── opentelekomcloud-functiongraph-java-test    # FunctionGraph test helper classes
  ├── samples-doc                                 # Samples refered in documentations
  ├── samples-events                              # Samples on how to use event triggers
  ├── LICENSE
  ├── tox.ini                                     # tox 
  └── README.rst                                  # This file

