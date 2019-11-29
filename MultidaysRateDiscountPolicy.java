package bikerental;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MultidaysRateDiscountPolicy implements PricingPolicy{
	@Override
	public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
		bikeType.setTypePrice(dailyPrice);
	}
	@Override
	public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) {
		long rangeOfDate = duration.toDays();
		BigDecimal sumPrice =  new BigDecimal(0);
		BigDecimal finalPrice = new BigDecimal(0);
		for(Bike a : bikes) {
			BikeType aBikeType = a.getType();
			BigDecimal aBikePrice = aBikeType.getTypePrice();
			sumPrice = sumPrice.add(aBikePrice);
		}
		if(rangeOfDate>=1 && rangeOfDate<=2) {
			finalPrice = sumPrice;
		}
		else if(rangeOfDate>=3 && rangeOfDate<=6) {
			BigDecimal discountValue = new BigDecimal(0.95);
			finalPrice = sumPrice.multiply(discountValue);
		}
		else if(rangeOfDate>=7 && rangeOfDate<=13) {
			BigDecimal discountValue = new BigDecimal(0.90);
			finalPrice = sumPrice.multiply(discountValue);
		}
		else if(rangeOfDate>=14){
			BigDecimal discountValue = new BigDecimal(0.85);
			finalPrice = sumPrice.multiply(discountValue);
		}
		return finalPrice;
	}

	/*
	public static void main(String[] args) {
		MultidaysRateDiscountPolicy policy1 = new MultidaysRateDiscountPolicy();
		BigDecimal result = new BigDecimal(56.95);
		
		result = result.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		
		BikeType mBikeType = new BikeType("mountain");
		BikeType cBikeType = new BikeType("commuting");
		BikeType kBikeType = new BikeType("kids");
		Bike bike1 = new Bike("463729", mBikeType);
		Bike bike2 = new Bike("362784", cBikeType);
		Bike bike3 = new Bike("654793", kBikeType);
		BigDecimal mPrice = new BigDecimal(35);
		BigDecimal cPrice = new BigDecimal(20);
		BigDecimal kPrice = new BigDecimal(12);
		mBikeType.setTypePrice(mPrice);
        cBikeType.setTypePrice(cPrice);
        kBikeType.setTypePrice(kPrice);
		HashSet<Bike> bikes = new HashSet<Bike>();
		
		bikes.add(bike1);
        bikes.add(bike2);
        bikes.add(bike3);
        
        DateRange dateRange2 = new DateRange(LocalDate.of(2019, 1, 5),
                LocalDate.of(2019, 1, 23));
        
        
        
        BigDecimal outcome = policy1.calculatePrice(bikes, dateRange2);
        outcome = outcome.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        
        System.out.println(result.stripTrailingZeros());
        System.out.println(outcome.stripTrailingZeros());
		System.out.println(result.compareTo(outcome));
	}
	
	*/

}
