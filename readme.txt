Group 7's XML Parser Program ReadMe
=========================
Completeness of the assignment (as a percentage): 100%
a list of known deficiencies and/or missing functionalities: None.
Overview:
---------
This program is an XML parser. It reads an XML file, processes its contents, and performs the following tasks:
- Checks the validity of XML tags by verifying opening and closing tags.
- Identifies any errors in the XML structure, such as mismatched tags or extra/missing closing tags.
- Prints the parsed content of the XML file.
- Provides a summary of any errors found during the parsing process.

Installation:
--------------
1. Ensure that Java Development Kit (JDK) is installed on your system.
2. Download the XML Parser program source code and save it to a directory of your choice.

Usage:
------
1. Open a command prompt or terminal.
2. Navigate to the directory where the XML Parser program source code is saved.
3. Compile the program and execute it by typing the following command: 
java -jar .\Parser.jar filepath/filename.xml 
fill in filepath with your exact file path of your xml file and the name of the file.
Note: If the XML file is located within the "res" directory your path will simply be res/filename.xml 
Example: java -jar .\Parser.jar res/sample1.xml 
	 java -jar .\Parser.jar res/sample2.xml
4. The program will parse and display the XML file and display any syntax errors or inconsistencies found.
Additional Information:
-----------------------
- The program supports both relative file paths (within the "res" directory) and absolute file paths.

Contact:
--------
For any questions or issues, please contact us.
Group 7.
