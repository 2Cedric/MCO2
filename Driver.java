import java.util.Scanner;

/**
 * This class serves as the entry point for the Hotel Reservation System.
 * It provides a menu-driven interface for users to create, view, manage hotels,
 * simulate bookings, and exit the application.
 * 
 * <p>
 * Authors: Olivares, Maverick C.
 *          Ong, Cedric Clifford L.
 * </p>
 * 
 * Date Modified: July 30, 2024
 * 
 */
public class Driver {

    /**
     *
     * The main method that starts the Hotel Reservation System.
     * It initializes the system and displays a menu for user interaction.
     * 
     * @param args Sequence of string arguments.
     */
    public static void main(String[] args) {

        int check, manage, hotel, reservation, index, mode;
        Scanner scanner = new Scanner(System.in);
        HotelManagementSystem hotelmng = new HotelManagementSystem(scanner);

        System.out.println("Welcome to the Hotel Management System");
        System.out.println("1 - CLI Mode");
        System.out.println("2 - GUI Mode");
        System.out.print("Select Mode: ");
        mode = hotelmng.checkValidInt();

        if (mode == 2) {
            GUI gui = new GUI();
            Controller control = new Controller(gui, hotelmng);
            return;  // Exit the main method to let the GUI handle the interaction
        }

        // Continue with CLI mode if mode is 1
        do {
            System.out.println("\n*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*");
            System.out.println("/*/*/                           */*/*");
            System.out.println("/*/*/  HOTEL MANAGEMENT SYSTEM  */*/*");
            System.out.println("/*/*/                           */*/*");
            System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*\n");

            System.out.println("1 - Create Reservation");
            System.out.println("2 - Manage Hotels");
            System.out.println("3 - Exit");
            System.out.print("\nSelect Input: ");

            check = hotelmng.checkValidInt();

            // Set reservation
            if (check == 1) {
                if (hotelmng.hotelEmpty()) {
                    System.out.print("\033\143");
                    System.out.println("There are no existing hotels in the system");
                } else {
                    System.out.print("\033\143");
                    System.out.println("Select a Hotel");
                    hotelmng.displayHotels();
                    System.out.println();
                    reservation = hotelmng.getHotelIndex();
                    hotelmng.setHotelReservation(reservation);
                }
            }

            // Manage hotel
            else if (check == 2) {
                System.out.print("\033\143");

                do {
                    System.out.println("\nExisting Hotels: ");
                    hotelmng.displayHotels();

                    System.out.println("\n1 - Add Hotel");
                    System.out.println("2 - View Hotel");
                    System.out.println("3 - Main Menu");
                    System.out.print("\nSelect Input: ");
                    manage = hotelmng.checkValidInt();

                    // Create hotel
                    if (manage == 1) {
                        System.out.print("\033\143");
                        hotelmng.createHotel();
                    }

                    // Manage Hotels
                    if (manage == 2) {
                        // hotel list is not empty
                        if (!hotelmng.hotelEmpty()) {
                            index = hotelmng.getHotelIndex();
                            do {
                                System.out.print("\033\143");
                                System.out.println(hotelmng.getHotelName(index));

                                System.out.println("\n1 - Change Hotel Name");
                                System.out.println("2 - Add Room");
                                System.out.println("3 - Remove Room");
                                System.out.println("4 - Check Room Availability");
                                System.out.println("5 - View Room Information");
                                System.out.println("6 - Update Base Price");
                                System.out.println("7 - Modify Price Rate");
                                System.out.println("8 - View Rate Per Night");
                                System.out.println("9 - Check Reservation");
                                System.out.println("10 - Remove Reservation");
                                System.out.println("11 - View Hotel Information");
                                System.out.println("12 - Remove Hotel");
                                System.out.println("13 - Back");
                                System.out.print("\nSelect Input: ");

                                hotel = hotelmng.checkValidInt();

                                switch (hotel) {
                                    case 1:
                                        hotelmng.changeHotelName(index);
                                        break;
                                    case 2:
                                        hotelmng.addHotelRooms(index);
                                        break;
                                    case 3:
                                        hotelmng.removeHotelRooms(index);
                                        break;
                                    case 4:
                                        hotelmng.checkRoomAvailability(index);
                                        break;
                                    case 5:
                                        hotelmng.viewRoomInformation(index);
                                        break;
                                    case 6:
                                        hotelmng.setHotelBasePrice(index);
                                        break;
                                    case 7:
                                        hotelmng.datePriceMod(index);
                                        break;
                                    case 8:
                                        hotelmng.displayRate(index);
                                        break;
                                    case 9:
                                        hotelmng.checkReservationInfo(index);
                                        break;
                                    case 10:
                                        hotelmng.removeHotelReservation(index);
                                        break;
                                    case 11:
                                        hotelmng.viewHotel(index);
                                        break;
                                    case 12:
                                        hotel = hotelmng.removeHotel(index);
                                        break;
                                    case 13:
                                        break;
                                    default:
                                        System.out.println("Invalid selection");
                                }
                            } while (hotel != 13);
                        } else {
                            System.out.println("\nNo hotels found!");
                            System.out.print("\033\143");
                        }
                    }
                    System.out.print("\033\143");

                } while (manage != 3);

                System.out.print("\033\143");
            }

            // Input value is not between 1 and 3
            else if (check > 3 || check < 1) {
                System.out.println("-- Enter a Valid Option --");
                System.out.print("\033\143");
            }

        } while (check != 3); // Exit loop

        scanner.close();
    }
}
