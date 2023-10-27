package database;

public class User {
	private int userid;
	private String username;
	private String password;
	private String email;
	private String mobile_no;
	private boolean admin = false;
	private boolean event_organizer = false;
	private boolean service_provider = false;

	public User(int userid, String username, String password, String email, String mobile_no, boolean admin,
			boolean event_organizer, boolean service_provider) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile_no = mobile_no;
		this.admin = admin;
		this.event_organizer = event_organizer;
		this.service_provider = service_provider;
	}
	
	public User(String username, String password, String email, String mobile_no, boolean admin,
			boolean event_organizer, boolean service_provider) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile_no = mobile_no;
		this.admin = admin;
		this.event_organizer = event_organizer;
		this.service_provider = service_provider;
	}



	public int getUserid() {
		return userid;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public boolean isAdmin() {
		return admin;
	}
	public boolean isEvent_organizer() {
		return event_organizer;
	}
	public boolean isService_provider() {
		return service_provider;
	}
}
