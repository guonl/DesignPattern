package com.guonl.json;

import java.util.List;

public class Teacher {  
	  
    private String teaId;  
  
    private String teaName;  
  
    private List<Student> stus;  
  
    public Teacher() {  
    }  
  
    public Teacher(String teaId, String teaName, List<Student> stus) {  
        this.teaId = teaId;  
        this.teaName = teaName;  
        this.stus = stus;  
    }  
//getter setter  

	public String getTeaId() {
		return teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public List<Student> getStus() {
		return stus;
	}

	public void setStus(List<Student> stus) {
		this.stus = stus;
	}
    
    
  
}  
