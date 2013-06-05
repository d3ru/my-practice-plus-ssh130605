package businesses.cart;

import global.DataAccessImpl;
import global.IDataAccess;
import global.TableInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businesses.tools.CommonTools;
public class ShopCart extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		IDataAccess ida=DataAccessImpl.newInstance();
		Cookie cartproducts[]=request.getCookies();//ͨ����ookie�ӿͻ���ȡѡ����Ʒ��Ϣ.
		String cpid="";
		int pid;
		int pronum;
		double totalmoney=0;
		//out.print("<table>");
		//
		for(Cookie cook:cartproducts){//ѭ������Cookie�е�ѡ����Ʒ��Ϣ��
			cpid=cook.getName();//��ookie����ѡ����Ʒ��pro��ͷ��
			if(cpid.startsWith("pro_")){
				try {
					pid = Integer.parseInt(cpid.substring(4));//��Ʒ�ɣ�
					pronum = Integer.parseInt(cook.getValue());//��Ʒ����
					String sql = "select * from "+TableInfo.TABLE_Products+" where pid=" + pid;
					ResultSet rs = ida.queryBySQL(sql);
					while(rs.next()){
						String pname = rs.getString("pname");
						int pamount = rs.getInt("pamount");
						double pprice = rs.getDouble("pprice");
						
						out.println("<div id='shops_cart'>");
						out.println("<div class='productinfo_b' style='width: 40%'>"+ pname +"</div>");//��Ʒ����
						out.println("<div class='productinfo_b' style='width: 12%'>"+ pprice +"</div>");//���ۼ�
						out.println("<div class='productinfo_b' style='width: 10%'>"+ pamount +"</div>");//�����
						out.print("<div class='productinfo_b' style='width: 15%;'>"+ pronum + "</div>");
						out.println("<div class='productinfo_b' style='width: 12%'>"+ pronum * pprice +"</div>");
						out.print("<div class='productinfo_b' style='width: 10%; border-right: 0px;'>");
						out.println("<a href='javascript:void(null)' onclick='deleteCookieCart(\""+cpid+"\");'><img src='images/trash.gif' border='0'/></a></div>");
						out.println("</div>");			
						totalmoney+=pronum * pprice;
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.print(e.getMessage());
				}				
			}
		}
		out.print("<div class='moneys'><div style='float:left; width:50%; text-align:right;'>�ܽ��[&yen;]��</div><div id='allmoney' style='float:left'>"+totalmoney+"</div></div>");
		out.flush();
		out.close();
	}
	
	private int StringToInt(String id){
		int n=0;
		try {
			n=Integer.parseInt(id);
		} catch (NumberFormatException e) {
			System.out.println("idת��ʱ��������");
			e.printStackTrace();
			n=-1;
			//throw e;
		}
		return n;
	}
}
