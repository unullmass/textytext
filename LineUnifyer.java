import java.io.*;
import java.util.*;

/*
	Reads from a file provided as input.
	Merges lines that have been split across multiple lines with double-quotes.
	Should add options to allows to merge by any delimiter.
	
	http://ranthoranfell.blogspot.com/

*/
public class LineUnifyer{
	public static void main(String[] args) throws IOException{
		try{
			File f = new File(args[0]);
			Scanner s = new Scanner(f);
		}catch(IOException io){
			System.out.println("Cannot read from file.");
			System.exit(-1);
		}catch(ArrayOutOfBoundsException ae){
			System.out.println("Usage: java LineUnifyer filename"); 
			System.exit(-1);
		}
		
		
		ArrayList<String> lines = new ArrayList<String>();

		while(s.hasNextLine()){
			String thisLine = s.nextLine();
			boolean quotedLineEnd = false;
			//if the line contains both end quotes skip any more processing
			if(thisLine.matches("^\".*$") && !thisLine.matches("^\".*\"?\"$")){
				//if the line contains no end-quote we need to loop till we find one
				while(!quotedLineEnd && s.hasNextLine()){
					String nextLine = s.nextLine();
					//if the line ends with special characters that continue into the next line
					if(thisLine.matches(".*[\\p{Punct}&&[^\\s]]$"))
						thisLine += nextLine;
					else
						thisLine += " " + nextLine;
					if(nextLine.matches(".*\"$")){
						quotedLineEnd = true;
					}
					
				}
			}
			lines.add(thisLine);
		}
		for(int i  = 0 ; i < lines.size(); i++)
			System.out.println(lines.get(i));
	}
}
