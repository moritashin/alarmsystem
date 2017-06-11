package event.control;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import event.util.DBUtil;
import event.model.*;
public class EventManager {
	public int getaddEventNum(String contect,String reminderTime,String state,String reminderMode,String classification )throws Exception{
    	Time t = new Time();
		if(t.timeCompare(t.getCurrentTime(), reminderTime)>=0){
			throw new Exception("时间设置不合理，该时间已过");
		}
		if(contect.length()>200 ){
			throw new Exception("事务内容长度不能超过200个字");
			
		}
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(reminderTime);
		Date afterDate = new Date(date .getTime() - 300000);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
	    String dateString = formatter.format(afterDate);  
		if(t.timeCompare(t.getCurrentTime(), dateString)>=0){
			throw new Exception("时间差小于5分钟");
		}
		int num=0;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from beanevent where reminderTime = ? ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,reminderTime );
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()){
				num = rs.getInt(1);
			}	
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return num;
    }
	public void addEvent(String contect,String reminderTime,String state,String reminderMode,String classification )throws Exception{
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="insert into Beanevent(contect,reminderTime,state,reminderMode,classification,userid) values(?,?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, contect);
			pst.setString(2,reminderTime );
			pst.setString(3, state);
			pst.setString(4,reminderMode );
			pst.setString(5, classification);
			pst.setString(6, UserManager.currentLoginUser.getUserid());
			System.out.println(2);
			pst.execute();
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	public void deleteEvent(BeanEvent beanevent){
		Connection conn=null;
    	try{
    		conn=DBUtil.getConnection();
			String sql="DELETE FROM beanevent WHERE eventid = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, beanevent.getEventId());
			pst.execute();
			pst.close();
			
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	public int getmodifyEventNum(BeanEvent beanevent,String contect,String reminderTime,String state,String reminderMode,String classification )throws Exception{
    	Time t = new Time();
		if(t.timeCompare(t.getCurrentTime(), reminderTime)>=0){
			throw new Exception("时间设置不合理，该时间已过");
		}
		if(contect.length()>200 ){
			throw new Exception("事务内容长度不能超过200个字");
		}
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(reminderTime);
		Date afterDate = new Date(date .getTime() + 300000);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
	    String dateString = formatter.format(afterDate);  
		if(t.timeCompare(t.getCurrentTime(), dateString)>=0){
			throw new Exception("时间差小于5分钟");
		}
		int num=0;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from beanevent where reminderTime = ? ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,reminderTime );
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()){
				num = rs.getInt(1);
			}	
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return num;
    }
    public void modifyEvent(BeanEvent beanevent,String contect,String reminderTime,String state,String reminderMode,String classification )throws Exception{
    	
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update Beanevent set contect=?,reminderTime=?,state=?,reminderMode=?,classification = ? where eventid = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, contect);
			pst.setString(2,reminderTime );
			pst.setString(3, state);
			pst.setString(4,reminderMode );
			pst.setString(5, classification);
			pst.setInt(6, beanevent.getEventId());
			pst.execute();
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
			
		}
    }
    public void intoRecycle(BeanEvent beanevent){
    	Connection conn=null;
    	try{
    		conn=DBUtil.getConnection();
			String sql="UPDATE beanevent  SET state = '回收站' WHERE eventid =  ？";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, beanevent.getEventId());
			pst.execute();
			pst.close();
			
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    public void outRecycle(BeanEvent beanevent){
    	Connection conn=null;
    	try{
    		conn=DBUtil.getConnection();
			String sql="UPDATE beanevent  SET state = '回收站' WHERE eventid =  ？";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, beanevent.getEventId());
			pst.execute();
			pst.close();
			
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    public List<BeanEvent>  listState(String state){
		return null;
    	
    }
    public List<BeanEvent> listClassification(String classification){
		return null;
    	
    }
}
