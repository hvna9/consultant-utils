# Documentation

## Annotations
### `data.file.excel`
* `@ToExcel`: This annotation, written above a model, indicates that the annotated class should represents the table of an Excel.
* `@XslxExcel`: Written above a model, indicates that the annotated class should represents the table of an Excel which will be saved as .xslx.
* `@ExcelColumnName`: Written above a property of a model, indicates the name of the column represented by the property in the Excel file.
* `@ExcelColumnPosition`: Written above a property of a model, indicates the position of the column represented by the property in the Excel file.

<hr style="border: none; border-top: 1px solid #e9e9e9; margin-bottom: 10px;">

## Constants
* `FileExtension`: it contains the main files extensions.
* `Placeholder`: it contains some Lorem Ipsum placeholder text. You can decide the lenght of the constant string in a set of placeholders.

<hr style="border: none; border-top: 1px solid #e9e9e9; margin-bottom: 10px;">

## Data Manipulators
The package `constantutils.data` contains some classes useful to manipulate several types of data.

### DateManipulator
This class allow to work with standards date types. It contains some static methods, that you can use without the istantiation of a DateManipulator object.

| Modifier and type    | Method                                           | Description                                                              |
|----------------------|--------------------------------------------------|--------------------------------------------------------------------------|
| static LocalDateTime | dateToLocalDateTime(Date date)                   | It converts a Date into LocalDateTime                                    |
| static String        | dateToString(Date date, String pattern)          | Transform a Date into a String, based on the passed date-pattern         |
| static Date          | localDateTimeToDate(LocalDateTime localDateTime) | It converts a LocalDateTime into a Date.                                 |
| static Date          | stringToDate(String date, String pattern)        | It creates a Date from a String                                          |
| static Timestamp     | stringToTimestamp(String date, String pattern)   | It converts a String with specified format-style into a Timestamp object |

### FileManipulator
This class allow to work with files and their extension. It's possible to generate files (e.g. Excel), starting from a list of objects.

| Modifier and type    | Method                                           | Description                                                              |
|----------------------|--------------------------------------------------|--------------------------------------------------------------------------|
| static LocalDateTime | dateToLocalDateTime(Date date)                   | It converts a Date into LocalDateTime                                    |
| static String        | dateToString(Date date, String pattern)          | Transform a Date into a String, based on the passed date-pattern         |
| static Date          | localDateTimeToDate(LocalDateTime localDateTime) | It converts a LocalDateTime into a Date.                                 |
| static Date          | stringToDate(String date, String pattern)        | It creates a Date from a String                                          |
| static Timestamp     | stringToTimestamp(String date, String pattern)   | It converts a String with specified format-style into a Timestamp object |

### ObjectManipulator
This class uses static methods to manipulate objects (eg. fast object conversion into json and vice-versa).

| Modifier and type | Method                                        | Description                             |
|-------------------|-----------------------------------------------|-----------------------------------------|
| static String     | convertToJson(T obj)                          | To create a Json String from an Object. |
| static T          | convertToObject(String json, Class className) | To create an Object from a JSON String. |

### StringManipulator
This class uses static methods to manipulate String objects and work on them.

| Modifier and type | Method                         | Description                                                       |
|-------------------|--------------------------------|-------------------------------------------------------------------|
| static String     | generatePleaceholder(int size) | This method return a placeholder string with a specified length.  |
| static String     | reverse(String str)            | This method is defined to reverse the string passed as parameter. |

<hr style="border: none; border-top: 1px solid #e9e9e9; margin-bottom: 10px;">

## Exception
### `exceptions.annotations`
* `MissingAnnotationException`: a custom exception to signal the missing of a necessary annotation (e.g. during the generation of an Excel).

<hr style="border: none; border-top: 1px solid #e9e9e9; margin-bottom: 10px;">

## Testing
### JUnit - FastModelTester
Useful method to use during JUnit testing.

| Modifier and type | Method                          | Description                                                                                           |
|-------------------|---------------------------------|-------------------------------------------------------------------------------------------------------|
| static void       | testAllModels(Class... classes) | It allows to create fast junit tests for getters and setters methods of simple Java models and beans. |

### Mock - ObjectMocker
A class to generate mocked objects.

| Modifier and type | Method                                                          | Description                                                                                                                                                                   |
|-------------------|-----------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| static List       | createMultiObject(Class className, int size)                    | This method can be used to create a list of objects parameterized on the generic type T.                                                                                      |
| static T          | createSingleObject(Class className)                             | This method can be used to create an object parameterized on the generic object T.                                                                                            |
| static T          | getMock(String mockName, Class className)                       | To use this method you have to create a folder named "mocks" under the src/main/resources or src/test/resources folder of your project, depending on the application context. |
| static T          | getMock(String mockName, String subfolderPath, Class className) | To use this method you have to create a folder named "mocks" under the src/main/resources or src/test/resources folder of your project, depending on the application context. |