import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Assignment5.Node, List<Assignment5.Node>> graph = new HashMap<>();

    public void addVertex(Assignment5.Node v){
        graph.put(v, new LinkedList<Assignment5.Node>());
    }

    public void addEdge(Assignment5.Node source, Assignment5.Node destination, boolean bidirectional) {
        if (!graph.containsKey(source))
            addVertex(source);
        if (!graph.containsKey(destination))
            addVertex(destination);
        graph.get(source).add(destination);
        if (bidirectional == true)
            graph.get(destination).add(source);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (Assignment5.Node v : graph.keySet()) {
            builder.append(v.toString() + ": ");
            for (Assignment5.Node w : graph.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }

        return (builder.toString());
    }
}
