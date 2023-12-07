package scanner;

public class Token {
    public Tokens token;
    int position;
    public String type;
    public String text;
    public int line;
    public int column;

    public Token(Tokens token, int position, String text, String type, int line, int column){
        this.token = token;
        this.position = position;
        this.text = text;
        this.type = type;
        this.line = line;
        this.column = column;
    }
}
