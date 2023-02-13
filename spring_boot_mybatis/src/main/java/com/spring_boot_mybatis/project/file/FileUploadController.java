package com.spring_boot_mybatis.project.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	@RequestMapping("/fileUploadForm")
	public String fileUploadForm() {
		return "upload/fileUploadForm";
	}
    // 한개파일 업로드
	@RequestMapping("/fileUpload")
	public String fileUpload(@RequestParam("uploadFile") MultipartFile file, 
			                                              Model model) throws IOException {

		//1
		String uploadPath="C:/springWorkspace/upload/";
		//2
		String originalFileName=file.getOriginalFilename();
		//3
		UUID uuid =UUID.randomUUID();
		String savedFileName=uuid.toString() + "_" + originalFileName;
		//4
		File sendFile=new File(uploadPath+savedFileName);
		//5
		file.transferTo(sendFile);
		
		model.addAttribute("originalFileName",originalFileName);
		return "upload/fileUploadResultView";
	}
	
	//여러개의 파일 업로드
	@RequestMapping("/fileUploadMultiple")
      public String fileUploadMultiple(@RequestParam("uploadFileMulti")ArrayList<MultipartFile> files,
    		                                                 Model model) throws IOException {
		String uploadPath="C:/springWorkspace/upload/";
		
		ArrayList<String> originalFilenameList= new ArrayList<String>();
		
		for(MultipartFile file:files) {
			//2
			String originalFileName=file.getOriginalFilename();
			originalFilenameList.add(originalFileName);
			
			//3
			UUID uuid =UUID.randomUUID();
			String savedFileName=uuid.toString() + "_" + originalFileName;
			//4
			File sendFile=new File(uploadPath+savedFileName);
			//5
			file.transferTo(sendFile);
			
		}
		model.addAttribute("originalFileNameList",originalFilenameList);
		return "upload/fileUploadMultipleResultView";
	}
	//3번 파일이름을 변경핮; 않고
	@RequestMapping("/fileOriginalNameUpload")
	public String fileOriginalNameUpload(@RequestParam("uploadFileOrigin") MultipartFile file, 
			                                              Model model) throws IOException {

		//1
		String uploadPath="C:/springWorkspace/upload/";
		
		//2
		String originalFileName=file.getOriginalFilename();
		
		//3
		
		//4
		File sendFile=new File(uploadPath+originalFileName);
		
		//5
		file.transferTo(sendFile);
		
		model.addAttribute("originalFileName",originalFileName);
		return "upload/fileUploadResultView";
	}
	
	@RequestMapping("/imageFileUploadForm")
	public String imageFileUploadForm() {
		return "upload/imageFileUpload";
	}
	@ResponseBody
	@RequestMapping("/allFileUpload")
	public String imageFileUpload(@RequestParam("uploadFile") MultipartFile file) throws IOException {
		//1
				String uploadPath="C:/springWorkspace/upload/";
				
				//2
				String originalFileName=file.getOriginalFilename();
				
				//3
				
				//4
				File sendFile=new File(uploadPath+originalFileName);
				
				//5
				file.transferTo(sendFile);
		
		
	String result="success";
	return result;
	}
	
	@RequestMapping("/audioFileUploadForm")
	public String audioFileUploadForm() {
		return "upload/audioFileUpload";
	}
	@RequestMapping("/recordFileUploadForm")
	public String recordFileUploadForm() {
		return "upload/recordFileUpload";
	}
	
}
