package spring.model.board;

public class CommentDTO {
	private int cmt_id;
	private int brd_id;
	private int cmt_like;
	private int cmt_dislike;
	private int cmt_grp;
	private String email;
	private String cmt_content;
	private String cmt_email;
	private String create_time;
	private String update_time;
	
	public String getCmt_email() {
		return cmt_email;
	}
	public void setCmt_email(String cmt_email) {
		this.cmt_email = cmt_email;
	}
	public int getCmt_id() {
		return cmt_id;
	}
	public void setCmt_id(int cmt_id) {
		this.cmt_id = cmt_id;
	}
	public int getBrd_id() {
		return brd_id;
	}
	public void setBrd_id(int brd_id) {
		this.brd_id = brd_id;
	}
	public int getCmt_like() {
		return cmt_like;
	}
	public void setCmt_like(int cmt_like) {
		this.cmt_like = cmt_like;
	}
	public int getCmt_dislike() {
		return cmt_dislike;
	}
	public void setCmt_dislike(int cmt_dislike) {
		this.cmt_dislike = cmt_dislike;
	}
	public int getCmt_grp() {
		return cmt_grp;
	}
	public void setCmt_grp(int cmt_grp) {
		this.cmt_grp = cmt_grp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCmt_content() {
		return cmt_content;
	}
	public void setCmt_content(String cmt_content) {
		this.cmt_content = cmt_content;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
	
}
