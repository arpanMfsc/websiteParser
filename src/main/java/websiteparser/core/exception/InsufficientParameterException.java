/**
 * 
 * @author arpanpathak
 *
 * @created on Aug 28, 2018 12:53:16 PM
 */
package websiteparser.core.exception;

public class InsufficientParameterException extends Exception
{

	private static final long 	serialVersionUID = 1L;
	private String 				message;		// It will store the custom exception message
	/**
	 * Use this Exception if input parameters are not matching the condition, 
	 * give a custom message
	 * @param message
	 */
	public InsufficientParameterException(String message) 
	{
		this.message = message;
	}
	
	/**
	 * overriding to set custom message for the Exception
	 */
	@Override
	public String getMessage() 
	{
		return message;
	}
	
}
