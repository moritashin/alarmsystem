package event.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import event.model.BeanUser;
import event.util.BaseException;
import event.util.BusinessException;
import event.util.DBUtil;
import event.util.DbException;

public class UserManager {
	public static BeanUser currentLoginUser=null;
	public void createUser(String userId,String pwd,String pwd1) throws Exception{
		Connection conn=null;
		try
		{
			conn=DBUtil.getConnection();
			if(!pwd.equals(pwd1))
			{
				throw new BusinessException("两次输入密码不一致");
			}
			String sql="select userid from beanuser where userid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, userId);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				throw new BusinessException("该用户已存在");
			}
			sql="insert into beanuser(userid,pwd) values(?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, userId);
			pst.setString(2, pwd);
			pst.execute();
			pst.close();
			rs.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			//DriverManager.getConnection("jdbc:derby:H:\\db\\event;shutdown=true");
		}
		
	    	
	}
	public void login(String userId,String pwd) throws Exception{
		Connection conn=null;
		try
		{
			conn=DBUtil.getConnection();
			String sql="select * from beanuser where userid=? and pwd=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, userId);
			pst.setString(2, pwd);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next())
			{
				throw new BusinessException("密码输入错误");
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			//DriverManager.getConnection("jdbc:derby:H:\\db\\event;shutdown=true");
		}
	    	
	}
	public void modifyPwd(String userId,String pwd,String newpwd,String newpwd1) throws Exception{
		Connection conn=null;
		if(!newpwd.equals(newpwd1))
		{
			throw new BusinessException("两次输入密码不一致");
		}
		try
		{
			conn=DBUtil.getConnection();
			String sql="select userid,pwd from beanuser where userid=? and pwd=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, userId);
			pst.setString(2, pwd);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next())
			{
				throw new BusinessException("密码输入错误");
			}
			sql="update beanuser set pwd=? where userid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, userId);
			pst.setString(2, newpwd);
			pst.execute();
			pst.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			//DriverManager.getConnection("jdbc:derby:H:\\db\\event;shutdown=true");
		}
	    	
	}
	
	
	public BeanUser loaduser (String userid) throws BaseException, SQLException
	{
		Connection conn=null;
		
		try
		{
			conn=DBUtil.getConnection();
			String sql="select userid,pwd from beanuser where userid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) 
			{
				throw new BusinessException("登陆账号不存在");
			}
			BeanUser u=new BeanUser();
			u.setUserid(rs.getString(1));
			u.setPwd(rs.getString(2));
			pst.close();
			rs.close();
			return u;
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}finally
		{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			//DriverManager.getConnection("jdbc:derby:H:\\db\\event;shutdown=true");
			
			
		}
		
	
}
}
