import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 
 * This class serves as the controller for the GUI
 *
 *  <p>
 * Authors: Olivares, Maverick C.
 *          Ong, Cedric Clifford L.
 * </p>
 * 
 */
public class Controller {
    private HotelManagementSystem hotelManager;
    private GUI gui;

    public Controller(GUI gui, HotelManagementSystem hotelManager) {
        this.hotelManager = hotelManager;
        this.gui = gui;

        this.gui.setReserveButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setHotelListDropDown(getHotelNames());
                gui.setReservationPanel();
            }
        });

        // Submit button listener when making a reservation
        this.gui.setSubmitReserveButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guestName, voucher;
                int checkInDate, checkOutDate, index, roomType, vouchValid;
                float total;
                boolean success = false;

                // get the index and input of selected hotel
                index = gui.getHotelListDropDown().getSelectedIndex();
                checkInDate = gui.getCheckIn();
                checkOutDate = gui.getCheckOut();
                guestName = gui.getGuestNameInput().getText();
                voucher = gui.getVoucherInput().getText();
                roomType = gui.getTypesOfRoomDropdown().getSelectedIndex() + 1;

                // No voucher inputted
                if (voucher.isEmpty() && !guestName.isEmpty()) { 
                    total = hotelManager.getHotel(index).computeTotal(0, checkInDate,checkOutDate,roomType);
                    success = hotelManager.getHotel(index).setReservation(guestName, checkInDate, checkOutDate, roomType, total);
                }

                // Check VALIDITY of the Voucher
                else if (!guestName.isEmpty()) { 
                    if(!voucher.equals("I_WORK_HERE") && !voucher.equals("STAY4_GET1") && !voucher.equals("PAYDAY")) {
                        int choice = JOptionPane.showConfirmDialog(null, "Voucher cannot be used! Do you want to use another voucher?", "Voucher Invalid",
                                JOptionPane.YES_NO_OPTION);

                        vouchValid = hotelManager.getHotel(index).checkVoucher(voucher, checkInDate, checkOutDate);
                        if(choice == 0) {
                            if (vouchValid == 0) {
                                JOptionPane.showMessageDialog(null, "Voucher cannot be used!");
                                return;
                            }
                        }
                        else {
                            total = hotelManager.getHotel(index).computeTotal(vouchValid, checkInDate,checkOutDate, roomType);
                            success = hotelManager.getHotel(index).setReservation(guestName, checkInDate, checkOutDate, roomType, total);
                        }
                    } else{
                        vouchValid = hotelManager.getHotel(index).checkVoucher(voucher, checkInDate, checkOutDate);
                        total = hotelManager.getHotel(index).computeTotal(vouchValid, checkInDate,checkOutDate, roomType);
                        success = hotelManager.getHotel(index).setReservation(guestName, checkInDate, checkOutDate, roomType, total);
                    }
                }

                if (success) {
                    JOptionPane.showMessageDialog(null, "Successfully saved reservation!");
                    gui.returnToMenu();
                }
                else
                    JOptionPane.showMessageDialog(null, "Reservation failed!");
            }
        });

        //Action listener for adding hotel
        this.gui.setAddHotelButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.addHotelPanel();
            }
        });

        //Action listener for creating a new hotel
        this.gui.setCreateHotelButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalRooms, standard, deluxe, executive;
                String hotelName;
                boolean nameIsUnique = true;
                ArrayList<Hotel> hotels = hotelManager.getHotels();

                standard = gui.getStandardRooms();
                deluxe = gui.getDeluxeRooms();
                executive = gui.getExecutiveRooms();
                totalRooms = standard+deluxe+executive;
                hotelName = gui.getNewHotelName().getText();
                for(Hotel hotel: hotels){
                    if(hotel.getHotelName().equalsIgnoreCase(hotelName)) {
                        nameIsUnique = false;
                        break;
                    }
                }

                if(totalRooms <= 50 && !hotelName.isEmpty() && nameIsUnique){
                    hotelManager.createHotel(standard, deluxe, executive, hotelName);
                    JOptionPane.showMessageDialog(null, "Successfully added " + hotelName);
                    gui.setHotelListDropDown(getHotelNames());
                    gui.modifyHotelPanelAfterHome();
                }
                else
                    JOptionPane.showMessageDialog(null, "Hotel can only have at most 50 rooms", "Room limit reached",
                            JOptionPane.ERROR_MESSAGE);
            }
        });

        // Set up the ActionListener for check-in date dropdown
        this.gui.getCheckInDatesDropdown().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.updateCheckOutDatesDropdown();
            }
        });

        // Action listener when customizing hotel
        this.gui.setManageHotelButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                gui.setHotelListDropDown(getHotelNames());
                gui.modifyHotelPanelAfterHome();
            }
        });

        // Action listener for Back button
        this.gui.setManageFirstBackButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                gui.returnToMenu();
            }
        });

        // Action listener for submit button (Customizing Hotel)
        this.gui.setManageFirstSubmitButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int hotelIndex, actionIndex, roomNum;
                hotelIndex = gui.getHotelListDropDown().getSelectedIndex();
                actionIndex = gui.getHotelOptionsDropdown().getSelectedIndex();

                if(hotelIndex >= 0) {
                    switch (actionIndex) {
                        // Change hotel name
                        case 0: 
                            String prev = hotelManager.getHotelName(hotelIndex);
                            String newName = JOptionPane.showInputDialog("Enter new hotel name");
                            int choice = JOptionPane.showConfirmDialog(null, "Do you want to change " + prev +
                                    " to " + newName + "?");
                            boolean doesExist = false;
                            if (choice == 0) {
                                doesExist = hotelManager.changeHotelNameGui(hotelIndex, newName);
                                // Change name status
                                if (doesExist) 
                                    JOptionPane.showMessageDialog(null, "Successfully changed name!");
                                else
                                    JOptionPane.showMessageDialog(null, "Name must be unique!", "Duplicate!", JOptionPane.ERROR_MESSAGE);
                            }
                            // Update hotel list
                            gui.setHotelListDropDown(getHotelNames()); 
                            gui.modifyHotelPanelAfterHome();
                            break;
                        
                        // Add hotel rooms
                        case 1: 
                            gui.addRooms(hotelManager.getHotel(hotelIndex).getRooms().size());
                            break;

                        // Remove rooms
                        case 2: 
                            roomNum = Integer.parseInt(JOptionPane.showInputDialog(null, "Input room number to be removed (Standard starting at 101, Deluxe starting at 201, & Executive starting at 301)"));
                            int result = hotelManager.getHotel(hotelIndex).removeRoom(roomNum);
                            if (result == -1)
                                JOptionPane.showMessageDialog(null, "Room does not exist", "Room removal failed", JOptionPane.INFORMATION_MESSAGE);
                            else if (result == 0)
                                JOptionPane.showMessageDialog(null, "Room is occupied", "Room removal failed", JOptionPane.ERROR_MESSAGE);
                            else
                                JOptionPane.showMessageDialog(null, "Successfully removed Room " + roomNum);
                            break;

                        // Check room availability
                        case 3:  
                            roomNum = Integer.parseInt(JOptionPane.showInputDialog(null, "Input date to be checked"));
                            if (roomNum > 31 || roomNum < 1)
                                JOptionPane.showMessageDialog(null, "Date must be between 1 to 31 only", "Date Invalid", JOptionPane.ERROR_MESSAGE);
                            else { 
                                actionIndex = hotelManager.checkRoomAvailabilityGUI(hotelIndex, roomNum);
                                JOptionPane.showMessageDialog(null, actionIndex + " rooms available on Day " + roomNum);
                            }
                            break;

                        // View Room Information
                        case 4: 
                            int daysOccupied = 0;
                            roomNum = gui.inputRoomToCheck();
                            float price;
                            if (hotelManager.getHotel(hotelIndex).findRoom(roomNum)) {
                                price = hotelManager.getHotel(hotelIndex).getRoomPrice(roomNum);
                                daysOccupied = hotelManager.getHotel(hotelIndex).daysFull(roomNum);
                                gui.checkRoomInformation(roomNum, 31 - daysOccupied, daysOccupied, price);
                            } else
                                JOptionPane.showMessageDialog(null, "Room not found.");
                            break;

                        // Set Base Price
                        case 5: 
                            float curr = hotelManager.getHotel(hotelIndex).getBasePrice();
                            float newPrice = Float.parseFloat(JOptionPane.showInputDialog(null, "Enter new base rate (Current is " + curr));
                            if (newPrice < 100.0f)
                                JOptionPane.showMessageDialog(null, "Base price must be > 100.0", "Price Rate Invalid", JOptionPane.ERROR_MESSAGE);
                            else {
                                JOptionPane.showMessageDialog(null, "Successfully changed base rate to " + newPrice);
                                hotelManager.getHotel(hotelIndex).setBasePrice(newPrice);
                            }
                            break;

                        // Date Price Modifier
                        case 6: 
                            gui.dateModifierPanel();
                            break;

                        // Check Reservations
                        case 7: 
                            if (!hotelManager.getHotel(hotelIndex).isReservationEmpty())
                                gui.viewReservations(hotelManager.getHotel(hotelIndex).getReservations(), hotelManager.getHotelName(hotelIndex));
                            else
                                JOptionPane.showMessageDialog(null, "No reservations found");
                            break;

                        // View Hotel Information
                        case 8: 
                            gui.initViewHotelPanel(hotelManager.getHotel(hotelIndex).getHotelName(), hotelManager.getHotel(hotelIndex).getHotelEarnings(),
                                    hotelManager.getHotel(hotelIndex).getRooms().size(), hotelManager.getHotel(hotelIndex).numStandard(),
                                    hotelManager.getHotel(hotelIndex).numDeluxe(), hotelManager.getHotel(hotelIndex).numExecutive());
                            break;

                        // Remove Hotel
                        case 9: 
                            int option = gui.removeHotel(hotelManager.getHotelName(hotelIndex));
                            boolean success = false;
                            if (option == 0) {
                                success = hotelManager.removeHotelgui(hotelIndex);
                                // Hotel was not deleted
                                if (!success) 
                                    JOptionPane.showMessageDialog(null, "Hotel has reservations!", "Operation Failed!",
                                            JOptionPane.ERROR_MESSAGE);
                            }
                            // Update hotel list
                            gui.setHotelListDropDown(getHotelNames()); 
                            gui.modifyHotelPanelAfterHome();
                            break;
                        default:
                            gui.modifyHotelPanelAfterHome();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Please add hotel");
            }
        });

        this.gui.setChangeRateButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float newBasePrice;
                int date = gui.getDatesDropdown().getSelectedIndex() + 1;
                int hotelIndex = gui.getHotelListDropDown().getSelectedIndex();
                float standard, deluxe, executive, rate;
                int day;

                try {
                    String input = JOptionPane.showInputDialog(null, "Enter new base rate (e.g. 100, 75, 50)");

                    if (input != null && !input.trim().isEmpty()) {
                        newBasePrice = Float.parseFloat(input);

                        // Check if the new base price is within the desired range
                        if (newBasePrice >= 50 && newBasePrice <= 150) {

                            // Convert percentage to a decimal fraction
                            newBasePrice /= 100; 
                            hotelManager.getHotel(hotelIndex).modifyRate(date, newBasePrice);
                            JOptionPane.showMessageDialog(null, String.format("Successfully changed base rate to %.2f%%", newBasePrice*100));

                            hotelIndex = gui.getHotelListDropDown().getSelectedIndex();

                            day = gui.getDatesDropdown().getSelectedIndex() + 1;
                            rate = hotelManager.getHotel(hotelIndex).getRate(day);
                            standard = hotelManager.getHotel(hotelIndex).getBasePrice() * rate;
                            deluxe = standard * 1.2f;
                            executive = standard * 1.35f;
                            gui.dateModifierPanel();
                            gui.ratesOnDayView(standard, deluxe, executive, rate);
                        } else {
                            throw new NumberFormatException("Base rate must be between 50 and 150");
                        }
                    } else {
                        throw new NumberFormatException("Empty or null input");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input: " + ex.getMessage(), "Invalid Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /**
         * Listener for adding a room
         */
        this.gui.setSubmitAddRoomListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int roomsAdded, roomType, hotelIndex;
                boolean success;

                hotelIndex = gui.getHotelListDropDown().getSelectedIndex();
                roomType = gui.getTypesOfRoomDropdown().getSelectedIndex() + 1;
                roomsAdded = Integer.parseInt(gui.getNumOfRoomsInput().getText());

                success = hotelManager.getHotel(hotelIndex).addRoom(roomsAdded, roomType);

                if(success)
                    JOptionPane.showMessageDialog(null, "Successfully added rooms!");
                else
                    JOptionPane.showMessageDialog(null, "Hotel rooms should only be <= 50", "Add Room Failed",
                                                JOptionPane.ERROR_MESSAGE);
            }
        });

        this.gui.setDatesDropdownListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float standard, deluxe, executive, rate;
                int hotelIndex, day;
                hotelIndex = gui.getHotelListDropDown().getSelectedIndex();

                day = gui.getDatesDropdown().getSelectedIndex() + 1;
                rate = hotelManager.getHotel(hotelIndex).getRate(day);
                standard = hotelManager.getHotel(hotelIndex).getBasePrice() * rate;
                deluxe = standard * 1.2f;
                executive = standard * 1.35f;

                gui.ratesOnDayView(standard, deluxe, executive, rate);
            }
        });

        this.gui.setReservationListDropdownListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = gui.getHotelListDropDown().getSelectedIndex();
                int reserveIndex = gui.getReservationListDropdown().getSelectedIndex();
                if(reserveIndex >= 0)
                    gui.showReservation(hotelManager.getHotel(index).getReservations().get(reserveIndex).toString());
            }
        });

        this.gui.setDeleteReservationButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hotelIndex = gui.getHotelListDropDown().getSelectedIndex(),
                    reserveIndex = gui.getReservationListDropdown().getSelectedIndex();
                String guestName = hotelManager.getHotel(hotelIndex).getReservations().get(reserveIndex).getGuestName();

                int choice1 = JOptionPane.showConfirmDialog(null, "Do you want to delete reservation for " + guestName + "?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (choice1 == 0) {
                    hotelManager.getHotel(hotelIndex).removeReservationGui(reserveIndex);
                    JOptionPane.showMessageDialog(null, "Successfully remove reservation for " + guestName);
                    if(!hotelManager.getHotel(hotelIndex).isReservationEmpty())
                        gui.viewReservations(hotelManager.getHotel(hotelIndex).getReservations(), hotelManager.getHotelName(hotelIndex));
                    // No reservation found update view
                    else { 
                        JOptionPane.showMessageDialog(null, "No reservations found");
                        gui.closeReservationFrame();
                    }
                }
            }
        });
    }

    /**
     * Gets the hotel names in hotel manager
     * @return
     */
    public String[] getHotelNames(){
        String[] hotelNames = new String[hotelManager.getHotels().size()];
        for (int i = 0; i < hotelManager.getHotels().size(); i++) {
            hotelNames[i] = hotelManager.getHotelName(i);
        }
        return hotelNames;
    }


}
