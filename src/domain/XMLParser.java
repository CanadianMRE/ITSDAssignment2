package domain;

import utilities.MyStack;
import utilities.MyQueue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class XMLParser {
    private MyStack<String> tagStack;
    private MyQueue<String> errorQueue;
    private MyQueue<String> parsedFileQueue;

    public XMLParser() {
        tagStack = new MyStack<>();
        errorQueue = new MyQueue<>();
        parsedFileQueue = new MyQueue<>();
    }

    public void parseXML(String fileName) {
        String filePath = "res/" + fileName;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1; // Variable to track the line number
            while ((line = br.readLine()) != null) {
                processLine(line.trim(), lineNumber); // Pass the line number to the processLine method
                lineNumber++; // Increment the line number
            }
        } catch (IOException e) {
            System.out.println("Error reading the XML file: " + e.getMessage());
        }

        matchRemainingTags();

        printErrors();
    }

    private void processLine(String line, int lineNumber) {
    	
    	 if (line.startsWith("<?") && line.endsWith("?>")) {
            parsedFileQueue.enqueue(line);
        } else if (line.startsWith("<")) {
            int startIndex = 0;
            int openingBracketIndex = line.indexOf('<', startIndex);
            int closingBracketIndex = line.indexOf('>', openingBracketIndex);

            while (openingBracketIndex >= 0 && closingBracketIndex >= 0) {
                String tag = line.substring(openingBracketIndex + 1, closingBracketIndex).trim();
                if (tag.startsWith("/")) {
                    processEndTag(tag.substring(1).trim(), lineNumber);
                } else if (tag.endsWith("/")) {
                    processSelfClosingTag(tag.substring(0, tag.length() - 1).trim(), lineNumber);
                } else if (line.endsWith(">>")) {
            		 tag = line.substring(line.indexOf("<"), line.lastIndexOf(">")+1);
            		System.out.println("new tag: "+ tag);
            		processStartTag(tag, lineNumber);
            	}else{
                    processStartTag(tag, lineNumber);
                }

                startIndex = closingBracketIndex + 1;
                openingBracketIndex = line.indexOf('<', startIndex);
                closingBracketIndex = line.indexOf('>', openingBracketIndex);
            }

            parsedFileQueue.enqueue(line);
        }
    }


   
    private void processSelfClosingTag(String tag, int lineNumber) {
    	   if (tag.contains(" ")) {
               // Start tag has attributes, extract the tag name without attributes
               int spaceIndex = tag.indexOf(" ");
               tag = tag.substring(0, spaceIndex);
               
           }
    	    System.out.println("Self closing tag: <" + tag + "/>");
    	}

    

    private void processStartTag(String tag, int lineNumber) {
    	 if (tag.contains(">")) {
         	System.out.println("start tag on the process:" + tag);
       	   errorQueue.enqueue("Line " + lineNumber + ": " + tag + " tag has an extra > symbol");
       	   tag = tag.substring(tag.indexOf("<") + 1, tag.indexOf(" "));
       	System.out.println("start tag after the process:" + tag);
          }
        // Check if the start tag has attributes by searching for a space
        if (tag.contains(" ")) {
            // Start tag has attributes, extract the tag name without attributes
            int spaceIndex = tag.indexOf(" ");
            tag = tag.substring(0, spaceIndex);
           
        }
        
        System.out.println("start tag: <" + tag + ">");
        tagStack.push(tag);
    }

    private void processEndTag(String tag, int lineNumber) {
    	System.out.println("end tag: </" + tag + ">");
        if (tagStack.isEmpty()) {
            errorQueue.enqueue("Line " + lineNumber + ": </" + tag + "> tag has no matching opening tag.");
        } else {
            String topTag = tagStack.peek();
            if (!topTag.equalsIgnoreCase(tag)) {
                if (tagStack.contains(tag)) {
                    while (!tagStack.isEmpty() && !tagStack.peek().equalsIgnoreCase(tag)) {
                        String extraTag = tagStack.pop();
                        errorQueue.enqueue("Line " + lineNumber + ": <" + extraTag + "> tag is extra and does not have a matching closing tag.");
                    }
                    if (!tagStack.isEmpty()) {
                        tagStack.pop(); // Pop the matching opening tag
                    }
                } else {
                    errorQueue.enqueue("Line " + lineNumber + ": </" + tag + "> tag does not have a matching opening tag.");
                }
            } else {
                tagStack.pop(); // Pop the matching opening tag
            }
        }
    }


    private void matchRemainingTags() {
        while (!tagStack.isEmpty()) {
            String tag = tagStack.pop();
            errorQueue.enqueue("<" + tag + "> tag is not closed properly.");
        }
    }

    private void printErrors() {
        if (errorQueue.isEmpty()) {
            System.out.println("No errors found.");
        } else {
            System.out.println("Errors found:");
            while (!errorQueue.isEmpty()) {
                System.out.println(errorQueue.dequeue());
            }
        }

        System.out.println("\nParsed File Content:");
        while (!parsedFileQueue.isEmpty()) {
            System.out.println(parsedFileQueue.dequeue());
        }
    }
}
