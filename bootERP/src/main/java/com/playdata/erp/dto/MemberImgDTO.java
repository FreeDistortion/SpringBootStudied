package com.playdata.erp.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("memberimg")
public class MemberImgDTO {
	private String id;
	private String originalFilename;
	private String storeFilename;
}
