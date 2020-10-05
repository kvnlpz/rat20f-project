import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder fileContents = new StringBuilder();

        try {
            File file = new File("src/input.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                fileContents.append(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("FIle not found.");
            e.printStackTrace();
        }
        String input = String.valueOf(fileContents);

        FileWriter file = new FileWriter("output.txt");
        // Create tokens and print them
        ArrayList<Lexer.Token> tokens = Lexer.lexFunc(input);
        for (Lexer.Token token : tokens){
//            System.out.println(token);
            file.write(token.toString() + "\n");
        }
        file.close();
    }


}






