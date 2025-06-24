# Samples

This folder contains samples for FunctionGraph event triggers.

## Prerequisites

To build runtime, execute in ${project.root} of [opentelekomcloud-functiongraph-java](../opentelekomcloud-functiongraph-java)

```bash
mvn clean install -DskipTests
```

## Build samples

In [samples-events](.) execute:

```bash
mvn package
```

and find in each target folder of \${SAMPLENAME} folder ```functiongraph-${SAMPLENAME}-jar-with-dependencies.jar```

## Deployment

Create FunctionGraph in console:


<table>
<tr>
    <td>Function Type</td><td><b>Event Function</b></td>
</tr>
<tr>
    <td>Function Name</td><td><b>YOUR_FUNCTION_NAME</b></td>
</tr>
<tr>
    <td>Runtime</td><td><b>Java 11</b></td>
</tr>
</table>


After creation upload jar file using "Upload"

In tab **Configuration** -> **Basic Settings**
spezify **Handler** in the format of [package name].[class name].[execution function name].

**Example:**

If your code is:

```java
package com.opentelekomcloud.samples.apig;
...

public class APIGTriggerTest {

  public APIGTriggerResponse handleRequest(final APIGTriggerEvent event, final Context context) {
    ...
  }

}
```

Following table shows parts:
<table>
<tr><td>[Package name]</td><td><b>com.opentelekomcloud.samples.apig</b></td></tr>
<tr><td>[Class name]</td><td><b>APIGTriggerTest</b></td></tr>
<tr><td>[Execution function name]</td><td><b>handleRequest</b></td></tr>
</table>

Will result in handler name: **com.opentelekomcloud.samples.apig.APIGTriggerTest.handleRequest**
