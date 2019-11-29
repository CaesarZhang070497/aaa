package bikerental;
import java.math.BigDecimal;
import java.util.*;

public class Renters {
	private String name;
	private String phoneNum;
	private Location address;
	private DateRange when;
	private int numberOfBikes;
	private BikeType typeOfBike;
	
	public Renters(String name, String phoneNum, Location address) {
		this.name = name;
		this.phoneNum = phoneNum;
		this.address = address;
	}
	public Location getAddress() {
		return address;
	}
	public void setDateRange(DateRange when) {
		this.when = when;
	}
	public DateRange getDateRange() {
		return when;
	}
	public void setQuantity(int numberOfBike) {
		this.numberOfBikes = numberOfBike;
	}
	public int getQuantity() {
		return numberOfBikes;
	}
	public void setTypeBikes(BikeType typeOfBike) {
		this.typeOfBike = typeOfBike;
	}
	public BikeType getTypeBike() {
		return typeOfBike;
	}
	public void setIRent(HashSet<Bike> selectedBikes) {
		for(Bike aBike : selectedBikes) {
			aBike.setRenter(this);
		}
	}
	public HashSet<Bike> getQuote(DataBase Source){
		HashSet<Bike> quoteBikes = new HashSet<Bike>();
		HashSet<Bike> all_bikes = Source.getBikeSource();
		for(Bike aBike : all_bikes) {
			Location this_bike_location = aBike.getLocation();
			Boolean is_close_to = address.isNearTo(this_bike_location);
			Boolean is_avaiable = aBike.isAvailable(when, aBike);
			if(is_close_to && is_avaiable) {
				int num_of_available_bikes = Source.getQuantitySource().get(typeOfBike);
				if(numberOfBikes<=num_of_available_bikes) {
					if(aBike.getType().equals(typeOfBike)){
						quoteBikes.add(aBike);
					}
				}
			}

		}
		return quoteBikes;
	}
	public BookingInfo bookingQuote(HashSet<Bike> quoteBikes, boolean needDelivery) {
		HashSet<Bike> selectedBikes = new HashSet<Bike>();
		HashMap<Integer, Bike> sortBikes = new HashMap<Integer, Bike>();
		Integer num = 0;
		BigDecimal deposit = new BigDecimal(0);
		for(Bike aBike : quoteBikes) {
			num++;
			sortBikes.put(num, aBike);
		}

		BigDecimal aggregate_price = new BigDecimal(0);


		for(int i= 1; i<= numberOfBikes; i++){
			Bike aBike = sortBikes.get(i);
			selectedBikes.add(aBike);
			MultidaysRateDiscountPolicy priceTotal = new MultidaysRateDiscountPolicy();
			BigDecimal totalPrice = priceTotal.calculatePrice(selectedBikes, when);
			BikeType temp = aBike.getType();
			BigDecimal depositRate = aBike.getProvider().getDepositRate(aBike.getType());
			BigDecimal replacementValue = aBike.getType().getReplacementValue();
			deposit = deposit.add(depositRate.multiply(replacementValue));


			aggregate_price = aggregate_price.add(totalPrice);
		}
		Bike aBike = sortBikes.get(num);
		Location bikeLocated = aBike.getLocation();
		String uniqueId = UUID.randomUUID().toString();

		BookingInfo bookingDetails = new BookingInfo(uniqueId, aggregate_price, deposit, when,bikeLocated,selectedBikes ,needDelivery);
		System.out.println(bookingDetails);
		return bookingDetails;		
	}
	public void returnBikesToProvider(DataBase Source, BookingInfo order, Providers aProvider) {
		HashSet<Bike> bikesOfOrder = order.getBookingItermBikes();
		for(Bike aBike: bikesOfOrder) {
			Providers partner_provider = aBike.getProvider();

			if(aBike.getProvider().equals(aProvider)) {
				aProvider.bikeStatusChanged(order, aProvider);
			}

			else if(aProvider.isPartner(partner_provider)) {
				aProvider.bikeStatusChanged(order, aProvider);
				aProvider.scheduleDelivery(aBike, aProvider.getLocation(), address, when.getStart());
			}
			else {
				System.out.println("The provider is neither the original provider nor partner");
			}
		}
	}
	

}
