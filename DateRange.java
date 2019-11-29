package bikerental;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.function.BooleanSupplier;

public class DateRange {
    private LocalDate start, end;
    
    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
    
    public LocalDate getStart() {
        return this.start;
    }
    
    public LocalDate getEnd() {
        return this.end;
    }

    public long toYears() {
        return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
    }

    public long toDays() {
        return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
    }

    public Boolean overlaps(DateRange other) {
        if(start.equals(other.getStart()) || end.equals(other.getEnd())) {
        	return true;
        }
        else if(other.getStart().isAfter(end) ) {
            return false;
        }
        else if(other.getStart().isAfter(start) && other.getEnd().isAfter(end)) {
        	return true;
        }
        else if(other.getStart().isAfter(start) && other.getEnd().equals(end)) {
        	return true;
        }
        else if(other.getStart().isAfter(start) && other.getEnd().isBefore(end)) {
        	return true;
        }
        else if(other.getStart().isBefore(start) && other.getEnd().isBefore(end)) {
        	if(other.getEnd().isBefore(start)) {
        		return false;
        	}
        	return true;
        }
        else if(other.getStart().isBefore(start) && other.getEnd().isAfter(end)) {
        	return true;
        }
        else {
        	return false;
        }
    }

    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(end, start);
    }

    @Override
    public boolean equals(Object obj) {
        // equals method for testing equality in tests
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DateRange other = (DateRange) obj;
        return Objects.equals(end, other.end) && Objects.equals(start, other.start);
    }
    
    // You can add your own methods here
}
