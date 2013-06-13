/**
 * 文件：showPagev.java 说明：显示分页的公共代码 时间：08-05-16 编写：tarena
 */
package businesses.tools;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.jsp.JspWriter;

public class ShowPage
{

	// currPage:当前页;totalPage:总共的页面;urlAddress：链接到的地址
	public static void printPage(PrintWriter out, int currPage, int totalPage, String urlAddress)
	{
		if (totalPage > 1)
		{
			out.println("<table width='90%' border='0' cellspacing='0' cellpadding='0' style='font-size:12px'><tr>");
			out.println("<td align=center'>");
			out.println("当前第 " + currPage + " 页/共 " + totalPage + " 页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			out.print(returnLink(urlAddress, 1, "&lt;&lt;第一页", "第一页") + "&nbsp;&nbsp;");
			out.print((currPage - 1 > 0 ? returnLink(urlAddress, currPage - 1, "&lt;上一页", "上一页") : "&lt;上一页") + "&nbsp;&nbsp;");
			out.print((currPage + 1 <= totalPage ? returnLink(urlAddress, currPage + 1, "下一&gt;", "下一页") : "下一&gt;") + "&nbsp;&nbsp;");
			out.print(returnLink(urlAddress, totalPage, "最后页&gt;&gt;", "最后页") + "&nbsp;&nbsp;&nbsp;");
			out.println("<input type='text' style='width:20;height:15;'  onkeydown='if(event.keyCode==13){showPage(\"" + urlAddress + "\",this.value);}' size='3' maxlength='5'/>");
			out.println("</td></tr></table>");
		}
	}

	// currPage:当前页;totalPage:总共的页面;urlAddress：链接到的地址\"
	public static void printPage(JspWriter out, int currPage, int totalPage, String urlAddress)
	{
		try
		{
			if (totalPage > 1)
			{
				out.println("<table width='90%' border='0' cellspacing='0' cellpadding='0' style='font-size:12px'><tr>");
				out.println("<td align=center'>");
				out.println("当前第 " + currPage + " 页/共 " + totalPage + " 页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				out.print(returnLink(urlAddress, 1, "&lt;&lt;第一页", "第一页") + "&nbsp;&nbsp;");
				out.print((currPage - 1 > 0 ? returnLink(urlAddress, currPage - 1, "&lt;上一页", "上一页") : "&lt;上一页") + "&nbsp;&nbsp;");
				out.print((currPage + 1 <= totalPage ? returnLink(urlAddress, currPage + 1, "下一页&gt;", "下一页") : "下一页&gt;") + "&nbsp;&nbsp;");
				out.print(returnLink(urlAddress, totalPage, "最后页&gt;&gt;", "最后页") + "&nbsp;&nbsp;&nbsp;");
				out.println("<input type='text' style='width:20;height:15;' onkeydown='if(event.keyCode==13){showPage(\"" + urlAddress + "\",this.value);}' size='3' maxlength='5'/>");
				out.println("</td></tr></table>");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static String returnLink(String urlAddress, int page, String linkName, String linkTitle)
	{
		String url = getLink(urlAddress, page);
		return "<a href='" + url + "' title='" + linkTitle + "'>" + linkName + "</a>";
	}

	private static String getLink(String urlAddress, int page)
	{
		String url = (urlAddress.indexOf("?") == -1 ? "?" : "&") + "page=" + page;
		return urlAddress + url;
	}
}
