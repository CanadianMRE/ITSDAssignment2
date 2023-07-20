package domain;

import utilities.MyStack;
import utilities.MyQueue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class XMLParser {
    private MyStack<String> tagStack;
    private MyQueue<String> errorQueue;
    private MyQueue<String> extrasQueue;
    private MyQueue<String> parsedFileQueue;

    public XMLParser() {
        tagStack = new MyStack<>();
        errorQueue = new MyQueue<>();
        extrasQueue = new MyQueue<>();
        parsedFileQueue = new MyQueue<>();
    }

    public void parseXML(String fileName) {
        String filePath = "res/" + fileName;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                processLine(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading the XML file: " + e.getMessage());
        }

        matchRemainingTags();

        printErrors();
    }

    private void processLine(String line) {
        if (line.startsWith("<?") && line.endsWith("?>")) {
            parsedFileQueue.enqueue(line);
        } else if (line.startsWith("<")) {
            int startIndex = 0;
            int openingBracketIndex = line.indexOf('<', startIndex);
            int closingBracketIndex = line.indexOf('>', openingBracketIndex);
            
            while (openingBracketIndex >= 0 && closingBracketIndex >= 0) {
                String tag = line.substring(openingBracketIndex + 1, closingBracketIndex).trim();
                if (tag.startsWith("/")) {
                    processEndTag(tag.substring(1).trim());
                } else if (tag.endsWith("/")) {
                    processSelfClosingTag(tag.substring(0, tag.length() - 1).trim());
                } else {
                    processStartTag(tag);
                }
                
                
                startIndex = closingBracketIndex + 1;
                openingBracketIndex = line.indexOf('<', startIndex);
                closingBracketIndex = line.indexOf('>', openingBracketIndex);
            }
            
            parsedFileQueue.enqueue(line);
        } else {
            errorQueue.enqueue(line);
            parsedFileQueue.enqueue(line);
        }
    }




    private void processSelfClosingTag(String tag) {
    	System.out.println("self closing tag " + tag);
    }
    private void processStartTag(String tag) {
    	System.out.println("start tag " + tag);
    	if(tag.endsWith(">")) {
    		errorQueue.enqueue("<"+tag+"> the tag has a extra > symbol when opening the tag");
    	}
    	
        if (tag.contains(" ")) {
            // Start tag has attributes, extract the tag name without attributes
            int spaceIndex = tag.indexOf(" ");
            tag = tag.substring(0, spaceIndex);
        }
        tagStack.push(tag);
        
    }

    private void processEndTag(String tag) {
    	System.out.println("end tag " + tag);
        if (tagStack.isEmpty()) {
            errorQueue.enqueue("</" + tag + "> tag has no matching opening tag.");
        } else {
            String topTag = tagStack.peek();
            if (!topTag.equalsIgnoreCase(tag)) {
                // Check if the closing tag matches the expected opening tag
                if (tagStack.contains(tag)) {
                    // Process extraneous tags
                    while (!tagStack.isEmpty() && !tagStack.peek().equalsIgnoreCase(tag)) {
                        String extraTag = tagStack.pop();
                        extrasQueue.enqueue(extraTag);
                        errorQueue.enqueue("</" + extraTag + "> tag is extra and does not have a matching opening tag.");
                    }
                    // Matching start tag found, remove it from the stack
                    tagStack.pop();
                } else {
                    // Matching start tag not found, report as error
                    errorQueue.enqueue("</" + tag + "> tag does not have a matching opening tag.");
                }
            } else {
                // Matching start tag found, remove it from the stack
                tagStack.pop();
            }
        }
    }
    
    private void matchRemainingTags() {
        while (!tagStack.isEmpty()) {
            String tag = tagStack.pop();
            errorQueue.enqueue("<" + tag + "> tag is extra and does not have a matching closing tag.");
        }

        while (!extrasQueue.isEmpty()) {
            String tag = extrasQueue.dequeue();
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