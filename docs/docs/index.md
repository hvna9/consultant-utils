# ConsultantUtils

### An useful Java library for IT Consultants

**ConsultantUtils** is an open source Java utility library for all IT consultants and all developers who needs to be faster at work. This library contains some methods and data structures to make faster the consultants work.

<hr style="border: none; border-top: 1px solid #e9e9e9; margin-bottom: 10px;">
<div style="display: flex; justify-content: space-between;">

  <div style="flex: 1; margin-right: 10px;">
    <p><strong>Version:</strong> 0.1.7</p>
    <p><strong>Author:</strong> <a href="https://www.claudiodimauro.it" target="_blank">Claudio S. Di Mauro</a></p>
  </div>

  <div style="flex: 1; margin-left: 10px;">
    <p><strong>Created:</strong> 8 December 2022</p>
    <p><strong>Update:</strong> 16 June 2024</p>
  </div>

</div>

> _"Code is like humor. When you have to explain it, it's bad."_ – **Cory House**  

<hr style="border: none; border-top: 1px solid #e9e9e9; margin-bottom: 10px;">

## Installation
This library contains all needed dependency to work properly. The only dependency you have to install to use it is already available in the library.  

Using Maven, read the following steps to understand how to do:  

1. Download from the [project's GitHub page](https://github.com/hvna9/consultant-utils) the JAR file of the latest build;
2. Place the downloaded file under a folder of your Maven project;
3. In the pom of your Maven project, set the following dependency:

```xml
<!-- ConsultantUtils -->
<dependency>
  <groupId>com.github.hvna9</groupId>
  <artifactId>consultant-utils</artifactId>
  <version>${selected_version}</version>
  <scope>system</scope>
  <systemPath>${path}\Consultant-Utils-${selected_version}.jar</systemPath>
</dependency>
```

where:

* `${selected_version}` is the variable setted in the properties tag of the POM, which identifies the version you want to use (the same you downloaded at step 1);
* `${path}` is the variable setted in the properties tag of the POM, which identifies the absolute path of the JAR.

<br/>
**pom.xml example:**
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.groupId</groupId>
  <artifactId>demo</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>Demo</name>
  
  <properties>
    <selected_version>0.1.6-SNAPSHOT</selected_version>
    <path>C:\Users\UserName\consultant-utils\</path>
  </properties>
  
  <dependencies>
    <dependency>
      <groupId>com.github.h9cu</groupId>
      <artifactId>consultant-utils</artifactId>
      <version>${selected_version}</version>
      <scope>system</scope>
      <systemPath>${path}\Consultant-Utils-${selected_version}.jar</systemPath>
    </dependency>
  </dependencies>

</project>
```
