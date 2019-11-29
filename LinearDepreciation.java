package bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LinearDepreciation implements ValuationPolicy {

    private BigDecimal depreciationRate;
    private int productionYear;
    private BigDecimal depositRate;
	
	public LinearDepreciation(double depreciationRate, int productionYear, double depositRate) {
		this.depreciationRate = new BigDecimal(depreciationRate);
		this.productionYear = productionYear;
		this.depositRate = new BigDecimal(depositRate);
	}
	
	public BigDecimal calculateValue(Bike bike, LocalDate date) {
		int age = date.getYear() - productionYear;
		BigDecimal replacementValue = (bike.getType()).getReplacementValue();
		BigDecimal newValue = replacementValue.subtract(
				new BigDecimal(age).multiply(depreciationRate).multiply(replacementValue));
	    BigDecimal deposit = depositRate.multiply(newValue);
		return deposit;
	}
}
