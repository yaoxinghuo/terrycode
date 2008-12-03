package cn.edu.jiangnan.lab.data.service.intf;

public interface IMessageService {
	public String getMessages();
	
	public String getMessagesByType(int type);
	
	public String getIntrodoceMessagesByType(int type);
}
