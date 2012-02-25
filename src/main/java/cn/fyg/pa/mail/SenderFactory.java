package cn.fyg.pa.mail;

public class SenderFactory {
	
	public static Sender createSender(){
		return new  SenderImp();
	}

}
