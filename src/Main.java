import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static String[] tokens;
    String commentStart = "/*";
    String commentEnd = "*/";
    String int_ID = "int";
    String real_ID = "real"; //must have a . in the number
    String if_keyword = "if";
    String else_keyword = "else";
    String fi_keyword = "fi";
    String while_keyword = "while";
    String return_keyword = "return";
    String get_keyword = "get";
    String put_keyword = "put";

    public static void main(String[] args) {
        StringBuilder fileContents = new StringBuilder();

        try {
            File file = new File("src/ratlanguage.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
//                System.out.println(line);
                fileContents.append(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("FIle not found.");
            e.printStackTrace();
        }
//        String input = "11 + 22 - 33";
        String input = String.valueOf(fileContents);
        Lexer lex = new Lexer(input);


        // Create tokens and print them
        ArrayList<Lexer.Token> tokens = Lexer.lexFunc(input);
        for (Lexer.Token token : tokens)
            System.out.println(token);

//        try {
//            Scanner parser = new Scanner(new File("src\\ratlanguage.txt"));
//            while (parser.hasNextLine()) {
//                //System.out.println(parser.nextLine());
//                analyzermethod(parser.nextLine());
//            }
//            parser.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    }

    //iterate through each character in the line
//    private static void analyzermethod(String line) {
//        tokens = line.split(" ");
//
//        for (int i = 0; i < tokens.length; i++){
//            System.out.print(tokens[i]);
//        }
//
//        for (int i = 0; i < tokens.length; i++){
////            if(/*if the first token is a qualifer*/){
//                //check what the rest of the line is
//                //dont stop checking until you see a semicolon
//                //if there's no semicolon and you hit the end of the line, go to the next line
//            }//if it's not a qualifier, what is it?
//        }

//        for(int i = 0; i < line.length(); i++){
//            char c = line.charAt(i);
//            System.out.println(c);
//        }
}

//}




