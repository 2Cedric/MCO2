import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class serves as the hotel management system.
 * 
 * <p>
 * Authors: Olivares, Maverick C.
 *          Ong, Cedric Clifford L.
 * </p>
 * 
 */
public class HotelManagementSystem {
    private ArrayList<Hotel> hotel; 
    private Scanner scanner;

    /**
     * Constructor for HotelManagementSystem
     * 
     * @param scanner The scanner.
     * 
     */
    public HotelManagementSystem(Scanner scanner) {
        this.hotel = new ArrayList<>();
        this.scanner = scanner;
    }

    /**
     * Adds a temporary hotel placeholder.
     * 
     * @param hotels specified hotel object.
     * 
     */
    public void temphotel(Hotel hotels) {
        hotel.add(hotels);
    }

    /**
     * Creates a Hotel
     *
     */
    public void createHotel(){
        String name, temp1, temp2;
        int numStandard, numDeluxe, numExecutive, numRooms = 1, check;

        do {
            check = 0;
            System.out.print("Enter new hotel name: ");
            name = scanner.nextLine();

            // Check for hotel name uniqueness
            for(Hotel hotel : hotel){
                temp1 = hotel.getHotelName().toLowerCase();
                temp2 = name.toLowerCase();

                if (temp1.equals(temp2)) {
                    check = -1;
                    System.out.println("Hotel already exists!");
                    System.out.print("\nPress enter to continue...");
                    scanner.nextLine();
                    break;
                }
            }
        } while (check == -1);

        // Number of rooms
        do{
            if(numRooms > 50 || numRooms < 1 )
                System.out.println("\nA hotel can only have 1 to 50 rooms.");

            System.out.print("Enter number of Standard Rooms: ");
            numStandard = scanner.nextInt();
            System.out.print("Enter number of Deluxe Rooms: ");
            numDeluxe = scanner.nextInt();
            System.out.print("Enter number of Executive Rooms: ");
            numExecutive = scanner.nextInt();
            scanner.nextLine();

            numRooms = numDeluxe + numExecutive + numStandard;

        } while (numRooms > 50 || numRooms < 1);

        // Summary
        System.out.println("\nHotel Name: " + name);
        System.out.println("Standard Rooms: " + numStandard);
        System.out.println("Deluxe Rooms: " + numDeluxe);
        System.out.println("Executive Rooms: " + numExecutive);
        System.out.println("Total rooms: " + numRooms);
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();

        hotel.add(new Hotel(name, numExecutive, numDeluxe, numStandard));
        System.out.print("\033\143");
    }

    /**
     * Checks for valid integer in the input.
     * 
     * @return A valid integer input.
     */
    public int checkValidInt(){
        boolean check = false;

        int value = -1;

        while (!check) {
            if(scanner.hasNextInt()) {
                check = true;
                value = scanner.nextInt();
                scanner.nextLine();
            }
            else {
                System.out.println("Invalid Input!");
                scanner.next();
            }
        }
        return value;
    }

    /**
     * Checks if the Hotel list is empty.
     *
     * @return The value of isEmpty() (true / false).
     */
    public boolean hotelEmpty(){
        return hotel.isEmpty();
    }

    /**
     * Gets the index of the Hotel.
     * 
     * @return The index of the hotel in the arraylist.
     * 
     */
    public int getHotelIndex(){
        int index, num;

        System.out.print("Enter hotel number: ");
        num = checkValidInt();

        while (num < 1 || num > hotel.size()) {
            System.out.print("\nThe number selected is not found. Please enter a valid number: ");
            num = checkValidInt();
        }
        index = num - 1;
        return index;
    }

    /**
     * Gets the hotel name.
     * 
     * @param index The index of the Hotel selected.
     * @return The name of the Hotel.
     * 
     */
    public String getHotelName(int index){
        return hotel.get(index).getHotelName();
    }

    /**
     * Displays the hotel list.
     * 
     */
    public void displayHotels(){
        int ctr = 1;

        if(!hotel.isEmpty()) {
            for (Hotel hotel : hotel) {
                System.out.println(ctr + ". " + hotel.getHotelName());
                ctr++;
            }
        }
        else
            System.out.println("There are no hotels found\n");
    }

