package parser;

import java.util.List;
import java.util.Stack;

import scanner.*;

public class ParserBottomUp extends Parser {
    private Stack<ASTNode> treeStack;

    private Stack<BinaryTreeNode> binaryTreeStack;

    private List<Token> tokenStream;

    private int tokenIndex;

    Token nextToken;

    boolean hasNext() {
        return tokenIndex <= tokenStream.size() - 2;
    }

    String peek() {
        if (tokenIndex <= tokenStream.size() - 1) {
            return tokenStream.get(tokenIndex).type;
        }
        System.out.println("TOKENS ARE OVER");
        throw new Error();
    }

    // set the next token and increment the index
    void getNextToken() {
        nextToken = tokenStream.get(tokenIndex);
        tokenIndex++;
    }

    public ParserBottomUp(List<Token> tokenStream) {
        this.tokenStream = tokenStream;
        treeStack = new Stack<>();
        binaryTreeStack = new Stack<>();

        getNextToken();
        anelAST();
    }

    // look what's the next token without incrementing the token index
    public Token peekToken() {
        return tokenStream.get(tokenIndex);
    }

    void anelAST() {
        read("program");
        Dclns();
        SubProgs();
        Body();

        // System.out.println(tokenStream.get(1));
        // System.out.println(tokenStream.get(tokenStream.size()-2));

        // change count accordingly
        constructTree("program", 3);

        // for(ASTNode node : treeStack){
        // node.DFTraverse(0);
        // System.out.println("---------------------");
        // }

        for (BinaryTreeNode node : binaryTreeStack) {
            node.PreOrderTraverse(0);
        }

    }

    void Name() {
        read(Tokens.tkIdentifier);
    }

    void Dclns() {
        if (nextToken.type == "var") {
            read("var");
            Dcln();
            read(";");
            //System.out.println(count);
            constructTree("dclns", 1);
        } else {
            constructTree("dclns", 0);
        }
    }

    void Dcln() {
        Name();
        int count = 1;
        while (nextToken.type != ";" & nextToken.type != ")") {
            read(",");
            Name();
            count++;
        }
        //count++;
        System.out.println(count);
        constructTree("var", count);
    }

    void SubProgs() {

        Fcn();
        int count = 1;
        while (nextToken.token == Tokens.tkFunc) {
            Fcn();
            count++;
        }

        constructTree("subprogs", count);
    }

    void Fcn() {
        System.out.println("-_-");
        read("func");
        Name();
        read("(");
        Params();
        read(")");
        read("is");
        Body();
   
        read(";");

        constructTree("fcn", 3);
    }

    void Params() {
        Dcln();
        int count = 1;
        while (nextToken.type == ";") {
            read(";");
            Dcln();
            count++;
        }

        constructTree("params", count);
    }

    void Body() {
        Statement();
        int count = 1;

        while (nextToken.type == ";") {
            read(";");
            Statement();
            count++;
        }
        read("end");
        constructTree("body", count);
    }

    void Statement() {
        int count = 0;
        switch (nextToken.type) {
            case "if":
                read("if");
                Expression();
                read("then");
                Statement();
                count = 2;
                if (nextToken.type == "else") {
                    read("else");
                    Statement();
                    count++;
                }
                constructTree("if", count);
                break;
            case "for":
                read("for");
                ForStat();
                read(";");
                ForExp();
                read(";");
                ForStat();
                read("loop");
                Statement();
                constructTree("for", 4);
                break;
            case "while":
                read("while");
                Expression();
                read("loop");
                Statement();
                constructTree("while", 2);
                break;
            case "output":
                read("output");
                OutEXp();
                count = 1;
                // out exp list
                while (nextToken.type == ",") {
                    read(",");
                    OutEXp();
                    count++;
                }
                read(";");
                constructTree("output", count);
                break;
            case "return":
                read("return");
                Expression();
                read(";");
                constructTree("return", 1);
                break;
            case "read":
                read("read");
                read("(");
                Name();
                count = 1;
                while (nextToken.type == ",") {
                    read(",");
                    Name();
                    count++;
                }
                read(")");
                constructTree("read", count);
                break;
            case "<identifier>":
                Assignment();
                break;
            default:
                constructTree("<null>", 0);
                break;
        }
    }


    void OutEXp() {
        if (nextToken.type == "<string>") {
            StringNode();
        } else {
            Expression();
            constructTree("nonString", 1);
        }
    }

    void StringNode() {
        read(Tokens.tkString);
    }

    void ForStat() {
        if (nextToken.type == ";") {
            constructTree("<null>", 0);
        } else {
            Assignment();
        }
    }

    void ForExp() {
        if (nextToken.type == ";") {
            constructTree("true", 0);
        } else {
            Expression();
        }
    }

    void Assignment() {
        switch (peek()) {
            case ":=":
                Name();
                read(":=");
                Expression();
                constructTree("assign", 2);
                break;
            default:
                System.out.println("ERROR PEEK: " + peek());
                System.out.println("ERROR NEXT: " + nextToken.type);
                System.out.println("LINE NO: " + nextToken.line);
                System.out.println("CLMN NO: " + nextToken.column);
                throw new Error();
        }
    }

