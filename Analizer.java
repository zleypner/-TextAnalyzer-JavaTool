
package project.pkg1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Project1 {

    public static int line_count = 0;
    public static int non_space_character_count = 0;
    public static int space_inclusive_character_count = 0;
    public static int word_count = 0;

    public static void analyzeLine(String line) {

        boolean in_word = false;
        line_count++;
        for (int i = 0; i < line.length(); i++) {

            char letter = line.charAt(i); // count any character, including spaces

            space_inclusive_character_count++;

            if (letter != " ".charAt(0)) {
                non_space_character_count++;
            }
            if (Character.isLetter(letter)) {

                if (!in_word) {
                    word_count++;
                    in_word = true;
                }
            } else {
                in_word = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        // Method to find out the working directory
        File file = new File("");
        String directoryName = file.getAbsoluteFile().toString();
        System.out.println("Current Working Directory is = " + directoryName);

        FileReader fileReader = null;
        try {
            // open the file and create a BufferedReader for reading (readLine())
            File fileObj = new File("codigovb.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileObj), StandardCharsets.UTF_8));

            String line;

            // traverse the file line by line
            System.out.println("");
            System.out.println("*************************************TEXT****************************************");
            while ((line = bufferedReader.readLine()) != null) {

                System.out.println(line);
                analyzeLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // finally close the reader to handle exceptions
            try {
                if (null != fileReader) {
                    fileReader.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        writeResults(); // call to write the results
        // print results to the console

        executeCommand();
    }

    public static void writeResults() { // Here I print the results in notepad
        try {
            String location = "result.txt";
            // String location = "\\Compiladores_Tarea1\\result.txt";
            String content = "*********************************RESULTS*********************************" + "\n"
                    + "* Total Words in the Text:               " + word_count + "                          *" + "\n"
                    + "*__________________________________________________________________________*" + "\n"
                    + "* Total Characters without spaces:       " + non_space_character_count + "                         *" + "\n"
                    + "*__________________________________________________________________________*" + "\n"
                    + "* Total Characters with spaces:          " + space_inclusive_character_count + "                         *" + "\n"
                    + "*__________________________________________________________________________*" + "\n"
                    + "* Total lines in the text:               " + line_count + "                          *" + "\n"
                    + "****************************************************************************" + "\n";
            System.out.println(content);

            File file = new File(location);
            // If the file doesn't exist, create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void executeCommand() throws IOException {
        try {

            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("notepad.exe result.txt");
        } catch (IOException e) {
        }
    }

}
