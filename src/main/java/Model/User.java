package Model;

public class User {


      private int UserID;
      private String UserName;
      private int age;
      private String email;
      private  long phoneNo;
      private String password;

    public User(int userID, String userName, int age, String email, long phoneNo, String password)
    {
        UserID = userID;
        UserName = userName;
        this.age = age;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserID=" + UserID +
                ", UserName='" + UserName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNo=" + phoneNo +
                ", password='" + password + '\'' +
                '}';
    }
}
