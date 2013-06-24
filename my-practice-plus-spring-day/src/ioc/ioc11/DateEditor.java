package ioc.ioc11;

import java.beans.PropertyEditorSupport;
import java.util.Calendar;

/**
 * 自定义编辑器类<br/>
 * PropertyEditorSupport ：将String类型转换成您需要的数据类型。
 * 
 * @author Jert
 * 
 */
public class DateEditor extends PropertyEditorSupport
{
	@Override
	public void setAsText(String text) throws IllegalArgumentException
	{
		String[] t = text.split("-");
		int year = Integer.parseInt(t[0]);
		int month = Integer.parseInt(t[1]) - 1;
		int date = Integer.parseInt(t[2]);

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, date);
		setValue(calendar.getTime());
	}
}
