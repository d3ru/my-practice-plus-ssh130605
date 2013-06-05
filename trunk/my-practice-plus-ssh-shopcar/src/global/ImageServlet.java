/*
 * ������ImageServlet ���ã���ɸ�����ͼ�������� ���ڣ� ���ߣ�
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
	private final int width = 57;// ͼ����
	private final int height = 21;// ͼ��߶�

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
		// ���������ʽ
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		// ׼������ͼ��,��֧�ֱ�
		BufferedImage bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Random r = new Random();
		// ��ȡͼ�������Ļ���
		Graphics gc = bimg.getGraphics();
		// �趨����ɫ���������
		gc.setColor(getRandColor(200, 250));
		gc.fillRect(0, 0, width, height);
		// ����ͼ�������Ļ�������
		gc.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// �������200������������ʹͼ���е���֤�벻�ױ�������������̽�⵽
		gc.setColor(getRandColor(160, 200));
		for (int i = 0; i < 200; i++)
		{
			int x1 = r.nextInt(width);
			int y1 = r.nextInt(height);
			int x2 = r.nextInt(15);
			int y2 = r.nextInt(15);
			gc.drawLine(x1, y1, x1 + x2, y1 + y2);
		}
		// �������100�����ŵ㣬ʹͼ���е���֤�벻�ױ�������������̽�⵽
		gc.setColor(getRandColor(120, 240));
		for (int i = 0; i < 100; i++)
		{
			int x = r.nextInt(width);
			int y = r.nextInt(height);
			gc.drawOval(x, y, 0, 0);
		}

		// �������4�����ֵ���֤��
		String rs = "";
		String rn = "";
		for (int i = 0; i < 4; i++)
		{
			rn = String.valueOf(r.nextInt(10));
			rs += rn;
			gc.setColor(new Color(20 + r.nextInt(110), 20 + r.nextInt(110), 20 + r.nextInt(110)));
			gc.drawString(rn, 13 * i + 1, 16);
		}
		// �ͷ�ͼ�������Ļ���
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
		int red = fc + r.nextInt(bc - fc);// ��
		int green = fc + r.nextInt(bc - fc);// ��
		int blue = fc + r.nextInt(bc - fc);// ��
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
