<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>

<%
if(request.getParameter("city_id")!=null) 
{
    int id=Integer.parseInt(request.getParameter("city_id")); //get city_id from index.jsp page with function city_change() through ajax and store in id variable
    System.out.println("district.jsp / City Id : " + id);   
    try
    {
        Class.forName("oracle.jdbc.driver.OracleDriver"); //load driver
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JSPPROJECTDATABASE","123"); //create connection
            
        PreparedStatement pstmt=null ; //create statement
                
        pstmt=con.prepareStatement("select * from district where city_id=?"); //sql select query
        pstmt.setInt(1,id);
        ResultSet rs=pstmt.executeQuery(); //execute query and set in resultset object rs.
        %>        
            <option disabled selected>İlçe Seçin</option>
        <%    
        while(rs.next())
        {
        	int i = rs.getInt("ID");
        	String str = rs.getString("DISTRICT_NAME");
        	System.out.println("district.jsp / District ID  : " + i + " District Name : " + str);   
        %>        
            <option value="<%=rs.getInt("ID")%>">
                <%=rs.getString("DISTRICT_NAME")%>
            </option>
        <%
        }
  
        con.close(); //close connection
    }
    catch(Exception e)
    {
        out.println(e);
    }
}
%>
