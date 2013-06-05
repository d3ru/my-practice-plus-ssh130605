package businesses.login;

public class UserInfo {
	private String umail;
	private Integer uid;
	private String uname;
	private String upassword;
	private String uaddress;
	private String utele;
	private String umobile;
	private String uzip;
	public UserInfo(){}
	public UserInfo(String uname,String upassword){
		this.uname=uname;
		this.upassword=upassword;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUmail() {
		return umail;
	}
	public void setUmail(String umail) {
		this.umail = umail;
	}
	public String getUmobile() {
		return umobile;
	}
	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUtele() {
		return utele;
	}
	public void setUtele(String utele) {
		this.utele = utele;
	}
	public String getUzip() {
		return uzip;
	}
	public void setUzip(String uzip) {
		this.uzip = uzip;
	}
	@Override
	public boolean equals(Object user) {
		// TODO Auto-generated method stub
		if(user instanceof UserInfo){
			if(((UserInfo)user).uname.equals(this.uname) && ((UserInfo)user).upassword.equals(this.upassword)){
				return true;
			}
		}
		return false;
	}

}
