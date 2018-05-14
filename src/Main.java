import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PApplet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by s141753 on 8-5-2018.
 * Overall main of map view and settings windows.
 */
public class Main {
    public static UnfoldingWindow unfolding;
    public static Gui gui;

    static Statement statement;
    static Connection connection;

    static String url;
    static String driver;
    static String user;
    static String password;

    String country;


    Main() {
        getProperties();
        setupConnection();
        constructWindows();
        try {
            drawMarkers(performQueries());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    public void constructWindows() { // make the settings and map windows
        unfolding = new UnfoldingWindow();
        gui = new Gui(this);
        PApplet.main(new String[]{"UnfoldingWindow"});
    }

    private void getProperties() {
        url = System.getenv("url");
        driver = System.getenv("driver");
        user = System.getenv("user");
        password = System.getenv("password");
    }

    void setupConnection() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver class not found");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ResultSet performQueries() {  // for now, just performs default routes-per-country query
        ResultSet rs = null;
        try {
            rs = queryAllRoutes(country).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    void drawMarkers(ResultSet rs) throws SQLException { // draw simple line for each leg
        float fromLat;
        float toLat;
        float fromLon;
        float toLon;

        List<Marker> markers = new ArrayList<>();

        unfolding.clearMarkers();

        while (rs.next()) {
            fromLat = rs.getFloat("fromlat");
            fromLon = rs.getFloat("fromlon");
            toLat = rs.getFloat("tolat");
            toLon = rs.getFloat("tolon");

            Location from = new Location(fromLat, fromLon);
            Location to = new Location(toLat, toLon);

            Marker marker = new SimpleLinesMarker(from, to);
            markers.add(marker);

        }
        unfolding.addMarkers(markers);
    }

    PreparedStatement queryAllRoutes(String country) throws SQLException {
        String query = "SELECT * FROM transportdata WHERE countryfrom = \'" + country + "\' AND " +
                "countryto = \'" +
                country + "\' FETCH FIRST 100 ROWS ONLY;"; // only draw 100 so it doesnt slow too
        // much
        PreparedStatement ps;

        ps = connection.prepareStatement(query);

        return ps;
    }

    public void setCountry(String c) throws SQLException {
        this.country = c;
        System.out.println("Country set to: " + country);
        drawMarkers(performQueries());
    }


}
