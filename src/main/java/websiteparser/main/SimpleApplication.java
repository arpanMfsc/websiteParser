/**
 * @author arpanpathak
 *
 * @created on Aug 27, 2018 4:22:52 PM
 */
package websiteparser.main;

import java.io.IOException;
import java.net.*;
import websiteparser.core.*;

/**
 * SimpleApplication
 * this class is used for testing the code.
 *
 */
public class SimpleApplication 
{
	/**
	 * main method
	 * @param args
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void main(String[] args) throws IOException, URISyntaxException 
	{
		
		try 
		{
			ParserThreadController tc=new ParserThreadController(); // creating object of ParserController
			tc.inputUrls(); 		// taking urls input
			tc.inputFileNames(); 	// taking file name inputs
			tc.fetchData(); 		// calling the fetch data method which will create multiple threads to fetch data...
		}
		catch(Exception e) 
		{
			System.out.println(e.getMessage());
		}
		
	}
}
