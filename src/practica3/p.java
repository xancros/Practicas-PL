package practica3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Pattern pat = Pattern.compile("[a-zA-Z]+");
		Pattern pat2 = Pattern.compile(pat.pattern()+"|[0-9]+");
		char c = 'a';
		String cad = String.valueOf(c);
		Matcher m = pat2.matcher(cad);
		if (m.matches())
			System.out.println("Letras");
		else
			System.out.println(cad);
	}

}
