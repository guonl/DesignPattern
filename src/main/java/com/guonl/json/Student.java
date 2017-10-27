package com.guonl.json;

public class Student {  
	  
    private String stuNo;  
  
    private String stuName;  
  
    public Student() {  
    }  
  
    public Student(String stuNo, String stuName) {  
        this.stuNo = stuNo;  
        this.stuName = stuName;  
    }  
  
    // getter setter  
  
    /*@Override  
    public String toString() {  
        return "Student [stuNo=" + stuNo + ", stuName=" + stuName + "]";  
    }*/

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}


	
	
    
    
  
}  
