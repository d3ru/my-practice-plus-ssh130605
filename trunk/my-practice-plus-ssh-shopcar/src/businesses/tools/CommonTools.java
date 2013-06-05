/**
 * �ļ���commonTools.java
 * ˵����һЩ���ù�����
 * ʱ�䣺08-05-17
 * ��д��tarena
 */
package businesses.tools;

import global.TableInfo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommonTools {

	//�ַ���ת��Ϊ����
	public static int StringToInt(String id){
		int n=0;
		try {
			n=Integer.parseInt(id);
		} catch (NumberFormatException e) {
			//System.out.println(id+"ת��ʱ��������");
			//e.printStackTrace();
			n=-1;
		}
		return n>0?n:-1;
	}
	
	//�ַ���ת��ΪС��
	public static double StringToDouble(String id){
		double n=0;
		try {
			n=Double.parseDouble(id);
		} catch (NumberFormatException e) {
			//System.out.println(id+"ת��ʱ��������");
			//e.printStackTrace();
			n=-1;
		}
		return n>0?n:-1;
	}
	
	public static void Error(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Error(request,response,session,"��Ч�ķ���");
	}
	//��������ʱת�򵽵�ҳ��
	public static void Error(HttpServletRequest request, HttpServletResponse response, HttpSession session, String error){
		try {
			session.setAttribute(TableInfo.SC_Error, error);
			response.sendRedirect(request.getContextPath()+"/error.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
