package com.playdata.erp.member;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.playdata.erp.dto.MemberImgDTO;


@Service
public class ImageFileUploadLogic {

	public MemberImgDTO uploadIamge(MultipartFile multipartFile, String path) throws IllegalStateException, IOException {

		MemberImgDTO imageFileDTO = new MemberImgDTO();
		if (!multipartFile.isEmpty()) {
			String originalFileName = multipartFile.getOriginalFilename();
			String storageFileName = createStorageFileName(originalFileName);
			System.out.println(originalFileName + ", " + storageFileName);
			multipartFile.transferTo(new File(path + File.separator + storageFileName));
			imageFileDTO = new MemberImgDTO(null, originalFileName, storageFileName);
		}
		return imageFileDTO;
	}
	
	private String createStorageFileName(String originalFileName) {
		int position = originalFileName.lastIndexOf(".");
		String ext = originalFileName.substring(position + 1);
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}

}
