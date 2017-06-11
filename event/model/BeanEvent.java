package event.model;

import java.util.Date;

public class BeanEvent {
	
	private int eventId;        //事务编号
	private String contect;        //事务内容
	private String reminderTime;   //提醒时间
	private String state;          //状态（1已完成，0未完成）
	private String reminderMode;   //提醒类型（1提前五分钟，0是准时）
	private String classification; //分类（1重要，0一般）
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
