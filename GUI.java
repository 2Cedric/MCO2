import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 
 * This class serves as the GUI.
 *
 *  <p>
 * Authors: Olivares, Maverick C.
 *          Ong, Cedric Clifford L.
 * </p>
 * 
 */
public class GUI {
    private JPanel homeScreenPanel, reserveRoomPanel, modifyHotelPanel, panelCenter, reserveSubmitButtonPanel,
                    newHotelPanel, roomInfoPanel, addRoomsPanel, dateModifierPanel, successfulReservationPanel,
                    checkReservationPanel;
    private JFrame frame, hotelFrame, successfulReservationFrame, createHotelFrame, roomInfoFrame, addRoomsFrame,
                    dateModifierFrame, checkReservationFrame;
    private JLabel homeMenuTitle, modifyHotelMenuTitle, nameLbl, earningsLbl, numRoomLbl, numStandardLbl, numDeluxeLbl, numExecutiveLbl,
                    guestNameLbl, checkInLbl, checkOutLbl, vouchLbl, roomTypeLbl, totalPriceLbl, statusLbl, newHotelLbl,
                    newStandardRoomLbl, newDeluxeRoomLbl, newExecutiveRoomLbl, daysEmptyLbl, daysFullLbl, roomNumLbl, roomPriceLbl,
                    selectDateLbl, existingRatesLbl, standardRateLbl, deluxeRateLbl, executiveRateLbl;
    private JButton reserveButton, addHotelButton, submitReserveButton, manageHotelButton, manageFirstBackButton, manageFirstSubmitButton,
                    newHotelButton, submitAddRoom, changeRateButton, deleteReservationButton;
    private JComboBox<String> hotelListDropDown, hotelOptionsDropdown, typesOfRoomDropdown, reservationListDropdown;
    private JComboBox<Integer> checkInDatesDropdown, checkOutDatesDropdown, newStandardRoomDropdown, newDeluxeRoomDropdown, newExecutiveRoomDropdown,
                                datesDropdown;
    private JTextField guestNameInput, voucherInput, newHotelName, numOfRoomsInput;
    private JTextArea checkReservationArea;

