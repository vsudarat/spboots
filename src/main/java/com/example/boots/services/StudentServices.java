package com.example.boots.services;

import java.util.HashMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.boots.model.Student;

@RestController
@RequestMapping(value="/rest/student")
class StudentServices{
 
	public static HashMap<Long,Student> hmStudent;
	static {
		 hmStudent=new HashMap<Long,Student>();
		 
	      Student one=new Student("John","math");
	      hmStudent.put(new Long(one.getId()),one);
	 
	      Student two=new Student("Jane","history");
	      hmStudent.put(new Long(two.getId()),two);
	}
	
	//http://localhost:8080/rest/student/
   @RequestMapping(value="/",method = RequestMethod.GET)
   public HashMap<Long,Student> getAllStudents(){
      return hmStudent;
   }
   //http://localhost:8080/rest/student/add?name=Joe&subject=english
   @RequestMapping(value="/add",method = RequestMethod.POST)
   public Student addStudent(@RequestParam(value="name") String name
         ,@RequestParam(value="subject",defaultValue = "unknown") String subject){
    
      Student student=new Student(name,subject);
      hmStudent.put(new Long(student.getId()),student);
      return student;
    
   }
   
   @RequestMapping(value="/update",method = RequestMethod.PUT)
   public Student updateStudent(@RequestBody Student student) throws Exception {
    
      if(hmStudent.containsKey(new Long(student.getId()))){
         hmStudent.put(new Long(student.getId()),student);
      }else{
         throw new Exception("Student "+student.getId()+" does not exists");
      }
    
      return student;
   }
   
   //http://localhost:8080/rest/student/delete/1459664087939
   @RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
   public Student deleteStudent(@PathVariable long id) throws Exception {
    
      Student student;
    
      if(hmStudent.containsKey(new Long(id))){
         student=hmStudent.get(new Long(id));
         hmStudent.remove(new Long(id));
      }else{
         throw new Exception("Student "+id+" does not exists");
      }
      return student;
   }
   
   @RequestMapping(value="/{id}",method = RequestMethod.GET)
   public Student getStudent(@PathVariable long id){
      return hmStudent.get(new Long(id));
   }
}
