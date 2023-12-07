import java.io.*;
import scanner.CustomScanner;
import scanner.Token;
import scanner.Tokens;
import parser.*;
import java.util.ArrayList;
import java.util.List;

import interpretation.FileCreator;

public class Main {
    private static List<Token> tokenStream = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String path_to_program = "./code.txt";
        String program_as_a_string = null;
        try {
            program_as_a_string = readProgram(path_to_program);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CustomScanner lexer = new CustomScanner(program_as_a_string);
       
        Tokens kind;
        do {
            
            Token token = lexer.findNextToken();
            tokenStream.add(token);
            kind = token.token;
            //System.out.println(kind);
        } while (kind != Tokens.EOF); // & kind != SyntaxKind.BadToken
        //System.out.println(tokenStream);
        List<Token> screenedTokenStream = screenTokenStream();
        ParserBottomUp parser = new ParserBottomUp(screenedTokenStream);
        table table = new table();
        table.displayTypeInformation();
        FileCreator file = new FileCreator();
        file.createJavaFile("C:\\Users\\Асус\\Documents\\compiler");
    }

    private static String readProgram(String path) throws IOException {
        
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString() + "   ";
        } finally {
            reader.close();
        }
    }

    private static List<Token> screenTokenStream() {
        List<Token> screenedToken = new ArrayList<>();
        for (Token token : tokenStream) {
            if (token.token != Tokens.tkComment & token.token != Tokens.tkWhiteSpace & token.token != Tokens.tkNewLine & token.token != Tokens.EOF) {
                System.out.println(token.type);
                screenedToken.add(token);
            }
        }
        return screenedToken;
    }
}
