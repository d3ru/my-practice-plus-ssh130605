/**
 * 文件：commonTools.java
 * 说明：一些常用工具类
 * 时间：08-05-17
 * 编写：tarena
 */
package businesses.tools;

import global.TableInfo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommonTools {

	//字符串转换为整数
	public static int StringToInt(String id){
		int n=0;
		try {
			n=Integer.parseInt(id);
		} catch (NumberFormatException e) {
			//System.out.println(id+"转换时发生错误");
			//e.printStackTrace();
			n=-1;
		}
		return n>0?n:-1;
	}
	
	//字符串转换为小数
	public static double StringToDouble(String id){
		double n=0;
		try {
			n=Double.parseDouble(id);
		} catch (NumberFormatException e) {
			//System.out.println(id+"转换时发生错误");
			//e.printStackTrace();
			n=-1;
		}
		return n>0?n:-1;
	}
	
	public static void Error(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Error(request,response,session,"无效的访问");
	}
	//发生错误时转向到的页面
	public static void Error(HttpServletRequest request, HttpServletResponse response, HttpSession session, String error){
		try {
			session.setAttribute(TableInfo.SC_Error, error);
			response.sendRedirect(request.getContextPath()+"/error.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
