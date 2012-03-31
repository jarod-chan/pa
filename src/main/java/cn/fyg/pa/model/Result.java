package cn.fyg.pa.model;

public class Result {
	
	private boolean pass=false;
	
	private String cause="";
	
	public Result(){
	}

	public Result(boolean pass, String cause) {
		this.pass = pass;
		this.cause = cause;
	}
	
	public Result pass(Boolean pass){
		this.pass=pass;
		return this;
	}
	
	public Result cause(String cause){
		this.cause=cause;
		return this;
	}
	
	public boolean pass(){
		return this.pass;
	}
	
	public boolean notPass(){
		return !this.pass;
	}
	
	public String cause(){
		return this.cause;
	}
}
