# Task 04 – Event-Handling Forensics (7 Marks)

## 4.1 Defect
The listener method is misspelled as `mouseclicked`. Java's method name is case-sensitive: the correct callback is `mouseClicked(MouseEvent e)`. Therefore the adapter's method is not overridden and the event callback is never invoked. The label also is not updated in the defective code.

## 4.2 Failing interaction
Click to draw several dots, minimise the frame, then restore it. Dots drawn directly with `getGraphics()` are temporary screen pixels; repainting clears/recreates the window and those pixels are lost because they were not stored as application state.

## 4.3 Repair
Store every click as a Point, update the label, call repaint(), and draw the stored points inside paint(). See DrawPad.java.

## 4.4 @Override
With `@Override`, misspelling `mouseClicked` as `mouseclicked` produces a compile-time error because no superclass method with that exact signature is overridden. Without the annotation, the method merely becomes an unrelated overload.

## Complete-code TODOs
TODO 1:
```java
@Override
public void mouseClicked(MouseEvent e) {
    points.add(e.getPoint());
    status.setText("Clicks: " + points.size());
    repaint();
}
```
TODO 2:
```java
for (Point p : points) {
    g.fillOval(p.x - 3, p.y - 3, 6, 6);
}
```

## Alternative: BufferedImage
Create an image of the drawing area, obtain its Graphics2D and draw each dot into the image when clicked. In paint(), draw the image once. Repainting then costs O(width × height) pixels for a straightforward full image copy (often optimized by the graphics pipeline), rather than O(n) point draw operations. The point list uses O(n) memory; an ARGB image uses O(width × height) pixels, about 4 × width × height bytes, excluding object overhead. A hybrid can retain both image and points if editing/undo is required.

## Run
```bash
javac DrawPad.java
java DrawPad
```
