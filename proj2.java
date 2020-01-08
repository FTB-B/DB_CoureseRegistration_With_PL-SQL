import java.sql.*;
import oracle.jdbc.*;
import java.math.*;
import java.io.*;
import java.awt.*;
import oracle.jdbc.pool.OracleDataSource;


public class proj2 
{
    public static void printTables(Connection conn) throws SQLException
	{	
		/*This fucntion use the procedures in the display_tables package to get the information in the tables in the database*/
		try{
			//printing tables
			//getting the information of the students table using the show_students procedure in the display_tables package
			CallableStatement cs = conn.prepareCall("begin display_tables.show_students(?); end;");
			cs.registerOutParameter(1,OracleTypes.CURSOR);
			//printing student table
            // execute and retrieve the result set
        	cs.executeQuery();
        	ResultSet rs = (ResultSet)cs.getObject(1);
            System.out.println("STUDENTS TABLE");
            System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.println("SID\t\t\tFNAME\t\t\tLNAME\t\t\tSTATUS\t\t\tGPA\t\t\tEMAIL");
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			while (rs.next ())
			{
				System.out.print (rs.getString (1)+"\t\t\t");
				System.out.print (rs.getString (2)+"\t\t\t");
				System.out.print (rs.getString (3)+"\t\t\t");
				System.out.print (rs.getString (4)+"\t\t\t");
				System.out.print (rs.getString (5)+"\t\t\t");
				System.out.println (rs.getString (6)+"\t\t\t");
			}
			//printing the courses table using the show_courses in the display_tables package
            cs = conn.prepareCall("begin display_tables.show_courses(?); end;");
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            //printing courses table
            //execute and retrieve the result set
            cs.executeQuery();
            rs = (ResultSet)cs.getObject(1);
            System.out.println("COURSES TABLE");
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            System.out.println("DEPTCODE\t\t\tCOURSENO\t\t\tTITLE");
            System.out.println("-------------------------------------------------------------------------------------------------------------");

            while (rs.next ())
            {
                System.out.print (rs.getString (1)+"\t\t\t");
                System.out.print (rs.getString (2)+"\t\t\t");
                System.out.println (rs.getString (3)+"\t\t\t");
            }
            //printing the prerequisites table using the show_prereq precedure in the display_tables package    
            cs = conn.prepareCall("begin display_tables.show_prereq(?); end;");
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            // printing courses table
            // execute and retrieve the result set
            cs.executeQuery();
            rs = (ResultSet)cs.getObject(1);
            System.out.println("PREREQUISITES TABLE");
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            System.out.println("DEPTCODE\t\t\tCOURSENO\t\t\tPREDEPTCODE\t\t\tPRECOURSENO");
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            while (rs.next ())
            {
                System.out.print (rs.getString (1)+"\t\t\t");
                System.out.print (rs.getString (2)+"\t\t\t");
                System.out.print (rs.getString (3)+"\t\t\t");
                System.out.println (rs.getString (4)+"\t\t\t");
            }
             //printing the classes table using the show_classes precedure in the display_tables package  
            cs = conn.prepareCall("begin display_tables.show_classes(?); end;");
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            // printing courses table
            // execute and retrieve the result set
            cs.executeQuery();
            rs = (ResultSet)cs.getObject(1);
            System.out.println("CLASSES TABLE");
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            System.out.println("CLASSID\tDEPTCODE\tCOURSENO\tSECTNO\tYEAR\tSEMESTER\tLIMIT\tCLASSSIZE");
            System.out.println("-------------------------------------------------------------------------------------------------------------");

            while (rs.next ())
            {
                System.out.print (rs.getString (1)+"\t");
                System.out.print (rs.getString (2)+"\t");
                System.out.print (rs.getString (3)+"\t");
                System.out.print (rs.getString (4)+"\t");
                System.out.print (rs.getString (5)+"\t");
			    System.out.print (rs.getString (6)+"\t");
			    System.out.print (rs.getString (7)+"\t ");
                System.out.println (rs.getString (8)+"\t");
            }
             //printing the enrollments table using the show_enrollments precedure in the display_tables package       
            cs = conn.prepareCall("begin display_tables.show_enrollments(?); end;");
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            //execute and retrieve the result set
            cs.executeQuery();
            rs = (ResultSet)cs.getObject(1);
            System.out.println("ENROLLMENTS TABLE");
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            System.out.println("SID\t\t\tCLASSID\t\t\tLGRADE");
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            while (rs.next ())
            {
                System.out.print (rs.getString (1)+"\t\t\t");
                System.out.print (rs.getString (2)+"\t\t\t");
                System.out.println (rs.getString (3)+"\t\t\t");
            }

        rs.close();
        
        }
        catch(SQLException ex){System.out.println("SQL Exception in print table function");}
	 return;
	}

//----------------------------------------------------------------------------------------------------------------------

public static void addStudent(Connection conn) throws SQLException
{
	 /*This function is used to add a student into student table using insert_student in the insert_student_package*/
     try{
       		// getting the inputs from the key board
        	BufferedReader readkeyB;
			readkeyB = new BufferedReader(new InputStreamReader(System.in));        
       		System.out.println("Please enter the student information as follow:");
			System.out.println("SID:");
        	String sid = readkeyB.readLine();
			System.out.println("First Name:");
			String firstName = readkeyB.readLine();
			System.out.println("Last Name:");
			String lastName = readkeyB.readLine();
			System.out.println(lastName + " Status: ");
			String status = readkeyB.readLine();
			System.out.println("GPA: ");
			String g = readkeyB.readLine();
        	double gpa = Double.parseDouble(g);
        	System.out.println("Email: ");
			String email = readkeyB.readLine();
			//Prepare to call insert_student procedure:
        	CallableStatement cs = conn.prepareCall("begin insert_student_package.insert_student(:1,:2,:3,:4,:5,:6); end;");
				
			//set the in parameter (sid)
			cs.setString(1,sid);
            
			// set the in parameter (firstname)
			cs.setString(2,firstName);
            //System.out.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE2");
			//set the in parameter (lastname)
			cs.setString(3,lastName);
            //System.out.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE3");
			// set the in parameter (status)
         	cs.setString(4,status);
            //System.out.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE4");
			// set the in parameter (gpa)
        	cs.setDouble(5,gpa);
            // System.out.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
       		// set the in parameter (email)
        	cs.setString(6,email);
            // System.out.println("******************************");
        	cs.executeQuery();
			//System.out.println("******************************tttttt");
            cs.close();
        }
       catch(SQLException ex){ System.out.println("sql exception happened in add studnet function");}
       catch(Exception e){System.out.println("Keyboard exception");}



     return;
}
//---------------------------------------------------------------------------------------------------------------------
public static void getStudentInfo(Connection conn) throws SQLException
{
	/*This function in being used to get the infomation of a given student*/	
	try
	{
		BufferedReader readKeyBoard;
		readKeyBoard = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the Student ID");
		String sid = readKeyBoard.readLine();
		CallableStatement cs = conn.prepareCall("begin student_info(:1,:2,:3); end;");
		//System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
		cs.setString(1,sid);
		cs.registerOutParameter(2,Types.VARCHAR);
		//System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU2");
		cs.registerOutParameter(3,OracleTypes.CURSOR);
        //System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU3");
		cs.executeQuery();
		//System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU4");
	    String error_message = null;
		error_message =  cs.getString(2);
		ResultSet rs = (ResultSet)cs.getObject(3);
		//System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU5");
		//check if there was any error while running the pl/sql procedure
        if(error_message ==  null)
		{
			//print the results
			System.out.println("SID\t\tLNAME\t\tSTATUS\t\tCLASSID\t\tCOURSEID");
			System.out.println("------------------------------------------------------------------------------");
			while(rs.next())
			{	
				System.out.println(rs.getString(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t\t" + rs.getString(4)+ "\t\t"+ rs.getString(5));
				//System.out.println(rs.getString(1) + "\t\t" );
				// System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU6");
			}
		}
		else
		{
			System.out.println(error_message);
		}

		cs.close();
	}
	catch(SQLException ex){System.out.println("SQLException in getStudentInfo function!");}
	catch(Exception e){System.out.println("IO exception in getStudentInfo!");}

	return;
}
//---------------------------------------------------------------------------------------------------------------------------
public static void classInfoStudents(Connection conn)
{
	try
	{
		BufferedReader readKeyBoard;
		readKeyBoard = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the class ID");
		String cid = readKeyBoard.readLine();
		CallableStatement cs = conn.prepareCall("begin class_info(:1,:2,:3); end;");
		//System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
		cs.setString(1,cid);
		 cs.registerOutParameter(2,Types.VARCHAR);
		//System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU2");
		cs.registerOutParameter(3,OracleTypes.CURSOR);
                //System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU3");

		
		cs.executeQuery();
		//System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU4");
	     String error_message = "n";
		error_message =  cs.getString(2);
		ResultSet rs = (ResultSet)cs.getObject(3);
		//System.out.println("HEREREREREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		//System.out.println(error_message);
        if(error_message == null)
		{
			//print the results
			System.out.println("CLASSID\t\tCOURSETITLE\t\tSEMESTER\t\tYEAR\t\tSID\t\tLNAME");
			System.out.println("------------------------------------------------------------------------------------------------");
			while(rs.next())
			{	
				System.out.println(rs.getString(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t\t" + rs.getString(4)+ "\t\t"+ rs.getString(5)+"\t\t" + rs.getString(6));
				//System.out.println(rs.getString(1) + "\t\t" );
				// System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU6");
			}
		}
		else
		{
			System.out.println(error_message);
		}

		cs.close();
	}
	catch(SQLException ex){System.out.println("SQLException in getStudentInfo function!");}
	catch(Exception e){System.out.println("IO exception in getStudentInfo!");}

	return;
	
}
//---------------------------------------------------------------------------------------------------
public static void deleteStudent(Connection conn) throws SQLException
{
	/*This function is being used to delete a student*/
    try
    {
        BufferedReader readKeyB;
        readKeyB = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the Studentid to delete");
        String sid = readKeyB.readLine();
        CallableStatement cs = conn.prepareCall("begin delete_student(:1); end;");
        cs.setString(1,sid);
        cs.executeQuery();
        cs.close();
    }
    catch(SQLException ex){System.out.println("SQLException in deleteStudent function");}
    catch(Exception e){System.out.println("IO exception in deleteStudent function");}

}

//-----------------------------------------------------------------------------------------------------
public static void getCoursePrereq(Connection conn) throws SQLException
{
 	try
    {
        BufferedReader readKeyB;
        readKeyB = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the DeptCode");
        String deptCode = readKeyB.readLine();
		System.out.println("Please enter the course number");
		String courseN = readKeyB.readLine();
		int courseNo = Integer.parseInt(courseN);
        int count = 0;
		String result;
		CallableStatement cs = conn.prepareCall("begin course_info(:1,:2,:3,:4); end;");
		cs.setString(1,deptCode);
   		//System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH1");
		cs.setInt(2,courseNo);
		//System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH2");
		cs.setInt(3,count);
        //System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH3");
        cs.registerOutParameter(4, Types.VARCHAR);
		//System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH4");
        cs.executeQuery();
        //System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH5");
        result = cs.getString(4);
		//System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH6");
        // print the results
        System.out.println("THE PRE_REQUISITES COURSES");
        System.out.println("-----------------------------------------------------------------------------------------------");
        
        System.out.println(result);

	      

		cs.close();


        }
        catch(SQLException ex){System.out.println("SQLException in getCoursePrereq funtion");}
        catch(Exception e){System.out.println("IO exception in getCoursePrereq function");}


}
//------------------------------------------------------------------------------------------------------
public static void enrollStudent(Connection conn) throws SQLException
{
     try
	{
		BufferedReader readKeyBoard;
		readKeyBoard = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the student ID");
		String sid = readKeyBoard.readLine();
		System.out.println("Please enter the class ID");
		String cid = readKeyBoard.readLine();
		CallableStatement cs = conn.prepareCall("begin enroll_student(:1,:2,:3); end;");
		//System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
		cs.setString(1,sid);
		cs.setString(2,cid);
		 cs.registerOutParameter(3,Types.VARCHAR);
		//System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU2");
				
		cs.executeQuery();
		//System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU4");
	     String error_message = null;
		error_message =  cs.getString(3);
		System.out.println(error_message);
		

		cs.close();
	}
	catch(SQLException ex){System.out.println("SQLException in enrollemtn function!");}
	catch(Exception e){System.out.println("IO exception in enrollemet!");}

	return;
}
//--------------------------------------------------------------------------------------------------------------
public static void dropStudent(Connection conn) throws SQLException
{  
    try
	{
		BufferedReader readKeyBoard;
		readKeyBoard = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the student ID");
		String sid = readKeyBoard.readLine();
		System.out.println("Please enter the class ID");
		String cid = readKeyBoard.readLine();
		CallableStatement cs = conn.prepareCall("begin delete_enrollment(:1,:2,:3); end;");
		//System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
		cs.setString(1,sid);
		cs.setString(2,cid);
		 cs.registerOutParameter(3,Types.VARCHAR);
		//System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU2");
				
		cs.executeQuery();
		//System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU4");
	        String error_message = null;
		error_message =  cs.getString(3);
		System.out.println(error_message);
		

		cs.close();
	}
	catch(SQLException ex){System.out.println("SQLException in dropenrollemtn function!");}
	catch(Exception e){System.out.println("IO exception in dropenrollemet!");}

	return;
	
}

//---------------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args)  throws SQLException
	{
        try
		{
			//Connection to Oracle server
        	OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        	ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:ACAD111");
        	Connection conn = ds.getConnection("ftahmas1","metropolice3328");
			int userChoice = -1;
            BufferedReader readKeyBoard;
			String choice;
			readKeyBoard = new BufferedReader(new InputStreamReader(System.in));
            //Printing the options for the user to use
			while(userChoice != 0)
			{
				System.out.println("Please choose one of the following options:");
                System.out.println("1- Print the tables in the database");
				System.out.println("2- Add a student into the students table");
				System.out.println("3- Get the information for a given student");
				System.out.println("4- Show the prerequisites courses for a given course");
				System.out.println("5- Show the information and the list of students for a given course");
				System.out.println("6- Enroll a student into a class");
				System.out.println("7- Drop a student from a class");
				System.out.println("8- Delete a student");
				System.out.println("0- Exit");

				// read the user choice
                choice = readKeyBoard.readLine();
                userChoice = Integer.parseInt(choice);
				//switching between the user choice to call the appropriate function to perform the corresponding procedure
                if(userChoice == 1)
				{
					// print all the tables
					printTables(conn);
				}
				else if(userChoice == 2)
                {
                    //add student
                    addStudent(conn);
                }
                else if(userChoice == 3)
                {
                    //getStudentInfo
                    getStudentInfo(conn);
                }
                else if(userChoice == 4)
                {
                    getCoursePrereq(conn);
                }
                else if(userChoice == 5)
                {
                    //showcourseinfo
                    classInfoStudents(conn);
                }
                else if(userChoice == 6)
                {
                    enrollStudent(conn);
                }
                else if(userChoice == 7)
                {
                    dropStudent(conn);
                }
                else if(userChoice == 8)
                {
                    deleteStudent(conn);
                }

            
            }
        conn.close();
        }
        catch(SQLException ex){System.out.println("SQL exception happend");}
        catch(Exception e){System.out.println("IO exception");}
	}
}
