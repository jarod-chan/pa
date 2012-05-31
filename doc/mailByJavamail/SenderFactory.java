package cn.fyg.pa.infrastructure.mail;

public class SenderFactory {
	
	public static Sender createSender(){
		return new  SenderImp();
	}

}
