/*
 * 类名：ImageServlet 作用：完成附加码图象的输出。 日期： 作者：
 */
package global;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1925469807858069817L;
	private final int width = 57;// 图像宽度
	private final int height = 21;// 图像高度

	/**
	 * Constructor of the object.
	 */
	public ImageServlet()
	{
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		// 定义输出格式
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		// 准备缓冲图像,不支持表单
		BufferedImage bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Random r = new Random();
		// 获取图形上下文环境
		Graphics gc = bimg.getGraphics();
		// 设定背景色并进行填充
		gc.setColor(getRandColor(200, 250));
		gc.fillRect(0, 0, width, height);
		// 设置图形上下文环境字体
		gc.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// 随机产生200条干扰线条，使图像中的认证码不易被其他分析程序探测到
		gc.setColor(getRandColor(160, 200));
		for (int i = 0; i < 200; i++)
		{
			int x1 = r.nextInt(width);
			int y1 = r.nextInt(height);
			int x2 = r.nextInt(15);
			int y2 = r.nextInt(15);
			gc.drawLine(x1, y1, x1 + x2, y1 + y2);
		}
		// 随机产生100个干扰点，使图像中的验证码不易被其他分析程序探测到
		gc.setColor(getRandColor(120, 240));
		for (int i = 0; i < 100; i++)
		{
			int x = r.nextInt(width);
			int y = r.nextInt(height);
			gc.drawOval(x, y, 0, 0);
		}

		// 随机产生4个数字的验证码
		String rs = "";
		String rn = "";
		for (int i = 0; i < 4; i++)
		{
			rn = String.valueOf(r.nextInt(10));
			rs += rn;
			gc.setColor(new Color(20 + r.nextInt(110), 20 + r.nextInt(110), 20 + r.nextInt(110)));
			gc.drawString(rn, 13 * i + 1, 16);
		}
		// 释放图形上下文环境
		gc.dispose();

		// if(request.getSession().getAttribute("hk_verify")!=null || request.getSession().getAttribute("hk_verify")!=""){
		// request.getSession().setAttribute("hk_verify", null);
		// request.getSession().removeAttribute("hk_verify");
		// }
		request.getSession().setAttribute("img", rs);
		JPEGImageEncoder codee = JPEGCodec.createJPEGEncoder(out);
		codee.encode(bimg);
		out.flush();
		out.close();
	}

	public Color getRandColor(int fc, int bc)
	{
		Random r = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int red = fc + r.nextInt(bc - fc);// 红
		int green = fc + r.nextInt(bc - fc);// 绿
		int blue = fc + r.nextInt(bc - fc);// 蓝
		return new Color(red, green, blue);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occure
	 */
	@Override
	public void init() throws ServletException
	{
		// Put your code here
	}

}
