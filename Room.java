import java.util.ArrayList;

/**
 * Parent class of the types of room. This class serves as the room of the hotel.
 * 
 * <p>
 * Authors: Olivares, Maverick C.
 *          Ong, Cedric Clifford L.
 * </p>
 * 
 */
public class Room {
    private int roomNum; 
    private float roomPrice; 
    private ArrayList<Reservation> reservation; 

    /**
     * Constructor for Room.
     * 
     * @param roomNum The room number.
     * 
     */
    public Room (int roomNum){
        this.roomNum = roomNum;
        this.roomPrice = 1299.0f;
        this.reservation = new ArrayList<>();
    }

    /**
     * Sets the room number.
     *
     * @param roomNum The room number.
     * 
     */
    public void setRoomNum(int roomNum){
        this.roomNum = roomNum;
    }

    /**
     * Gets the room number.
     * 
     * @return The room number.
     * 
     */
    public int getRoomNum(){
        return roomNum;
    }

    /**
     * Sets the room price.
     *
     * @param roomPrice The new room price.
     * 
     */
    public void setRoomPrice(float roomPrice){
        this.roomPrice = roomPrice;
    }

    /**
     * Gets the room price.
     * 
     * @return The base price of the room.
     * 
     */
    public float getRoomPrice(){
        return roomPrice;
    }

    /**
     * Gets the reservations of the room.
     * 
     * @return The reservation.
     * 
     */
    public ArrayList<Reservation> getReservations(){
        return reservation;
    }

    /**
     * Remove the reservation of the room
     * 
     * @param index The index of the room reserved 
     */
    public void removeReservation(int index){
        reservation.remove(index);
    }

    /**
     * Checks the date for availability
     *    
     * @param checkIn The date of check-in.
     * @param checkOut The date of check-out.
     * @return true - reservation available / false - reservation is full.
     * 
     */
    public boolean checkDate(int checkIn, int checkOut){
        for(Reservation reservation: reservation){
            // check for existing reservation within the selected date
            if ((checkIn >= reservation.getCheckInDate() && checkIn < reservation.getCheckOutDate()) ||
                    (checkOut > reservation.getCheckInDate() && checkOut <= reservation.getCheckOutDate()) ||
                    (checkIn <= reservation.getCheckInDate() && checkOut >= reservation.getCheckOutDate())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates the total earnings of the room.
     * 
     * @return The total earnings of the room.
     * 
     */
    public float getEarnings(){
        float earnings = 0.0f;

        for(Reservation reservation: reservation){
            earnings += reservation.getTotalPrice();
        }
        return earnings;
    }

    /**
     * Add a reservation to the room
     * 
     */
    public void addReservation(Reservation reservations){
        reservation.add(reservations);
    }
}
