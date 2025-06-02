OpenTelekomCloud FunctionGraph Java libraries
=============================================

> [!WARNING]  
> ################### BETA Version, subject to change ###################
>

This Repository provides key libraries for running FunctionGraph on OpenTelekomCloud (OTC).

Documentation
-------------

For documentation and usage overview see: 
* [FunctionGraph documentation](https://docs.otc.t-systems.com/opentelekomcloud-functiongraph-java/)

* [FunctionGraph User Guides](https://docs.otc.t-systems.com/function-graph/umn)

>[!NOTE] 
>  To run documentation localy, run:
>
>  ```bash
>  tox -e docs-auto
>  ```


Repository Overview
-------------------

```text
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
  └── README.md                                  # This file

```