package bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDateRange {
    private DateRange dateRange1, dateRange2, dateRange3;

    @BeforeEach
    void setUp() throws Exception {
        // Setup resources before each test
        this.dateRange1 = new DateRange(LocalDate.of(2019, 1, 7),
                LocalDate.of(2019, 1, 10));
        this.dateRange2 = new DateRange(LocalDate.of(2019, 1, 5),
                LocalDate.of(2019, 1, 23));
        this.dateRange3 = new DateRange(LocalDate.of(2015, 1, 7),
                LocalDate.of(2018, 1, 10));
    }

    // Sample JUnit tests checking toYears works
    @Test
    void testToYears1() {
        assertEquals(0, this.dateRange1.toYears());
    }

    @Test
    void testToYears3() {
        assertEquals(3, this.dateRange3.toYears());
    }

    @Test
    // |----| A     |----| A     |----| A   |----| A         |---| A   |--| A     |-----| A
    // |------| B     |----| B     |--| B        |---| B   |---| B    |-----| B     |--| B
    void testOverlapsTrue() {
        Boolean isTrue = dateRange1.overlaps(dateRange2);
        assertTrue(isTrue);
    }

    @Test
    // |----| |---|    |----| |----|
    //   A      B        B      A
    void testOverlapsFalse() {
    	Boolean isFalse = dateRange2.overlaps(dateRange3);
        assertFalse(isFalse);
    }

    // TODO: put some of your own unit tests here
}
