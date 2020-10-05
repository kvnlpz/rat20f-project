import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private final String input;

    public Lexer(String input) {

        this.input = input;
    }




    public static ArrayList<Token> lexFunc(String input) {
        // The tokens
        ArrayList<Token> tokens = new ArrayList<Token>();

        // Our lexer starts here

        //StringBuffer is mutable
        StringBuffer buffer = new StringBuffer();
        //iterate through all the tokens we have
        for (TokenType tokenType : TokenType.values()) {
            buffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
        }
        //regex patterns
        matcher(input, tokens, buffer);
        return tokens;
    }

    private static void matcher(String input, ArrayList<Token> tokens, StringBuffer tokenBuffer) {
        Pattern pattern = Pattern.compile(tokenBuffer.substring(1));
        // Now we're going to match the tokens with our enums
        Matcher tokenMatcher = pattern.matcher(input);
        while (tokenMatcher.find()) {
            if (tokenMatcher.group(TokenType.KEYWORD.name()) != null) {
                tokens.add(new Token(TokenType.KEYWORD, tokenMatcher.group(TokenType.KEYWORD.name())));
            } else if ((tokenMatcher.group(TokenType.IDENTIFIER.name()) != null)) {
                tokens.add(new Token(TokenType.IDENTIFIER, tokenMatcher.group(TokenType.IDENTIFIER.name())));
            }else if (tokenMatcher.group(TokenType.SEPARATOR.name()) != null) {
                tokens.add(new Token(TokenType.SEPARATOR, tokenMatcher.group(TokenType.SEPARATOR.name())));
            } else if (tokenMatcher.group(TokenType.NUMBER.name()) != null) {
                tokens.add(new Token(TokenType.NUMBER, tokenMatcher.group(TokenType.NUMBER.name())));
            } else if (tokenMatcher.group(TokenType.OPERATOR.name()) != null) {
                tokens.add(new Token(TokenType.OPERATOR, tokenMatcher.group(TokenType.OPERATOR.name())));
            }
        }
    }


    public enum TokenType {
        // The types of tokens we can have
        NUMBER("[0-9]*\\.?[0-9]"),
        OPERATOR("[*|/|+|-]"),
        WHITESPACE("[ \t\f\r\n]+"),
        KEYWORD("(?<![a-zA-Z0-9])(if|while|int|get|for)(?![a-zA-Z0-9])"),
        IDENTIFIER("\\b(?!(if|while|int|get|for)\\b)\\w+"),
        SEPARATOR("[^a-zA-Z\\d\\s:]");
        public final String pattern;
        TokenType(String pattern) {
            this.pattern = pattern;
        }
    }

    public static class Token {
        public TokenType type;
        public String data;
        public int lineNumber; //unused now

        public Token(TokenType type, String data) {
            this.type = type;
            this.data = data;
        }

        @Override
        public String toString() {
            return type.name() + " " + data;
        }
    }

}
