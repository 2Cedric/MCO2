import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Hotel with rooms and reservations.
 * 
 * <p>
 * Authors: Olivares, Maverick C.
 *          Ong, Cedric Clifford L.
 * </p>
 * 
 */
public class Hotel {
    private String name; 
    private ArrayList<Room> room; 
    private ArrayList<Reservation> reservation; 
    private ArrayList<ModifyDate> date; 

    /**
     * Constructs a Hotel with the given name, and number of rooms (different types)
     *
     * @param name The name of the Hotel.
     * @param numExecutive The number of Executive rooms.
     * @param numDeluxe The number of Deluxe rooms.
     * @param numStandard The number of Standard rooms.
     */
    public Hotel(String name, int numExecutive, int numDeluxe, int numStandard){
        this.name = name;
        this.reservation = new ArrayList<>();
        this.room = new ArrayList<>();
        this.date = new ArrayList<>();

        for(int i = 1; i <= numExecutive; i++)
            room.add(new ExecutiveRoom(300 + i));

        for(int i = 1; i <= numDeluxe; i++)
            room.add(new DeluxeRoom(200 + i));

        for(int i = 1; i <= numStandard; i++)
            room.add(new StandardRoom(100 + i));

        for(int i = 1; i <= 31; i++) // Default 100% price rate for dates
            date.add(new ModifyDate(i, 1.0f));
    }

    /**
     * Sets the hotel name.
     * 
     * @param name The name of the Hotel.
     */
    public void setHotelName (String name){
        this.name = name;
    }

    /**
     * Gets the hotel name assigned.
     *
     * @return The current assigned hotel name.
     */
    public String getHotelName(){
        return name;
    }

    /**
     * Gets the earnings of the Hotel.
     * 
     * @return The total earnings of the Hotel.
     */
    public float getHotelEarnings(){
        float earnings = 0.0f;
        for (Room room : room)
            earnings += room.getEarnings();

        return earnings;
    }

    /**
     * Returns the reservation list of the Hotel.
     *
     * @return The reservation list.
     */
    public ArrayList<Reservation> getReservations(){
        return reservation;
    }

    /**
     * Gets the rooms of the Hotel (including details).
     *
     * @return The room list.
     */
    public ArrayList<Room> getRooms() {
        return room;
    }

    /**
     * Returns the dates (including additional charges)
     * 
     * @return the date list.
     */
    public ArrayList<ModifyDate> getDates(){
        return date;
    }

    /**
     * Checks if a room exists.
     * 
     * @param roomNum The room number
     * @return true - a room is found / false - room is not found
     * 
     */
    public boolean findRoom(int roomNum){
        for(Room room : room) {
            if(roomNum == room.getRoomNum())
                return true;
        }
        return false;
    }

    /**
     * Displays hotel information which includes the hotel
     * name, number of rooms, and total earnings.
     */
    public void viewHotelInfo(){
        float earnings = getHotelEarnings();

        System.out.println("\nHotel Name: " + name);
        System.out.println("Total earnings for the month: " + String.format("%.2f", earnings));
        System.out.println("\nTotal number of rooms: " + room.size());

        if (numStandard() > 0) { // The number of Standard Rooms
            System.out.print("Standard Rooms: " + numStandard());
            System.out.println(" [" + "Rooms" + 101 + " to " + (100 + numStandard()) + "]");
        }

        if(numDeluxe() > 0) { // The number of Deluxe Rooms
            System.out.print("Deluxe Rooms: " + numDeluxe());
            System.out.println(" [" + "Rooms" + 201 + " to " + (200 + numDeluxe()) + "]");
        }

        if(numExecutive() > 0) { // The number of Executive Rooms
            System.out.print("Executive Rooms: " + numExecutive());
            System.out.println(" [" + "Rooms" + 301 + " to " + (300 + numExecutive()) + "]");
        }
    }

    /**
     * Displays the room information of the selected room of the hotel
     *
     * @param roomNum The room number.
     * 
     */
    public void viewRoomInfo(int roomNum){
        int daysEmpty, daysFull = 0, index = 0;

        System.out.println("\nRoom " + roomNum + ": ");

        for(Room room : room) {
            if(roomNum == room.getRoomNum())
                break;
            index++;
        }

        System.out.println("Price per night: " + room.get(index).getRoomPrice());

        for(Reservation reservation : reservation){
            if(reservation.getRoomNum() == roomNum)
                daysFull += reservation.getCheckOutDate() - reservation.getCheckInDate();
        }

        daysEmpty = 31 - daysFull;

        System.out.println("Total days available: " + daysEmpty);
        System.out.println("Total days with reservation: " + daysFull);
    }

