package DAO_impl;

import ConnectionPackage.Connection_Class;
import ExceptionPackage.Jukebox_Exception;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class UserDAO implements DAO_interfaces.UserDAO {
    Scanner sc= new Scanner(System.in);
     public  static int UserId;
    Connection_Class c1= new Connection_Class();
    public static int generateuserID()
    {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(random.nextInt(9) + 1);
        for (int i = 0; i < 3; i++) {
            sb.append(random.nextInt(10));
        }
        return Integer.valueOf(sb.toString()).intValue();
    }
   public  void UserRegistration() throws Jukebox_Exception {
        try {
            System.out.println("============SIGN_UP==========");
            System.out.println("Enter your name");
            String name= sc.next();
            System.out.println("Enter Your age");
            int age =sc.nextInt();
            System.out.println("Enter your email address");
            String email= sc.next();
            System.out.println("Enter your PhoneNo");
            long phoneNo= sc.nextLong();
            System.out.println("Create A password........eg: abc@123..(Max 10 charachters allowed)");
            String Password=sc.next();
            int userid= generateuserID();
            Connection con = c1.EstablishConnection();
            PreparedStatement s1= con.prepareStatement("insert into user values(?,?,?,?,?,?)");
            s1.setInt(1,userid);
            s1.setString(2,name);
            s1.setString(3,email);
            s1.setInt(4,age);
            s1.setLong(5,phoneNo);
            s1.setString(6,Password);
            int row= s1.executeUpdate();
            if(row>0)
            {
                System.out.println("New User Registerd Sucessfully");
                System.out.println("Hey "+name+" your user id is : "+userid);
                System.out.println("Now you can login.....");
                existingUser1();
            }
            else
            {
                System.out.println("problem is SignUP....Please try after some time");
            }

        }
        catch( SQLException e)
        {
            System.out.println("problem  "+e);
        }
        catch (ClassNotFoundException e1)
        {
            System.out.println("problem is "+e1);
        }
        catch (NumberFormatException n)
        {
            System.out.println("Please Enter valid input in fields");
        }
        catch (InputMismatchException m)
        {
            System.out.println("Please Enter valid input in fields");
        }


    }
   public  void  existingUser1 () throws Jukebox_Exception
    {

        try {
            Connection con= new Connection_Class().EstablishConnection();
            System.out.println("Enter emailAddress");
            String email = sc.next();
            System.out.println("Enter Your Password");
            String Password = sc.next();
            PreparedStatement s1= con.prepareStatement("select userid, password from user where emailAdd=? and password=?");
            s1.setString(1,email);
            s1.setString(2,Password);
            ResultSet rs = s1.executeQuery();
            if (rs.next())
              {
                     UserId = rs.getInt("userid");

                    System.out.println("Sign in succesfull for user id ="+rs.getInt("userid"));

              }
            else {
                    System.out.println("You have entered wrong credentials.....press 1 to try again and 2 if forgot password");
                    int choice = sc.nextInt();
                    if (choice == 1)
                    {
                        existingUser1();
                    }
                    else
                    {
                        System.out.println("Foll0w the steps for retriving your password");
                        System.out.println("Enter user id");
                        int id2 = sc.nextInt();
                        PreparedStatement s2 = con.prepareStatement("select  password from user where userid=?");
                        s2.setInt(1, id2);
                        ResultSet r = s2.executeQuery();
                        while (r.next())
                        {
                            String pass1 = r.getString("password");
                            System.out.println("Your Password is : " + pass1);

                        }
                        System.out.println("Please login now again............");
                        existingUser1();
                    }
                 }
        }
        catch ( SQLException e)
        {
            System.out.println(e);
        }
        catch (ClassNotFoundException e1)
        {
            System.out.println(e1);
        }
        catch (NumberFormatException w)
        {
            System.out.println("Entry details are not in format");
        }
        catch (InputMismatchException i)
        {
            System.out.println("Enter credentails in predefined format");
        }

    }
}
