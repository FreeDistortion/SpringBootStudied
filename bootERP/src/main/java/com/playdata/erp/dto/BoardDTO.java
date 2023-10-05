package com.playdata.erp.dto;
import java.sql.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

@Alias("board")
public class BoardDTO {
	private String board_no;
	private String id;
	private Date write_date;
	private String title;
	private String content;
	private String category;
	// client가 전송하는 binary data를 Spring MVC 내부에서 array or list로 관리.
	private List<MultipartFile> files; 
	
	public BoardDTO() {
//		System.out.println("*************************Default Constructor of BoardDTO*************************");
		// TODO Auto-generated constructor stub
	}
	public BoardDTO(String board_no, String id, Date write_date, String title, String content, String category) {
		super();
		this.board_no = board_no;
		this.id = id;
		this.write_date = write_date;
		this.title = title;
		this.content = content;
		this.category = category;
	}
	
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	public BoardDTO(String board_no, String id, Date write_date, String title, String content, String category,
			List<MultipartFile> files) {
		super();
		this.board_no = board_no;
		this.id = id;
		this.write_date = write_date;
		this.title = title;
		this.content = content;
		this.category = category;
		this.files = files;
	}
	@Override
	public String toString() {
		return "BoardDTO [board_no=" + board_no + ", id=" + id + ", write_date=" + write_date + ", title=" + title
				+ ", content=" + content + ", category=" + category + ", files=" + files + "]";
	}
	public String getBoard_no() {
//		System.out.println("*************************get method*************************");
		return board_no;
	}
	public void setBoard_no(String board_no) {
//		System.out.println("*************************set method*************************");
		this.board_no = board_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
