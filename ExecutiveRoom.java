/**
 * 
 * This class is a subclass of Room and represents the Executive Room type.
 *
 *  <p>
 * Authors: Olivares, Maverick C.
 *          Ong, Cedric Clifford L.
 * </p>
 * 
 */

public class ExecutiveRoom extends Room{
    /**
     * Constructor for the Executive room.
     * 
     * @param roomNum The room number.
     * 
     */
    public ExecutiveRoom(int roomNum){
        super(roomNum);
        setRoomPrice(super.getRoomPrice());
    }

    /**
     * Sets the room price of an executive room (35% higher than base price).
     * 
     * @param roomPrice The base price.
     * 
     */
    @Override
    public void setRoomPrice(float roomPrice) {
       roomPrice = roomPrice * 1.35f;
       super.setRoomPrice(roomPrice);
    }
}