    public GUI() {
        this.frame = new JFrame();
        this.hotelFrame = new JFrame();
        this.successfulReservationFrame = new JFrame();


        //Initialization of panels
        this.homeScreenPanel = new JPanel(new GridLayout(3, 1));
        this.reserveRoomPanel = new JPanel(new BorderLayout());
        this.modifyHotelPanel = new JPanel(new GridLayout(6, 1));
        // for making a reservation
        this.panelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        this.reserveSubmitButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.successfulReservationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Main menu
        homeMenuTitle = new JLabel("HOTEL MANAGEMENT SYSTEM");
        homeMenuTitle.setFont(new Font("Arial", Font.BOLD, 25));
        homeMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
        modifyHotelMenuTitle = new JLabel("CUSTOMIZE HOTEL");

        // Create buttons for modification
        this.reserveButton = new JButton("CREATE RESERVATION");
        this.manageHotelButton = new JButton("MANAGE HOTELS");
        this.submitReserveButton = new JButton("CONFIRM BOOKING");
        this.addHotelButton = new JButton("ADD NEW HOTEL");

        // Initialize other buttons
        manageFirstBackButton = new JButton("BACK TO MAIN MENU");
        manageFirstSubmitButton = new JButton("CONFIRM");

        //Creating a new hotel
        this.createHotelFrame = new JFrame("Creating New Hotel");
        this.newHotelPanel = new JPanel();
        newHotelName = new JTextField("");
        newHotelLbl = new JLabel("Hotel Name: ");
        newStandardRoomLbl = new JLabel("No. of Standard Rooms: ");
        newDeluxeRoomLbl = new JLabel("No. of Deluxe Rooms: ");
        newExecutiveRoomLbl = new JLabel("No. of Executive Rooms: ");
        newHotelButton = new JButton("Create Hotel");
        newStandardRoomDropdown = new JComboBox<>();
        newDeluxeRoomDropdown = new JComboBox<>();
        newExecutiveRoomDropdown = new JComboBox<>();

        // View Hotel information
        nameLbl = new JLabel("Hotel");
        earningsLbl = new JLabel("Current earnings is: " );
        numRoomLbl = new JLabel("Total number of rooms: " );
        numStandardLbl = new JLabel("Total number of Standard rooms: " );
        numDeluxeLbl = new JLabel("Total number of Deluxe rooms: " );
        numExecutiveLbl = new JLabel("Total number of Executive rooms: " );

        // Add rooms
        addRoomsFrame = new JFrame();
        addRoomsPanel = new JPanel();
        submitAddRoom = new JButton();
        numOfRoomsInput = new JTextField();

        // Modify/View Rates
        dateModifierFrame = new JFrame();
        dateModifierPanel = new JPanel();
        selectDateLbl = new JLabel();
        existingRatesLbl = new JLabel();
        standardRateLbl = new JLabel();
        deluxeRateLbl = new JLabel();
        executiveRateLbl = new JLabel();
        changeRateButton = new JButton();

        // Reservations
        checkInDatesDropdown = new JComboBox<>();
        checkOutDatesDropdown = new JComboBox<>();
        guestNameInput = new JTextField("");
        voucherInput = new JTextField("");
        guestNameLbl = new JLabel("Guest Name: ");
        checkInLbl = new JLabel("Check-in Date: ");
        checkOutLbl = new JLabel("Check-out Date: ");
        vouchLbl = new JLabel("Voucher Code: ");
        roomTypeLbl = new JLabel("Room Type: ");
        totalPriceLbl = new JLabel("Total Price: ");
        statusLbl = new JLabel();

        //Initialize Hotel Management System Options
        String[] actions = {"CHANGE HOTEL NAME", "ADD ROOMS", "REMOVE ROOMS", "CHECK ROOM AVAILABILITY",
                "VIEW ROOM INFORMATION", "UPDATE BASE PRICE", "MODIFY PRICE RATE",
                "CHECK RESERVATIONS", "VIEW HOTEL INFORMATION", "REMOVE HOTEL"};
        String[] roomTypes = {"Standard Room", "Deluxe Room", "Executive Room"};
        hotelListDropDown = new JComboBox<>(actions);
        hotelOptionsDropdown = new JComboBox<>(actions);
        typesOfRoomDropdown = new JComboBox<>(roomTypes);

        //Room information
        roomNumLbl = new JLabel();
        daysEmptyLbl = new JLabel();
        daysFullLbl = new JLabel();
        roomPriceLbl = new JLabel();
        roomInfoFrame = new JFrame();
        roomInfoPanel = new JPanel();

        // Check reservation
        checkReservationFrame = new JFrame();
        checkReservationPanel = new JPanel();
        reservationListDropdown = new JComboBox<>();
        checkReservationArea = new JTextArea();
        deleteReservationButton = new JButton();

        Integer[] dates = new Integer[31];
        for (int i = 1; i <= 31; i++)
            dates[i-1] = i;

        datesDropdown = new JComboBox<>(dates);

        initHomePanel();

        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setSize(600, 350);
        frame.setTitle("Hotel Reservation System Simulation");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Reservation submit button action listener
     * 
     * @param submitReserveButtonListener
     * 
     */
    public void setSubmitReserveButtonListener(ActionListener submitReserveButtonListener){
        this.submitReserveButton.addActionListener(submitReserveButtonListener);
    }

    /**
     * Action listener
     * 
     * @param reserveButtonListener
     * 
     */
    public void setReserveButtonListener(ActionListener reserveButtonListener){
        this.reserveButton.addActionListener(reserveButtonListener);
    }

    /**
     * Action setter listener
     * 
     * @param manageHotelButtonListener manage hotel button
     * 
     */
    public void setManageHotelButtonListener(ActionListener manageHotelButtonListener) {
        manageHotelButton.addActionListener(manageHotelButtonListener);
    }

    /**
     * Back button listener
     * 
     * @param manageFirstBackButtonListener
     * 
     */
    public void setManageFirstBackButtonListener(ActionListener manageFirstBackButtonListener) {
        manageFirstBackButton.addActionListener(manageFirstBackButtonListener);
    }

    /**
     * Manage hotel (1st button) Listener
     * 
     * @param manageFirstSubmitButtonListener
     * 
     */
    public void setManageFirstSubmitButtonListener(ActionListener manageFirstSubmitButtonListener) {
        manageFirstSubmitButton.addActionListener(manageFirstSubmitButtonListener);
    }

    /**
     * Add hotel listener
     * 
     * @param listener
     * 
     */
    public void setAddHotelButtonListener(ActionListener listener){
        this.addHotelButton.addActionListener(listener);
    }

    /**
     * Create a hotel button
     * 
     * @param listener
     * 
     */
    public void setCreateHotelButtonListener(ActionListener listener){
        this.newHotelButton.addActionListener(listener);
    }

    /**
     * Submit add room listener
     * 
     * @param listener
     * 
     */
    public void setSubmitAddRoomListener(ActionListener listener){
        this.submitAddRoom.addActionListener(listener);
    }

    /**
     * Change rate listener
     */
    public void setChangeRateButton(ActionListener listener){
        this.changeRateButton.addActionListener(listener);
    }

    public void setDatesDropdownListener(ActionListener listener){
        this.datesDropdown.addActionListener(listener);
    }

    public void setDeleteReservationButtonListener (ActionListener listener){
        this.deleteReservationButton.addActionListener(listener);
    }

    public void setReservationListDropdownListener(ActionListener listener){
        this.reservationListDropdown.addActionListener(listener);
    }

    // Add Home Panel Menu
    public void initHomePanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // top, left, bottom, right
        gbc.anchor = GridBagConstraints.CENTER;
        homeScreenPanel.add(homeMenuTitle, gbc);

        gbc.gridy = 1;
        homeScreenPanel.add(reserveButton, gbc);

        gbc.gridy = 2;
        homeScreenPanel.add(manageHotelButton, gbc);

        frame.add(homeScreenPanel);
    }

