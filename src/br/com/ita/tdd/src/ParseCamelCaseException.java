/**
 * 
 */
package br.com.ita.tdd.src;

/**
 * @author JoaoDantas
 *
 */
public class ParseCamelCaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8778217030521531089L;

	/**
	 * @param string
	 */
	public ParseCamelCaseException(String string) {
		super(string);
	}

}