    /**
     * Changes the name of the Hotel.
     * 
     * @param index The index of the hotel selected.
     * 
     */
    public void changeHotelName(int index){
        int check, value;
        String name, old = hotel.get(index).getHotelName(); // retrieve old name
        String temp1, temp2;

        do {
            value = 0;
            System.out.print("Enter new hotel name: ");
            name = scanner.nextLine();

            // Validates if the name is unique
            for(Hotel hotel : hotel){
                temp1 = hotel.getHotelName().toLowerCase();
                temp2 = name.toLowerCase();
                if (temp1.equals(temp2)) {
                    value = -1;
                    System.out.println("Hotel already exists!");
                    System.out.print("\nPress enter to continue...");
                    scanner.nextLine();
                    break;
                }
            }
        } while (value == -1);

        // Confirm input
         System.out.println("\nDo you want to change " + old + " to " + name + "?");
         System.out.println("1 - Yes");
         System.out.println("2 - No");
         System.out.print("Select Input: ");
         check = checkValidInt();

         switch (check) {
             case 1:
                 hotel.get(index).setHotelName(name);
                 System.out.println(old + " changed to " + hotel.get(index).getHotelName());
             case 2: break;
            }

        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    /**
     * Add rooms in the hotel.
     *
     * @param index The index of the hotel selected.
     * 
     */
    public void addHotelRooms(int index){
        int num, check, roomType;
        boolean success = false;
        String type = "";

            while(!success) {
                System.out.println("Adding rooms at " + hotel.get(index).getHotelName());
                displayRoomCount(index);

                System.out.print("Enter number of rooms to be added: ");
                num = checkValidInt();

                System.out.println("\nSelect the type of Room:" +
                                    "\n 1 - Standard Room" + "\n 2 - Deluxe Room" + "\n 3 - Executive Room");
                System.out.print("Select Input: ");

                roomType = checkValidInt();

                type = switch (roomType) {
                    case 1 -> "Standard Room";
                    case 2 -> "Deluxe Room";
                    case 3 -> "Executive Room";
                    default -> type;
                };
                System.out.println("\nDo you want to add " + num + " " + type + "?");
                System.out.println("1 - Yes");
                System.out.println("2 - No");
                System.out.print("Select Input: ");
                check = checkValidInt();

                switch (check) {
                    case 1:
                        success = hotel.get(index).addRoom(num, roomType);
                        System.out.print("Press enter to continue...");
                        scanner.nextLine();
                        System.out.print("\033\143");
                        break;
                    case 2:
                        success = true;
                        System.out.print("\nPress enter to continue...");
                        scanner.nextLine();
                        System.out.print("\033\143");
                        break;
                }
                System.out.print("\033\143");
            }
    }

    /**
     * Removes rooms in the hotel.
     * 
     * @param index The index of the hotel selected.
     * 
     */
    public void removeHotelRooms(int index) {
        int remove, check, type;
        boolean success = false;

        if (hotel.get(index).getRooms().size() == 1) {
            System.out.println("Hotel must have at least one room");
            System.out.print("\nPress enter to continue...");
            scanner.nextLine();
            System.out.print("\033\143");
        } 
        
        else {

            // Remove Room
            do {
                System.out.print("\033\143");
                System.out.println("\nRemove rooms at " + hotel.get(index).getHotelName());
                displayRoomCount(index);
                System.out.print("Enter room to be removed: ");
                remove = checkValidInt();

                System.out.println("\nDo you want to remove Room " + remove + "?");
                System.out.println("1 - Yes");
                System.out.println("2 - No");
                System.out.print("Select Input: ");
                check = checkValidInt();

                switch (check) {
                    case 1:
                        type = hotel.get(index).removeRoom(remove);
                        if (type == -1 || type == 0) {
                            System.out.println("Do you want to select another room?");
                            System.out.println("1 - YES");
                            System.out.println("2 - NO");
                            System.out.print("Enter your choice: ");
                            check = checkValidInt();
                            if (check == 1) {
                                break;
                            }
                            else {
                                success = true;
                                System.out.print("\nPress enter to continue...");
                                scanner.nextLine();
                                System.out.print("\033\143");
                            }
                        }

                        else if (type == 1) {
                            System.out.print("\nPress enter to continue...");
                            scanner.nextLine();
                            success = true;
                            System.out.print("\033\143");
                        }
                        break;
                    case 2:
                        success = true;
                        System.out.print("\nPress enter to continue...");
                        scanner.nextLine();
                        System.out.print("\033\143");
                        break;
                }
            } while (!success);
        }
    }

    /**
     * Display the room count
     * 
     * @param index The index of the hotel
     * 
     */

    public void displayRoomCount(int index) {
        System.out.println("Current number of rooms: " + hotel.get(index).getRooms().size());
        System.out.println("Standard Rooms: " + hotel.get(index).numStandard() + " [101 to " + (100+ hotel.get(index).numStandard()) + "]");
        System.out.println("Deluxe Rooms: " + hotel.get(index).numDeluxe() + " [201 to " + (200+ hotel.get(index).numDeluxe()) + "]");
        System.out.println("Executive Rooms: " + hotel.get(index).numExecutive() + " [301 to " + (300+ hotel.get(index).numExecutive()) + "]");
    }


    /**
     * Sets the base price of the hotel.
     *
     * @param index The index of the hotel selected.
     * 
     */
    public void setHotelBasePrice(int index){
        float price = 0.0f, deluxePrice = 0.0f, executivePrice = 0.0f, standardPrice = 0.0f;
        boolean success = false;
        int check;
        ArrayList<Room> rooms = hotel.get(index).getRooms();

        if(hotel.get(index).isReservationEmpty()) {

            do {
                System.out.print("\033\143");
                System.out.println("\nChanging base price at " + hotel.get(index).getHotelName());
                System.out.println("Current price per night: " + hotel.get(index).getBasePrice());

                while (price < 100.0f) {
                    System.out.print("\nEnter new room base price: ");
                    if (scanner.hasNextFloat()) {
                        price = scanner.nextFloat();
                        scanner.nextLine();
                    } else {
                        System.out.println("Enter a valid price (>100.0)\n");
                        scanner.nextLine();
                    }
                }

                // Confirmation
                System.out.println("\nDo you want to change base price from " + hotel.get(index).getBasePrice()
                                    + " to " + price + "?");
                System.out.println("1 - Yes");
                System.out.println("2 - No");
                System.out.print("Select Input: ");
                check = checkValidInt();

                switch (check) {
                    case 1:
                        // Set the base price
                        hotel.get(index).setBasePrice(price); 

                        // Assign the new room rates
                        for(Room room: rooms){ 
                            if(room instanceof ExecutiveRoom)
                                executivePrice = room.getRoomPrice();
                            else if (room instanceof DeluxeRoom)
                                deluxePrice = room.getRoomPrice();
                            else
                                standardPrice = room.getRoomPrice();
                        }

                        System.out.println("Successfully changed base price to " + hotel.get(index).getBasePrice());
                        System.out.println("Standard Room: " + String.format("%.2f", standardPrice));
                        System.out.println("Deluxe Room: " + String.format("%.2f", deluxePrice));
                        System.out.println("Executive Room: " + String.format("%.2f", executivePrice));

                        System.out.print("\nPress enter to continue...");
                        scanner.nextLine();
                        System.out.print("\033\143");
                        success = true;
                        break;
                    case 2:
                        success = true;
                        System.out.print("\nPress enter to continue...");
                        scanner.nextLine();
                        System.out.print("\033\143");
                        break;
                }
            }while (!success);
        }
        // Hotel has a reservation for that room
        else {
            System.out.println("\nHotel has reservation!");
            System.out.print("Press enter to continue...");
            scanner.nextLine();
            System.out.print("\033\143");
        }
    }

    /**
     * Sets reservation of a room for the hotel.
     *
     * @param index The index of the hotel selected.
     * 
     */
    public void setHotelReservation(int index){
        String guestName;
        int checkInDate, checkOutDate, option, check, vouchOption, vouchType;
        boolean success = false, checkOut;
        String roomType, voucher = "";
        float totalPrice;

        System.out.print("\033\143");
        System.out.println("Making a reservation at " + hotel.get(index).getHotelName());

        do {
            System.out.print("Enter guest name: ");
            guestName = scanner.nextLine();

            do{
                System.out.print("Enter check in date: ");
                checkInDate = checkValidInt();
                if (checkInDate > 30 || checkInDate < 1){
                    System.out.println("\nCheck in date must be from 1 to 30 only");
                }
            } while (checkInDate > 30 || checkInDate < 1);

            do {
                System.out.print("Enter check out date: ");
                checkOutDate = checkValidInt();
                checkOut = !(checkInDate > checkOutDate || checkOutDate > 31 || checkOutDate < 2 || checkInDate == checkOutDate);
                if (!checkOut) {
                    System.out.println("\nCheck out date must be within the month (" + (checkInDate + 1) + " to 31 only)");
                    System.out.println("and must be after check in date");
                }
            } while (!checkOut);

            do{
                System.out.println("\nSelect room type ");
                System.out.println("[1] Standard Room");
                System.out.println("[2] Deluxe Room");
                System.out.println("[3] Executive Room");
                System.out.print("Enter selection: ");
                option = checkValidInt();
            } while (option < 1 || option > 3);

            roomType = switch (option) {
                case 1 -> "Standard Room";
                case 2 -> "Deluxe Room";
                case 3 -> "Executive Room";
                default -> throw new IllegalStateException("Unexpected value: " + option);
            };

            // Ask for Voucher
            do{ 
                System.out.println("\nDo you have a voucher?");
                System.out.println("1 - Yes");
                System.out.println("2 - No");
                System.out.print("Select Input: ");
                vouchOption = checkValidInt();
                do {
                    if (vouchOption == 1) {
                        System.out.print("Voucher Code: ");

                        // Check Voucher
                        voucher = scanner.nextLine(); 
                        if (hotel.get(index).checkVoucher(voucher, checkInDate, checkOutDate) != 0) {
                            vouchType = hotel.get(index).checkVoucher(voucher, checkInDate, checkOutDate);
                            totalPrice = hotel.get(index).computeTotal(vouchType, checkInDate, checkOutDate, option);
                            vouchOption = 2;
                        }

                        // Voucher is Invalid
                        else { 
                            System.out.println("\nVoucher cannot be applied!");
                            System.out.println("Do you want to try another voucher?");
                            System.out.println("1 - Yes");
                            System.out.println("2 - No");
                            System.out.print("Select Input: ");
                            vouchOption = checkValidInt();
                            totalPrice = hotel.get(index).computeTotal(0, checkInDate, checkOutDate, option);
                        }
                    }
                    else
                        totalPrice = hotel.get(index).computeTotal(0, checkInDate, checkOutDate, option);

                } while (vouchOption == 1);
            } while (vouchOption != 2);

            // Summary
            do{
                System.out.println("\n-- Reservation Details --");
                System.out.println("Guest name: "+guestName);
                System.out.println("Check in date: "+checkInDate);
                System.out.println("Check out date: "+checkOutDate);
                System.out.println("Type of room: " + roomType);
                System.out.println("Voucher Applied: " + voucher);
                System.out.println("Total Price: " + String.format("%.2f", totalPrice));

                System.out.println("\n1 - Continue Reservation");
                System.out.println("2 - Cancel Reservation");
                System.out.print("Select Input: ");
                check = checkValidInt();

                // continue setting reservation
                if (check == 1){ 
                    success = hotel.get(index).setReservation(guestName, checkInDate, checkOutDate, option, totalPrice);
                    if (success) {
                        System.out.println("\nSuccessfully saved reservation!");
                        System.out.print("Press enter to continue...");
                        scanner.nextLine();
                        check = 2; // terminate loop
                        System.out.print("\033\143");
                    }

                    // Date is fully booked
                    else {
                        System.out.println("\nNo available rooms on selected dates...\n");
                        do {
                            System.out.println("Do you want to set another reservation?");
                            System.out.println("1 - Yes");
                            System.out.println("2 - Go back to main menu");
                            System.out.print("Select Input: ");
                            check = checkValidInt();
                        } while (check != 1 && check != 2);

                        if (check == 2) {
                            System.out.print("\033\143");
                            return;
                        }
                        System.out.println();
                        break;
                    }
                }
                else if (check == 2) {
                    success = true; // terminate outermost loop
                    System.out.print("\033\143");

                }
            } while (check != 2);
        } while(!success);
    }

    /**
     * Removes a reservation of the selected hotel.
     * 
     * @param index The index of the hotel selected.
     * 
     */
    public void removeHotelReservation(int index){
        hotel.get(index).removeReservation(scanner);
    }

    /**
     * Removes the selected hotel.
     * 
     * @param index The index of the hotel selected.
     * @return value in looping Driver class.
     * 
     */
    public int removeHotel (int index) {
        int choice = -1;
        String temp = hotel.get(index).getHotelName();

            if (hotel.get(index).isReservationEmpty()){
                do {
                    System.out.print("\033\143");
                    System.out.println("\nDo you want to remove " + temp + "?");
                    System.out.println("1 - Yes");
                    System.out.println("2 - No");
                    System.out.print("Select Input: ");

                    // Check if the input value is an integer
                    if(scanner.hasNextInt())
                        choice = checkValidInt();
                    else
                        scanner.nextLine();

                    if (choice == 1) {
                        hotel.remove(index);
                        System.out.println("\nSuccessfully removed " + temp);
                        return 13; // Main Menu
                    }

                } while (choice < 1 || choice > 2);
            }
            else 
                // Hotel cannot be removed because there are reservations
                System.out.println(temp + " has reservations");

        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
        return 0;
    }

    /**
     * Displays Hotel information.
     *
     * @param index The index of the hotel selected.
     * 
     */
    public void viewHotel(int index){
        System.out.print("\033\143");
        hotel.get(index).viewHotelInfo();
        System.out.print("\nPress enter to exit...");
        scanner.nextLine();
    }

    /**
     * Displays Room information.
     *
     * @param index The index of the hotel selected.
     * 
     */
    public void viewRoomInformation(int index){
        int roomNum;
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter room number: ");
            roomNum = checkValidInt();

            for (Room room : hotel.get(index).getRooms()) {
                if (room.getRoomNum() == roomNum) {
                    valid = true;

                    // Display room information
                    hotel.get(index).viewRoomInfo(roomNum); 
                    System.out.print("\nPress enter to continue...");
                    scanner.nextLine();
                    break;
                }
            }

            if (!valid) {
                System.out.println("Room not found!\n");
            }
        }

    }

