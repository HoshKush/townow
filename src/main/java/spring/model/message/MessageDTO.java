package spring.model.message;

public class MessageDTO {
	private int msg_id;
	private char msg_delete;
	private String recipient;
	private String sender;
	private String msg_content;
	private String create_time;
	private String read_time;
	private char msg_status;
	
	public char getMsg_status() {
		return msg_status;
	}	
	public void setMsg_status(char msg_status) {
		this.msg_status = msg_status;
	}
	public char getMsg_delete() {
		return msg_delete;
	}
	public void setMsg_delete(char msg_delete) {
		this.msg_delete = msg_delete;
	}
	public int getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getRead_time() {
		return read_time;
	}
	public void setRead_time(String read_time) {
		this.read_time = read_time;
	}
	
	
	
}
