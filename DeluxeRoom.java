/**
 * 
 * This class is a subclass of Room and represents the Deluxe Room type.
 *
 *  <p>
 * Authors: Olivares, Maverick C.
 *          Ong, Cedric Clifford L.
 * </p>
 * 
 */

public class DeluxeRoom extends Room{

    /**
     * Constructor for a deluxe room.
     * 
     * @param roomNum The room number.
     * 
     */
    public DeluxeRoom(int roomNum){
        super(roomNum);
        setRoomPrice(super.getRoomPrice());
    }

    /**
     * Sets the room price of a deluxe room (20% more than the base price))
     * 
     * @param roomPrice The base price of the hotel.
     * 
     */
    @Override
    public void setRoomPrice(float roomPrice) {
        roomPrice = roomPrice * 1.2f;
        super.setRoomPrice(roomPrice);
    }
}
