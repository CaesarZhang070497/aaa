package bikerental;


import java.math.BigDecimal;
import java.time.LocalDate;

public class DoubleDecBalanceDepreciation implements ValuationPolicy {

    private BigDecimal depreciationRate;
    private int productionYear;
    private BigDecimal depositRate;
    
	public DoubleDecBalanceDepreciation(double depreciationRate, int productionYear, double depositRate) {
		this.depreciationRate = new BigDecimal(depreciationRate);
		this.productionYear = productionYear;
		this.depositRate = new BigDecimal(depositRate);
	}
	
	public BigDecimal calculateValue(Bike bike, LocalDate date) {
		int age = date.getYear() - productionYear;
		BigDecimal replacementValue = bike.getType().getReplacementValue();
		BigDecimal newValue = replacementValue.multiply(
				((new BigDecimal(1)).subtract(depreciationRate.multiply(
						new BigDecimal(2))).pow(age)));
		BigDecimal deposit = depositRate.multiply(newValue);
		return deposit;
	}
	
}
