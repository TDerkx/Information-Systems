import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MarkerManager;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

import java.util.List;

/**
 * Created by s141753 on 8-5-2018.
 */
public class UnfoldingWindow extends PApplet {

    static UnfoldingMap map;
    static MarkerManager markerManager;

    public void setup() {
        this.frameRate(10);
        size(1000, 900);
        map = new UnfoldingMap(this, new OpenStreetMap.OpenStreetMapProvider());

        map.setTweening(true);
        Location center = new Location(49f, 5f);
        map.zoomAndPanTo(6, center);
        MapUtils.createDefaultEventDispatcher(this, map);
        markerManager = map.getDefaultMarkerManager();
    }

    public void draw() {
        map.draw();
    }

    public void addMarkers(List<Marker> markers) {
        markerManager.addMarkers(markers);
    }

    public void clearMarkers() {
        markerManager.clearMarkers();
    }


}
