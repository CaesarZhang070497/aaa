package bikerental;

public class Bike implements Deliverable{
	private String bikeId;
	private BikeType typeOfBike;
	private DateRange rangeOfDate;
	private boolean availability = true;
	private boolean isReturned = true;
	private Location locatedPlace;
	private Providers belongToProvider;
	private Renters whoRent;
	
	public Bike(String bikeId, BikeType typeOfBike) {
		this.bikeId = bikeId;
		this.typeOfBike = typeOfBike;
	}
	public void setLocation(Location locatedPlace) {
		this.locatedPlace = locatedPlace;
	}
	public Location getLocation() {
		return locatedPlace;
	}
	public void setProvider(Providers belongToProvider) {
		this.belongToProvider = belongToProvider;
	}
	public Providers getProvider() {
		return belongToProvider;
	}
	public void setRenter(Renters whoRent) {
		this.whoRent = whoRent;
	}
	public Renters getRenter() {
		return whoRent;
	}
	public void setAvailablity(Boolean availability) {
		this.availability = availability;
	}
	public Boolean getAvailablity() {
		return availability;
	}
	public void setDateRange(DateRange rangeOfDate1) {
		if(availability=true) {
			this.rangeOfDate = rangeOfDate1;
		}
		setAvailablity(false);
	}
	public DateRange getDateRange() {
		return rangeOfDate;
	}
    public BikeType getType() {
       return typeOfBike;
    }
    public boolean isAvailable(DateRange other, Bike aBike) {
    	if(aBike.getDateRange() == null) {
    		return true;
    	}
    	else if(aBike.getDateRange().overlaps(other)) {
    		return false;
    	}
    	else {
    		setAvailablity(true);
    		return true;
    	}
    }
    public void setIsReturned(Boolean isReturned) {
    	this.isReturned = isReturned;
    	setAvailablity(isReturned);
    }
    public boolean getIsReturn() {
    	return isReturned;
    }
	@Override
	public void onPickup() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDropoff() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString() {
		return "Bike [bikeId=" + bikeId + ", typeOfBike=" + typeOfBike + "]";
	}
	
    
}