import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SubmitListener implements ActionListener {

    private UnfoldingWindow unfolding;
    private Gui gui;
    private Main main;

    SubmitListener(Main m, Gui g) {
        unfolding = Main.unfolding;
        gui = g;
        main = m;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            main.setCountry(gui.getCountry());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
