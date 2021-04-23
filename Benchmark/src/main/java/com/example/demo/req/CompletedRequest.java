package com.example.demo.req;

import java.util.Date;


public class CompletedRequest {
    long exe;
    private int id; // The object ID associated with the completed request 
    Date date;
    
    public CompletedRequest(long exe,Date date, int i) {
        super();
        this.exe=exe;
        this.id = i;
        this.date=date;
    }
    
    
    
    
    public CompletedRequest(long l, Date date2) {
		// TODO Auto-generated constructor stub
    	this.exe=l;
        this.id = -1;
        this.date=date2;
	}




	public long getExe() {
		return exe;
	}




	public void setExe(long exe) {
		this.exe = exe;
	}




	public Date getDate() {
		return date;
	}




	public void setDate(Date date) {
		this.date = date;
	}




	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    
}
