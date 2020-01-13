import java.io.*;
import org.apache.log4j.*;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* This class contains all necessary operations to read and write on file*/
public class FileOperations {

	List<Person> records_ = new Vector<Person>(); // Contains all the records from csv file
	static Logger log = Logger.getLogger(FileOperations.class.getName()); // getting log4j logger

	FileOperations() {
		log.setLevel(Level.DEBUG);
	}

	//function for Getting the csv file for input of records
	public FileReader getFile(Scanner sc) throws Exception {
		try {
			System.out.println("Enter the File name with complete path : ");
			String file = sc.nextLine();
			FileReader read = new FileReader(file);
			return read;
		} catch (FileNotFoundException ex) {
			log.error(ex);
			throw new Exception();
		}

	}

	
	// Function to read and store in object of Person
	public void read() throws Exception {

		try (Scanner sc = new Scanner(System.in); FileReader reader = getFile(sc)) {
			Person record = new Person(); 
			String temp = "";
			int ch;

			while ((ch = reader.read()) != -1) {

				if (ch == 9) { //When tab space

					records_.add(record); // Adding record to list of records
					record = new Person(); // Making new empty record
					continue;
				} else {
					if (ch == 44) { // Adding new field on comma encountered
						record.setValue(temp); // set value to required field
						temp = "";
					} else {
						temp += (char) ch;
					}
				}

			}

		} catch (Exception ex) {
			log.error(ex); // logging exception in the file
			throw new Exception();
		}

	}

	// function to write logs in file
	public void write() throws Exception {
		try (Scanner sc = new Scanner(System.in);
				FileWriter writer = new FileWriter("C:\\Users\\Mridul\\Desktop\\Test\\Output.csv");) {

			List<Person> sorted = records_.stream().sorted((p1, p2) -> p1.id.compareTo(p2.id))
					.filter(distinctByKey(p -> p.id))

					.collect(Collectors.toList());
			// sorting the records and filtering the redundant records

			String result = "";
			for (int i = 0; i < sorted.size(); i++) {
				result += sorted.get(i).getCSV() + "	";   // Printing in CSV Format
			}

			writer.write(result);

			int overwritten = sorted.size();  // Number of Overwritten
			int duplicates = records_.size() - overwritten; // number of duplicates

			System.out.println("Over Written : " + overwritten);
			System.out.println("Duplicates : " + duplicates);
			
		} catch (Exception ex) {
			log.error(ex); // Logging the exception
			throw new Exception();
		}
	}

	// Function to assist sorting of the function
	public static <T> Predicate<T> distinctByKey(Function<T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> (map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) != null);
	}

}
