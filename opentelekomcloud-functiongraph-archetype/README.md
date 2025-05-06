# Maven archetype for FunctionGraph sample project

## Install maven archetype

Run following command in folder [opentelekomcloud-functiongraph-archetype](/../opentelekomcloud-functiongraph-java/opentelekomcloud-functiongraph-archetype)

```bash
mvn clean install
```

## Create sample FunctionGraph function

To create a sample java project using maven archetype use:

```bash
mvn archetype:generate                                           \
  -DarchetypeGroupId=com.opentelekomcloud                        \
  -DarchetypeArtifactId=opentelekomcloud-functiongraph-archetype \
  -DarchetypeVersion=1.0.0-SNAPSHOT                              \
  -DgroupId=com.sample                                           \
  -DartifactId=sample                                            \
  -DhandlerClassName=MyHandler
```
