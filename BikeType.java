package bikerental;
import java.math.BigDecimal;

public class BikeType {
	private String bikeType;
	private BigDecimal typePrice;
	private BigDecimal replacementValue;
	private BigDecimal depositRate;
	
	public BikeType(String bikeType, BigDecimal typePrice, BigDecimal replacementValue, BigDecimal depositRate) {
		this.bikeType = bikeType.toUpperCase();
		this.typePrice = typePrice;
		this.replacementValue = replacementValue;
		this.depositRate = depositRate;
	}
	public void setTypePrice(BigDecimal typePrice) {
		this.typePrice = typePrice;
	}
	public void setReplacementValue(BigDecimal replacementValue) {
		this.replacementValue = replacementValue;
	}
    public BigDecimal getReplacementValue() {
        return replacementValue;
    }
    public void setDepositRate(BigDecimal depositRate) {
		this.depositRate = depositRate;
	}
    public BigDecimal getDepositRate() {
        return this.depositRate;
    }
    public String getBikeType() {
    	return bikeType;
    }
    
    public BigDecimal getTypePrice() {
    	return typePrice;
    }
	@Override
	public String toString() {
		return "BikeType [bikeType=" + bikeType + "]";
	}
    
}