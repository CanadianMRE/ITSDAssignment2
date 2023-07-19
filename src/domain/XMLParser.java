package domain;
import java.io.*;
import utilities.*;

public class XMLParser {
    private MyStack<String> tagStack;
    private MyQueue<String> errorQueue;
   

    public XMLParser() {
        tagStack = new MyStack<>();
        errorQueue = new MyQueue<>();
    }

    public void parseXML(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the XML file: " + e.getMessage());
        }

        matchRemainingTags();

        printErrors();
    }


    private void processLine(String line) {
        if (line.startsWith("<") && line.endsWith(">")) {
            String tag = line.substring(1, line.length() - 1);
            if (tag.startsWith("/")) {
                processClosingTag(tag.substring(1));
            } else if (tag.endsWith("/")) {
                processSelfClosingTag(tag.substring(0, tag.length() - 1));
            } else {
                processOpeningTag(tag);
            }
        } else {
            errorQueue.enqueue(line);
        }
    }

    private void processOpeningTag(String tag) {
        tagStack.push(tag);
    }

    private void processClosingTag(String tag) {
    	
    	
        if (tagStack.isEmpty()) {
            errorQueue.enqueue("</" + tag + "> tag has no matching opening tag.");
        } else {
            String topTag = tagStack.pop();
            if (!topTag.equalsIgnoreCase(tag)) {
                errorQueue.enqueue("</" + tag + "> tag does not match the opening tag <" + topTag + ">.");
            }
        }
    }

    private void processSelfClosingTag(String tag) {
        // No action needed for self-closing tags
    }

    private void matchRemainingTags() {
        while (!tagStack.isEmpty()) {
            String unmatchedTag = tagStack.pop();
            errorQueue.enqueue("</" + unmatchedTag + "> tag has no matching closing tag.");
        }
    }


    private void printErrors() {
        while (!errorQueue.isEmpty()) {
            System.out.println(errorQueue.dequeue());
        }
    }
}