    /**
     * Checks and returns the number of days a room is occupied
     * 
     * @param roomNum The room number
     * @return The number of days a room is occupied
     * 
     */
    public int daysFull(int roomNum){
        int daysFull = 0;
        for(Reservation reservation : reservation){
            if(reservation.getRoomNum() == roomNum)
                daysFull += reservation.getCheckOutDate() - reservation.getCheckInDate();
        }
        return daysFull;
    }

    /**
     * Gets the base price of the room
     * 
     * @param roomNum The room number
     * @return The base price of the room (if it exists) / else return 0 price
     * 
     */
    public float getRoomPrice(int roomNum){
        for(Room room: room){
            if(room.getRoomNum() == roomNum)
                return room.getRoomPrice();
        }
        return 0.0f;
    }

    /**
     * Sets a reservation in the hotel
     * 
     * @param guestName The name of the guest.
     * @param checkInDate Check-in date of the reservation.
     * @param checkOutDate Check-out date of the reservation.
     * @param roomType The room type of the reservation.
     * @param totalPrice The total price of the reservation.
     * @return The status of the reservation (true - confirmed / false - cancelled).
     * 
     */
    public boolean setReservation(String guestName, int checkInDate, int checkOutDate, int roomType, float totalPrice){

        // Standard room
        if(roomType == 1){ 
            for(Room room : room){
                if(room instanceof StandardRoom){
                    if(room.checkDate(checkInDate, checkOutDate)) {
                        reservation.add(new Reservation(guestName, checkInDate, checkOutDate, room.getRoomNum(),
                                totalPrice));
                        room.addReservation(reservation.get(reservation.size() - 1));
                        return true;
                    }
                }
            }
        } 
        // Deluxe Room
        else if (roomType == 2) { 
            for(Room room : room){
                if(room instanceof DeluxeRoom){
                    if(room.checkDate(checkInDate, checkOutDate)) {
                        reservation.add(new Reservation(guestName, checkInDate, checkOutDate, room.getRoomNum(),
                                totalPrice));
                        room.addReservation(reservation.get(reservation.size() - 1));
                        return true;
                    }
                }
            }
        } 
        // Executive Room
        else if (roomType == 3) { 
            for(Room room : room){
                if(room instanceof ExecutiveRoom){
                    if(room.checkDate(checkInDate, checkOutDate)) {
                        reservation.add(new Reservation(guestName, checkInDate, checkOutDate, room.getRoomNum(),
                                totalPrice));
                        room.addReservation(reservation.get(reservation.size() - 1));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Removes a reservation in this hotel
     *
     * @param scanner Scanner passed as argument.
     * 
     */
    public void removeReservation(Scanner scanner){
        int ctr = -1, check;
        boolean status = false;
        HotelManagementSystem intCheck = new HotelManagementSystem(scanner);

        if(!isReservationEmpty() && viewRoomReservation() == 1) {
            do {
                System.out.print("\033\143");
                viewRoomReservation();
                System.out.print("\nEnter reservation number to be removed: ");

                if (scanner.hasNextInt())
                    ctr = scanner.nextInt() - 1;

                if (ctr >= 0 && ctr < reservation.size()) {
                    System.out.println("\nDo you want to remove reservation # " + (ctr + 1));
                    System.out.println("1 - Yes");
                    System.out.println("2 - No");
                    System.out.print("Select Input: ");
                    check = intCheck.checkValidInt();

                    switch (check) {
                        case 1:
                            reservation.remove(ctr);
                            status = true;
                            System.out.print("\nSuccessfully removed reservation # " + (ctr + 1));
                            System.out.print("\nPress enter to continue...");
                            scanner.nextLine();
                            System.out.print("\033\143");
                            break;  
                        case 2:
                            status = true;
                            System.out.print("\nPress enter to continue...");
                            scanner.nextLine();
                            System.out.print("\033\143");
                            break;
                    }
                }

                // Reservation number does not exist
                else {
                    scanner.nextLine();
                    System.out.println("\n-- Enter a valid reservation number --");
                    System.out.print("Press enter to continue...");
                    scanner.nextLine();
                }
            } while (!status);
        }

        // No reservations in the hotel
        else {
            System.out.println("There are no reservations found");
            System.out.print("\nPress enter to continue...");
            scanner.nextLine();
            System.out.print("\033\143");
        }
    }

    /**
     * Displays room reservations.
     * 
     * @return 1 - There is a reserved room / 0 - no reserved room found.
     * 
     */
    public int viewRoomReservation(){
        int ctr = 1, index = 0;

        System.out.println("Reservations at " + name);

        if(!reservation.isEmpty()) { // check if arraylist is not empty
            for (Reservation room : reservation) {
                System.out.println(ctr + ". " + reservation.get(index).getGuestName());
                ctr++;
                index++;
            }
            return 1;
        }
        else
            System.out.println("There are no reservations found");
        return 0;
    }

    /**
     * Displays reservation info
     * 
     * @param index The index of the reservation.
     * 
     */
    public void viewReservationInfo(int index){
        System.out.println(reservation.get(index).toString());
    }

    /**
     * Checks if the reservations list is empty.
     * 
     * @return The status of the reservation (if empty or not).
     * 
     */
    public boolean isReservationEmpty(){
        return reservation.isEmpty();
    }

    /**
     * Adds additional rooms to the hotel.
     * 
     * @param numRoom The number of rooms to add.
     * @param roomType The room type of the room to be added.
     * @return The status of adding rooms (true - success / false - fail).
     * 
     */
    public boolean addRoom(int numRoom, int roomType){
        int ctr = 0;
        int available = 50 - room.size();
        String type = "";

            // check if the inputted number is within the range of 1 to 50
            if ((numRoom > 0 && numRoom < 50) && (numRoom <= available)) {
                    if (roomType == 1) {
                        while (ctr < numRoom) {
                            room.add(new StandardRoom(100 + numStandard() + 1));
                            ctr++;
                        }
                        type = "Standard Rooms";
                    }
                    else if (roomType == 2) {
                        while (ctr < numRoom) {
                            room.add(new DeluxeRoom(200 + numDeluxe() + 1));
                            ctr++;
                        }
                        type = "Deluxe Rooms";
                    }
                    else if (roomType == 3) {
                        while (ctr < numRoom) {
                            room.add(new ExecutiveRoom(300 + numExecutive() + 1));
                            ctr++;
                        }
                        type = "Executive Rooms";
                    }

                System.out.println("\nSuccessfully added " + numRoom + " " + type);
                System.out.println("Total rooms: " + room.size());
                System.out.println("Standard Rooms: " + numStandard() );
                System.out.println("Deluxe Rooms: " + numDeluxe());
                System.out.println("Executive Rooms: " + numExecutive());
                return true;
            }

            else
                System.out.println("Add at least a single room and only " + available + " rooms left available!");
            return false;
    }

    /**
     * Removes a room from the Hotel.
     *
     * @param roomNum The room number to be removed.
     * @return The removal status of the selected room (-1 - room not found / 0 - room full / 1 - room removed).
     * 
     */
    public int removeRoom(int roomNum) {
        int index = -1;

        for (int i = 0; i < room.size(); i++) {
            if (room.get(i).getRoomNum() == roomNum) {
                index = i;
                break;
            }
        }

        // Room not found
        if (index == -1) {
            System.out.println("\nRoom " + room + " does not exist.");
            return -1;
        }

        // Check if room is reserved
        if (!room.get(index).getReservations().isEmpty()) {
            System.out.println("\nRoom " + room + " is full, cannot be removed!");
            return 0;
        }

        room.remove(index);
        System.out.println("\nSuccessfully removed Room " + room);
        for (int j = index; j < room.size(); j++) {
            room.get(j).setRoomNum(room.get(j).getRoomNum() - 1);
        }
        return 1;
    }

    /**
     * Sum of the number of standard rooms.
     * 
     * @return The total number of standard rooms.
     * 
     */
    public int numStandard(){
        int ctr = 0;
        for(Room room : room){
            if(room instanceof StandardRoom)
                ctr++;
        }
        return ctr;
    }

    /**
     * Sum of the number of deluxe rooms.
     * 
     * @return The total number of deluxe rooms.
     * 
     */
    public int numDeluxe(){
        int ctr = 0;
        for(Room room : room){
            if(room instanceof DeluxeRoom)
                ctr++;
        }
        return ctr;
    }

    /**
     * Sum of the number of executive rooms.
     * 
     * @return The total number of executive rooms.
     * 
     */
    public int numExecutive(){
        int ctr = 0;
        for(Room room : room){
            if(room instanceof ExecutiveRoom)
                ctr++;
        }
        return ctr;
    }

    /**
     * Sets the base price for the rooms of the hotel.
     * 
     * @param price The new price for the rooms (>100).
     * 
     */
    public void setBasePrice(float roomPrice){
        for (Room room : room) {
            if(room instanceof ExecutiveRoom)
                room.setRoomPrice(roomPrice);
            else if(room instanceof DeluxeRoom)
                room.setRoomPrice(roomPrice);
            else if(room instanceof StandardRoom)
                room.setRoomPrice(roomPrice);
        }
    }

    /**
     * Gets the current base price of the hotel.
     * 
     * @return The current base price of the room (standard).
     * 
     */
    public float getBasePrice(){
        float roomPrice = 0.0f;
        for(Room room : room){
            if(room instanceof StandardRoom){
                roomPrice = room.getRoomPrice();
                break;
            }
        }
        return roomPrice;
    }

    /**
     * Checks the validity of the voucher.
     * 
     * @param voucher The voucher string inputted by the user
     * @param checkIn The check in date of the reservation
     * @param checkOut The check out date of the reservation
     * @return The voucher effect if valid (1, 2, 3) / 0 if not
     * 
     */
    public int checkVoucher(String voucher, int checkIn, int checkOut){

        // Flat 10% discount to the final price of a reservation
        if(voucher.equals("I_WORK_HERE")){
            return 1;
        }


        // If the reservation has 5 days or more, the first day of the reservation is given for free
        else if (voucher.equals("STAY4_GET1") && (checkOut - checkIn >= 5)) {
            return 2;
        }

        /*
            This gives a 7% discount to the overall price if reservation covers (but not as
        checkout) either day 15 or 30.
         */
        else if (voucher.equals("PAYDAY") && ( (15 >= checkIn && 15 < checkOut) || (30 >= checkIn && 30 < checkOut) )) {
            return 3;
        }
        return 0;
    }

    /**
     * Computes for the total reservation price.
     * 
     * @param voucher The type of voucher that will be applied.
     * @param checkIn The check in date of the reservation.
     * @param checkOut The check out date of the reservation.
     * @param roomType The type of room selected.
     * @return The total price of the reservation.
     * 
     */
    public float computeTotal(int voucher, int checkIn, int checkOut, int roomType){
        float roomPrice = 0.0f, roomRate = 0.0f;

        roomRate = switch (roomType) {
            case 1 -> getBasePrice();
            case 2 -> getBasePrice() * 1.2f;
            case 3 -> getBasePrice() * 1.35f;
            default -> roomRate;
        };

        // I_WORK_HERE (Voucher 1)
        if(voucher == 1){ 
            for (ModifyDate date : date){
                if (date.getDay() >= checkIn && date.getDay() < checkOut)
                    roomPrice += roomRate * date.getPricePercent();
            }
            return roomPrice * 0.9f; 
        }

        // STAY4_GET1 (Voucher 2)
        else if (voucher == 2){ 
            for (ModifyDate date : date){ 
                if (date.getDay() > checkIn && date.getDay() < checkOut)
                    roomPrice += roomRate * date.getPricePercent();
            }
            return roomPrice;
        }

        // PAYDAY (Voucher 3)
        else if (voucher == 3){ 
            for (ModifyDate date : date){
                if (date.getDay() >= checkIn && date.getDay() < checkOut)
                    roomPrice += roomRate * date.getPricePercent();
            }
            return roomPrice * 0.93f; 
        }

        // No voucher applied
        else{ 
            for (ModifyDate date : date){
                if (date.getDay() >= checkIn && date.getDay() < checkOut)
                    roomPrice += roomRate * date.getPricePercent();
            }
            return roomPrice;
        }
    }

     /**
     * Displays the price rate for each day
     * 
     */
    public void displayDayRate(){
        System.out.println("Day    Rate");
        for (ModifyDate list : date){
            System.out.println(String.format("%02d", list.getDay()) + " || " +
                    String.format("%.2f", list.getPricePercent()*100) +"%" +
                    "  == Standard [" + String.format("%.2f", (getBasePrice() * list.getPricePercent())) + "]" +
                    "  Deluxe [" + String.format("%.2f", (getBasePrice() * 1.2f * list.getPricePercent())) + "]" +
                    "  Executive [" + String.format("%.2f", (getBasePrice() * 1.35f * list.getPricePercent())) + "]");
        }
    }

    /**
     * Modifies the price rate for the selected date.
     * 
     * @param setDate The date to be modified.
     * @param rate The new rate for the selected date.
     * 
     */
    public void modifyRate(int setDate, float rate){
        for(ModifyDate day : date){
            if(setDate == day.getDay()){
                day.setPricePercent(rate);
                break;
            }
        }
    }

    /**
     * Gets the rate for the selected day.
     * 
     * @param day The selected date.
     * @return The price rate for the selected date.
     * 
     */
    public float getRate(int day){
        day -= 1;
        return date.get(day).getPricePercent();
    }


    /**
     * Finds and removes the reservation from the list
     * 
     * @param index The index of the arraylist
     * 
     */
    public void removeReservationGui(int index){
        int ctr, size, roomDate;
        int roomNum = reservation.get(index).getRoomNum();
        int checkIn = reservation.get(index).getCheckInDate();

        for(Room room: room){
            if (roomNum == room.getRoomNum()) {
                size = room.getReservations().size();
                ctr = 0;
                while (ctr < size) {
                    roomDate = room.getReservations().get(ctr).getCheckInDate();
                    if (roomDate == checkIn) {
                        room.removeReservation(ctr);
                        reservation.remove(index);
                        return;
                    }
                    ctr++;
                }
            }
        }
    }
}