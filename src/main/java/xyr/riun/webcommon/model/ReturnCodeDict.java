package xyr.riun.webcommon.model;

/**
 * Service返回值数据字典
 * @author Administrator
 *
 */
public class ReturnCodeDict {


	//成功
	public static final int SUC = 200;
	//请求错误
	public static final int REQ_ERR = 400;
	//系统内部错误
	public static final int SER_ERR = 500;
	//http请求调用运营商发生异常
	public static final int RPC_SER_ERR = 504;

	/**
	 * 第一组2位 20:表示服务编码,
	 * 第二组2位:表示
	 * 01-应用级错误(参数错误)，
	 * 02-系统级错误(如权限不足)
	 * 03-业务级错误(service自身处理出错)，
	 * 04-依赖级错误(service内部调用其他接口出错)
	 * 05-交互级业务提醒(正常业务逻辑，非错误)
	 * 99-未知异常
	 * 第三组2位错误明细,如01参数值为空
	 *
	 */

	/**调用服务成功*/
	public static final String SUCCESS = "200000";

	/**应用级错误：参数值为空*/
	public static final String FAIL_APP_NULL = "200101";

	/**应用级错误：参数值错误*/
	public static final String FAIL_APP_ERR = "200102";

	/**应用级错误：权限不足*/
	public static final String FAIL_APP_PERMISSIONS = "200201";

	/**应用级错误：重复调用拦截*/
	public static final String FAIL_APP_REPEAT = "200202";

	/**业务级错误:业务handle执行错误*/
	public static final String FAIL_HANDLE_COMMON = "200301";

	/*-------------------------------1000 未知异常开始--------------------------------------*/

	/**依赖级错误：数据服务调用失败*/
	public static final String FAIL_DEP_COMMON_CALL = "200451";//调用异常
	public static final String FAIL_DEP_COMMON_DATA = "200452";//返回数据异常
	public static final String FAIL_DEP_COMMON_CODE = "200453";//返回code异常

	/**内部错误,未知异常*/
	public static final String ERR_INNER = "209999";

	/**后置错误,未知异常*/
	public static final String ERR_INNER_AFTER = "209901";

	/**异步线程错误,未知异常*/
	public static final String ERR_INNER_HANDDLE_THREAD = "209901";

	/*-------------------------------1000 未知异常结束--------------------------------------*/




}
