package Principal;
import Secundario.*;
public class Principal {
	public static String[] user = new String[11];
	public static String[] name = new String[11];
	public static String[] pass = new String[11];
	public static int click=1;
	public static String []prodName = new String[1];
	public static float []prodPrecio= new float[1];
	public static int []prodCant= new int[1];
	public static String []prodAvatar = new String[1];


	public static void main(String[] args) {
		user[0] ="admin";
		pass[0] ="admin";
		Frames fr = new Frames();
		fr.setVisible(true);
	}
}
