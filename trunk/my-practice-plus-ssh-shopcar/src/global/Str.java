package global;

public class Str {
	/**
     * ��iso��ʽ���ַ���ת���ɹ�����
     * @param str String
     * @return String
     */
    public static String isoToGb(String str){
        String s=null;
        try{
            s= new String(str.getBytes("iso8859-1"),"GBK");
        }catch(Exception e){
            return s;
        }
        return s;
    }
    /**
     * ���������ʽ�ַ���ת����iso��ʽ�ַ���
     * @param str String   �����ʽ�ַ���
     * @return String
     */
    public static String gbToIso(String str){
        String s=null;
        try{
            s=new String(str.getBytes("GBK"),"iso8859-1");
        }catch(Exception e){
            return s;
        }
        return s;
    }
}
