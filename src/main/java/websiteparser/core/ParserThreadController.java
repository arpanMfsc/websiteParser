/**
 * @author arpanpathak
 *
 * @created on Aug 27, 2018 4:22:52 PM
 */

package websiteparser.core;

import java.io.*;
import websiteparser.core.exception.*;


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
		//creating an instance of BufferedReader for taking input...
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	/**
	 * call this method for taking input urls from user
	 * @throws InvalidUrlsException
	 * @throws IOException
	 */
	public void inputUrls() throws IOException
	{
		System.out.println("Enter urls separated by comma(,) :");
		urls = br.readLine().split(",");
	}
	
	/**
	 * call this method for taking input file names from user
	 * @throws InsufficientParameterException
	 * @throws IOException
	 */
	public void inputFileNames() throws InsufficientParameterException,IOException
	{
		System.out.println("Enter destination fileNames separated by comma(,) :");
		fileNames = br.readLine().split(",");
		
		/*
		 * validating input list lengths and throwing exception in case of 
		 * parameter length mismatch
		 */
		if( fileNames.length > urls.length)
			throw new InsufficientParameterException("File names provided more than no of urls.");
		
		if( fileNames.length < urls.length)
			throw new InsufficientParameterException("File names provided less than no of urls.");
	}
	
	/**
	 * This method will create multiple threads which will fetch data in parallel 
	 */
	public void fetchData() 
	{
		for(int i=0 ; i<urls.length ; i++) 
			new ParserThread(urls[i],fileNames[i]).start();
	}
}
