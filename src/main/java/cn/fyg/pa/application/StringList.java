package cn.fyg.pa.application;

import java.util.ArrayList;
import java.util.List;

public class StringList {
	
	private List<String> stringlist;
	
	public StringList(List<String> stringlist) {
		super();
		this.stringlist = stringlist;
	}

	public String single(){
		if(stringlist==null||stringlist.isEmpty()){
			return "";
		}
		return stringlist.get(0);
	}
	
	public List<String> list(){
		if(stringlist==null){
			return new ArrayList<String>();
		}
		return stringlist;
	}
	
	public int count(){
		return this.stringlist.size();
	}
	
	public StringList push(String key){
		this.stringlist.add(key);
		return this;
	}
	
	public StringList pop(){
		this.stringlist.remove(this.stringlist.size()-1);
		return this;
	}

}