    /**
     * Checks room availability of the hotel.
     *
     * @param index The index of the hotel selected.
     * 
     */
    public void checkRoomAvailability(int index){
        int date, full = 0, numRoom = hotel.get(index).getRooms().size();

            do {
                System.out.print("Enter date to check availability: ");
                date = checkValidInt();
            } while (date < 1 || date > 31);

            // Iterates through the rooms to check the available rooms
            for(Room room : hotel.get(index).getRooms()){
                for(Reservation reservation : room.getReservations()){
                    if(date >= reservation.getCheckInDate() && date < reservation.getCheckOutDate()){
                        full++;
                    }
                }
            }

            System.out.println("\nNumber of available rooms: " + (numRoom - full));
            System.out.println("Number of booked rooms: " + full);

            System.out.print("\nPress enter to continue...");
            scanner.nextLine();
    }

    /**
     * Checks the reservation details of the Hotel.
     * 
     * @param index The index of the hotel selected.
     * 
     */
    public void checkReservationInfo(int index){
       int choice;

       if (hotel.get(index).getReservations().isEmpty()){
           System.out.println("\nNo reservations found");
        }

       else {
           do {
               System.out.print("\033\143");
               hotel.get(index).viewRoomReservation();
               System.out.print("\nEnter reservation to check: ");
               choice = checkValidInt() - 1;
           } while (choice > hotel.get(index).getReservations().size() - 1 || choice < 0);
           hotel.get(index).viewReservationInfo(choice);
       }

        System.out.print("Press enter to continue...");
        scanner.nextLine();
    }

