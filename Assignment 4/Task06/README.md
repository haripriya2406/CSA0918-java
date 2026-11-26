# Task 06 – Campus Event Registration Desktop Application (13 Marks)

## Stage 1: users, screens, data and testable rules
**Users:** student (register), committee member (view/filter/export).  
**Screens:** registration form; committee registrations table with event filter; confirmation/error dialogs.  
**Data:** Student(registerNo, name, email), Event(eventId, title), Registration(registrationId, registerNo, eventId, registeredAt).  
**Rules:** required fields cannot be blank; email must have a valid basic format; event must be selected; one register number can register for a given event only once; successful registration is persisted; filter returns only selected event; export produces a CSV file.

## Stage 2: screen and event flow
```text
JFrame MainWindow + JMenuBar
├── Registration JPanel
│   ├── JTextField registerNo, studentName, email
│   ├── JComboBox event
│   └── JButton Register
└── Committee JPanel
    ├── JComboBox eventFilter
    ├── JButton Filter, Export CSV
    └── JTable registrations

Register -> validate -> SwingWorker -> Service -> DAO/JDBC -> confirmation/error
Filter -> SwingWorker -> DAO query -> JTable model update
Export -> JFileChooser -> CSV writer
```

## Stage 3: critical decisions
- Validate fields in the service layer; display field-specific errors in the UI.
- Enforce duplicate prevention twice: query for a friendly message and a database UNIQUE constraint on (register_no, event_id) for race-safe integrity.
- JDBC access belongs in DAO/repository classes, not Swing event handlers.
- Run database operations in SwingWorker.doInBackground(); update labels/table/dialog in done() on the EDT.

## Stage 4: class design and pseudocode
```text
EventRegistrationFrame -> EventRegistrationService -> RegistrationDAO
                                          |
                                          +-> DatabaseConnection
RegistrationDAO -> MySQL tables (students, events, registrations)
```

**Register**
```text
BEGIN RegisterAction
    read form fields
    validate required fields and email
    disable Register button; show "Saving..."
    START SwingWorker
        result ← service.register(student, selectedEvent)
        RETURN result
    DONE
        re-enable Register button
        IF result is success THEN show confirmation
        ELSE show readable validation/duplicate/database error
END
```

**Filter**
```text
BEGIN FilterAction
    selectedEvent ← filter combo selection
    START SwingWorker
        rows ← service.findByEvent(selectedEvent)
        RETURN rows
    DONE
        replace JTable model with returned rows
        show number of matching registrations
END
```

## Stage 5: implementation
- `database.sql` creates the database and tables, including a UNIQUE constraint.
- `EventRegistrationApp.java` is a runnable Swing demonstration with in-memory sample data and CSV export. It demonstrates the UI flow without requiring credentials.
- For the required MySQL/JDBC submission, connect the supplied schema to a DAO using PreparedStatement and run queries in SwingWorker. Add your own MySQL credentials locally; never commit passwords.

## Compile and run demonstration
```bash
javac EventRegistrationApp.java
java EventRegistrationApp
```

## Alternative: load all 100,000 rows vs pagination
- Load all: database transfer and table-model construction process O(N) rows; client memory is O(N × rowSize), plus Swing table objects. A large result can delay display, increase GC pressure and make the UI appear frozen if run on the EDT.
- Page size p: each page query transfers O(p) rows and the visible table uses O(p) memory; total work to visit all pages is still O(N), but first-page latency and memory are bounded. Run each query in SwingWorker so the EDT remains responsive. OFFSET pagination can become slower for large offsets depending on the database; keyset pagination can improve this.
- For 100,000 rows, estimate memory from measured average row footprint; do not assume exact bytes without measuring. Keep only one page in the JTable model.

## Evidence
Run and capture normal, invalid and duplicate registrations; filter results; export CSV; show the MySQL schema and actual query results. The included demo is not a substitute for the required MySQL integration if the instructor expects live JDBC.
