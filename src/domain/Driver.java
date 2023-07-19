package domain;

public class Driver {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the name of the XML file.");
            return;
        }

        String fileName = args[0];

        XMLParser parser = new XMLParser();
        parser.parseXML(fileName);
    }
}
