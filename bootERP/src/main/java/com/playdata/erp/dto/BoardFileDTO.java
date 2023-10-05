package com.playdata.erp.dto;
import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
@Setter
@Getter
@ToString
*/
@Data

// 전체 member variables를 parameter로 하는 constructor 정의
@AllArgsConstructor

// default constructor
@NoArgsConstructor

@Alias("boardfile")
public class BoardFileDTO {
	public BoardFileDTO(Object object, String originalFileName2, String storageFileName, String string) {
		// TODO Auto-generated constructor stub
	}
	private String board_no; 
	private String originalFilename;
	private String storeFilename;
	private String boardFileorder;
}
