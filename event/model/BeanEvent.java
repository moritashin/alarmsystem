package event.model;

import java.util.Date;

public class BeanEvent {
	
	private int eventId;        //������
	private String contect;        //��������
	private String reminderTime;   //����ʱ��
	private String state;          //״̬��1����ɣ�0δ��ɣ�
	private String reminderMode;   //�������ͣ�1��ǰ����ӣ�0��׼ʱ��
	private String classification; //���ࣨ1��Ҫ��0һ�㣩
	private String userid;
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getContect() {
		return contect;
	}
	public void setContect(String contect) {
		this.contect = contect;
	}
	public String getReminderTime() {
		return reminderTime;
	}
	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getReminderMode() {
		return reminderMode;
	}
	public void setReminderMode(String reminderMode) {
		this.reminderMode = reminderMode;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	

   
  
    
    
	
}
