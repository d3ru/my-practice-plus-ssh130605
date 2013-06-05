package global;

public class Str {
	/**
     * 将iso格式的字符串转换成国标码
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
     * 将国标码格式字符串转换成iso格式字符串
     * @param str String   国标格式字符串
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
