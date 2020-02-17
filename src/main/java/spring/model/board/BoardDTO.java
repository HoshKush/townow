package spring.model.board;

import org.springframework.web.multipart.MultipartFile;

import spring.model.users.UsersDTO;

public class BoardDTO {
	private int brd_id	 	;
	private String title	 	;
	private String content	;
	private String filename	 	;
	private int filesize	 	;
	private MultipartFile filenameMF;
	private int grpno	 	;
	private int indent	 	;
	private int ansno	 	;
	private int grp		 	;
	private String email	 	;
	private String create_time ;
	private String update_time ;
	private int loc_id		 ;
	private int brd_like 		 ;
	private int brd_dislike 	     ;
	private int viewcount 	 ;
	private int ca_id;
	private UsersDTO usersdto;
	
	public BoardDTO() {
		super();   
	}              
	               
	public BoardDTO(int brd_id, String title, String content, String filename,
			int grpno, int indent, int ansno, int grp, String email, String create_time, String update_time,
			int loc_id, int brd_like, int brd_dislike, int viewcount, int ca_id, UsersDTO usersdto, int filesize) {
		super();
		this.brd_id = brd_id;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.filesize = filesize;
		this.grpno = grpno;
		this.indent = indent;
		this.ansno = ansno;
		this.grp = grp;
		this.email = email;
		this.create_time = create_time;
		this.update_time = update_time;
		this.loc_id = loc_id;
		this.brd_like = brd_like;
		this.brd_dislike = brd_dislike;
		this.viewcount = viewcount;
		this.ca_id = ca_id;
		this.usersdto = usersdto;
	}
	
	public UsersDTO getUsersdto() {
		return usersdto;
	}
	public void setUsersdto(UsersDTO usersdto) {
		this.usersdto = usersdto;
	}
	public int getCa_id() {
		return ca_id;
	}
	public void setCa_id(int ca_id) {
		this.ca_id = ca_id;
	}
	public int getBrd_id() {
		return brd_id;
	}
	public void setBrd_id(int brd_id) {
		this.brd_id = brd_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public MultipartFile getFilenameMF() {
		return filenameMF;
	}
	public void setFilenameMF(MultipartFile filenameMF) {
		this.filenameMF = filenameMF;
	}
	public int getGrpno() {
		return grpno;
	}
	public void setGrpno(int grpno) {
		this.grpno = grpno;
	}
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}
	public int getAnsno() {
		return ansno;
	}
	public void setAnsno(int ansno) {
		this.ansno = ansno;
	}
	public int getGrp() {
		return grp;
	}
	public void setGrp(int grp) {
		this.grp = grp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public int getLoc_id() {
		return loc_id;
	}
	public void setLoc_id(int loc_id) {
		this.loc_id = loc_id;
	}	
	public int getBrd_like() {
		return brd_like;
	}
	public void setBrd_like(int brd_like) {
		this.brd_like = brd_like;
	}
	public int getBrd_dislike() {
		return brd_dislike;
	}
	public void setBrd_dislike(int brd_dislike) {
		this.brd_dislike = brd_dislike;
	}
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	
	
}
