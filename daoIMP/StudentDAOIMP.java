package daoIMP;
import bean.Student;
import dao.StudentDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connection.DataBaseConnection;

public class StudentDAOIMP implements StudentDAO{
	// ��Ӳ���
    public void insert(Student s){
    	String sql = "INSERT INTO student (id, name) values (?,?)";
	    PreparedStatement pstmt = null;
	    DataBaseConnection conn = null;
	    //������ݿ�ľ������
	    try{
	        conn = new DataBaseConnection();

	        pstmt = conn.getConnection().prepareStatement(sql);
	        pstmt.setLong(1,s.getID());
	        pstmt.setString(2,s.getName());

	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch(Exception e){

	    }
    }

    public void update(Student s){
	    String sql = "UPDATE student SET name = ? WHERE id = ?";
	    PreparedStatement pstmt = null;
	    DataBaseConnection conn = null;
	    //������ݿ�ľ������
	    try{
		    conn = new DataBaseConnection();

		    pstmt = conn.getConnection().prepareStatement(sql);
		    pstmt.setString(1,s.getName());
		    pstmt.setLong(2,s.getID());

		    pstmt.executeUpdate();
		    pstmt.close();
		    conn.close();
	    } catch(Exception e){

	    }
    }

    public void delete(String ID){
	    String sql = "DELETE FROM student WHERE id = ?";
	    PreparedStatement pstmt = null;
	    DataBaseConnection conn = null;
	    //������ݿ�ľ������
	    try{
		    conn = new DataBaseConnection();

		    pstmt = conn.getConnection().prepareStatement(sql);
		    pstmt.setLong(1,Long.parseLong(ID));
		    pstmt.executeUpdate();
		    pstmt.close();
		    conn.close();
	    } catch(Exception e){

	    }
    }

    public List<Student> findAll(){
	    String sql = "SELECT * FROM student";
	    PreparedStatement pstmt = null;
	    DataBaseConnection conn = null;
	    List<Student> list = new ArrayList<>();
	    //������ݿ�ľ������
	    try{
		    conn = new DataBaseConnection();

		    pstmt = conn.getConnection().prepareStatement(sql);
		    ResultSet resultSet = pstmt.executeQuery();

		    while (resultSet.next()) {
		    	Student student = new Student();
		    	String id = resultSet.getString("id");
		    	student.setID(Long.parseLong(id));
		    	student.setName(resultSet.getString("name"));
		    	list.add(student);
		    }
		    pstmt.close();
		    conn.close();
	    } catch(Exception e){
	    }
	    return list;
    }

    public Student findByID(long ID){
	    String sql = "SELECT * FROM student WHERE id = ?";
	    PreparedStatement pstmt = null;
	    DataBaseConnection conn = null;
	    Student student = new Student();
	    //������ݿ�ľ������
	    try{
		    conn = new DataBaseConnection();

		    pstmt = conn.getConnection().prepareStatement(sql);
		    pstmt.setLong(1,ID);
		    ResultSet resultSet = pstmt.executeQuery();
		    while (resultSet.next()) {
		    	//��һ���Ǽ����� ���ݴӵڶ��п�ʼ
			    student.setID(resultSet.getLong(2));
			    student.setName(resultSet.getString(3));
		    }
		    pstmt.close();
		    conn.close();

	    } catch(Exception e){

	    }
	    return student;
    }

}
