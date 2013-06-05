/**
 * 文件：order.OrderOperation.java URL：order/order_manager.jsp?orderid=xx 说明：实现对订单的增删修查 订单的增：即购物车中商品的结算
 * 方法：通过判定method=add/delete/update/query,分别对应订单的增/删/修/查 时间：08-05-16
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
		// 购物车结算
		else if (method.equals("add"))
			orderAccount();
		// 订单删除
		else if (method.equals("delete"))
			orderDelete();
		// 订单修改
		else if (method.equals("update"))
			orderUpdate();
		else
			CommonTools.Error(request, response, session);

		// out.println("hello");
		// out.flush();
		// out.close();

	}

	/**
	 * 说明:订单结算 方法：从cookie中读取存放的pid和商品数量 表示：cookie以pro_开头的即为用户下的订单，内容为欲购买的数量
	 */
	private void orderAccount()
	{
		boolean isCreateOrder = false;
		Cookie[] cookies = request.getCookies();
		// 从Cookie中获取选购商品，并生成订单。
		for (Cookie cookie : cookies)
		{
			String pidx = cookie.getName();
			if (pidx.startsWith("pro_"))
			{
				pidx = pidx.substring(4);
				String amountx = cookie.getValue();
				int pid = CommonTools.StringToInt(pidx);
				int amount = CommonTools.StringToInt(amountx);
				// 防止cookie中存在非法的pid、数量且商品表中不存在该商品时
				// 对于存在非法pid或商品数量小于等于0的此处不做处理
				if (pid > 0 && amount > 0 && QueryTools.isExistColumn(dataAccess, TableInfo.PROT_pid, TableInfo.TABLE_Products, TableInfo.PROT_pid + "=" + pid))
				{
					// 操作记录表保存订单
					// 得到用户的id
					String uid = session.getAttribute("sc_user").toString();// 存放的就是用户ID

					// 将当前的时间转换为JDBC的时间格式yyyy-mm-dd hh:mm:ss.fffffffff
					String currTime = new Timestamp(System.currentTimeMillis()).toString();
					String date = currTime.substring(0, 11);
					String time = currTime.substring(11, 19);
					// mysql中DATE的格式：'YYYY-MM-DD'
					// mysql中TIME的格式：'HH:MM:SS'
					String sql = "insert into " + TableInfo.TABLE_Transactions + " (";
					// 交易id,用户id,商品id,交易日期,交易时间,交易数量
					// 交易id为自增长字段,赋0即可
					// tshiped标记默认为0，表示未发货。
					sql += TableInfo.TRAN_tid + "," + TableInfo.TRAN_tuid + "," + TableInfo.TRAN_tpid + "," + TableInfo.TRAN_tdate + "," + TableInfo.TRAN_ttime + "," + TableInfo.TRAN_tamount + "," + TableInfo.TRAN_tshiped + ")";
					sql += " values(0," + uid + "," + pid + ",'" + date + "','" + time + "'," + amount + ",0)";
					// 增加交易记录
					dataAccess.executeSQL(sql);
					isCreateOrder = true;
				}
				else
				{
					// System.out.println("用户ip为"+request.getLocalAddr()+" cookie中存在非法id");
				}

				// 清空cookie中的数据//等显示选购清单后清空
				// cookie.setMaxAge(0);
				// response.addCookie(cookie);
			}

			// 生成临时定单信息
		}
		try
		{
			// 定位到定单信息页面.
			// 打印定单信息
			// response.sendRedirect("../index.jsp");
			// out.println("订单生成，下面显示订单");
			// response.sendRedirect(request.getContextPath()+"/userinfo/orderquery.jsp");
			// response.sendRedirect(request.getContextPath()+"/order/orderatonce.jsp");
			if (isCreateOrder)
			{
				String order = createTmpOrder();
				request.getSession().setAttribute("order", order);
			}
			else
			{
				request.getSession().setAttribute("order", "没有产生定单");
			}

			// RequestDispatcher rd=request.getRequestDispatcher("/order/orderatonce.jsp");
			// rd.forward(request, response);
			response.sendRedirect(request.getContextPath() + "/order/orderatonce.jsp");
			return;

		}
		catch (Exception e)
		{
			// TODO 自动生成 catch 块
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
			Cookie cartproducts[] = request.getCookies();// 通过Ｃookie从客户获取选购商品信息.
			String cpid = "";
			int pid;
			int pronum;
			double totalmoney = 0;
			tmp += "<p>&nbsp;</p>";
			tmp += "<table border='1' width='80%' cellspacing='0' cellpadding='0'>";
			tmp += "<tr style='background-color:#333333;color:blue;'>";
			tmp += "<td>商品名称</td>";
			tmp += "<td>商品价格</td>";
			tmp += "<td>商品说明</td>";
			tmp += "<td>选购数量</td>";
			tmp += "<td>价格小记</td>";
			tmp += "</tr>";
			//
			for (Cookie cook : cartproducts)
			{// 循环解析Cookie中的选购商品信息。
				cpid = cook.getName();// Ｃookie名，选购商品以pro开头。
				if (cpid.startsWith("pro_"))
				{
					try
					{
						pid = Integer.parseInt(cpid.substring(4));// 商品ＩＤ
						pronum = Integer.parseInt(cook.getValue());// 商品数量
						String sql = "select * from " + TableInfo.TABLE_Products + " where pid=" + pid;
						ResultSet rs = ida.queryBySQL(sql);
						while (rs.next())
						{
							tmp += "<tr>";
							String pname = rs.getString("pname");
							String pdescription = rs.getString("pdescription");
							double pprice = rs.getDouble("pprice");

							tmp += "<td>" + pname + "</td>";// 商品名称
							tmp += "<td>" + pprice + "</td>";// 销售价
							tmp += "<td>" + pdescription + "</td>";// 库存量
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
					// 清空Cookie
					cook.setMaxAge(0);// 正:在指定秒数后删除,0:马上删除,负:浏览器关闭后删除.
					// cook.setValue("");
					response.addCookie(cook);
					// response.setHeader("refresh", "0;url='"+request.getContextPath()+"/index.jsp'");

					// System.out.println("Cookie过期");
				}
			}
			// tmp+="<tr  align='center'><td colspan='5' align='center'><B>总金额:</b><font color=red>"+totalmoney+"</font></td></tr>");
			tmp += "<tr  align='center'><td colspan='5' align='center'><B>总金额:</b><font color=red>" + totalmoney + "</font></td></tr>";
			tmp += "</table>";
			tmp += "<p>&nbsp;</p>";
			tmp += "<a href='" + request.getContextPath() + "/index.jsp'><u>返回</u></a>";
		}
		catch (Exception err)
		{

		}
		return tmp;
	}

	/**
	 * 说明：订单删除 方法：从订单表中把tid为orderid的记录删除
	 */
	private void orderDelete()
	{
		try
		{
			String tidx = request.getParameter("orderid");
			int tid = CommonTools.StringToInt(tidx);
			// 防止非法的tid且订单中存在该tid
			if (tid > 0 && QueryTools.isExistColumn(dataAccess, TableInfo.TRAN_tid, TableInfo.TABLE_Transactions, TableInfo.TRAN_tid + "=" + tid))
			{
				String sql = "delete from " + TableInfo.TABLE_Transactions + " where ";
				sql += TableInfo.TRAN_tid + "=" + tid;
				System.out.println("删除订单语句：" + sql);
				boolean b = dataAccess.executeSQL(sql);
				System.out.println("删除订单：" + b);
				response.sendRedirect("orderquery.jsp");
			}
			else
				CommonTools.Error(request, response, session, "orderid非法或该订单不存在!");
		}
		catch (IOException e)
		{
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

	}

	/*
	 * 说明：订单更新 方法：从订单表中把tid为orderid的记录的订单数量tamount修改为传来的数量
	 */
	private void orderUpdate()
	{
		try
		{
			String tidx = request.getParameter("orderid");
			int tid = CommonTools.StringToInt(tidx);
			// 防止非法的tid且订单中存在该tid
			if (tid > 0 && QueryTools.isExistColumn(dataAccess, TableInfo.TRAN_tid, TableInfo.TABLE_Transactions, TableInfo.TRAN_tid + "=" + tid))
			{
				String tamountx = request.getParameter(TableInfo.TRAN_tamount);
				int tamount = CommonTools.StringToInt(tamountx);

				// 从交易记录表中查询id为tid的商品pid
				String pid = QueryTools.getColumnValue(dataAccess, TableInfo.TRAN_tpid, TableInfo.TABLE_Transactions, TableInfo.TRAN_tid + "=" + tid);

				// 从商品表中查询id为pid的商品库存量
				String pamountx = QueryTools.getColumnValue(dataAccess, TableInfo.PROT_pamount, TableInfo.TABLE_Products, TableInfo.PROT_pid + "=" + pid);
				int pamout = CommonTools.StringToInt(pamountx);
				// 确保输入的商品数量小于当前商品的库存量
				if (tamount <= pamout)
				{
					String sql = "update " + TableInfo.TABLE_Transactions + " set ";
					sql += TableInfo.TRAN_tamount + "=" + tamount;
					sql += " where " + TableInfo.TRAN_tid + "=" + tid;
					System.out.println("修改订单语句：" + sql);
					boolean b = dataAccess.executeSQL(sql);
					System.out.println("修改订单：" + b);
					response.sendRedirect("orderquery.jsp");
				}
				else
					CommonTools.Error(request, response, session, "输入的商品数量:" + tamount + "超过了当前商品库存量：" + pamout);
			}
			else
				CommonTools.Error(request, response, session, "orderid非法或该订单不存在!");
		}
		catch (IOException e)
		{
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

	// //说明：返回符合某个条件某表中某个字段的值
	// //条件：只能返回查询的第一个字段值
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
	// System.out.println("执行语句:\n"+sql+"\n时发生错误,请检查!");
	// e.printStackTrace();
	// return null;
	// }
	// }
	//
	// //说明：查询符合某个条件的某表中的某些字段是否有记录集
	// private boolean isExistProduct(String columnName,String tableName,String queryFactor){
	// String sql ="select "+columnName +" from "+tableName+" where "+queryFactor;
	// //System.out.println(sql);
	// try {
	// ResultSet rst = dataAccess.queryBySQL(sql);
	// return rst.next();
	// } catch (SQLException e) {
	// System.out.println("执行语句:\n"+sql+"\n时发生错误,请检查!");
	// e.printStackTrace();
	// return false;
	// }
	// }
}
