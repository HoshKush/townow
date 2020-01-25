package spring.model.users;

public class UserlocalDTO {
	private int ul_id;
	private String email; 
	private int loc_id; 
	private int ul_priority;
	private String create_time;
	
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public int getUl_id() {
		return ul_id;
	}
	public void setUl_id(int ul_id) {
		this.ul_id = ul_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getLoc_id() {
		return loc_id;
	}
	public void setLoc_id(int loc_id) {
		this.loc_id = loc_id;
	}
	public int getUl_priority() {
		return ul_priority;
	}
	public void setUl_priority(int ul_priority) {
		this.ul_priority = ul_priority;
	}
	
}
