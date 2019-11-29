package bikerental;
import java.util.HashMap;
import java.util.HashSet;

public class DataBase {
	private HashSet<Bike> bikeSource;
	private HashSet<Providers> providerSource;
	private HashSet<Location> locationSource;
	private HashMap<BikeType, Integer> quantitySource;
	private HashMap<Providers, Providers> listOfPartnersSource ;
	
	public DataBase(HashSet<Bike> bikeSource, HashSet<Providers> providerSource,
			        HashSet<Location> locationSource, HashMap<BikeType, Integer> quantitySource,
			        HashMap<Providers, Providers> listOfPartnersSource) {
		this.bikeSource = bikeSource;
		this.providerSource = providerSource;
		this.locationSource = locationSource;
		this.quantitySource = quantitySource;
		this.listOfPartnersSource =  new HashMap<Providers, Providers>();
	}
	public void setProviderSource(Providers aProvider) {
		providerSource.add(aProvider);
	}
	public HashSet<Providers> getProviderSource(){
		return providerSource;
	}
	public void setBikeSource(HashSet<Providers> providerSource) {
		for(Providers aProviders : providerSource) {
			for(Bike aBike : aProviders.getStockOfBikes()) {
				bikeSource.add(aBike);
			}
		}
	}
	public HashSet<Bike> getBikeSource(){
		return bikeSource;
	}
	public void setLocationSource(HashSet<Providers> providerSource){
		for(Providers aProviders : providerSource) {
			locationSource.add(aProviders.getLocation());
		}
	}
	public HashSet<Location> getLocationSource(){
		return locationSource;
	}
	public void setQuantitySource(HashSet<Bike> bikeSource) {
		for(Bike aBike : bikeSource) {
			if(!quantitySource.containsKey(aBike.getType())) {
				quantitySource.put(aBike.getType(), 1);	
			}
			else {
				quantitySource.replace(aBike.getType(), quantitySource.get(aBike.getType())+1);
			}
		}
	}
	public HashMap<BikeType, Integer> getQuantitySource(){
		return quantitySource;
	}
	public HashMap<Providers, Providers> setPartnership(HashSet<Providers> providerSource){
		for(Providers aProvider : providerSource) {
			HashSet<Providers> providers = aProvider.getListOfPartner();
			for(Providers bProviders: providers) {
				listOfPartnersSource.put(aProvider, bProviders);
			}
			
		}
		return listOfPartnersSource;
	}
	public HashMap<Providers, Providers> getPartnership(){
		return listOfPartnersSource;
	}
	public void bikeStatusChanged(BookingInfo order, Providers aProvider) {
		for(Bike aBike: order.getBookingItermBikes()) {
			if(aProvider.recordBooking(order, aBike)) {
				aProvider.recordReturning(aBike, aProvider);
			}
		}
	}

}
