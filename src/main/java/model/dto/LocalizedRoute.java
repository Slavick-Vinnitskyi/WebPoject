package model.dto;

import model.entity.Route;

import java.util.HashMap;
import java.util.Map;

public class LocalizedRoute {
    private Route route;

    private Map<String, LocalizedPart > localizedPartMap = new HashMap<>();

    class LocalizedPart {
        private String start;
        private String finish;

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getFinish() {
            return finish;
        }

        public void setFinish(String finish) {
            this.finish = finish;
        }
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Map<String, LocalizedPart> getLocalizedPartMap() {
        return localizedPartMap;
    }

    public void setLocalizedPartMap(Map<String, LocalizedPart> localizedPartMap) {
        this.localizedPartMap = localizedPartMap;
    }
}
