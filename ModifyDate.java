/**
 * 
 * This class serves as the modification of the date and price rate.
 *
 *  <p>
 * Authors: Olivares, Maverick C.
 *          Ong, Cedric Clifford L.
 * </p>
 * 
 */

public class ModifyDate {
    private int day; 
    private float pricePercent; 

    /**
     * Constructor for ModifyDate.
     * 
     * @param day The selected day.
     * @param pricePercent The rate assigned for that day.
     * 
     */
    public ModifyDate(int day, float pricePercent){
        this.day = day;
        this.pricePercent = pricePercent;
    }

    /**
     * Sets the rate for the selected day.
     * 
     * @param pricePercent The new percentage rate for that day.
     * 
     */
    public void setPricePercent(float pricePercent) {
        this.pricePercent = pricePercent;
    }

    /**
     * Gets the current rate for the day.
     * 
     * @return the rate for that day.
     * 
     */
    public float getPricePercent() {
        return pricePercent;
    }

    /**
     * Gets the selected day.
     * 
     * @return the selected day as an integer.
     */
    public int getDay() {
        return day;
    }
}