    /**
     * Modify price for the selected date
     * 
     * @param index The index of the Hotel
     * 
     */
    public void datePriceMod(int index){
        ArrayList<ModifyDate> dateList = hotel.get(index).getDates();
        int dateOption, option;
        float oldPercent = 0.0f, newPercent = 0.0f;

        hotel.get(index).displayDayRate();
        do {
            System.out.print("\nEnter date to modify: ");
            dateOption = checkValidInt();
        } while (dateOption < 1 || dateOption > 31);

        for(ModifyDate list : dateList){
            if (list.getDay() == dateOption)
                oldPercent = list.getPricePercent();
        }

        System.out.print("\033\143");
        System.out.println("Modifying day " + dateOption);
        System.out.println("Previous rate: " + (oldPercent*100) +"%");

        // Ensure correct rate
        do {
            try {
                System.out.println("\nYou can only modify the rate from 50% to 150%");
                System.out.print("Enter new rate (Do not include % symbol) \n[e.g. 100% = 100, 50% = 50, 23.3% = 23.3]: ");
                if (scanner.hasNextFloat()) {
                    newPercent = scanner.nextFloat();
                } else {
                    System.out.println("Invalid input. Enter a valid float value.");
                    scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Error! Enter a float value");
                scanner.nextLine();
            }
        } while (newPercent < 50.0f || newPercent > 150.0f);


        // Modify Rate
        do{
            System.out.println("\nModifying rate on day " + dateOption);
            System.out.println("from " + (oldPercent*100) + "% to " + newPercent +"%");
            System.out.println("[1] CONTINUE (This is irreversible!)");
            System.out.println("[2] CANCEL MODIFICATION");
            System.out.print("Enter your choice: ");
            option = checkValidInt();

            if(option == 1) {
                hotel.get(index).modifyRate(dateOption, newPercent / 100);
                System.out.println("Successfully changed rate on Day " + dateOption + " from " + (oldPercent*100) + "% to " + newPercent +"%");
                System.out.print("Press enter to continue...");
                scanner.nextLine();
                option = 2;
            }
        } while (option != 2);
    }

    /**
     * Display the rate for the selected dates
     * 
     * @param index The index of the hotel
     * 
     */
    public void displayRate(int index){
        hotel.get(index).displayDayRate();
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    /**
     * Gets the specified Hotel
     * 
     * @param index The index of the hotel
     * @return the specified hotel
     * 
     */
    public Hotel getHotel(int index){
        return hotel.get(index);
    }
    
    /**
     * Gets the Hotel list.
     * 
     * @return hotel list
     * 
     */
    public ArrayList<Hotel> getHotels(){
        return hotel;
    }

    /**
     * Gets the Hotel Names.
     * 
     * @return name of the hotels
     * 
     */
    public ArrayList<String> getHotelNames(){
        ArrayList<String> names = new ArrayList<>();
        for(Hotel hotel : hotel){
            names.add(hotel.getHotelName());
        }
        return names;
    }

    /**
     * GUI Create Hotel.
     * 
     * @param standard The number of standard rooms
     * @param deluxe The number of deluxe rooms
     * @param executive The number of executive rooms
     * @param name The name of the hotel
     * 
     */
    public void createHotel(int standard, int deluxe, int executive, String name){
        hotel.add(new Hotel(name, executive, deluxe, standard));
    }

    /**
     * GUI Change name of the Hotel
     * 
     * @param index The index of the hotel
     * @param newName The new name for the hotel
     * @return The status of name change 
     */
    public boolean changeHotelNameGui(int index, String newName){
        String oldName = hotel.get(index).getHotelName();
        for(Hotel hotel : hotel){
            oldName = hotel.getHotelName().toLowerCase();
            newName = newName.toLowerCase();
            if (oldName.equals(newName)) {
                return false;
            }
        }
        hotel.get(index).setHotelName(newName);
        return true;
    }

    /**
     * GUI Remove hotel
     * 
     * @param index The index of the hotel
     * @return the status of hotel removal
     * 
     */
    public boolean removeHotelgui (int index){
        if(hotel.get(index).isReservationEmpty()) {
            hotel.remove(index);
            return true;
        }
        return false;
    }

    /**
     * GUI Room availability checker.
     * 
     * @param index The index of the hotel
     * @param date The date selected
     * @return available rooms
     * 
     */
    public int checkRoomAvailabilityGUI(int index, int date){
        int full = 0, numRoom = hotel.get(index).getRooms().size();

        // Iterates through the rooms to check the available rooms on the selected date
        for(Room room : hotel.get(index).getRooms()){
            for(Reservation reservation : room.getReservations()){
                if(date >= reservation.getCheckInDate() && date < reservation.getCheckOutDate()){
                    full++;
                }
            }
        }
        return (numRoom - full);
    }
    
}
