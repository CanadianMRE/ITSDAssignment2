package domain;

public class Driver {
	public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the XML file name as a command line argument."
            		          + "If the file is in res folder just type in res/filename.xml"
            		          + "If the file is in a file path of your own type in yourpath/filename.xml if your path has spaces please put quotes around the path"
            		          + "your path means the path in your computer and filename.xml means the name of your filename");
            return;
        }

        String fileName = args[0];
        XMLParser parser = new XMLParser();
        parser.parseXML(fileName);
    }
}
