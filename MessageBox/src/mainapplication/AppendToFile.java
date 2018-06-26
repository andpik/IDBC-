package mainapplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AppendToFile {

	private static final String FILENAME = "F:\\Eclipse\\Consoleapp2\\SendMessages.txt";
        private String username;
        private String rusername;
        private String messagetext;
        private String datesent;
        
        public AppendToFile(String username, String rusername, String messagetext, String datesent)
        {
               
                
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

		

			File file = new File(FILENAME);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			bw.write(username);
                        bw.write("\t");
                        bw.write(rusername);
                        bw.write("\t");
                        bw.write(messagetext);
                        bw.write("\t");
                        bw.write(datesent);
                        bw.newLine();

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}

	}

}