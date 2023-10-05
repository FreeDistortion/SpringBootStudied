package com.playdata.erp.board;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.playdata.erp.dto.BoardFileDTO;

@Service
public class FileUploadLogicService {

	// file upload execution method
	// upload한 file이 곧 DB에 저장할 정보
	// uploaded file의 정보를 이용해 DTO를 만들고 ArrayList에 담아서 return.
	public ArrayList<BoardFileDTO> uploadFiles(List<MultipartFile> multipartFiles, String path) throws IllegalStateException, IOException {
		ArrayList<BoardFileDTO> fileDTOList=new ArrayList<BoardFileDTO>();
		int count=1;
		for (MultipartFile file : multipartFiles) {
			if (!file.isEmpty()) {
				// Upload를 하는 경우 original file name과 server에 저장되는 server단에서 식별 가능한 file name 두
				// 개를 관리.
				String originalFileName = file.getOriginalFilename();
				String storageFileName = createStorageFileName(originalFileName);
				System.out.println(originalFileName + ", " + storageFileName);

				// File name과 path를 이용해 실제 file object를 만들고 upload하기.
				file.transferTo(new File(path+File.separator+storageFileName));
				fileDTOList.add(new BoardFileDTO(null, originalFileName, storageFileName,""+(count++)));
			}
		}
		
		return fileDTOList;
	}

	// method return file name to identify
	private String createStorageFileName(String originalFileName) {
		int position = originalFileName.lastIndexOf(".");
		String ext = originalFileName.substring(position + 1);
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}
}
