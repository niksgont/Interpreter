package scanner;

public class CustomScanner {
    private final String _text;
    private int _column = 0;
    private int _line = 1;
    private int _position;

    private char getCurrentChar() {
        if (_position >= _text.length())
            return '\0';
        return _text.charAt(_position);
    }

    public CustomScanner(String text) {
        _text = text;
    }

    String findIdentifiersAndSyntax() {
        String identiferChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
        int start = _position;
        if (identiferChars.indexOf(getCurrentChar()) >= 10) {
            _position++;
            _column++;

            while (identiferChars.indexOf(getCurrentChar()) >= 0) {
                _position++;
                _column++;
            }
            return _text.substring(start, _position);
        }
        return null;
    }
    Token findComment(){
        int start = _position;
        if(getCurrentChar() == '#'){
            _position++; _column++;
            while(getCurrentChar() != '\n'){
                _position++; _column++;
            }
            return new Token(Tokens.tkComment, start, _text.substring(start, _position), "#COMMENT", _line, _column);
        }
        return null;
    }
    Token findTuple(){
        int start = _position;
        if(getCurrentChar() == '{'){
            _position++; _column++;
            while(getCurrentChar() != '}'){
                _position++; _column++;
            }
            _position++; _column++;
            return new Token(Tokens.tkTuple, start, _text.substring(start, _position), "tuple", _line, _column);
        }
        return null;
    }
    Token findNewLine() {
        int start = _position;
        if (getCurrentChar() == '\n') {
            _position++;
            _column++;
            _line++;
            _column = 0;
            return new Token(Tokens.tkNewLine, start, "\n", "NEWLINE", _line, _column);
        }
        return null;
    }

    Token findWhiteSpace() {
        char[] spaceChars = { ' ', '\f', '\r', '\t' };
        int start = _position;
        if (charIsInArray(getCurrentChar(), spaceChars)) {
            _position++;
            _column++;
            while (charIsInArray(getCurrentChar(), spaceChars)) {
                _position++;
                _column++;
            }
            return new Token(Tokens.tkWhiteSpace, start, _text.substring(start, _position), "WHITESPACE", _line,
                    _column);
        }
        return null;
    }

    Token findInteger() {
        char[] intChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        int start = _position;
        if (charIsInArray(getCurrentChar(), intChars)) {
            _position++;
            _column++;
            while (charIsInArray(getCurrentChar(), intChars)) {
                _position++;
                _column++;
            }
            return new Token(Tokens.tkInteger, start, _text.substring(start, _position), "<integer>", _line, _column);
        }
        return null;
    }

    Token findStrings() {
        int start = _position;
        if (getCurrentChar() == '"') {
            _position++;
            _column++;
            while (getCurrentChar() != '"') {
                _position++;
                _column++;
            }
            _position++;
            _column++;
            return new Token(Tokens.tkString, start, _text.substring(start, _position), "<string>", _line, _column);
        }
        return null;
    }

    boolean charIsInArray(char c, char[] charArray) {
        for (char spaceChar : charArray) {
            if (spaceChar == c) {
                return true;
            }
        }
        return false;
    }

