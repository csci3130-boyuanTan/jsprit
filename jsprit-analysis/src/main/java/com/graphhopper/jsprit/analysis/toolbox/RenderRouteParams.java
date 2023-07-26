package com.graphhopper.jsprit.analysis.toolbox;

import com.graphhopper.jsprit.core.problem.solution.route.VehicleRoute;
import org.graphstream.graph.Graph;

public class RenderRouteParams {
    private Graph g;

    private VehicleRoute route;

    private int routeId;

    private long renderDelay_in_ms;

    private GraphStreamViewer.Label label;

    public RenderRouteParams() {

    }


    public Graph getG() {
        return g;
    }

    public void setG(Graph g) {
        this.g = g;
    }

    public VehicleRoute getRoute() {
        return route;
    }

    public void setRoute(VehicleRoute route) {
        this.route = route;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public long getRenderDelay_in_ms() {
        return renderDelay_in_ms;
    }

    public void setRenderDelay_in_ms(long renderDelay_in_ms) {
        this.renderDelay_in_ms = renderDelay_in_ms;
    }

    public GraphStreamViewer.Label getLabel() {
        return label;
    }

    public void setLabel(GraphStreamViewer.Label label) {
        this.label = label;
    }
}
