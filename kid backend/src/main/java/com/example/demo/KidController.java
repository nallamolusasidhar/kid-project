package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins="*")
public class KidController {
	@Autowired
	KidRepo doctorRepo;
	
	@GetMapping("/stu/find")
	public Kid findById(@RequestParam int id) {
		
		
		 Kid doctor =doctorRepo.findById(id).get();
		 
		 doctor.setImage(decompressBytes(doctor.getImage()));
		 
		 return doctor;
		
	}
	
	@PostMapping("/stu/add")
	public String addProduct(@RequestParam ("dietFile") MultipartFile myFile,
			String name,
			String classname,
			String section,
			String fatherName,
			String address,
			String phone) {
		
		try {
			
			Kid prdImage = new Kid(name,classname,section,fatherName,address,phone,
					compressBytes(myFile.getBytes()));
			doctorRepo.save(prdImage);		
		}catch(Exception e) {
			
		}
		
		
		
		return "Successfully Added New Product";
		
	}
	
	@GetMapping("/stu/delete")
	public List<Kid> deleteDoctor(@RequestParam int id){
		
		doctorRepo.deleteById(id);
		
		return getAllProducts();
	}
	@GetMapping("/stu/all")
	public List<Kid> getAllProducts(){
		
		List<Kid> drList = new ArrayList<Kid>();
		
		List<Kid> resDrList = doctorRepo.findAll();
		Kid doctor = null;
		for(int i=0;i<resDrList.size();i++) {
			
			doctor = resDrList.get(i);
			
			doctor.setImage(decompressBytes(doctor.getImage()));
			
			drList.add(doctor);
			
		}
		
		
		return drList;
	}
	
	// compress the image bytes before storing it in the database
			public static byte[] compressBytes(byte[] data) {
				Deflater deflater = new Deflater();
				deflater.setInput(data);
				deflater.finish();

				ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
				byte[] buffer = new byte[1024];
				while (!deflater.finished()) {
					int count = deflater.deflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				try {
					outputStream.close();
				} catch (IOException e) {
				}
				System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

				return outputStream.toByteArray();
			}

			// uncompress the image bytes before returning it to the angular application
			public static byte[] decompressBytes(byte[] data) {
				Inflater inflater = new Inflater();
				inflater.setInput(data);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
				byte[] buffer = new byte[1024];
				try {
					while (!inflater.finished()) {
						int count = inflater.inflate(buffer);
						outputStream.write(buffer, 0, count);
					}
					outputStream.close();
				} catch (IOException ioe) {
				} catch (DataFormatException e) {
				}
				return outputStream.toByteArray();
			}


}
