package gui;

import org.jdatepicker.impl.*;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

/**
 * Created by Stijn on 6-2-2017.
 */
public class DatePicker extends JPanel {
    UtilDateModel model;

    public DatePicker(){
        super();

        model = new UtilDateModel();

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        add(datePicker);
    }

    public UtilDateModel getModel(){
        return model;
    }
}
