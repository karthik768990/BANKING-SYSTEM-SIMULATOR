public class Address {

    private String townName;
    private String street;
    private String landMark;
    private int pinCode;
    private String  state;
    private String doorNo;
    Address(String street, String landMark, String state, String doorNo, String townName , int pincode ){
        this.doorNo = doorNo;
        this.townName = townName;
        this.landMark = landMark;
        this.state = state;
        this.pinCode = pincode;
        this.street = street;
    }

    String getTownName(){
        return this.townName;
    }
    String getStreet(){return this.street;}
    private String getLandMark(){return this.landMark;}
    private String getState(){return this.state;}
    String getDoorNo(){return this.doorNo;}
    int getPinCode(){return this.pinCode;}




}