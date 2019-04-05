package lexer;
/**
 * @auther shchzh@mail.ustc.edu.cn
 * @blog https://blog.csdn.net/raylrnd
 * @date 04/04/2019 20:31
 */
//参照javacc源码
public enum  TokenType {
   //关键字
    VOID,
    INT,
    NEW,
    IF,
    ELSE,
    FOR,
    CLASS,
    RETURN,
    NULL,
    BREAK,
    ID,          //标志符
    SYMBOL,      //合法的符号，比如 + - * / () 等
    NUM,
    BOOLEAN,
    STRING,
    CHAR,
            //等同null
    ERROR,       //错误token
    ENDOFFILE;


    //判断是否是本地类型
    public boolean isPrimitive(){
        switch (this){
            case NUM:
            case BOOLEAN:
            case STRING:
            case CHAR:
                return true;
            default:
                return false;
        }
    }
}

