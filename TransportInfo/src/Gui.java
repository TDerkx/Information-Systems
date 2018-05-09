import javax.swing.*;

/**
 * Created by s141753 on 9-5-2018.
 */
public class Gui {
    public static Main main;

    private JButton submitButton;
    private JComboBox country;
    private JPanel gui;
    private JFrame frame;

    Gui(Main c) {
        this.main = c;

        frame = new JFrame("Gui");
        frame.setContentPane(gui);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        submitButton.addActionListener(new SubmitListener(main, this));
        frame.setVisible(true);
    }

    public String getCountry() {
        return (String)country.getSelectedItem();
    }
}
