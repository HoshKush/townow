package spring.model.users;

public class InterestDTO {
	private int in_id;
	private String ca2_name; 
	private String email; 
	private int in_priority;
	private String create_time;
	
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public int getIn_id() {
		return in_id;
	}
	public void setIn_id(int in_id) {
		this.in_id = in_id;
	}
	public String getCa2_name() {
		return ca2_name;
	}
	public void setCa2_name(String ca2_name) {
		this.ca2_name = ca2_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIn_priority() {
		return in_priority;
	}
	public void setIn_priority(int in_priority) {
		this.in_priority = in_priority;
	}
	
	
}
