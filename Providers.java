package bikerental;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

public class Providers implements DeliveryService{
	private String name;
	private Location locatedPlace;
	private String phoneNumber;
	private HashSet<Bike> stockOfBikes;
	private BigDecimal replacementValue;
	private BigDecimal ownDepositRate;
	private HashSet<Providers> listOfPartner;
	
	
	public Providers(String name, Location locatedPlace, String phoneNumber,
			        HashSet<Bike> stockOfBikes, HashSet<Providers> listOfPartner, BigDecimal ownDepositRate) {
		this.name = name;
		this.locatedPlace = locatedPlace;
		this.phoneNumber = phoneNumber;
		this.stockOfBikes = stockOfBikes;
		this.listOfPartner = listOfPartner;
		this.ownDepositRate = ownDepositRate;
		
	}
	public void setBike(Bike aBike) {
		stockOfBikes.add(aBike);
	}
	public void setBikeLocation(Bike aBike) {
		aBike.setLocation(locatedPlace);
	}
	
	public HashSet<Bike> getStockOfBikes(){
		return stockOfBikes;
	}
	public Location getLocation(){
		return locatedPlace;
	}
	public void setDailyRentalPrice(Bike aBike, BigDecimal typePrice) {
		aBike.getType().setTypePrice(typePrice);
	}
	public BigDecimal getDepositRate(BikeType aBikeType) {
		aBikeType.setDepositRate(ownDepositRate);
		return aBikeType.getDepositRate();
	}
	public void setReplacementValue(Bike aBike, BigDecimal replacementValue) {
		aBike.getType().setReplacementValue(replacementValue);
	}
	public void setListOfPartner(Providers bProvider) {
		listOfPartner.add(bProvider);
	}
	public HashSet<Providers> getListOfPartner(){
		return listOfPartner;
	}
	public boolean isPartner(Providers bProvider) {
		return listOfPartner.contains(bProvider);
	}
	public HashSet<Providers> isAgreedPartnership(Providers aProvider, Providers bProvider, boolean isAgreed){
		if(isAgreed==true) {
			 listOfPartner.add(bProvider);
			 return listOfPartner;
		}
		else {
			return listOfPartner;
		}
	}
	
	public boolean recordBooking(BookingInfo order, Bike abike) {
		return abike.isAvailable(order.getBookingDate(), abike);
	}
	public boolean recordReturning(Bike abike, Providers receivingProvider) {
		if(abike.getProvider().equals(receivingProvider)) {
			abike.setIsReturned(true);
			return abike.getIsReturn();
		}
		else if(receivingProvider.isPartner(abike.getProvider())) {
			abike.setIsReturned(true);
			return abike.getIsReturn();
		}
		else {
			return false;
		}
	}
	public void bikeStatusChanged(BookingInfo order, Providers receivingProvider) {
		for(Bike aBike: order.getBookingItermBikes()) {
			if(receivingProvider.recordBooking(order, aBike)) {
				receivingProvider.recordReturning(aBike, receivingProvider);
			}
		}
	}
	@Override
	public void scheduleDelivery(Deliverable deliverable, Location pickupLocation, Location dropoffLocation,
			LocalDate pickupDate) {
		// TODO Auto-generated method stub
		
	}
	
	

}
