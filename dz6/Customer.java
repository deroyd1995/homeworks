package dz6;

public class Customer extends AbstractUser implements Comparable<Customer>{
    private String FIO;
    private String telnumber;
    private String userID;

    public String getFIO() {
        return FIO;
    }
    public String getTelnumber() {
        return telnumber;
    }
    public String getUserID() {
        return userID;
    }

    public Customer(String FIO, String login, String password, String telnumber, String userID) {
        super(login,password);
        this.FIO = FIO;
        this.telnumber=telnumber;
        this.userID=userID;
    }

    @Override
    public int compareTo(Customer s)
    {
        String[] parts1 = this.FIO.split(" ");
        String[] parts2 = s.getFIO().split(" ");
        int result=0;

        for (int i=0;i<3;i++){
              result=parts1[i].compareTo(parts2[i]);
                if (result!=0){
                  break;
                }
    }

        return result;
    }



    @Override
    public String toString() {
        return getFIO() + "," + getLogin() + "," + getPassword() + "," + getTelnumber()+","+getUserID();
    }
}
