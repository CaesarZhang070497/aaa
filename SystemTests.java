package bikerental;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;



public class SystemTests {
	private DateRange dateRange1, dateRange2, dateRange3;
	private BigDecimal depositRate1, depositRate2, depositRate3;
	Integer bookingNum = 2;
	    //create three different prices for three different types of bikes.
	    BigDecimal mPrice = new BigDecimal(35);
	    BigDecimal cPrice = new BigDecimal(20);
	    BigDecimal kPrice = new BigDecimal(12);
	    
	    BigDecimal mReValue = new BigDecimal(35);
	    BigDecimal cReValue = new BigDecimal(20);
	    BigDecimal kReValue = new BigDecimal(12);
	    
	    BigDecimal aDepositRate = new BigDecimal(0.2);
	    BigDecimal bDepositRate = new BigDecimal(0.1);
	    BigDecimal cDepositRate = new BigDecimal(0.2);
	    
	 // create types of bike for testing.
	 	BikeType mBikeType = new BikeType("mountain", mPrice,mReValue,depositRate1);
	 	BikeType cBikeType = new BikeType("commuting", cPrice,cReValue,depositRate2);
	 	BikeType kBikeType = new BikeType("kids",kPrice,kReValue,depositRate3);
	    
		
		// create three bikes with random bikeId and BikeTypes 
		//  which correspond to the created bikeType.
		Bike bike1 = new Bike("463729", mBikeType);
		Bike bike2 = new Bike("362784", cBikeType);
		Bike bike3 = new Bike("654793", kBikeType);
		Bike bike4 = new Bike("364782", mBikeType);
		Bike bike5 = new Bike("567823", cBikeType);
		Bike bike6 = new Bike("357328", cBikeType);
		Bike bike7 = new Bike("455738", kBikeType);
		Bike bike8 = new Bike("347383", mBikeType);
		Bike bike9 = new Bike("463723", mBikeType);
		
		Location providerLocA = new Location("18 Holyrood", "EH3 9SS");
		Location providerLocB = new Location("27 chapel", "E32 76S");
		Location providerLocC = new Location("23 Chamer", "EH5 56F");
		Location renterAd = new Location("23 Marry", "EH4 SE3");
		HashSet<Bike> stockOfA = new HashSet<Bike>();
		HashSet<Bike> stockOfB = new HashSet<Bike>();
		HashSet<Bike> stockOfC = new HashSet<Bike>();
		HashSet<Providers> partnerOfA = new HashSet<Providers>();
		HashSet<Providers> partnerOfB = new HashSet<Providers>();
		HashSet<Providers> partnerOfC = new HashSet<Providers>();
		
		Providers providerA = new Providers("JOoey", providerLocA, "3523627", stockOfA,partnerOfA,aDepositRate );
		Providers providerB = new Providers("Daniel", providerLocB, "463732", stockOfB,partnerOfB,bDepositRate);
		Providers providerC = new Providers("Duke", providerLocC, "463728", stockOfC,partnerOfC,cDepositRate);
		
		Renters aRenter = new Renters("Monica", "463728", renterAd);
		
		String uniqueNum = UUID.randomUUID().toString();
	    // create a empty set of bikes which avoid the duplicated values 
		// and the order does not matter
		HashSet<Bike> bikes = new HashSet<Bike>();
		HashSet<Bike> bikesSource = new HashSet<Bike>();
		HashSet<Providers> providerSource = new HashSet<Providers>();
		HashMap<BikeType, Integer> quantitySource = new HashMap<BikeType, Integer>();
		HashSet<Location> locationSource = new HashSet<Location>();
		HashMap<Providers, Providers> listOfPartnersSource;
		
		DataBase source = new DataBase(bikesSource, providerSource, locationSource,
				                       quantitySource, listOfPartnersSource);
		
		HashSet<Bike> resultGetQuote = new HashSet<Bike>();
		HashSet<Bike> listOfBookingBikes = new HashSet<Bike>();
		
		BookingInfo order = new BookingInfo(uniqueNum, mPrice, cDepositRate,
                                            dateRange2, providerLocC,listOfBookingBikes,false);
		
		// create an object of the MultidaysRateDiscountPolicy class
		// for testing the calculatePrcie method.
		MultidaysRateDiscountPolicy policy1 = new MultidaysRateDiscountPolicy();


    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();
        
        this.dateRange1 = new DateRange(LocalDate.of(2019, 1, 7),
                LocalDate.of(2019, 1, 10));
        this.dateRange2 = new DateRange(LocalDate.of(2019, 1, 5),
                LocalDate.of(2019, 1, 23));
        this.dateRange3 = new DateRange(LocalDate.of(2015, 1, 7),
                LocalDate.of(2018, 1, 10));
        mBikeType.setTypePrice(mPrice);
        cBikeType.setTypePrice(cPrice);
        kBikeType.setTypePrice(kPrice);
        
        mBikeType.setReplacementValue(mReValue);
        cBikeType.setReplacementValue(cReValue);
        kBikeType.setReplacementValue(kReValue);
        
