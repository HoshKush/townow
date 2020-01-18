package spring.model.users;

public class UserlocalDTO {
	private String email; 
	private int loc_id; 
	private int ul_priority;
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
