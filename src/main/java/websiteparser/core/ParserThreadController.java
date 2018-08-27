/**
 * @author arpanpathak
 *
 * @created on Aug 27, 2018 4:22:52 PM
 */

package websiteparser.core;

import java.io.*;


/**
 * This class is used to take input from user and create multiple threads for fetching data
 */
public class ParserThreadController 
{
	
	private String[] 	urls ,			// array of website url
					 	fileNames;		// output file names
	BufferedReader 		br;			   // BufferedReader object to take input from user
	
	/**
	 * ParserThreadConstroller Constructor which will initialize BufferedReader object
	 */
	public ParserThreadController() 
	{
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	/**
	 * call this method for taking input urls from user
	 * @throws Exception
	 */
	public void inputUrls() throws Exception 
	{
		System.out.println("Enter urls separated by comma(,) :");
		urls = br.readLine().split(",");
	}
	
	/**
	 * call this method for taking input file names from user
	 * @throws Exception
	 */
	public void inputFileNames() throws Exception 
	{
		System.out.println("Enter destination fileNames separated by comma(,) :");
		fileNames = br.readLine().split(",");
	}
	
	/**
	 * This method will create multiple threads which will fetch data paralley 
	 */
	public void fetchData() 
	{
		for(int i=0 ; i<urls.length ; i++) {
			new ParserThread(urls[i],fileNames[i]).start();
		}
	}
}
