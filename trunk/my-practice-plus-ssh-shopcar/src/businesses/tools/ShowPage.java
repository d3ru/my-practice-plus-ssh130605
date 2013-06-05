/**
 * �ļ���showPagev.java
 * ˵������ʾ��ҳ�Ĺ�������
 * ʱ�䣺08-05-16
 * ��д��tarena
 */
package businesses.tools;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.jsp.JspWriter;


public class ShowPage {

	//currPage:��ǰҳ;totalPage:�ܹ���ҳ��;urlAddress�����ӵ��ĵ�ַ
	public static void printPage(PrintWriter out,int currPage,int totalPage,String urlAddress){
		if(totalPage>1){
			out.println("<table width='90%' border='0' cellspacing='0' cellpadding='0' style='font-size:12px'><tr>");
			out.println("<td align=center'>");
			out.println("��ǰ�� "+currPage+" ҳ/�� "+totalPage+" ҳ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			out.print(returnLink(urlAddress,1,"&lt;&lt;��һҳ","��һҳ")+"&nbsp;&nbsp;");
			out.print((currPage-1>0?returnLink(urlAddress,currPage-1,"&lt;��һҳ","��һҳ"):"&lt;��һҳ")+"&nbsp;&nbsp;");
			out.print((currPage+1<=totalPage?returnLink(urlAddress,currPage+1,"��һ&gt;","��һҳ"):"��һ&gt;")+"&nbsp;&nbsp;");
			out.print(returnLink(urlAddress,totalPage,"���ҳ&gt;&gt;","���ҳ")+"&nbsp;&nbsp;&nbsp;");
			out.println("<input type='text' style='width:20;height:15;'  onkeydown='if(event.keyCode==13){showPage(\""+urlAddress+"\",this.value);}' size='3' maxlength='5'/>");
			out.println("</td></tr></table>");
		}
	}
	//currPage:��ǰҳ;totalPage:�ܹ���ҳ��;urlAddress�����ӵ��ĵ�ַ\"
	public static void printPage(JspWriter out,int currPage,int totalPage,String urlAddress){		
		try {
			if(totalPage>1){
				out.println("<table width='90%' border='0' cellspacing='0' cellpadding='0' style='font-size:12px'><tr>");
				out.println("<td align=center'>");
				out.println("��ǰ�� "+currPage+" ҳ/�� "+totalPage+" ҳ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				out.print(returnLink(urlAddress,1,"&lt;&lt;��һҳ","��һҳ")+"&nbsp;&nbsp;");
				out.print((currPage-1>0?returnLink(urlAddress,currPage-1,"&lt;��һҳ","��һҳ"):"&lt;��һҳ")+"&nbsp;&nbsp;");
				out.print((currPage+1<=totalPage?returnLink(urlAddress,currPage+1,"��һҳ&gt;","��һҳ"):"��һҳ&gt;")+"&nbsp;&nbsp;");
				out.print(returnLink(urlAddress,totalPage,"���ҳ&gt;&gt;","���ҳ")+"&nbsp;&nbsp;&nbsp;");
				out.println("<input type='text' style='width:20;height:15;' onkeydown='if(event.keyCode==13){showPage(\""+urlAddress+"\",this.value);}' size='3' maxlength='5'/>");
				out.println("</td></tr></table>");
			}
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
	//
	private static String returnLink(String urlAddress,int page,String linkName,String linkTitle){
		String url = getLink(urlAddress,page);
			return "<a href='"+url+"' title='"+linkTitle+"'>"+linkName+"</a>";
	}
	private static String getLink(String urlAddress,int page){
		String url = (urlAddress.indexOf("?")==-1?"?":"&")+"page="+page;
		return urlAddress+url;
	}
}
