package domain;

import utilities.MyStack;
import utilities.Iterator;
import utilities.MyQueue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Implements the parsing and display of a XML file
 * 
 * @author Jaymen
 *
 */
public class XMLParser {
	/**
	 * The tag stack for processed tags
	 */
	private MyStack<String> tagStack;
	/**
	 * The error queue
	 */
	private MyQueue<String> errorQueue;
	/**
	 * The parsed file queue
	 */
	private MyQueue<String> parsedFileQueue;

	/**
	 * Creates a new XML parser
	 */
	public XMLParser() {
		tagStack = new MyStack<>();
		errorQueue = new MyQueue<>();
		parsedFileQueue = new MyQueue<>();
	}

	/**
	 * Begins the parsing of the file at the given path
	 * 
	 * @param filePath - The path to the XML file we want to parse
	 */
	public void parseXML(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			int lineNumber = 1; // Variable to track the line number
			while ((line = br.readLine()) != null) {
				processLine(line.trim(), lineNumber); // Pass the line number to the processLine method
				lineNumber++; // Increment the line number
			}
		} catch (IOException e) {
			System.out.println("Error reading the XML file: " + e.getMessage()
					+ " \nPlease follow the right instructions that are shown in the Driver class");
		}

		matchRemainingTags();

		printErrors();
	}

	/**
	 * Processes a line of XML text
	 * 
	 * @param line - The raw string for this line
	 * @param lineNumber - The line number we are currently on
	 */
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
					tag = line.substring(line.indexOf("<"), line.lastIndexOf(">") + 1);
					System.out.println("new tag: " + tag);
					processStartTag(tag, lineNumber);
				} else {
					processStartTag(tag, lineNumber);
				}

				startIndex = closingBracketIndex + 1;
				openingBracketIndex = line.indexOf('<', startIndex);
				closingBracketIndex = line.indexOf('>', openingBracketIndex);
			}

			parsedFileQueue.enqueue(line);
		}
	}

	/**
	 * Processes a self closing tag
	 * 
	 * @param tag - The tag we are on
	 * @param lineNumber - The line number we are currently on
	 */
	private void processSelfClosingTag(String tag, int lineNumber) {
		if (tag.contains(" ")) {
			// Start tag has attributes, extract the tag name without attributes
			int spaceIndex = tag.indexOf(" ");
			tag = tag.substring(0, spaceIndex);

		}
	}

	/**
	 * Processes a start tag
	 * 
	 * @param tag - The tag we are starting
	 * @param lineNumber - The line number we are currently on
	 */
	private void processStartTag(String tag, int lineNumber) {
		if (tag.contains(">")) {
//			System.out.println("start tag on the process:" + tag);
			errorQueue.enqueue("Line " + lineNumber + ": " + tag + " tag has an extra > symbol");
			tag = tag.substring(tag.indexOf("<") + 1, tag.indexOf(" "));
//			System.out.println("start tag after the process:" + tag);
		}
		// Check if the start tag has attributes by searching for a space
		if (tag.contains(" ")) {
			// Start tag has attributes, extract the tag name without attributes
			int spaceIndex = tag.indexOf(" ");
			tag = tag.substring(0, spaceIndex);

		}

//		System.out.println("start tag: <" + tag + ">");
		tagStack.push(tag);
	}

	/**
	 * Processes an end tag
	 * 
	 * @param tag - The tag we are looking to end
	 * @param lineNumber - The line number we are currently on
	 */
	private void processEndTag(String tag, int lineNumber) {
//		System.out.println("end tag: </" + tag + ">");
		if (tagStack.isEmpty()) {
			errorQueue.enqueue("Line " + lineNumber + ": </" + tag + "> tag has no matching opening tag.");
		} else {
			String topTag = tagStack.peek();
			if (!topTag.equalsIgnoreCase(tag)) {
				if (tagStack.contains(tag)) {
					while (!tagStack.isEmpty() && !tagStack.peek().equalsIgnoreCase(tag)) {
						String extraTag = tagStack.pop();
						errorQueue.enqueue("Line " + lineNumber + ": <" + extraTag
								+ "> tag is extra and does not have a matching closing tag.");
					}
					if (!tagStack.isEmpty()) {
						tagStack.pop(); // Pop the matching opening tag
					}
				} else {
					errorQueue.enqueue(
							"Line " + lineNumber + ": </" + tag + "> tag does not have a matching opening tag.");
				}
			} else {
				tagStack.pop(); // Pop the matching opening tag
			}
		}
	}

	/**
	 * Outputs a line with a specific number of tabs
	 * 
	 * @param line - The text we want to output
	 * @param tabs - The number of tabs we want prior to the text
	 */
	private void outputLine(String line, int tabs) {
		String tabString = "";
		
		for (int i = tabs; i > 0; i--) {
			tabString += "\s\s";
		}

		System.out.println(tabString + line);
	}

	/**
	 * Tries to match any remaining tags in the XML File
	 */
	private void matchRemainingTags() {
		while (!tagStack.isEmpty()) {
			String tag = tagStack.pop();
			errorQueue.enqueue("<" + tag + "> tag is not closed properly.");
		}
	}

	/**
	 * Outputs any errors, and displays the XML file we have stored.
	 */
	private void printErrors() {
		if (errorQueue.isEmpty()) {
			System.out.println("No errors found.");
		} else {
			System.out.println("Errors found:");
			while (!errorQueue.isEmpty()) {
				System.out.println(errorQueue.dequeue());
			}
		}

		int tabCount = 0;
		Iterator<String> iter = parsedFileQueue.iterator();
		System.out.println("\nParsed File Content:");

		while (iter.hasNext()) {
			String currentLine = iter.next();

			if (currentLine.startsWith("<?") && currentLine.endsWith("?>")) {
				// Self closing
				outputLine(currentLine, tabCount);
			} else if (currentLine.startsWith("</") && currentLine.endsWith(">")) {
				// Ender
				tabCount -= 1;
				outputLine(currentLine, tabCount);
			} else if (currentLine.startsWith("<") && currentLine.endsWith("/>")) {
				// Self closing
				outputLine(currentLine, tabCount);
			} else {
				// Starting
				outputLine(currentLine, tabCount);
				tabCount += 1;
			}
		}
	}
}
