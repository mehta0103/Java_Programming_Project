package DAO_interfaces;

import ExceptionPackage.Jukebox_Exception;

import java.sql.SQLException;

public interface UserDAO {

         public void UserRegistration() throws Jukebox_Exception;
       public  void existingUser1() throws Jukebox_Exception;
}
