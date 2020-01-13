import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Welcome to Record Maintainance System");
		System.out.println("--------------------------------------");
		System.out.println("");

		try {
			
			/*Loading properties for log4j*/
			Properties props = new Properties();
			props.load(new FileInputStream("properties/log4j.properties"));
			PropertyConfigurator.configure(props);
			
			/*Performing Operations on file*/
			FileOperations obj = new FileOperations();
			obj.read();
			obj.write();
		} catch (Exception ex) {

			System.out.println("Some Error occoured please try again.");
		}
	}
}
