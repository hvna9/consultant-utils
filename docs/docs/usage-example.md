# Usage Example
Tutorials on how to use the library on all its parts.

## Constants
Tutorials on use of `constants` package.

### Placeholder
Understand how to use a placeholder string:

```java
import io.github.hvna9.consultantutils.constants.Placeholder;

public class Main {

  public static void main(String[] args) {
    String loremIpsum10 = Placeholder.LOREM_IPSUM_10;
    String loremIpsum100 = Placeholder.LOREM_IPSUM_100;
    
    System.out.println(Placeholder.LOREM_IPSUM_10);
    System.out.println("------");
    System.out.println(Placeholder.LOREM_IPSUM_100);
  }
  
}
```

**Output:**
```
Lorem nam.
------
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed auctor nulla et lobortis fermentum leo.
```

<hr style="border: none; border-top: 1px solid #e9e9e9; margin-bottom: 10px;">

## Data Manipulators
Tutorials on use of `data` package.

### DateManipulator
A class with static methods to use on conversion and parsing of Date types.

</br>

#### Convert a String into Date
To convert a String into a `java.util.Date` we'll use the static method `stringToDate()`.
This method accept two String as parameters, the first one is the string we want to convert into a Date, the second one is the pattern of the passed String.
Be sure to use the right pattern to identify and parse the String, or you'll get a `ParseException`.

```java
import java.text.ParseException;
import java.util.Date;

import io.github.hvna9.consultantutils.data.DateManipulator;

public class Main {

  public static void main(String[] args) {
    String date = "12/01/2023";
    Date dateFromString = null;
    
    try {
      dateFromString = DateManipulator.stringToDate(date, "dd/MM/yyyy");
    } catch (ParseException e) {
      e.printStackTrace();
    }
    
    System.out.println(dateFromString);
  }

}
```

**Output:**
```
Thu Jan 12 00:00:00 CET 2023
```

If we use a wrong pattern, as in the following example

```java
import java.text.ParseException;
import java.util.Date;

import io.github.hvna9.consultantutils.data.DateManipulator;

public class Main {

  public static void main(String[] args) {
    String date = "12/01/2023";
    Date dateFromString = null;
    
    try {
      dateFromString = DateManipulator.stringToDate(date, "yyyy-MM-dd"); //the pattern style is different 
                                                                         //from the passed String
    } catch (ParseException e) {
      e.printStackTrace();
    }
    
    System.out.println(dateFromString);
  }

}
```

we'll get an exception:

```
java.text.ParseException: Unparseable date: "12/01/2023"
at java.base/java.text.DateFormat.parse(DateFormat.java:399)
at io.github.h9lib.consultantutils.data.DateManipulator.stringToDate(DateManipulator.java:24)
at main.Main.main(Main.java:15)
null
```

</br>

#### Convert a String into java.sql.Timestamp
To convert a `String` into a `java.sql.Timestamp`, read the example in the following snippet:

```java
import io.github.hvna9.consultantutils.data.DateManipulator;

public class Main {

  public static void main(String[] args) {
    String date = "12/01/2023";
    Timestamp timestampFromString = DateManipulator.stringToTimestamp(date, "dd/MM/yyyy");
    
    System.out.println(timestampFromString);

  }

}
```

**Output:**
```
2023-01-12 00:00:00.0
```

</br>

#### Convert a Date into LocalDateTime
Let'see how to convert a `java.util.Date` into a `java.time.LocalDateTime`:

```java
import java.time.LocalDateTime;
import java.util.Date;

import io.github.hvna9.consultantutils.data.DateManipulator;

public class Main {

  public static void main(String[] args) {
    Date date = new Date();
    LocalDateTime ldt = DateManipulator.dateToLocalDateTime(date);
    
    System.out.println(ldt);
  }

```

**Output:**
```
2023-02-05T13:26:03.655
```

<hr style="border: none; border-top: 1px solid #e9e9e9; margin-bottom: 10px;">

### FileManipulator
A class with useful methods to work with files.

</br>

#### Retrieve the mime type of a file
Sometimes we need to know the mime type of a file but we have difficult to do it. With this method, we only need to know the file extension to get the mime type of the main file types.

```java
import io.github.hvna9.consultantutils.data.FileManipulator;

public class Main {

  public static void main(String[] args) {
    String filename = "doc.pdf";
    String mimeType = FileManipulator.retrieveMimeType(filename);
    
    System.out.println("Mime Type: " + mimeType);
  }

} 
```

**Output:**
```
Mime Type: application/pdf
```

In the example above the filename is a fixed string, but of course you can get the filename from any source (such as an input file). Be sure that the name has the complete extension inside.

</br>

#### Retrieve extension from a file
The method to retrieve the extension from a filename is the same used inside the FileManipulator.retrieveMimeType(), but we can use it also in stand-alone mode, just to get the extension of a file from his name:

```java
import io.github.hvna9.consultantutils.data.FileManipulator;

public class Main {

  public static void main(String[] args) {
    String filename = "doc.pdf";
    String extension = FileManipulator.retrieveExtensionFromFilename(filename);
    
    System.out.println("File extension: " + extension);
  }

} 
```

**Output:**
```
File extension: pdf
```

</br>

#### How to generate an Excel