package co.alonsos.java_utilities.io;

public class IO_Text {

	/**
	 * Given the input, it will replace strings with their HTML counterparts. It currently has the
	 * following support: 
	 * \n --> <br><br>
	 * \s\s --> <br>
	 * @param input
	 * @return
	 */
	public String convertToHTML(String input) throws Exception {
		String converted = "";
		input = input.replaceAll("\\n", "<br><br>");
		input = input.replaceAll("\\s\\s", "<br>");
		converted = input;
		return converted;
	}
	
	

}
