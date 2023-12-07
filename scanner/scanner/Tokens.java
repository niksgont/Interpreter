package scanner;
public enum Tokens {

    // Literals
    tkVar, tkIdentifier,  
    // Keywords
    tkProgram, tkPrint, tkRead, tkIf, tkThen, tkElse, tkEnd, tkWhile, tkLoop, tkFor, tkReturn, tkIs, tkLength, tkFunc, tkTrue, tkFalse,

    // Signs, comparison, initialization (one or two characters)
    tkColonEqual, tkPlus, tkMinus, tkMultiply, tkDivide, tkSemicolon, tkGreater, tkLess, tkGreaterEqual, tkLessEqual, tkEqual, tkNotEqual,
    tkDot, tkComma,
    // Type
    tkBool, tkInteger, tkReal, tkTuple, tkString, tkEmpty, tkArray,
    //Operators
    tkOr, tkAnd, tkNot, 
    // Brackets
    tkOpenSquareBracket, tkCloseSquareBracket, tkOpenParenthesis, tkCloseParenthesis, tkOpenCurlyBracket, tkCloseCurlyBracket,
    // Comment
    tkComment, 
    tkNewLine, 
    tkWhiteSpace,
    tkNotDefined,
    EOF
}
