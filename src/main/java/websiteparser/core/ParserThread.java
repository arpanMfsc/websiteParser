/**
 * @author arpanpathak
 *
 * @created on Aug 27, 2018 4:22:52 PM
 */
//change
package websiteparser.core;

import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import websiteparser.core.exception.InsufficientParameterException;
/**
 * Thread for fetching data from website
 */
public class ParserThread extends Thread 
{
	
	private String 		url;						// url from which data needs to be fetched..
	private String 		fileName;					// name of the file where the fetched data will be stored
	final String 		BASE_PATH="D:/Output/"; 	// hard coded base file location...
	
	public ParserThread(String url,String fileName) 
	{
		this.url		= url;
		this.fileName	= fileName; 
	}
	
	/**
	 * This method is used to split the data and returned set of cleaned strings...
	 * @return set of strings removing leading and trailing white spaces and blank lines
	 * @throws IOException
	 */
	public Set<String> getCleanedData() throws IOException 
	{
		// splitting the data into tokens.....
		List<String> tokens 	= Arrays.asList( Jsoup.connect(url)
														  .get()
														  .text()
														  .split("\\.")
												);
		
		Set<String> result 		= new TreeSet<>(String.CASE_INSENSITIVE_ORDER); // data will be sorted ignoring case
	
		// Filtering Fetched data, removing empty lines, leading and trailing white spaces...
		result.addAll(	tokens  .stream     ( )
					  			.map		( token-> token.trim() )
								.filter		( token->token!=null && !token.equals("") )
								.collect	( Collectors.toSet() )
					 );
		
		return result;
	}
	
	@Override
	public void run() 
	{
		FileWriter f = null;
		try 
		{
			System.out.printf("[#%s] Fetching data from \"%s\" \n",this.getName(),url);
			
			
			if(fileName.trim().equals(""))
				throw new InsufficientParameterException("File name can not be empty");
			
			// creating an Instance of FileWriter which will output the data to fiven file name...
			f = new FileWriter( BASE_PATH + fileName + ".txt" );
			
			// Data will atomically be sorted in ascending order because TreeSet is used
			Set<String> tokens		= getCleanedData();		// will store the cleaned data
			StringBuilder data		= new StringBuilder(); // is used to concatenate data for writing into file
			
			// Looping through cleaned data
			for(String s: tokens)
				data.append(s+".\n\n");
			
			
			f.write(data.toString()); // writing data to file
			f.close();
			System.out.printf("Fetched data from \"%s\" is stored in %s.txt\n",url,fileName);
			
		}
		
		catch(IllegalArgumentException e)
		{
			System.out.println("Invalid url given.");
		}
		
		catch(UnknownHostException e)
		{
			System.out.println("404 URL Not Found!...");
		}
		
		catch(HttpStatusException e) 
		{
			System.out.println(e.getStatusCode());
		}
		
		catch(Exception e) 
		{
			System.out.println(e.getMessage());
		}
	
	}

}