    /**
     * Initialize the panel for displaying the high level information
     * 
     * @param hotelName
     * @param earnings
     * @param numOfRooms
     * @param numStandard
     * @param numDeluxe
     * @param numExecutive
     * 
     */
    public void initViewHotelPanel(String hotelName, float earnings, int numOfRooms, int numStandard,
                                       int numDeluxe, int numExecutive){
        hotelFrame.setLayout(new GridLayout(6, 1));
        hotelFrame.setVisible(true);
        hotelFrame.setSize(300, 350);
        hotelFrame.setTitle(hotelName);
        hotelFrame.setResizable(true);
        hotelFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        nameLbl.setText(hotelName);
        earningsLbl.setText("Earnings is now: " + earnings);
        numRoomLbl.setText("Total number of rooms: " + numOfRooms);
        numStandardLbl.setText("Total number of Standard rooms: " + numStandard);
        numDeluxeLbl.setText("Total number of Deluxe rooms: " + numDeluxe);
        numExecutiveLbl.setText("Total number of Executive rooms: " + numExecutive);

        hotelFrame.add(nameLbl);
        hotelFrame.add(earningsLbl);
        hotelFrame.add(numRoomLbl);
        hotelFrame.add(numStandardLbl);
        hotelFrame.add(numDeluxeLbl);
        hotelFrame.add(numExecutiveLbl);

        hotelFrame.revalidate();
        hotelFrame.repaint();
    }

