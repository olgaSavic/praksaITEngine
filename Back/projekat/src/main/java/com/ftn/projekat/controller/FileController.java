package com.ftn.projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ftn.projekat.service.FileService;

import java.io.IOException;
 
@RestController
@RequestMapping(value = "files")
public class FileController {
	
	private final FileService fileService;
	 
	@Autowired
	public FileController(FileService fileService) {
		this.fileService = fileService;
	}
 
	@PostMapping(value = "/add")
	@ResponseStatus(HttpStatus.OK)
	public void handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		fileService.storeFile(file);
		System.out.println(file.getOriginalFilename());
	}

}
