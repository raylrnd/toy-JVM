package lexer;
/**
 * @auther shchzh@mail.ustc.edu.cn
 * @blog https://blog.csdn.net/raylrnd
 * @date 04/04/2019 20:31
 */
public enum TokenState {
    START_STATE,		// 开始状态
    ID_STATE,			// 标识符状态
    INT_STATE,			// 整型数状态
    CHAR_STATE,			// 字符状态
    SYMBOL_STATE,
    INCOMMENT_STATE,	// 注释状态
    ERROR_STATE,		// 错误状态
    DONE_STATE          //结束状态
}