    /**
     * Display modifications after home screen
     */
    public void modifyHotelPanelAfterHome(){
        frame.getContentPane().removeAll(); 
        modifyHotelMenuTitle.setFont(new Font("Arial", Font.BOLD, 25));
        modifyHotelMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);

        modifyHotelPanel.add(modifyHotelMenuTitle);
        modifyHotelPanel.add(hotelListDropDown);
        modifyHotelPanel.add(hotelOptionsDropdown);
        modifyHotelPanel.add(manageFirstSubmitButton);
        modifyHotelPanel.add(addHotelButton);
        modifyHotelPanel.add(manageFirstBackButton);
        modifyHotelPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

        frame.add(modifyHotelPanel); 
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Panel for making a room reservation
     */
    public void setReservationPanel() {
        // Clear existing components
        frame.getContentPane().removeAll();

        // Panel setup
        panelCenter.setLayout(new GridLayout(0, 2, 5, 5));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding around the panel

        // Configure component sizes
        guestNameInput.setPreferredSize(new Dimension(150, 30));
        checkInDatesDropdown.setPreferredSize(new Dimension(150, 30));
        checkOutDatesDropdown.setPreferredSize(new Dimension(150, 30));
        voucherInput.setPreferredSize(new Dimension(150, 30));

        initializeDateDropdowns();
        nameLbl.setText("Hotel: ");
        guestNameInput.setText("");
        voucherInput.setText("");

        // Add components to the center panel
        panelCenter.add(nameLbl);
        panelCenter.add(hotelListDropDown);
        panelCenter.add(roomTypeLbl);
        panelCenter.add(typesOfRoomDropdown);
        panelCenter.add(guestNameLbl);
        panelCenter.add(guestNameInput);
        panelCenter.add(checkInLbl);
        panelCenter.add(checkInDatesDropdown);
        panelCenter.add(checkOutLbl);
        panelCenter.add(checkOutDatesDropdown);
        panelCenter.add(vouchLbl);
        panelCenter.add(voucherInput);
        panelCenter.add(totalPriceLbl);

        // Modify submit button
        submitReserveButton.setBackground(Color.decode("#66FF00"));
        submitReserveButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        submitReserveButton.setHorizontalAlignment(SwingConstants.CENTER);
        submitReserveButton.setPreferredSize(new Dimension(200, 30));
        reserveSubmitButtonPanel.add(submitReserveButton);

        // Set up the reserveRoomPanel
        reserveRoomPanel.setLayout(new BorderLayout());
        reserveRoomPanel.setBackground(Color.YELLOW);
        reserveRoomPanel.add(panelCenter, BorderLayout.CENTER);
        reserveRoomPanel.add(reserveSubmitButtonPanel, BorderLayout.EAST);
        reserveRoomPanel.add(manageFirstBackButton, BorderLayout.SOUTH);

        // Add the reserveRoomPanel to the frame
        frame.add(reserveRoomPanel);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Creates the panel when adding a hotel
     */
    public void addHotelPanel() {
        newStandardRoomDropdown.removeAllItems();
        newDeluxeRoomDropdown.removeAllItems();
        newExecutiveRoomDropdown.removeAllItems();
        newHotelPanel.removeAll();

        for (int i = 0; i <= 50; i++) {
            newStandardRoomDropdown.addItem(i);
            newDeluxeRoomDropdown.addItem(i);
            newExecutiveRoomDropdown.addItem(i);
        }

        createHotelFrame.setLayout(new BorderLayout());
        createHotelFrame.setResizable(false);
        createHotelFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        createHotelFrame.setPreferredSize(new Dimension(400, 200));
        createHotelFrame.setSize(500, 500);  

        newHotelPanel.setPreferredSize(new Dimension(400, 200));
        newHotelPanel.setLayout(new GridLayout(5, 2, 3, 3));

        newHotelName.setText("");
        newHotelPanel.add(newHotelLbl);
        newHotelPanel.add(newHotelName);
        newHotelPanel.add(newStandardRoomLbl);
        newHotelPanel.add(newStandardRoomDropdown);
        newHotelPanel.add(newDeluxeRoomLbl);
        newHotelPanel.add(newDeluxeRoomDropdown);
        newHotelPanel.add(newExecutiveRoomLbl);
        newHotelPanel.add(newExecutiveRoomDropdown);
        newHotelPanel.add(newHotelButton);

        createHotelFrame.add(newHotelPanel, BorderLayout.CENTER);
        createHotelFrame.pack(); 
        createHotelFrame.setLocationRelativeTo(null); 
        createHotelFrame.setVisible(true); 
    }

    public void initializeDateDropdowns() {
        // Clear existing items
        checkInDatesDropdown.removeAllItems();
        checkOutDatesDropdown.removeAllItems();

        // Populate checkInDatesDropdown 
        for (int i = 1; i <= 30; i++) {
            checkInDatesDropdown.addItem(i);
        }

        // Populate checkOutDatesDropdown 
        for (int j = 2; j <= 31; j++) {
            checkOutDatesDropdown.addItem(j);
        }
    }

    public void updateCheckOutDatesDropdown() {
        Integer checkInDate = (Integer) checkInDatesDropdown.getSelectedItem();

        // Clear existing items
        checkOutDatesDropdown.removeAllItems();

        if (checkInDate != null) {
            // Populate checkOutDatesDropdown
            for (int i = checkInDate + 1; i <= 31; i++) {
                checkOutDatesDropdown.addItem(i);
            }
        }
    }

        /**
         * Return Main Menu
         */
    public void returnToMenu(){
        frame.getContentPane().removeAll();
        frame.add(homeScreenPanel);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Reservation is successful
     */
    public void successfulReservation(String status){
        successfulReservationPanel.setBackground(Color.decode("#90EE90"));
        successfulReservationPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        successfulReservationFrame.setPreferredSize(new Dimension(150, 30));

        statusLbl.setHorizontalAlignment(SwingConstants.CENTER);
        statusLbl.setText(status);

        successfulReservationPanel.add(statusLbl);
        successfulReservationFrame.add(successfulReservationPanel);

        successfulReservationFrame.setVisible(true);
        successfulReservationFrame.setSize(300, 80);
        successfulReservationFrame.setTitle("Status");
        successfulReservationFrame.setResizable(false);
        successfulReservationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public int inputRoomToCheck() {
        int input = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter room number (Standard starting at 101, Deluxe starting at 201, & Executive starting at 301):"));
        int roomNum = -1;
        try {
            roomNum = Integer.parseInt(String.valueOf(input));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
        }
        return roomNum;
    }

    // Method to display room information
    public void checkRoomInformation(int roomNum, int daysAvail, int daysOccupied, float price) {
        // Clear existing contents
        roomInfoFrame.getContentPane().removeAll(); 

        // Set size of the frame
        roomInfoFrame.setSize(150, 200); 
        roomInfoFrame.setResizable(false);
        roomInfoFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        roomInfoPanel.setLayout(new GridLayout(4, 1, 3, 3));
        roomInfoPanel.setBackground(Color.white);

        // change background color
        roomNumLbl.setOpaque(true); 
        roomNumLbl.setBackground(Color.yellow);
        roomNumLbl.setHorizontalAlignment(SwingConstants.CENTER);

        roomNumLbl.setText("Room " + roomNum);
        roomPriceLbl.setText("Room rate is: ₱" + price);
        daysEmptyLbl.setText("Days Available: " + daysAvail);
        daysFullLbl.setText("Days Occupied: " + daysOccupied);

        roomInfoPanel.add(roomNumLbl);
        roomInfoPanel.add(roomPriceLbl);
        roomInfoPanel.add(daysEmptyLbl);
        roomInfoPanel.add(daysFullLbl);

        // Add the panel to the frame
        roomInfoFrame.add(roomInfoPanel); 

        // Make the frame visible
        roomInfoFrame.setVisible(true); 
    }

    // Initialize panel when modifying date
    public void dateModifierPanel(){
        dateModifierFrame.getContentPane().removeAll();
        dateModifierPanel.removeAll();
        dateModifierFrame.setResizable(false);
        dateModifierFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dateModifierFrame.setTitle("Date Rate Modifier");
        dateModifierFrame.setSize(new Dimension(300, 250));

        dateModifierPanel.setBackground(Color.decode("#FFA500"));
        dateModifierPanel.setLayout(new GridLayout(7, 1));
        dateModifierPanel.setPreferredSize(new Dimension(280, 230));

        selectDateLbl.setText("Choose from dates below");
        selectDateLbl.setFont(new Font("Arial", Font.BOLD, 20));
        selectDateLbl.setHorizontalAlignment(SwingConstants.CENTER);
        existingRatesLbl.setText("Current rate: " );
        existingRatesLbl.setFont(new Font("Arial", Font.PLAIN, 15));
        standardRateLbl.setText("Standard Room price: ");
        standardRateLbl.setFont(new Font("Arial", Font.PLAIN, 15));
        deluxeRateLbl.setText("Deluxe Room price: ");
        deluxeRateLbl.setFont(new Font("Arial", Font.PLAIN, 15));
        executiveRateLbl.setText("Executive Room price: ");
        executiveRateLbl.setFont(new Font("Arial", Font.PLAIN, 15));
        changeRateButton.setText("Update Price Rate");
        changeRateButton.setFont(new Font("Aharoni", Font.BOLD, 16));

        dateModifierPanel.add(selectDateLbl);
        dateModifierPanel.add(datesDropdown);
        dateModifierPanel.add(existingRatesLbl);
        dateModifierPanel.add(standardRateLbl);
        dateModifierPanel.add(deluxeRateLbl);
        dateModifierPanel.add(executiveRateLbl);
        dateModifierPanel.add(changeRateButton);

        dateModifierFrame.add(dateModifierPanel);
        dateModifierFrame.setVisible(true);
    }

    // Update panel based from selected date
    public void ratesOnDayView(float standard, float deluxe, float executive, float rate){
        rate *= 100;
        existingRatesLbl.setText(String.format("Current Price Rate: %.2f%% ", rate));
        standardRateLbl.setText(String.format("Standard room price: %.2f", standard));
        deluxeRateLbl.setText(String.format("Deluxe room price: %.2f", deluxe));
        executiveRateLbl.setText(String.format("Executive room price: %.2f", executive));
    }

    // Remove Hotel
    public int removeHotel(String hotelName){
        return JOptionPane.showOptionDialog(
                null,
                "Do you want to delete " + hotelName + "?",
                "Hotel Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE, null,
                // Custom options
                new Object[]{"Yes", "No"}, 
                // Default option
                "No" 
        );
    }

    // Add Rooms
    public void addRooms(int currNum){
        addRoomsFrame.getContentPane().removeAll();
        addRoomsFrame.setVisible(true);
        addRoomsFrame.setResizable(false);
        addRoomsFrame.setTitle("Adding rooms");
        addRoomsFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addRoomsFrame.setSize(new Dimension(300, 130));

        addRoomsPanel.setBackground(Color.decode("#0000FF"));
        addRoomsPanel.setLayout(new GridLayout(3, 1, 5, 5));

        numOfRoomsInput.setText("At most " + (50-currNum) + " rooms only");
        submitAddRoom.setText("ADD ROOMS");

        addRoomsPanel.add(typesOfRoomDropdown);
        addRoomsPanel.add(numOfRoomsInput);
        addRoomsPanel.add(submitAddRoom);

        addRoomsFrame.add(addRoomsPanel);
    }

    // Hotel List drop down
    public void setHotelListDropDown(String[] hotels){
        hotelListDropDown.removeAllItems();
        for(String hotelName : hotels){
            hotelListDropDown.addItem(hotelName);
        }
    }

    // View Reservations
    public void viewReservations(ArrayList<Reservation> reservations, String name){
        checkReservationFrame.getContentPane().removeAll();
        checkReservationFrame.setSize(350, 250);
        checkReservationFrame.setResizable(false);
        checkReservationFrame.setVisible(true);
        checkReservationFrame.setTitle("Reservations at " + name);

        checkReservationPanel.setLayout(new BorderLayout());
        checkReservationPanel.setPreferredSize(new Dimension(220,120));
        checkReservationPanel.setBackground(Color.decode("#FFCCEE"));

        reservationListDropdown.removeAllItems();
        for(Reservation reservation: reservations)
            reservationListDropdown.addItem(reservation.getGuestName());

        checkReservationArea.setText(reservations.get((0)).toString());
        deleteReservationButton.setText("Delete Reservation");

        checkReservationPanel.add(reservationListDropdown, BorderLayout.NORTH);
        checkReservationPanel.add(checkReservationArea, BorderLayout.CENTER);
        checkReservationPanel.add(deleteReservationButton, BorderLayout.SOUTH);

        checkReservationFrame.add(checkReservationPanel);
    }

    // View Reservation
    public void showReservation(String details){
        checkReservationArea.setText(details);
        checkReservationArea.setFont(new Font("Arial", Font.BOLD, 25));
    }

    public void closeReservationFrame(){
        checkReservationFrame.setVisible(false);
    }

    public JComboBox<String> getHotelListDropDown() {
        return hotelListDropDown;
    }

    public JComboBox<String> getHotelOptionsDropdown() {
        return hotelOptionsDropdown;
    }

    public JComboBox<String> getTypesOfRoomDropdown(){
        return typesOfRoomDropdown;
    }

    public JComboBox<String> getReservationListDropdown(){
        return reservationListDropdown;
    }

    public JComboBox<Integer> getCheckInDatesDropdown(){
        return checkInDatesDropdown;
    }

    public JComboBox<Integer> getCheckOutDatesDropdown(){
        return checkOutDatesDropdown;
    }

    public JComboBox<Integer> getNewStdRoomDropdown(){
        return newStandardRoomDropdown;
    }

    public JComboBox<Integer> getNewDlxRoomDropdown(){
        return newDeluxeRoomDropdown;
    }

    public JComboBox<Integer> getNewExeRoomDropdown(){
        return newExecutiveRoomDropdown;
    }

    public JComboBox<Integer> getDatesDropdown(){
        return datesDropdown;
    }

    public int getCheckIn() {
        Object date = getCheckInDatesDropdown().getSelectedItem();
        if (date instanceof Integer)
            return (Integer) date;
        return -1;
    }

    public int getCheckOut() {
        Object date = getCheckOutDatesDropdown().getSelectedItem();
        if (date instanceof Integer)
            return (Integer) date;
        return -1;
    }

    public int getStandardRooms(){
        Object std = getNewStdRoomDropdown().getSelectedItem();
        if(std instanceof Integer)
            return (Integer) std;
        return -1;
    }

    public int getDeluxeRooms(){
        Object std = getNewDlxRoomDropdown().getSelectedItem();
        if(std instanceof Integer)
            return (Integer) std;
        return -1;
    }

    public int getExecutiveRooms(){
        Object std = getNewExeRoomDropdown().getSelectedItem();
        if(std instanceof Integer)
            return (Integer) std;
        return -1;
    }

    public JTextField getGuestNameInput(){
        return guestNameInput;
    }

    public JTextField getVoucherInput(){
        return voucherInput;
    }

    public JTextField getNewHotelName(){
        return newHotelName;
    }

    public JTextField getNumOfRoomsInput(){
        return numOfRoomsInput;
    }


}
