/**
 * �ļ���order.OrderOperation.java URL��order/order_manager.jsp?orderid=xx ˵����ʵ�ֶԶ�������ɾ�޲� ���������������ﳵ����Ʒ�Ľ���
 * ������ͨ���ж�method=add/delete/update/query,�ֱ��Ӧ��������/ɾ/��/�� ʱ�䣺08-05-16
 */
package businesses.order;

import global.DataAccessImpl;
import global.IDataAccess;
import global.TableInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import businesses.tools.CommonTools;
import businesses.tools.QueryTools;

public class OrderOperation extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4964139044506136949L;
	private HttpSession session = null;
	private DataAccessImpl dataAccess = null;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private PrintWriter out;

	public OrderOperation()
	{
		super();
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		this.request = request;
		this.response = response;
		session = request.getSession();
		dataAccess = DataAccessImpl.newInstance();
		response.setContentType("text/html;charset=gb2312");
		out = response.getWriter();
		String method = request.getParameter("method");

		if (method == null)
		{
			CommonTools.Error(request, response, session);
			return;
		}
		// ���ﳵ����
		else if (method.equals("add"))
			orderAccount();
		// ����ɾ��
		else if (method.equals("delete"))
			orderDelete();
		// �����޸�
		else if (method.equals("update"))
			orderUpdate();
		else
			CommonTools.Error(request, response, session);

		// out.println("hello");
		// out.flush();
		// out.close();

	}

	/**
	 * ˵��:�������� ��������cookie�ж�ȡ��ŵ�pid����Ʒ���� ��ʾ��cookie��pro_��ͷ�ļ�Ϊ�û��µĶ���������Ϊ�����������
	 */
	private void orderAccount()
	{
		boolean isCreateOrder = false;
		Cookie[] cookies = request.getCookies();
		// ��Cookie�л�ȡѡ����Ʒ�������ɶ�����
		for (Cookie cookie : cookies)
		{
			String pidx = cookie.getName();
			if (pidx.startsWith("pro_"))
			{
				pidx = pidx.substring(4);
				String amountx = cookie.getValue();
				int pid = CommonTools.StringToInt(pidx);
				int amount = CommonTools.StringToInt(amountx);
				// ��ֹcookie�д��ڷǷ���pid����������Ʒ���в����ڸ���Ʒʱ
				// ���ڴ��ڷǷ�pid����Ʒ����С�ڵ���0�Ĵ˴���������
				if (pid > 0 && amount > 0 && QueryTools.isExistColumn(dataAccess, TableInfo.PROT_pid, TableInfo.TABLE_Products, TableInfo.PROT_pid + "=" + pid))
				{
					// ������¼���涩��
					// �õ��û���id
					String uid = session.getAttribute("sc_user").toString();// ��ŵľ����û�ID

					// ����ǰ��ʱ��ת��ΪJDBC��ʱ���ʽyyyy-mm-dd hh:mm:ss.fffffffff
					String currTime = new Timestamp(System.currentTimeMillis()).toString();
					String date = currTime.substring(0, 11);
					String time = currTime.substring(11, 19);
					// mysql��DATE�ĸ�ʽ��'YYYY-MM-DD'
					// mysql��TIME�ĸ�ʽ��'HH:MM:SS'
					String sql = "insert into " + TableInfo.TABLE_Transactions + " (";
					// ����id,�û�id,��Ʒid,��������,����ʱ��,��������
					// ����idΪ�������ֶ�,��0����
					// tshiped���Ĭ��Ϊ0����ʾδ������
					sql += TableInfo.TRAN_tid + "," + TableInfo.TRAN_tuid + "," + TableInfo.TRAN_tpid + "," + TableInfo.TRAN_tdate + "," + TableInfo.TRAN_ttime + "," + TableInfo.TRAN_tamount + "," + TableInfo.TRAN_tshiped + ")";
					sql += " values(0," + uid + "," + pid + ",'" + date + "','" + time + "'," + amount + ",0)";
					// ���ӽ��׼�¼
					dataAccess.executeSQL(sql);
					isCreateOrder = true;
				}
				else
				{
					// System.out.println("�û�ipΪ"+request.getLocalAddr()+" cookie�д��ڷǷ�id");
				}

				// ���cookie�е�����//����ʾѡ���嵥�����
				// cookie.setMaxAge(0);
				// response.addCookie(cookie);
			}

			// ������ʱ������Ϣ
		}
		try
		{
			// ��λ��������Ϣҳ��.
			// ��ӡ������Ϣ
			// response.sendRedirect("../index.jsp");
			// out.println("�������ɣ�������ʾ����");
			// response.sendRedirect(request.getContextPath()+"/userinfo/orderquery.jsp");
			// response.sendRedirect(request.getContextPath()+"/order/orderatonce.jsp");
			if (isCreateOrder)
			{
				String order = createTmpOrder();
				request.getSession().setAttribute("order", order);
			}
			else
			{
				request.getSession().setAttribute("order", "û�в�������");
			}

			// RequestDispatcher rd=request.getRequestDispatcher("/order/orderatonce.jsp");
			// rd.forward(request, response);
			response.sendRedirect(request.getContextPath() + "/order/orderatonce.jsp");
			return;

		}
		catch (Exception e)
		{
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}

	public String createTmpOrder()
	{
		String tmp = "";
		Cookie[] cookies = request.getCookies();
		try
		{
			IDataAccess ida = DataAccessImpl.newInstance();
			Cookie cartproducts[] = request.getCookies();// ͨ����ookie�ӿͻ���ȡѡ����Ʒ��Ϣ.
			String cpid = "";
			int pid;
			int pronum;
			double totalmoney = 0;
			tmp += "<p>&nbsp;</p>";
			tmp += "<table border='1' width='80%' cellspacing='0' cellpadding='0'>";
			tmp += "<tr style='background-color:#333333;color:blue;'>";
			tmp += "<td>��Ʒ����</td>";
			tmp += "<td>��Ʒ�۸�</td>";
			tmp += "<td>��Ʒ˵��</td>";
			tmp += "<td>ѡ������</td>";
			tmp += "<td>�۸�С��</td>";
			tmp += "</tr>";
			//
			for (Cookie cook : cartproducts)
			{// ѭ������Cookie�е�ѡ����Ʒ��Ϣ��
				cpid = cook.getName();// ��ookie����ѡ����Ʒ��pro��ͷ��
				if (cpid.startsWith("pro_"))
				{
					try
					{
						pid = Integer.parseInt(cpid.substring(4));// ��Ʒ�ɣ�
						pronum = Integer.parseInt(cook.getValue());// ��Ʒ����
						String sql = "select * from " + TableInfo.TABLE_Products + " where pid=" + pid;
						ResultSet rs = ida.queryBySQL(sql);
						while (rs.next())
						{
							tmp += "<tr>";
							String pname = rs.getString("pname");
							String pdescription = rs.getString("pdescription");
							double pprice = rs.getDouble("pprice");

							tmp += "<td>" + pname + "</td>";// ��Ʒ����
							tmp += "<td>" + pprice + "</td>";// ���ۼ�
							tmp += "<td>" + pdescription + "</td>";// �����
							tmp += "<td>" + pronum + "</td>";
							tmp += "<td>" + pronum * pprice + "</td>";
							totalmoney += pronum * pprice;
							tmp += "<tr>";
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
						// System.tmp+=e.getMessage());
					}
					// ���Cookie
					cook.setMaxAge(0);// ��:��ָ��������ɾ��,0:����ɾ��,��:������رպ�ɾ��.
					// cook.setValue("");
					response.addCookie(cook);
					// response.setHeader("refresh", "0;url='"+request.getContextPath()+"/index.jsp'");

					// System.out.println("Cookie����");
				}
			}
			// tmp+="<tr  align='center'><td colspan='5' align='center'><B>�ܽ��:</b><font color=red>"+totalmoney+"</font></td></tr>");
			tmp += "<tr  align='center'><td colspan='5' align='center'><B>�ܽ��:</b><font color=red>" + totalmoney + "</font></td></tr>";
			tmp += "</table>";
			tmp += "<p>&nbsp;</p>";
			tmp += "<a href='" + request.getContextPath() + "/index.jsp'><u>����</u></a>";
		}
		catch (Exception err)
		{

		}
		return tmp;
	}

	/**
	 * ˵��������ɾ�� �������Ӷ������а�tidΪorderid�ļ�¼ɾ��
	 */
	private void orderDelete()
	{
		try
		{
			String tidx = request.getParameter("orderid");
			int tid = CommonTools.StringToInt(tidx);
			// ��ֹ�Ƿ���tid�Ҷ����д��ڸ�tid
			if (tid > 0 && QueryTools.isExistColumn(dataAccess, TableInfo.TRAN_tid, TableInfo.TABLE_Transactions, TableInfo.TRAN_tid + "=" + tid))
			{
				String sql = "delete from " + TableInfo.TABLE_Transactions + " where ";
				sql += TableInfo.TRAN_tid + "=" + tid;
				System.out.println("ɾ��������䣺" + sql);
				boolean b = dataAccess.executeSQL(sql);
				System.out.println("ɾ��������" + b);
				response.sendRedirect("orderquery.jsp");
			}
			else
				CommonTools.Error(request, response, session, "orderid�Ƿ���ö���������!");
		}
		catch (IOException e)
		{
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

	}

	/*
	 * ˵������������ �������Ӷ������а�tidΪorderid�ļ�¼�Ķ�������tamount�޸�Ϊ����������
	 */
	private void orderUpdate()
	{
		try
		{
			String tidx = request.getParameter("orderid");
			int tid = CommonTools.StringToInt(tidx);
			// ��ֹ�Ƿ���tid�Ҷ����д��ڸ�tid
			if (tid > 0 && QueryTools.isExistColumn(dataAccess, TableInfo.TRAN_tid, TableInfo.TABLE_Transactions, TableInfo.TRAN_tid + "=" + tid))
			{
				String tamountx = request.getParameter(TableInfo.TRAN_tamount);
				int tamount = CommonTools.StringToInt(tamountx);

				// �ӽ��׼�¼���в�ѯidΪtid����Ʒpid
				String pid = QueryTools.getColumnValue(dataAccess, TableInfo.TRAN_tpid, TableInfo.TABLE_Transactions, TableInfo.TRAN_tid + "=" + tid);

				// ����Ʒ���в�ѯidΪpid����Ʒ�����
				String pamountx = QueryTools.getColumnValue(dataAccess, TableInfo.PROT_pamount, TableInfo.TABLE_Products, TableInfo.PROT_pid + "=" + pid);
				int pamout = CommonTools.StringToInt(pamountx);
				// ȷ���������Ʒ����С�ڵ�ǰ��Ʒ�Ŀ����
				if (tamount <= pamout)
				{
					String sql = "update " + TableInfo.TABLE_Transactions + " set ";
					sql += TableInfo.TRAN_tamount + "=" + tamount;
					sql += " where " + TableInfo.TRAN_tid + "=" + tid;
					System.out.println("�޸Ķ�����䣺" + sql);
					boolean b = dataAccess.executeSQL(sql);
					System.out.println("�޸Ķ�����" + b);
					response.sendRedirect("orderquery.jsp");
				}
				else
					CommonTools.Error(request, response, session, "�������Ʒ����:" + tamount + "�����˵�ǰ��Ʒ�������" + pamout);
			}
			else
				CommonTools.Error(request, response, session, "orderid�Ƿ���ö���������!");
		}
		catch (IOException e)
		{
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}

	// //˵�������ط���ĳ������ĳ����ĳ���ֶε�ֵ
	// //������ֻ�ܷ��ز�ѯ�ĵ�һ���ֶ�ֵ
	// private String getColumnValue(String columnName,String tableName,String queryFactor){
	// String sql ="select "+columnName +" from "+tableName+" where "+queryFactor;
	// System.out.println(sql);
	// try {
	// ResultSet rst = dataAccess.queryBySQL(sql);
	// if(rst.next())
	// return rst.getString(1);
	// else
	// return null;
	// } catch (SQLException e) {
	// System.out.println("ִ�����:\n"+sql+"\nʱ��������,����!");
	// e.printStackTrace();
	// return null;
	// }
	// }
	//
	// //˵������ѯ����ĳ��������ĳ���е�ĳЩ�ֶ��Ƿ��м�¼��
	// private boolean isExistProduct(String columnName,String tableName,String queryFactor){
	// String sql ="select "+columnName +" from "+tableName+" where "+queryFactor;
	// //System.out.println(sql);
	// try {
	// ResultSet rst = dataAccess.queryBySQL(sql);
	// return rst.next();
	// } catch (SQLException e) {
	// System.out.println("ִ�����:\n"+sql+"\nʱ��������,����!");
	// e.printStackTrace();
	// return false;
	// }
	// }
}
