package lexer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static lexer.TokenType.*;
import static lexer.TokenState.*;

/**
 * @auther shchzh@mail.ustc.edu.cn
 * @blog https://blog.csdn.net/raylrnd
 * @date 04/04/2019 20:31
 */
//参照jack-compiler
public class Scanner {
    private static final String pathname = "test.java";
    private static FileReader reader;
    private static String buffLine;
    private static BufferedReader br;
    private static int bufferPos;
    private static Token token;
    public static boolean isSymbols(char c){
        switch (c){
            case '{': case '}': case '(': case ')':
            case '[': case ']': case '.': case ',':
            case ';': case '+': case '-': case '*':
            case '/': case '>': case '<': case '=':
                return true;
            default:
                return false;
        }
    }

    public static void loadSrcFile(){
        try {
            reader = new FileReader(pathname);
            br = new BufferedReader(reader);
            //while ((buffLine = br.readLine()) != null){
            //}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public char nextChar(){
        if(bufferPos >= buffLine.length()){
            try {
                if((buffLine = br.readLine()) == null) return '$';  //读到文件末尾
                bufferPos = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffLine.charAt(bufferPos++);
    }
    public void rollback(){
        assert bufferPos > 0;
        bufferPos--;
    }

    public Token nextToken(){
        TokenState state = START_STATE;
        //确定的有限状态机（DFA）
        while (state != DONE_STATE){
            char c = nextChar();
            if(c == '$') {  //文件结束
                token.type = ENDOFFILE;
                break;
            }
            switch (state){
                case START_STATE:
                    if(isalpha(c) || c == '_'){              //标志符
                        state = ID_STATE;
                        token.type = ID;
                        token.lexeme += c;
                    }
                    else if (c == ' ' || c == '\t' || c == '\n');  //去除空格，换行符
                    else if(isdigit(c)){                           //数字
                        state = INT_STATE;
                        token.type = NUM;
                    }
                    else if(isSymbols(c)) { //符号
                        state = SYMBOL_STATE;
                        token.type = SYMBOL;
                        token.lexeme += c;
                    }
                    else{
                        state = ERROR_STATE;
                        token.type = ERROR;
                        token.lexeme += c;
                    }
                    break;
                case INT_STATE:
                    if(isdigit(c)){
                        token.lexeme +=c;
                    }
                    else {
                        rollback();
                        state = DONE_STATE;
                    }
                    break;
                case ID_STATE:
                    if(isdigit(c) || isalpha(c) || c == '_'){   //数字，字母，下划线
                        token.lexeme +=c;
                    }
                    else {
                        rollback();
                        state = DONE_STATE;
                    }
                case SYMBOL_STATE:
                    if(token.lexeme.equals(">") ||
                            token.lexeme.equals("<") ||
                            token.lexeme.equals("=") ||
                            token.lexeme.equals("!")){
                        if(c == '='){
                            token.lexeme +=c;
                            state = DONE_STATE;
                        }
                        else {
                            rollback();
                            state = DONE_STATE;
                        }
                    }
            }
        }
        MarkKeyWord();
        return token;
    }

    private void MarkKeyWord() {
        if(token.type == ID) {
            switch (token.lexeme){
                case "void":            token.type = VOID;    break;
                case "int":             token.type = INT;     break;
                case "new":             token.type = NEW;     break;
                case "if":              token.type = IF;      break;
                case "for":             token.type = FOR;     break;
                case "class":           token.type = CLASS;   break;
                case "return":          token.type = RETURN;  break;
                case "else":            token.type = ELSE;    break;
                case "null":            token.type = NULL;    break;
                case "break":           token.type = BREAK;   break;
            }
        }
    }


    private boolean isdigit(char c){
        if(c >= '0' && c <= '9') return true;
        return false;
    }
    private boolean isalpha(char c){
        if(c <= 'Z' && c >='A' || c <= 'z' && c >='a') return true;
        return false;
    }


}