    void Expression() {
        Term();
        if (nextToken.type == "<=" || nextToken.type == "<" || nextToken.type == ">=" || nextToken.type == ">"
                || nextToken.type == "=" || nextToken.type == "/=") {
            switch (nextToken.type) {
                case "<=":
                    read("<=");
                    Term();
                    constructTree("<=", 2);
                    break;
                case "<":
                    read("<");
                    Term();
                    constructTree("<", 2);
                    break;
                case ">=":
                    read(">=");
                    Term();
                    constructTree(">=", 2);
                    break;
                case ">":
                    read(">");
                    Term();
                    constructTree(">", 2);
                    break;
                case "=":
                    read("=");
                    Term();
                    constructTree("=", 2);
                    break;
                case "/=":
                    read("<>");
                    Term();
                    constructTree("<>", 2);
                    break;
                default:
                    System.out.println("ERROR in Expression");
                    System.out.println("TOKEN WAS: " + nextToken.type);
                    System.out.println("LINE NO: " + nextToken.line);
                    System.out.println("CLMN NO: " + nextToken.column);
                    throw new Error();
            }
        }
    }

    void Term() {
        Factor();
        while (nextToken.type == "+" || nextToken.type == "-" || nextToken.type == "or") {
            switch (nextToken.type) {
                case "+":
                    read("+");
                    Factor();
                    constructTree("+", 2);
                    break;
                case "-":
                    read("-");
                    Factor();
                    constructTree("-", 2);
                    break;
                case "or":
                    read("or");
                    Factor();
                    constructTree("or", 2);
                    break;
                default:
                    System.out.println("ERROR in Term");
                    System.out.println("LINE NO: " + nextToken.line);
                    System.out.println("CLMN NO: " + nextToken.column);
                    throw new Error();
            }
        }
    }

    void Factor() {
        Primary();
        while (nextToken.type == "*" || nextToken.type == "/" || nextToken.type == "and" || nextToken.type == "mod") {
            switch (nextToken.type) {
                case "*":
                    read("*");
                    Factor();
                    constructTree("*", 2);
                    break;
                case "/":
                    read("/");
                    Factor();
                    constructTree("/", 2);
                    break;
                case "and":
                    read("and");
                    Factor();
                    constructTree("and", 2);
                    break;
            }
        }
    }

    void Primary(){

        switch(nextToken.type){
            case "{":
                read("{");
                int countT = 1;
                Assignment();
                while (nextToken.type == ",") {
                    read(",");
                    Assignment();
                    countT++;
                }
                read("}"); 
                constructTree("tuple", countT); break;
            case "[":
                read("[");
                int countA = 1;
                Term();
                while (nextToken.type == ",") {
                    read(",");
                    Term();
                    countA++;
                }
                read("]"); 
                constructTree("array", countA); break;

            case "true":
                read(Tokens.tkTrue); break;
            case "false":
                read(Tokens.tkFalse); break;
            case "<string>":
                StringNode(); break;
            case "<integer>":
//                System.out.println(nextToken.type + " "+nextToken.text);
                read(Tokens.tkInteger); break;
            case "eof":
                read("eof");
                constructTree("eof",0);
                break;
            case "-":
                read("-");
                Primary();
                constructTree("-",1);
                break;
            case "+":
                read("+");
                Primary();
                constructTree("+",1);
                break;
            case "not":
                read("not");
                Primary();
                constructTree("not", 1);
                break;
            case "(":
                read("(");
                Expression();
                read(")");
                break;
            case "<identifier>":
                if(peek() == "("){
                    Name();
                    read("(");
                    Expression();
                    int count = 2;
                    while(nextToken.type == ","){
                        read(",");
                        Expression();
                        count++;
                    }
                    read(")");

                    constructTree("call", count);
                }else{
                    Name();
                }
                break;
            default:
                System.out.println("ERROR WHILE PARSING: " + nextToken.type);
                System.out.println("LINE NO: " + nextToken.line);
                System.out.println("CLMN NO: " + nextToken.column);
                throw new Error();
        }

    }

    void read(String type) {
        if (nextToken.type != type) {
            System.out.println("EXPECTED: ->|" + type + "|<-");
            System.out.println("FOUND: " + nextToken.type + " " + nextToken.text);
            System.out.println("LINE NO: " + nextToken.line);
            System.out.println("CLMN NO: " + nextToken.column);
            throw new Error();
        }

        if (hasNext()) {
            getNextToken();
        }
    }

    void read(Tokens kind) {

        if (nextToken.token != kind) {
            System.out.println("EXPECTED: " + kind);
            System.out.println("FOUND: " + nextToken.token + " " + nextToken.text);
            System.out.println("LINE NO: " + nextToken.line);
            System.out.println("CLMN NO: " + nextToken.column);
            throw new Error();
        }

        // ASTNode node_1 = new ASTNode(nextToken.type);
        //
        // ASTNode node_2 = new ASTNode(nextToken.text);
        // node_1.addChildNode(node_2);
        //
        // treeStack.push(node_1);

        BinaryTreeNode node_1 = new BinaryTreeNode(nextToken.type);
        BinaryTreeNode node_2 = new BinaryTreeNode(nextToken.text);

        node_1.setLeftChild(node_2);

        node_1.setChildCount(1);
        binaryTreeStack.push(node_1);

        getNextToken();

    }

    // void constructTree(String node_label, int count){
    // ASTNode node = new ASTNode(node_label);
    // for(int i = 0; i < count ;i++){
    // node.addChildAtIndex(0,treeStack.pop());
    // }
    // treeStack.push(node);
    // }

    void constructTree(String node_label, int count) {
        BinaryTreeNode node = new BinaryTreeNode(node_label);
        BinaryTreeNode p = null;

        for (int j = 0; j < count; j++) {
            BinaryTreeNode c = binaryTreeStack.pop();
            if (p != null) {
                c.setRightChild(p);
            }
            p = c;
        }
        node.setLeftChild(p);
        node.setChildCount(count);
        binaryTreeStack.push(node);
    }
}