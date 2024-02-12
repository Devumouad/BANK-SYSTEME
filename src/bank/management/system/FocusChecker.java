package bank.management.system;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FocusChecker implements FocusListener {

    private JTextField textField;
    private String placeholder;

    public FocusChecker(JTextField textField, String placeholder) {
        this.textField = textField;
        this.placeholder = placeholder;
        this.textField.setText(placeholder); // Set initial placeholder text
        this.textField.addFocusListener(this); // Add focus listener
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (textField.getText().equals(placeholder)) {
            textField.setText(""); // Clear placeholder text when focused
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (textField.getText().isEmpty()) {
            textField.setText(placeholder); // Set placeholder text when empty
        }
    }
}
