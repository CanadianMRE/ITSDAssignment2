package domain;

public class Driver {
	public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the XML file name as a command line argument.");
            return;
        }

        String fileName = args[0];
        XMLParser parser = new XMLParser();
        parser.parseXML(fileName);
    }
}
