# Task 02 – Hospital Outpatient Appointment Desk (8 Marks)

## 2.1 Event model

| Source | Event object | Listener | Action |
|---|---|---|---|
| Department JComboBox | ItemEvent | ItemListener | Load doctors for selected department |
| Doctor JComboBox | ItemEvent | ItemListener | Update selected doctor |
| Time-slot JComboBox | ItemEvent | ItemListener | Update selected slot |
| Book JButton | ActionEvent | ActionListener | Validate and book |
| Patient ID JTextField | ActionEvent | ActionListener | Optional Enter-to-book/validate |
| Window | WindowEvent | WindowListener / WindowAdapter | Confirm close if booking is in progress |

## 2.2 Component hierarchy
```text
JFrame AppointmentDesk
└── JPanel (BorderLayout)
    ├── JPanel form (GridLayout)
    │   ├── JLabel + JTextField patientId
    │   ├── JLabel + JComboBox department
    │   ├── JLabel + JComboBox doctor
    │   └── JLabel + JComboBox timeSlot
    ├── JPanel actions (FlowLayout)
    │   └── JButton Book
    └── JLabel status (SOUTH)
```

## 2.3 Handler pseudocode (SIMATS-style)

**Refresh doctors**
```text
BEGIN RefreshDoctors
    department ← selected department
    doctorCombo.removeAllItems()
    doctorList ← doctorMap.get(department)
    FOR EACH doctor IN doctorList
        doctorCombo.addItem(doctor)
    END FOR
    IF doctorList is empty THEN
        status ← "No doctors available"
    END IF
END

**Book appointment**
```text
BEGIN BookAppointment
    IF patientId is empty THEN DISPLAY error; RETURN
    IF department, doctor or timeSlot is not selected THEN DISPLAY error; RETURN
    SET bookingInProgress ← true
    TRY
        validate patient and slot
        save appointment through service/repository
        DISPLAY confirmation
    CATCH validation or database error
        DISPLAY useful error
    FINALLY
        SET bookingInProgress ← false
    END TRY
END
```

## 2.4 Event trace
Construct frame → create components and listeners → load department/doctor data → set visible → user selects department → ItemEvent → ItemListener refreshes doctor list → user clicks Book → ActionEvent → validate → service/repository saves → confirmation dialog/status → user closes → windowClosing event → if bookingInProgress, ask confirmation → if confirmed dispose → windowClosed event.

Applet comparison: an applet uses container callbacks `init()`, `start()`, `paint()`, `stop()`, and `destroy()`; a desktop JFrame is created by its constructor/main method and responds to explicit AWT/Swing events. JFrame does not use the applet lifecycle.

## Alternative: query database each department change
- Cached Map: build once in O(d + m) time and O(d + m) memory; subsequent department lookup is O(1) average plus O(number of doctors in selected department) to populate the combo.
- Query-on-change: each change incurs database round-trip and query cost, approximately O(log m + r) with an indexed department column, where r is returned doctors; cache memory can be O(1) beyond displayed rows. Actual latency is dominated by network/database access.
