package lexer;

import ast.ClassNode;
import ast.StmtNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import static lexer.TokenType.*;


/**
 * @auther shchzh@mail.ustc.edu.cn
 * @blog https://blog.csdn.net/raylrnd
 * @date 05/04/2019 08:03
 */
public class Parser {
    private static Scanner scanner;
    public static Token token;
    //LinkedList<Token> tokenBuffer1 = new LinkedList<Token>();  //，保留历史访问的10个记录，用来获取上一次读取的token
    //LinkedList<Token> tokenBuffer2 = new LinkedList<Token>(); //将unget的token存进tokenBuffer2
    ArrayList<Token> tokenBuffer = new ArrayList<>();
    private int buffPos =0;

    private void initScanner(Scanner scanner){
        this.scanner = scanner;
    }
    private ClassNode parseClass(){

    }
    private StmtNode parseStmt(){
        NextToken();
        if(LookAhead(ID,) ){ //函数的定义或者调用

        }
        else if(isF)

    }


    private boolean LookAhead(TokenType ... tokenTypes){
        int n = 0;
        boolean flag = true;
        for(TokenType type : tokenTypes){
            n++;
            if(NextToken().type != type) {
                flag = false;
                break;
            }
        }
        buffPos -= n;
        assert buffPos >= 0;
        return flag;
    }




    private  Token NextToken(){
        if(buffPos  >= tokenBuffer.size()){
            token = scanner.nextToken();
            tokenBuffer.add(token);
        }
        return tokenBuffer.get(buffPos++);
    }

    private void clearBuffer(){
        tokenBuffer.clear();
        buffPos = 0;
    }

}
