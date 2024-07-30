/**
 * 
 * This class serves as the reservation of the rooms of the Hotel.
 * 
 * <p>
 * Authors: Olivares, Maverick C.
 *          Ong, Cedric Clifford L.
 * </p>
 * 
 */
public class Reservation {
    private String guestName; 
    private int checkInDate; 
    private int checkOutDate; 
    private float totalPrice; 
    private int roomNum; 

    /**
     * Constructor for Reservation.
     * 
     * @param guestName The name of the guest.
     * @param checkInDate Check-in date.
     * @param checkOutDate Check-out date.
     * @param roomNum Room number.
     * @param price Total price of the reservation.
     * 
     */
    public Reservation (String guestName, int checkInDate, int checkOutDate, int roomNum, float price){
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomNum = roomNum;
        this.totalPrice = price;
    }

    /**
     * 
     * Gets the guest name of the selected reservation.
     * 
     * @return The guest name.
     * 
     */
    public String getGuestName(){
        return guestName;
    }

    /**
     * Gets the room number assigned to the selected reservation.
     * 
     * @return The room number assigned to the selected reservation.
     * 
     */
    public int getRoomNum(){
        return roomNum;
    }

    /**
     * Gets the check-in date of the selected reservation.
     * 
     * @return The check-in date.
     * 
     */
    public int getCheckInDate(){
        return checkInDate;
    }

    /**
     * Gets the check-out date of the selected reservation.
     * 
     * @return The check-out date.
     * 
     */
    public int getCheckOutDate(){
        return checkOutDate;
    }

    /**
     * Gets the total reservation price.
     * 
     * @return The total price for the selected reservation.
     * 
     */
    public float getTotalPrice(){
        return totalPrice;
    }


    /**
     * Converts attributes to String for display.
     * 
     * @return Room reservation details stored in a String.
     * 
     */
    public String toString(){
        int stayDuration = checkOutDate - checkInDate;
        float pricePerNight = getTotalPrice() / stayDuration;

        return "\n [Room " + this.roomNum + "]\n" +
                " Guest Name: " + getGuestName() + "\n" +
                " Check-in Date: " + getCheckInDate() + "\n" +
                " Check-out Date: " + getCheckOutDate() + "\n" +
                " Duration of Stay: " + stayDuration + " days\n" +
                " Price per night: " + String.format("%.2f", pricePerNight) + "\n\n" +
                " Total Price: " + String.format("%.2f", getTotalPrice() ) + "\n";
    }
}
