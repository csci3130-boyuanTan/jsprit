package com.graphhopper.jsprit.analysis.toolbox;

import com.graphhopper.jsprit.core.algorithm.recreate.InsertionData;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.job.Delivery;
import com.graphhopper.jsprit.core.problem.job.Job;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.job.Shipment;
import com.graphhopper.jsprit.core.problem.solution.route.VehicleRoute;
import com.graphhopper.jsprit.core.problem.solution.route.activity.TourActivity;
import com.graphhopper.jsprit.core.problem.vehicle.Vehicle;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;

import java.util.Collection;

public class RecorderUtil {

    public static String getToNodeId(Shipment shipment) {
        return shipment.getId() + "_delivery";
    }

    public static String getFromNodeId(Shipment shipment) {
        return shipment.getId() + "_pickup";
    }


    public static String makeStartId(Vehicle vehicle) {
        return vehicle.getId() + "_start";
    }


    public static String makeEndId(Vehicle vehicle) {
        if (vehicle.getStartLocation().getId().equals(vehicle.getEndLocation().getId())) return RecorderUtil.makeStartId(vehicle);
        return vehicle.getId() + "_end";
    }


    public static String getNodeId(TourActivity act) {
        String nodeId = null;
        if (act instanceof TourActivity.JobActivity) {
            Job job = ((TourActivity.JobActivity) act).getJob();
            if (job instanceof Service) {
                nodeId = job.getId();
            } else if (job instanceof Shipment) {
                if (act.getName().equals("pickupShipment")) nodeId = RecorderUtil.getFromNodeId((Shipment) job);
                else nodeId = RecorderUtil.getToNodeId((Shipment) job);
            }
        }
        return nodeId;
    }


    public static Edge getEnteringEdge(Graph graph,String toNodeId) {
        Collection<Edge> enteringEdges = graph.getNode(toNodeId).getEnteringEdgeSet();
        if (enteringEdges.size() == 1) return enteringEdges.iterator().next();
        else {
            for (Edge e : enteringEdges) {
                if (e.getId().startsWith("shipment")) {
                    continue;
                }
                return e;
            }
        }
        return null;
    }
}
