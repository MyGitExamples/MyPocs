package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.RootObject;
import com.example.demo.model.Student;


@RestController
@RequestMapping("rest")
public class StudentRestController {

	@GetMapping
	public String getStudent() {

		return "Zycus-Student";
	}

	@PostMapping("parseList")
	public ResponseEntity<RootObject> studentDetails(
			@RequestParam("startRollNo") String startRollNo,
			@RequestParam("endRollNo") String endRollNo, 
			@RequestBody RootObject root) throws Exception {
		
		List<Student> list = root.getStudents().getStudent();
		ArrayList<Student> sortedList = new ArrayList<Student>();
		
		if((list != null)) {
			
			for(Student stu: list){
				if (stu.getRollNo().equals(startRollNo)) {
					sortedList.add(stu);
				}
				if(stu.getRollNo().equals(endRollNo)){
					sortedList.add(stu);
					root.getStudents().setStudent(sortedList);
					break;
				}
			}
		}
		
		return new ResponseEntity<RootObject>(root, HttpStatus.OK);
	}

}
