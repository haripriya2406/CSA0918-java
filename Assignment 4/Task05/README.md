# Task 05 – Same Screen, Different Event-Handling Styles (7 Marks)

Four source files demonstrate the required styles:
- `ConverterFrameListener.java`: JFrame implements ActionListener.
- `ConverterNamedInner.java`: named inner handler class.
- `ConverterAnonymous.java`: anonymous ActionListener.
- `ConverterLambda.java`: lambda expression; also the completed TODO program.

All versions support Enter in the amount field and display a JOptionPane result. Each uses a WindowAdapter/default close operation.

## Change effort
When Enter support is added, register the same handler on the text field as well as the button. With a shared handler object or Action, both triggers call the same operation. Showing a dialog is a one-line change in the common conversion method. Record your own line counts if your faculty expects a measured comparison.

## Comparison

| Style | Readability | Access to frame fields | Reuse | Change effort |
|---|---|---|---|---|
| Frame implements listener | Simple for tiny forms; frame mixes roles | Direct | Low unless frame reused | Handler logic stays in frame |
| Named inner class | Clear for larger handler | Direct access to enclosing instance | Good | Easy to extend |
| Anonymous inner class | Good for one small handler | Direct | Low | Local changes |
| Lambda | Concise for one-method listener | Direct | Reuse by storing listener/action | Very easy for small actions |

For a large application, prefer a named controller/action or a shared Swing Action because it separates responsibilities and can be reused by a button, menu item and keyboard shortcut.

## Alternative and complexity
A method reference `this::convert` can be registered as an ActionListener where the signature matches. A shared AbstractAction can be attached to a button and menu item; bind a keyboard shortcut through the root pane's InputMap/ActionMap. A single shared Action object avoids duplicating conversion logic and makes adding triggers mostly configuration work. Listener-object count is O(1) when one action is shared, versus O(t) if each of t triggers gets a separate listener object. The conversion itself is O(1) time and O(1) extra space.
