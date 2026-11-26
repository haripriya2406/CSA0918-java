# Task 03 – Campus Library Search Screen (9 Marks)

## 3.1 Layout choices
- Main JFrame: BorderLayout. The central results region receives remaining space.
- Search panel: FlowLayout(LEFT); text field, category Choice/JComboBox and Search button retain preferred sizes.
- Results area: JScrollPane containing JTable/JTextArea in CENTER; expands when the frame grows.
- Bottom panel: FlowLayout(CENTER) for Prev, Next and Reserve.
- Status label: SOUTH (or a small status panel in the bottom region).

## 3.2 Nested structure
```text
JFrame (BorderLayout)
├── title JLabel (NORTH)
├── search JPanel (FlowLayout) (PAGE_START)
├── JScrollPane + JTable (CENTER)
└── footer JPanel (BorderLayout) (SOUTH)
    ├── button JPanel (FlowLayout) (CENTER)
    └── status JLabel (SOUTH)
```

## 3.3 Implementation
Run `LibrarySearchScreen.java`.

```bash
javac LibrarySearchScreen.java
java LibrarySearchScreen
```

## 3.4 Resizing test
Resize the window to small, medium and large sizes and save screenshots. The results scroll pane should take the additional width and height. The search and button rows should remain near their preferred component sizes. Attach your own screenshots; do not use sample images as execution evidence.

## Alternative layouts
GridBagLayout supports precise constraints and weights, but has more setup. Absolute positioning uses setLayout(null) and setBounds; it is quick for a fixed-size mockup but does not adapt correctly to resizing, fonts or different displays. Layout computation is generally O(c) in number of child components for a simple panel; GridBagLayout has more constraint work but remains practical for typical GUI sizes. Nested panels increase container count by a small O(p) amount; absolute positioning may reduce panel count but increases manual resize logic and maintenance cost.
