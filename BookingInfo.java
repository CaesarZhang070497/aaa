package bikerental;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

public class BookingInfo implements DeliveryService{
	private String bookingNum;
	private BigDecimal totalPrice;
	private BigDecimal deposit;
	private DateRange bookingDate;
	private Location locatedBikes;
	private boolean needDelivery;
	private HashSet<Bike> bookingItermBikes;
	private String confirmation;
	private Renters whoRent;
	
	
	
	public BookingInfo(String bookingNum, BigDecimal totalPrice, 
			           BigDecimal deposit, DateRange bookingDate,
			           Location locatedBikes, HashSet<Bike> bookingItemBikes,
			           boolean needDelivery) {
		this.bookingNum = bookingNum;
		this.totalPrice = totalPrice;
		this.deposit = deposit;
		this.bookingDate = bookingDate;
		this.locatedBikes = locatedBikes;
		this.needDelivery = needDelivery;
		this.bookingItermBikes = bookingItemBikes;
		if(needDelivery) {
			Location dropLocation = null;
			for(Bike aBike: bookingItermBikes) {
				dropLocation = aBike.getRenter().getAddress();
				LocalDate pickupDate = aBike.getRenter().getDateRange().getStart();
				scheduleDelivery(aBike,locatedBikes, dropLocation, pickupDate);
			}
		}	
	}
	public void setBookingItermBikes(HashSet<Bike> bookingItermBikes) {
		this.bookingItermBikes = bookingItermBikes;
		for(Bike aBike : bookingItermBikes) {
			aBike.getProvider().recordBooking(this, aBike);
		}
	}
	public HashSet<Bike> getBookingItermBikes(){
		return bookingItermBikes;
	}
	public DateRange getBookingDate() {
		return bookingDate;
	}
	public String generateConfirmation(String bookingNum, BigDecimal totalPrice, 
			                           BigDecimal deposit, DateRange bookingDate,
	                                   Location locatedBikes, DeliveryService deliveryInfo,
	                                   HashSet<Bike> bookingItemBikes ) {
		confirmation = "Thank you for your booking!\n" +"Your booking number is: %s,/n"+
	                   "you have booked the following bikes: %s, for the dates %s,\n"
				        +"for the price of %s "+ "and your deposit will be %s.\n"+
	                   "You can collect your bikes at %s.\n"+ "Here is the delivery information: %s.\n"+
				       "Thank you for choosing our service! We hope you enjoy your time!"; 
		
		return String.format(confirmation,bookingNum, bookingItemBikes, bookingDate,
				                          totalPrice,deposit,locatedBikes,deliveryInfo);
	}
	@Override
	public void scheduleDelivery(Deliverable deliverable, Location pickupLocation, Location dropoffLocation,
			LocalDate pickupDate) {
		// TODO Auto-generated method stub
		
	}

}