    public Token findNextToken() {

        // end of the program token
        if (_position >= _text.length()) {
            return new Token(Tokens.EOF, _position, "\0", null, _line, _column);
        }

        // find identifiers and alphabetic syntax token
        String lexeme = findIdentifiersAndSyntax();
        if (lexeme != null) {
            int start = _position;
            switch (lexeme) {
                case "program":
                    return new Token(Tokens.tkProgram, start, lexeme, "program", _line, _column);
                case "output":
                    return new Token(Tokens.tkPrint, start, lexeme, "output", _line, _column);
                case "var":
                    return new Token(Tokens.tkVar, start, lexeme, "var", _line, _column);
                case "func":
                    return new Token(Tokens.tkFunc, start, lexeme, "func", _line, _column);
                case "return":
                    return new Token(Tokens.tkReturn, start, lexeme, "return", _line, _column);
                case "then":
                    return new Token(Tokens.tkThen, start, lexeme, "then", _line, _column);
                case "end":
                    return new Token(Tokens.tkEnd, start, lexeme, "end", _line, _column);
                case "is":
                    return new Token(Tokens.tkIs, start, lexeme, "is", _line, _column);
                case "false":
                    return new Token(Tokens.tkFalse, start, lexeme, "false", _line, _column );
                case "true":
                    return new Token(Tokens.tkTrue, start, lexeme, "true", _line, _column );
                case "if":
                    return new Token(Tokens.tkIf, start, lexeme, "if", _line, _column);
                case "else":
                    return new Token(Tokens.tkElse, start, lexeme, "else", _line, _column);
                case "while":
                    return new Token(Tokens.tkWhile, start, lexeme, "while", _line, _column);
                case "for":
                    return new Token(Tokens.tkFor, start, lexeme, "for", _line, _column);
                case "loop":
                    return new Token(Tokens.tkLoop, start, lexeme, "loop", _line, _column);
                case "or":
                    return new Token(Tokens.tkOr, start, lexeme, "or", _line, _column);
                case "and":
                    return new Token(Tokens.tkAnd, start, lexeme, "and", _line, _column);
                case "not":
                    return new Token(Tokens.tkNot, start, lexeme, "not", _line, _column);
                case "read":
                    return new Token(Tokens.tkRead, start, lexeme, "read", _line, _column);
                default:
                    return new Token(Tokens.tkIdentifier, start, lexeme, "<identifier>", _line,
                            _column - lexeme.length());
            }
        }
        // comment
        Token comment = findComment();
            if(comment != null){ return comment; }
        // new line
        Token newLine = findNewLine();
        if (newLine != null) {
            return newLine;
        }
        // white spaces
        Token whiteSpace = findWhiteSpace();
        if (whiteSpace != null) {
            return whiteSpace;
        }
        // integers
        Token integers = findInteger();
        if (integers != null) {
            return integers;
        }

        // strings
        Token strings = findStrings();
        if (strings != null) {
            return strings;
        }

        // length 2 syntax tokens
        // ":=", "<=", "/=", ">="
        String syntax_len_2 = _text.substring(_position, _position + 2);
        switch (syntax_len_2) {
            case ":=":
                return new Token(Tokens.tkColonEqual, _position += 2, syntax_len_2, ":=", _line, _column);
            case "<=":
                return new Token(Tokens.tkLessEqual, _position += 2, syntax_len_2, "<=", _line, _column);
            case "/=":
                return new Token(Tokens.tkNotEqual, _position += 2, syntax_len_2, "/=", _line, _column);
            case ">=":
                return new Token(Tokens.tkGreaterEqual, _position += 2, syntax_len_2, ">=", _line, _column);
        }

        Character next = getCurrentChar();
        // ":", ".", "<", ">", "=", ";", ",", "(", ")", "+", "-", "*", "/"
        switch (next) {
            case '{':
                return new Token(Tokens.tkOpenCurlyBracket, ++_position, next.toString(), "{", _line, _column);
            case '}':
                return new Token(Tokens.tkCloseCurlyBracket, ++_position, next.toString(), "}", _line, _column);
            case '[':
                return new Token(Tokens.tkOpenSquareBracket, ++_position, next.toString(), "[", _line, _column);
            case ']':
                return new Token(Tokens.tkCloseSquareBracket, ++_position, next.toString(), "]", _line, _column);
            case '.':
                return new Token(Tokens.tkDot, ++_position, next.toString(), ".", _line, _column);
            case '<':
                return new Token(Tokens.tkLess, ++_position, next.toString(), "<", _line, _column);
            case '>':
                return new Token(Tokens.tkGreater, ++_position, next.toString(), ">", _line, _column);
            case '=':
                return new Token(Tokens.tkEqual, ++_position, next.toString(), "=", _line, _column);
            case ';':
                return new Token(Tokens.tkSemicolon, ++_position, next.toString(), ";", _line, _column);
            case ',':
                return new Token(Tokens.tkComma, ++_position, next.toString(), ",", _line, _column);
            case '(':
                return new Token(Tokens.tkOpenParenthesis, ++_position, next.toString(), "(", _line, _column);
            case ')':
                return new Token(Tokens.tkCloseParenthesis, ++_position, next.toString(), ")", _line, _column);
            case '+':
                return new Token(Tokens.tkPlus, ++_position, next.toString(), "+", _line, _column);
            case '-':
                return new Token(Tokens.tkMinus, ++_position, next.toString(), "-", _line, _column);
            case '*':
                return new Token(Tokens.tkMultiply, ++_position, next.toString(), "*", _line, _column);
            case '/':
                return new Token(Tokens.tkDivide, ++_position, next.toString(), "/", _line, _column);
        }

        return new Token(Tokens.tkNotDefined, _position, null, "UNKNOWN_TOKEN", _line, _column);
    }
}
