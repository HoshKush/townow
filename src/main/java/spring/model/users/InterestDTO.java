package spring.model.users;

public class InterestDTO {
	private int in_id;
	private int ca_id; 
	private String email; 
	private int in_priority;
	private String create_time;
	
	public int getCa_id() {
		return ca_id;
	}
	public void setCa_id(int ca_id) {
		this.ca_id = ca_id;
	}
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
