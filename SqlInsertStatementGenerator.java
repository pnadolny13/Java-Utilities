import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class SqlInsertStatementGenerator {
	
	/**
	 * Reads a csv file that contains data to generate insert statements for,
	 * outputs an insert.csv file of insert statements.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		String csvFile = "C:\\Users\\INSERT_YOUR_FILENAME.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String tableName = "INSERT_TABLE_NAME";
		File tempFile = new File("C:\\Users\\inserts.csv");
		@SuppressWarnings("resource")
		BufferedWriter wr = new BufferedWriter(new FileWriter(tempFile, true));
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				String insert = "INSERT INTO " + tableName + " VALUES (";
				// use comma as separator
				String[] element = line.split(cvsSplitBy);
				for (int i = 0; i < element.length; i++) {
					if (element[i].contains("\"")) {
						String temp = element[i].replaceAll("'", "");
						temp = temp.replaceAll("\"", "'");
						insert += temp;
					} else {
						insert += element[i];
					}
					if (!(i == (element.length - 1))) {
						insert += ",";
					}
				}
				insert += ");";
				wr.write(insert);
				wr.newLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}