package domain;

public class Driver {
	/**
	 * 
	 * 
	 * @param args - Arguments we start the program with
	 */
	public static void main(String[] args) {
		System.out.println("This program is an XML parser. It reads an XML file, processes its contents, and performs the following tasks:\n" +
		        "- Checks the validity of XML tags by verifying opening and closing tags.\n" +
		        "- Identifies any errors in the XML structure, such as mismatched tags or extra/missing closing tags.\n" +
		        "- Prints the parsed content of the XML file.\n" +
		        "- Provides a summary of any errors found during the parsing process.");
		
        if (args.length == 0) {
            System.out.println("Please provide the XML file name as a command line argument."
            		          + "\nIf the file is in res folder just type in res/filename.xml"
            		          + "\nIf the file is in a file path of your own type in yourpath/filename.xml if your path has spaces please put quotes around the path"
            		          + "\nyour path means the path in your computer and filename.xml means the name of your filename");
            return;
        }
        
        System.out.println("\n");

        String fileName = "";
        
        for (int i = 0; i < args.length; i++) {
        	fileName += args[i] + " ";
        }
        
        fileName = fileName.trim();
        
        XMLParser parser = new XMLParser();
        parser.parseXML(fileName);
        
    }
}
