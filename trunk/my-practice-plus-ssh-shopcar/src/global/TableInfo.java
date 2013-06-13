/**
 * 文件：TableInfo.java 说明：定义这些静态变量---表名和表字段名，在访问数据库时使用 时间：2008-08-16 编写：tarena
 */
package global;

public class TableInfo
{

	public static final String TABLE_Products = "Products";
	public static final String PROT_pid = "pid";
	public static final String PROT_pname = "pname";
	public static final String PROT_pdescription = "pdescription";
	public static final String PROT_pdate = "pdate";
	public static final String PROT_pprice = "pprice";
	public static final String PROT_pphoto = "pphoto";
	public static final String PROT_pamount = "pamount";
	public static final String PROT_pnotes = "pnotes";
	public static final String PROT_pprority = "pprority";
	public static final String PROT_pdiscount = "pdiscount";
	public static final String PROT_ptypeid = "ptypeid";

	public static final String TABLE_UserInfo = "UserInfo";
	public static final String USER_umail = "umail";
	public static final String USER_uid = "uid";
	public static final String USER_uname = "uname";
	public static final String USER_upassword = "upassword";
	public static final String USER_uaddress = "uaddress";
	public static final String USER_utele = "utele";
	public static final String USER_umobile = "umobile";
	public static final String USER_uzip = "uzip";

	public static final String TABLE_ProductType = "ProductType";
	public static final String TYPE_ptid = "ptid";
	public static final String TYPE_ptname = "ptname";
	public static final String TYPE_ptnote = "ptnote";

	public static final String TABLE_Transactions = "Transactions";
	public static final String TRAN_tid = "tid";
	public static final String TRAN_tuid = "tuid";
	public static final String TRAN_tpid = "tpid";
	public static final String TRAN_tdate = "tdate";
	public static final String TRAN_ttime = "ttime";
	public static final String TRAN_tshiped = "tshiped";
	public static final String TRAN_tshipdate = "tshipdate";
	public static final String TRAN_tshiptime = "tshiptime";
	public static final String TRAN_tamount = "tamount";

	public static final String SC_User = "sc_user";
	public static final String SC_Error = "sc_error";

}
