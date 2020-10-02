import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private String input;

    public Lexer(String input) {

        this.input = input;
    }

    public static enum TokenType {
        // The types of tokens we can have
        NUMBER("-?[0-9]+"), OPERATOR("[*|/|+|-]"), WHITESPACE("[ \t\f\r\n]+");

        public final String pattern;

        TokenType(String pattern) {
            this.pattern = pattern;
        }
    }


    public static class Token {
        public TokenType type;
        public String data;

        public Token(TokenType type, String data) {
            this.type = type;
            this.data = data;
        }

        @Override
        public String toString() {
            return String.format("(%s %s)", type.name(), data);
        }
    }


    public static ArrayList<Token> lexFunc(String input) {
        // The tokens
        ArrayList<Token> tokens = new ArrayList<Token>();

        // Our lexer starts here

        //StringBuffer is mutable
        StringBuffer tokenBuffer = new StringBuffer();
        //iterate through all the tokens we have
        for (TokenType tokenType : TokenType.values()){
            tokenBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
        }
        //regex patterns
        Pattern tokenPatterns = Pattern.compile(new String(tokenBuffer.substring(1)));
        // Now we're going to match the tokens with our enums
        Matcher matcher = tokenPatterns.matcher(input);
        while (matcher.find()) {
            if (matcher.group(TokenType.NUMBER.name()) != null) {
                tokens.add(new Token(TokenType.NUMBER, matcher.group(TokenType.NUMBER.name())));
                continue;
            } else if (matcher.group(TokenType.OPERATOR.name()) != null) {
                tokens.add(new Token(TokenType.OPERATOR, matcher.group(TokenType.OPERATOR.name())));
                continue;
            } else if (matcher.group(TokenType.WHITESPACE.name()) != null)
                continue;
        }
        return tokens;
    }

}
