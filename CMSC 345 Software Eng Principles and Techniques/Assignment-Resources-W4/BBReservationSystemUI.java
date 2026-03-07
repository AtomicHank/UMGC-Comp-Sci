import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Small B&B Reservation System - User Interface Implementation
 * CMSC 345 - Week 4 Assignment
 *
 * This Java Swing application implements the UI mockups for the
 * Bed & Breakfast reservation system used by John and Jane.
 */
public class BBReservationSystemUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Color scheme
    private static final Color PRIMARY_COLOR = new Color(70, 130, 180);
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color ALERT_COLOR = new Color(255, 193, 7);

    public BBReservationSystemUI() {
        setTitle("Small B&B Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add all screens
        mainPanel.add(createDashboardPanel(), "Dashboard");
        mainPanel.add(createSelectDatesPanel(), "SelectDates");
        mainPanel.add(createSelectRoomPanel(), "SelectRoom");
        mainPanel.add(createGuestPaymentPanel(), "GuestPayment");
        mainPanel.add(createConfirmationPanel(), "Confirmation");
        mainPanel.add(createManageGuestsPanel(), "ManageGuests");
        mainPanel.add(createCalendarViewPanel(), "CalendarView");

        add(mainPanel);
        cardLayout.show(mainPanel, "Dashboard");
    }

    // ==================== SCREEN 1: DASHBOARD ====================
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_COLOR);
        JLabel welcomeLabel = new JLabel("Welcome, John");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JLabel dateLabel = new JLabel("Today: " + LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, yyyy")));
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        headerPanel.add(welcomeLabel, BorderLayout.WEST);
        headerPanel.add(dateLabel, BorderLayout.EAST);

        // Quick Actions
        JPanel actionsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        actionsPanel.setBackground(BACKGROUND_COLOR);
        actionsPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR), "QUICK ACTIONS",
            TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14)));

        JButton newResButton = createActionButton("NEW RESERVATION", "Click to create a new reservation");
        JButton manageGuestsButton = createActionButton("MANAGE GUESTS", "View and edit guest records");
        JButton manageRoomsButton = createActionButton("VIEW CALENDAR", "View reservation calendar");

        newResButton.addActionListener(e -> cardLayout.show(mainPanel, "SelectDates"));
        manageGuestsButton.addActionListener(e -> cardLayout.show(mainPanel, "ManageGuests"));
        manageRoomsButton.addActionListener(e -> cardLayout.show(mainPanel, "CalendarView"));

        actionsPanel.add(newResButton);
        actionsPanel.add(manageGuestsButton);
        actionsPanel.add(manageRoomsButton);

        // Today's Activity
        JPanel activityPanel = new JPanel(new GridLayout(2, 2, 20, 10));
        activityPanel.setBackground(Color.WHITE);
        activityPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR), "TODAY'S ACTIVITY",
            TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14)));

        activityPanel.add(createStatLabel("Check-ins Today:", "2"));
        activityPanel.add(createStatLabel("Check-outs Today:", "1"));
        activityPanel.add(createStatLabel("Rooms Available:", "2"));
        activityPanel.add(createStatLabel("Pending Guarantees:", "3"));

        // Alerts
        JPanel alertsPanel = new JPanel(new BorderLayout());
        alertsPanel.setBackground(Color.WHITE);
        alertsPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(ALERT_COLOR), "ALERTS",
            TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14)));

        DefaultListModel<String> alertModel = new DefaultListModel<>();
        alertModel.addElement("⚠ Reservation #1042 - Guarantee deadline tomorrow (Smith, J.)");
        alertModel.addElement("⚠ Reservation #1039 - Guarantee deadline today (Johnson, M.)");
        JList<String> alertList = new JList<>(alertModel);
        alertList.setBackground(new Color(255, 250, 230));
        alertsPanel.add(new JScrollPane(alertList), BorderLayout.CENTER);

        // Layout
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setBackground(BACKGROUND_COLOR);
        centerPanel.add(actionsPanel);
        centerPanel.add(activityPanel);
        centerPanel.add(alertsPanel);

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    // ==================== SCREEN 2: SELECT DATES ====================
    private JPanel createSelectDatesPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header with back button
        JPanel headerPanel = createHeaderWithBack("NEW RESERVATION - Step 1 of 3", "Select Dates", "Dashboard");

        // Date selection
        JPanel datePanel = new JPanel(new GridLayout(3, 1, 10, 10));
        datePanel.setBackground(Color.WHITE);
        datePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel checkInPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkInPanel.setBackground(Color.WHITE);
        checkInPanel.add(new JLabel("Check-in Date:    "));
        checkInPanel.add(new JComboBox<>(new String[]{"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"}));
        checkInPanel.add(new JLabel(" / "));
        checkInPanel.add(new JComboBox<>(new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"}));
        checkInPanel.add(new JLabel(" / "));
        checkInPanel.add(new JComboBox<>(new String[]{"2026","2027"}));

        JPanel checkOutPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkOutPanel.setBackground(Color.WHITE);
        checkOutPanel.add(new JLabel("Check-out Date:  "));
        checkOutPanel.add(new JComboBox<>(new String[]{"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"}));
        checkOutPanel.add(new JLabel(" / "));
        checkOutPanel.add(new JComboBox<>(new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"}));
        checkOutPanel.add(new JLabel(" / "));
        checkOutPanel.add(new JComboBox<>(new String[]{"2026","2027"}));

        JPanel nightsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nightsPanel.setBackground(Color.WHITE);
        nightsPanel.add(new JLabel("Number of Nights: 3"));

        datePanel.add(checkInPanel);
        datePanel.add(checkOutPanel);
        datePanel.add(nightsPanel);

        // Mini Calendar
        JPanel calendarPanel = createMiniCalendar();

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        JButton cancelBtn = new JButton("Cancel");
        JButton checkAvailBtn = new JButton("Check Availability");
        checkAvailBtn.setBackground(PRIMARY_COLOR);
        checkAvailBtn.setForeground(Color.WHITE);

        cancelBtn.addActionListener(e -> cardLayout.show(mainPanel, "Dashboard"));
        checkAvailBtn.addActionListener(e -> cardLayout.show(mainPanel, "SelectRoom"));

        buttonPanel.add(cancelBtn);
        buttonPanel.add(checkAvailBtn);

        // Layout
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(BACKGROUND_COLOR);
        centerPanel.add(datePanel, BorderLayout.NORTH);
        centerPanel.add(calendarPanel, BorderLayout.CENTER);

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // ==================== SCREEN 3: SELECT ROOM ====================
    private JPanel createSelectRoomPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JPanel headerPanel = createHeaderWithBack("NEW RESERVATION - Step 2 of 3", "Select Room", "SelectDates");

        JLabel datesLabel = new JLabel("Dates: Feb 15 - Feb 18, 2026 (3 nights)");
        datesLabel.setFont(new Font("Arial", Font.ITALIC, 12));

        // Room options
        JPanel roomsPanel = new JPanel();
        roomsPanel.setLayout(new BoxLayout(roomsPanel, BoxLayout.Y_AXIS));
        roomsPanel.setBackground(BACKGROUND_COLOR);
        roomsPanel.setBorder(BorderFactory.createTitledBorder("AVAILABLE ROOMS"));

        ButtonGroup roomGroup = new ButtonGroup();

        roomsPanel.add(createRoomOption(roomGroup, "ROOM 101 - Room with Private Bath",
            "Features: Private bathroom with tub and shower", "$150.00 / night", "$450.00", true, true));
        roomsPanel.add(Box.createVerticalStrut(10));
        roomsPanel.add(createRoomOption(roomGroup, "ROOM 102 - Room with Lake View",
            "Features: Beautiful lake view, 2nd floor", "$120.00 / night", "$360.00", true, false));
        roomsPanel.add(Box.createVerticalStrut(10));
        roomsPanel.add(createRoomOption(roomGroup, "ROOM 103 - Normal Room",
            "(Booked for selected dates)", "-", "-", false, false));
        roomsPanel.add(Box.createVerticalStrut(10));
        roomsPanel.add(createRoomOption(roomGroup, "ROOM 104 - Normal Room",
            "Features: Cozy standard room", "$90.00 / night", "$270.00", true, false));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        JButton backBtn = new JButton("Back");
        JButton continueBtn = new JButton("Continue");
        continueBtn.setBackground(PRIMARY_COLOR);
        continueBtn.setForeground(Color.WHITE);

        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "SelectDates"));
        continueBtn.addActionListener(e -> cardLayout.show(mainPanel, "GuestPayment"));

        buttonPanel.add(backBtn);
        buttonPanel.add(continueBtn);

        // Layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BACKGROUND_COLOR);
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(datesLabel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(roomsPanel), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // ==================== SCREEN 4: GUEST & PAYMENT ====================
    private JPanel createGuestPaymentPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JPanel headerPanel = createHeaderWithBack("NEW RESERVATION - Step 3 of 3", "Guest Information & Payment", "SelectRoom");

        // Main content - two columns
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        contentPanel.setBackground(BACKGROUND_COLOR);

        // Left column - Summary and Payment
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(BACKGROUND_COLOR);

        // Reservation Summary
        JPanel summaryPanel = new JPanel(new GridLayout(6, 1));
        summaryPanel.setBackground(Color.WHITE);
        summaryPanel.setBorder(BorderFactory.createTitledBorder("RESERVATION SUMMARY"));
        summaryPanel.add(new JLabel("  Room: 102 - Lake View"));
        summaryPanel.add(new JLabel("  Check-in:  Feb 15, 2026"));
        summaryPanel.add(new JLabel("  Check-out: Feb 18, 2026"));
        summaryPanel.add(new JLabel("  Nights: 3"));
        summaryPanel.add(new JLabel("  Rate: $120.00/night"));
        JLabel totalLabel = new JLabel("  TOTAL: $360.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        summaryPanel.add(totalLabel);

        // Payment options
        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));
        paymentPanel.setBackground(Color.WHITE);
        paymentPanel.setBorder(BorderFactory.createTitledBorder("GUARANTEE PAYMENT"));

        JRadioButton guaranteeNow = new JRadioButton("Guarantee now (1 night) - $120.00");
        JRadioButton guaranteeLater = new JRadioButton("Guarantee later");
        ButtonGroup payGroup = new ButtonGroup();
        payGroup.add(guaranteeNow);
        payGroup.add(guaranteeLater);
        guaranteeNow.setSelected(true);
        guaranteeNow.setBackground(Color.WHITE);
        guaranteeLater.setBackground(Color.WHITE);

        JPanel ccPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        ccPanel.setBackground(Color.WHITE);
        ccPanel.add(new JLabel("Card Number:"));
        ccPanel.add(new JTextField(15));
        ccPanel.add(new JLabel("Expiry (MM/YY):"));
        JPanel expiryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        expiryPanel.setBackground(Color.WHITE);
        expiryPanel.add(new JTextField(3));
        expiryPanel.add(new JLabel(" / "));
        expiryPanel.add(new JTextField(3));
        expiryPanel.add(new JLabel("  CVV: "));
        expiryPanel.add(new JTextField(4));
        ccPanel.add(expiryPanel);

        paymentPanel.add(guaranteeNow);
        paymentPanel.add(guaranteeLater);
        paymentPanel.add(Box.createVerticalStrut(10));
        paymentPanel.add(ccPanel);

        leftPanel.add(summaryPanel);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(paymentPanel);

        // Right column - Guest Information
        JPanel guestPanel = new JPanel(new GridLayout(8, 2, 5, 10));
        guestPanel.setBackground(Color.WHITE);
        guestPanel.setBorder(BorderFactory.createTitledBorder("GUEST INFORMATION"));

        guestPanel.add(new JLabel("Search Existing:"));
        JComboBox<String> searchCombo = new JComboBox<>(new String[]{"-- Select or enter new --", "Smith, John", "Johnson, Mary"});
        guestPanel.add(searchCombo);

        guestPanel.add(new JLabel("First Name:"));
        guestPanel.add(new JTextField());
        guestPanel.add(new JLabel("Last Name:"));
        guestPanel.add(new JTextField());
        guestPanel.add(new JLabel("Address:"));
        guestPanel.add(new JTextField());
        guestPanel.add(new JLabel("City:"));
        guestPanel.add(new JTextField());
        guestPanel.add(new JLabel("State:"));
        JPanel stateZipPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        stateZipPanel.setBackground(Color.WHITE);
        stateZipPanel.add(new JTextField(5));
        stateZipPanel.add(new JLabel("  Zip: "));
        stateZipPanel.add(new JTextField(7));
        guestPanel.add(stateZipPanel);
        guestPanel.add(new JLabel("Phone:"));
        guestPanel.add(new JTextField());
        guestPanel.add(new JLabel(""));
        JCheckBox saveGuest = new JCheckBox("Save as new guest");
        saveGuest.setBackground(Color.WHITE);
        guestPanel.add(saveGuest);

        contentPanel.add(leftPanel);
        contentPanel.add(guestPanel);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        JButton cancelBtn = new JButton("Cancel");
        JButton completeBtn = new JButton("Complete Booking");
        completeBtn.setBackground(new Color(40, 167, 69));
        completeBtn.setForeground(Color.WHITE);

        cancelBtn.addActionListener(e -> cardLayout.show(mainPanel, "Dashboard"));
        completeBtn.addActionListener(e -> cardLayout.show(mainPanel, "Confirmation"));

        buttonPanel.add(cancelBtn);
        buttonPanel.add(completeBtn);

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // ==================== SCREEN 5: CONFIRMATION ====================
    private JPanel createConfirmationPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Success message
        JPanel successPanel = new JPanel();
        successPanel.setLayout(new BoxLayout(successPanel, BoxLayout.Y_AXIS));
        successPanel.setBackground(BACKGROUND_COLOR);

        JLabel checkMark = new JLabel("✓", SwingConstants.CENTER);
        checkMark.setFont(new Font("Arial", Font.BOLD, 72));
        checkMark.setForeground(new Color(40, 167, 69));
        checkMark.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel successLabel = new JLabel("Booking Confirmed!");
        successLabel.setFont(new Font("Arial", Font.BOLD, 24));
        successLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        successPanel.add(Box.createVerticalStrut(20));
        successPanel.add(checkMark);
        successPanel.add(successLabel);
        successPanel.add(Box.createVerticalStrut(20));

        // Reservation details
        JPanel detailsPanel = new JPanel(new GridLayout(10, 2, 10, 5));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createTitledBorder("RESERVATION DETAILS"));

        detailsPanel.add(new JLabel("  Confirmation #:"));
        detailsPanel.add(new JLabel("BB-2026-1043"));
        detailsPanel.add(new JLabel("  Guest:"));
        detailsPanel.add(new JLabel("John Smith"));
        detailsPanel.add(new JLabel("  Room:"));
        detailsPanel.add(new JLabel("102 - Room with Lake View"));
        detailsPanel.add(new JLabel("  Check-in:"));
        detailsPanel.add(new JLabel("February 15, 2026"));
        detailsPanel.add(new JLabel("  Check-out:"));
        detailsPanel.add(new JLabel("February 18, 2026"));
        detailsPanel.add(new JLabel("  Nights:"));
        detailsPanel.add(new JLabel("3"));
        detailsPanel.add(new JLabel("  Total:"));
        detailsPanel.add(new JLabel("$360.00"));
        detailsPanel.add(new JLabel("  Guarantee:"));
        detailsPanel.add(new JLabel("$120.00 (Paid)"));
        detailsPanel.add(new JLabel("  Balance Due:"));
        detailsPanel.add(new JLabel("$240.00 (Due at check-in)"));
        detailsPanel.add(new JLabel("  Status:"));
        JLabel statusLabel = new JLabel("GUARANTEED");
        statusLabel.setForeground(new Color(40, 167, 69));
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        detailsPanel.add(statusLabel);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        JButton printBtn = new JButton("Print Confirmation");
        JButton newResBtn = new JButton("New Reservation");
        JButton dashboardBtn = new JButton("Dashboard");
        dashboardBtn.setBackground(PRIMARY_COLOR);
        dashboardBtn.setForeground(Color.WHITE);

        newResBtn.addActionListener(e -> cardLayout.show(mainPanel, "SelectDates"));
        dashboardBtn.addActionListener(e -> cardLayout.show(mainPanel, "Dashboard"));

        buttonPanel.add(printBtn);
        buttonPanel.add(newResBtn);
        buttonPanel.add(dashboardBtn);

        // Layout
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(BACKGROUND_COLOR);
        centerPanel.add(successPanel, BorderLayout.NORTH);
        centerPanel.add(detailsPanel, BorderLayout.CENTER);

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // ==================== SCREEN 6: MANAGE GUESTS ====================
    private JPanel createManageGuestsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JPanel headerPanel = createHeaderWithBack("MANAGE GUESTS", "", "Dashboard");

        // Search bar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(BACKGROUND_COLOR);
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(new JTextField(30));
        searchPanel.add(new JButton("Search"));
        searchPanel.add(Box.createHorizontalStrut(50));
        JButton addGuestBtn = new JButton("+ Add New Guest");
        addGuestBtn.setBackground(PRIMARY_COLOR);
        addGuestBtn.setForeground(Color.WHITE);
        searchPanel.add(addGuestBtn);

        // Guest table
        String[] columns = {"Name", "Phone", "City", "Reservations", "Action"};
        Object[][] data = {
            {"Smith, John", "(617) 555-1234", "Boston, MA", "3", "Edit"},
            {"Johnson, Mary", "(212) 555-5678", "New York, NY", "1", "Edit"},
            {"Williams, Bob", "(415) 555-9012", "San Francisco, CA", "2", "Edit"},
            {"Brown, Alice", "(303) 555-3456", "Denver, CO", "1", "Edit"},
            {"Davis, Carol", "(617) 555-7890", "Boston, MA", "5", "Edit"},
        };

        JTable guestTable = new JTable(data, columns);
        guestTable.setRowHeight(30);
        guestTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane tableScroll = new JScrollPane(guestTable);

        // Pagination
        JPanel pagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pagePanel.setBackground(BACKGROUND_COLOR);
        pagePanel.add(new JButton("<< Prev"));
        pagePanel.add(new JLabel("Page 1 of 3"));
        pagePanel.add(new JButton("Next >>"));

        // Layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BACKGROUND_COLOR);
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(tableScroll, BorderLayout.CENTER);
        panel.add(pagePanel, BorderLayout.SOUTH);

        return panel;
    }

    // ==================== SCREEN 7: CALENDAR VIEW ====================
    private JPanel createCalendarViewPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_COLOR);

        JButton backBtn = new JButton("< Back to Dashboard");
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setForeground(PRIMARY_COLOR);
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "Dashboard"));

        JLabel titleLabel = new JLabel("RESERVATIONS CALENDAR", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navPanel.setBackground(BACKGROUND_COLOR);
        navPanel.add(new JButton("<"));
        navPanel.add(new JLabel("February 2026"));
        navPanel.add(new JButton(">"));
        JButton newBtn = new JButton("+ New");
        newBtn.setBackground(PRIMARY_COLOR);
        newBtn.setForeground(Color.WHITE);
        newBtn.addActionListener(e -> cardLayout.show(mainPanel, "SelectDates"));
        navPanel.add(newBtn);

        headerPanel.add(backBtn, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(navPanel, BorderLayout.EAST);

        // Calendar grid
        String[] columns = {"Room", "Mon 2", "Tue 3", "Wed 4", "Thu 5", "Fri 6", "Sat 7", "Sun 8"};
        Object[][] data = {
            {"101 - Bath", "Johnson", "Johnson", "Johnson", "", "", "Davis", "Davis"},
            {"102 - View", "", "Smith", "Smith", "Smith", "Smith", "Smith", ""},
            {"103 - Normal", "", "", "", "", "Williams", "Williams", "Williams"},
            {"104 - Normal", "", "", "", "Brown", "Brown", "Brown", ""},
        };

        JTable calendarTable = new JTable(data, columns) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                if (col > 0) {
                    String value = (String) getValueAt(row, col);
                    if (!value.isEmpty()) {
                        if (value.equals("Johnson") || value.equals("Smith") || value.equals("Brown")) {
                            c.setBackground(new Color(200, 230, 200)); // Guaranteed - green
                        } else {
                            c.setBackground(new Color(255, 230, 200)); // Pending - orange
                        }
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                } else {
                    c.setBackground(new Color(240, 240, 240));
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                }
                return c;
            }
        };
        calendarTable.setRowHeight(50);
        calendarTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 11));
        JScrollPane tableScroll = new JScrollPane(calendarTable);

        // Legend
        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        legendPanel.setBackground(BACKGROUND_COLOR);
        legendPanel.add(new JLabel("Legend:  "));
        JLabel guaranteedLabel = new JLabel("  Guaranteed  ");
        guaranteedLabel.setOpaque(true);
        guaranteedLabel.setBackground(new Color(200, 230, 200));
        legendPanel.add(guaranteedLabel);
        JLabel pendingLabel = new JLabel("  Pending  ");
        pendingLabel.setOpaque(true);
        pendingLabel.setBackground(new Color(255, 230, 200));
        legendPanel.add(pendingLabel);
        JLabel availLabel = new JLabel("  Available  ");
        availLabel.setOpaque(true);
        availLabel.setBackground(Color.WHITE);
        availLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        legendPanel.add(availLabel);

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(tableScroll, BorderLayout.CENTER);
        panel.add(legendPanel, BorderLayout.SOUTH);

        return panel;
    }

    // ==================== HELPER METHODS ====================

    private JButton createActionButton(String title, String tooltip) {
        JButton button = new JButton("<html><center><br><b>" + title + "</b><br><br></center></html>");
        button.setToolTipText(tooltip);
        button.setPreferredSize(new Dimension(150, 100));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private JPanel createStatLabel(String label, String value) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.WHITE);
        panel.add(new JLabel(label));
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        valueLabel.setForeground(PRIMARY_COLOR);
        panel.add(valueLabel);
        return panel;
    }

    private JPanel createHeaderWithBack(String title, String subtitle, String backScreen) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_COLOR);

        JButton backBtn = new JButton("< Back");
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setForeground(PRIMARY_COLOR);
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, backScreen));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(BACKGROUND_COLOR);
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);
        if (!subtitle.isEmpty()) {
            JLabel subtitleLabel = new JLabel(subtitle);
            subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            titlePanel.add(subtitleLabel);
        }

        headerPanel.add(backBtn, BorderLayout.WEST);
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        headerPanel.add(Box.createHorizontalStrut(80), BorderLayout.EAST); // Balance

        return headerPanel;
    }

    private JPanel createMiniCalendar() {
        JPanel calendarPanel = new JPanel(new BorderLayout());
        calendarPanel.setBackground(Color.WHITE);
        calendarPanel.setBorder(BorderFactory.createTitledBorder("FEBRUARY 2026"));

        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        JPanel headerRow = new JPanel(new GridLayout(1, 7));
        for (String day : days) {
            JLabel label = new JLabel(day, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 11));
            headerRow.add(label);
        }

        JPanel daysGrid = new JPanel(new GridLayout(5, 7, 2, 2));
        daysGrid.setBackground(Color.WHITE);
        int[] dates = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,0,0,0,0,0,0,0};
        for (int i = 0; i < 35; i++) {
            JLabel dayLabel;
            if (dates[i] > 0) {
                dayLabel = new JLabel(String.valueOf(dates[i]), SwingConstants.CENTER);
                if (dates[i] == 3) {
                    dayLabel.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR, 2));
                } else if (dates[i] >= 15 && dates[i] <= 17) {
                    dayLabel.setOpaque(true);
                    dayLabel.setBackground(new Color(200, 220, 255));
                }
            } else {
                dayLabel = new JLabel("", SwingConstants.CENTER);
            }
            daysGrid.add(dayLabel);
        }

        calendarPanel.add(headerRow, BorderLayout.NORTH);
        calendarPanel.add(daysGrid, BorderLayout.CENTER);

        return calendarPanel;
    }

    private JPanel createRoomOption(ButtonGroup group, String roomName, String features,
                                     String pricePerNight, String total, boolean available, boolean selected) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBackground(available ? Color.WHITE : new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(available ? PRIMARY_COLOR : Color.GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JRadioButton radio = new JRadioButton(roomName);
        radio.setEnabled(available);
        radio.setSelected(selected);
        radio.setBackground(panel.getBackground());
        radio.setFont(new Font("Arial", Font.BOLD, 12));
        group.add(radio);

        JPanel detailsPanel = new JPanel(new GridLayout(3, 1));
        detailsPanel.setBackground(panel.getBackground());
        detailsPanel.add(new JLabel("     " + features));
        if (available) {
            detailsPanel.add(new JLabel("     Price: " + pricePerNight));
            detailsPanel.add(new JLabel("     Total: " + total));
        }

        JLabel statusLabel = new JLabel(available ? (selected ? "[SELECTED]" : "") : "[UNAVAILABLE]");
        statusLabel.setForeground(available ? new Color(40, 167, 69) : Color.GRAY);

        panel.add(radio, BorderLayout.NORTH);
        panel.add(detailsPanel, BorderLayout.CENTER);
        panel.add(statusLabel, BorderLayout.EAST);

        return panel;
    }

    // ==================== MAIN ====================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new BBReservationSystemUI().setVisible(true);
        });
    }
}
