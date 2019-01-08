## Alonsos.co Java Utils
This is a library of various utilities ranging from Text manipulation, to HTTP helper methods, to  javax reponse methods which facilitate responses of Javax APIs. 

## Features
- Text Manipulation: 
   - Convert certain chars to HTML tags (like \n to `<br>`)
   - Conversion of HTML tags to ASCII
   - Basic clean up of text with embedded tabs or new lines
   - Wordcount using string tokenizer
   - String arrays to paragraph converter
   - Appends text to a string with a double new line char at the end
   - Global find a replace
   - Hash table lookup for finding elements in a hash and returning their value
   - Levenhstein average distance calculations
   - Removal of duplicate strings using various algos; recursion, iteration and regex
   
- IO utilities:
   - Returning a list of strings given a path
   - Returning the contents of a filepath or stream as a string
   - Confirming a file exists, is empty or executable
   - Creating a file given a string input and a file path
   - Unzipping a file
   - Saving the output of a stream to a file
   - Resizing an image
   
- HTTP Methods:
   - execGet returns a closable HTTP response
   - saveFile to temp allows you to save the return stream to a temporary file
   - execPost returns a closable HTTP response
   
- HTTP Helper:
   - encodeURL will encode the string
   - urlBuilder will create a proper URI 
   
For now, the `lang` package should be ignored

## Testing
* All Tests: `mvn clean test`
* Specific Test Packages: `mvn clean test -Dtest=HTTP_MethodsTest`
* Specific Tests in a given test package: `mvn clean test -Dtest=HTTP_MethodsTest#testGetAPIParams`
