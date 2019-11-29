package bikerental;


public class Location {
    private String postcode;
    private String address;
    
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
    
    public boolean isNearTo(Location other) {
        char[] postcodeNum1 = other.getPostcode().toCharArray();
        char[] postcodeNum2 = postcode.toCharArray();
        if(postcodeNum1[0]== postcodeNum2[0] && postcodeNum1[1]== postcodeNum2[1]) {
        	return true;
        }
        else return false;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getAddress() {
        return address;
    }
    
    // You can add your own methods here
}
