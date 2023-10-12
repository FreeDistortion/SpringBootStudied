package com.playdata.booterp.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*@Setter
@Getter
@ToString*/
@Data
//전체 멤버변수를 매개변수로 하는 생성자 정의
@AllArgsConstructor
@NoArgsConstructor
@Alias("boardfile")
public class BoardFileDTO {
	private String board_no;
	private String originalFilename;
	private String storeFilename;
	private String boardFileorder;
	
}
