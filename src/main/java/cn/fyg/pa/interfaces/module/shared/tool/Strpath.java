package cn.fyg.pa.interfaces.module.shared.tool;


/**
 * 用string来构建返回路径及url
 *
 */
public class Strpath {
	
	private String root;
	
	public Strpath(String root){
		this.root=root;
	}
	
	public String getPath(String relativePath){
		return this.root+relativePath;
	}
	
}