        for(Bike aBike : providerA.getStockOfBikes()) {
        	aBike.getType().setDepositRate(aDepositRate);
        }
        for(Bike bBike : providerB.getStockOfBikes()) {
        	bBike.getType().setDepositRate(bDepositRate);
        }
        for(Bike cBike : providerC.getStockOfBikes()) {
        	cBike.getType().setDepositRate(cDepositRate);
        }
        
        
        bike1.setProvider(providerA);
        bike2.setProvider(providerA);
        bike3.setProvider(providerA);
        bike4.setProvider(providerB);
        bike5.setProvider(providerB);
        bike6.setProvider(providerB);
        bike7.setProvider(providerC);
        bike8.setProvider(providerC);
        bike9.setProvider(providerC);

		providerA.setBikeLocation(bike1);
		providerA.setBikeLocation(bike2);
		providerA.setBikeLocation(bike3);
		providerB.setBikeLocation(bike4);
		providerB.setBikeLocation(bike5);
		providerB.setBikeLocation(bike6);
		providerC.setBikeLocation(bike7);
		providerC.setBikeLocation(bike8);
		providerC.setBikeLocation(bike9);
        


        
        bikes.add(bike1);
        bikes.add(bike2);
        bikes.add(bike3);
		bikes.add(bike4);
		bikes.add(bike5);
		bikes.add(bike6);
		bikes.add(bike7);
		bikes.add(bike8);
		bikes.add(bike9);


        aRenter.setDateRange(dateRange2);
        aRenter.setQuantity(bookingNum);
        aRenter.setTypeBikes(mBikeType);

        providerA.setBike(bike1);
        providerA.setBike(bike2);
        providerA.setBike(bike3);
        providerB.setBike(bike4);
        providerB.setBike(bike5);
        providerB.setBike(bike6);
        providerC.setBike(bike7);
        providerC.setBike(bike8);
        providerC.setBike(bike9);
        
        providerA.getDepositRate(mBikeType);
        providerA.getDepositRate(cBikeType);
        providerA.getDepositRate(kBikeType);
        providerB.getDepositRate(mBikeType);
        providerB.getDepositRate(cBikeType);
        providerB.getDepositRate(cBikeType);
        providerC.getDepositRate(kBikeType);
        providerC.getDepositRate(mBikeType);
        providerC.getDepositRate(mBikeType);
        

        
        providerA.isAgreedPartnership(providerA, providerC, true);
        
        source.setProviderSource(providerA);
        source.setProviderSource(providerB);
        source.setProviderSource(providerC);
        
        source.setLocationSource(partnerOfA);
        source.setLocationSource(partnerOfB);
        source.setLocationSource(partnerOfC);
        
        
        source.setBikeSource(providerSource);
        source.setQuantitySource(bikesSource);
        source.setPartnership(providerSource);

    	System.out.println(resultGetQuote.toString());

    	listOfBookingBikes.add(bike8);
    	listOfBookingBikes.add(bike9);
    	
    	order.setBookingItermBikes(listOfBookingBikes);
        
    }
    
    // TODO: Write system tests covering the three main use cases


    @Test
    void getQuote1() {
		for(Bike aBike : bikes) {
			aBike.setDateRange(dateRange3);
		}
		resultGetQuote.add(bike8);
		resultGetQuote.add(bike9);
    	HashSet<Bike> outcome = aRenter.getQuote(source);
    	assertEquals(resultGetQuote, outcome);
    }

	@Test
	void getQuote2() {

		for(Bike aBike : bikes) {
			aBike.setDateRange(dateRange1);
		}
		HashSet<Bike> outcome = aRenter.getQuote(source);
		assertEquals(resultGetQuote, outcome);
	}

    @Test
    void bookingQuote() {
		resultGetQuote.add(bike8);
		resultGetQuote.add(bike9);
		HashSet<Bike> order_bikes = order.getBookingItermBikes();
		BookingInfo aRenter_bookingQuote = aRenter.bookingQuote(resultGetQuote, false);
    	HashSet<Bike> outcome = aRenter_bookingQuote.getBookingItermBikes();
		System.out.println(order);

		Boolean is_same = outcome.equals(order_bikes);
    	assertTrue(is_same);
    }
    @Test
    void returnBikesToOriginalProvider() {
    	boolean outcome = true;
    	aRenter.returnBikesToProvider(source, order, providerC);
    	for(Bike abike : order.getBookingItermBikes()) {
    		outcome = outcome && providerC.recordReturning(abike, providerC);
    	}
    	assertTrue(outcome);
    }
    @Test
    void returnBikesToPartnerProvider() {
    	boolean outcome = true;
    	aRenter.returnBikesToProvider(source, order, providerA);
    	for(Bike abike : order.getBookingItermBikes()) {
    		outcome = outcome && providerA.recordReturning(abike, providerA);
    	}
    	assertTrue(outcome);
    }
    @Test
    void returnBikesToOtherProvider() {
    	boolean outcome = true;
    	aRenter.returnBikesToProvider(source, order, providerB);
    	for(Bike abike : order.getBookingItermBikes()) {
    		outcome = outcome && providerB.recordReturning(abike,providerB);
    	}
    	assertFalse(outcome);
    }
    
    
    @Test
	void testSetDailyRentalPrice() {
		mBikeType.setTypePrice(mPrice);
		BigDecimal mBikePrice = mBikeType.getTypePrice();
		
		assertEquals(mPrice,mBikePrice);
	}
	@Test
	void testCalculatePrice() {
		BigDecimal result = new BigDecimal(190.40);
		result = result.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		BigDecimal outcome = policy1.calculatePrice(bikes, dateRange2);
		outcome = outcome.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		assertEquals(result.stripTrailingZeros(), outcome.stripTrailingZeros());
	}
}
