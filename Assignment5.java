public class Assignment5 {

    private int numOfItems; //Number of items you want on the graph
    Graph graph;//The graph for the adjacency matrix

    public Node[] T;

    public Assignment5() {
        numOfItems = 10;
        T = new Node[numOfItems];
        graph = new Graph();
    }

    public void createGraph(){

        Node A = new Node("A",5,0,0,0,0,0);
        Node B = new Node("B",6,0,0,0,0,0);
        Node C = new Node("C",4,0,0,0,0,0);
        Node D = new Node("D",3,0,0,0,0,0);
        Node E = new Node("E",1,0,0,0,0,0);
        Node F = new Node("F",4,0,0,0,0,0);
        Node G = new Node("G",14,0,0,0,0,0);
        Node H = new Node("H",12,0,0,0,0,0);
        Node I = new Node("I",2,0,0,0,0,0);
        Node J = new Node("J",3,0,0,0,0,0);

        graph.addEdge(A,D, false);
        graph.addEdge(A,C, false);
        graph.addEdge(A,E, false);
        graph.addEdge(D,G, false);
        graph.addEdge(C,H, false);
        graph.addEdge(B,H, false);
        graph.addEdge(H,I, false);
        graph.addEdge(E,F, false);
        graph.addEdge(F,G, false);
        graph.addEdge(G,I, false);

        T[0] = A;
        T[1] = B;
        T[2] = C;
        T[3] = D;
        T[4] = E;
        T[5] = F;
        T[6] = G;
        T[7] = H;
        T[8] = I;

        T[0].succ = new Node[]{C,D,E};
        T[1].succ = new Node[]{H};
        T[2].succ = new Node[]{H};
        T[3].succ = new Node[]{G};
        T[4].succ = new Node[]{F};
        T[5].succ = new Node[]{G};
        T[6].succ = new Node[]{I};
        T[7].succ = new Node[]{I};

        T[2].pred = new Node[]{A};
        T[3].pred = new Node[]{A};
        T[4].pred = new Node[]{A};
        T[5].pred = new Node[]{E};
        T[6].pred = new Node[]{D,F};
        T[7].pred = new Node[]{B,C};
        T[8].pred = new Node[]{G,H};

        System.out.print(graph.toString());

    }
    //calculate ES,EF
    private void calculateE(Node[] arr){
        for(int i = 0; i < numOfItems; i++){
            //check if there are any predecessors
            if(arr[i].pred != null) {
                for (Node task : arr[i].pred) {
                    if (arr[i].ES < task.EF)
                        arr[i].ES = task.EF;
                }
            }
            arr[i].EF = arr[i].ES + arr[i].time;
        }
    }
    //calculate LS,LF
    private void calculateL(Node[] arr){
        arr[arr.length - 1].LF = arr[arr.length - 1].EF;
        arr[arr.length - 1].LS = arr[arr.length - 1].LF - arr[arr.length - 1].time;
        for(int i = arr.length - 2; i >= 0; i--){
            //check if there are any successors
            if(arr[i].succ != null) {
                for (Node task : arr[i].succ) {
                    if (arr[i].LF == 0)
                        arr[i].LF = task.LS;
                    else {
                        if (arr[i].LF > task.LS)
                            arr[i].LF = task.LS;
                    }
                }
            }
            arr[i].LS = arr[i].LF - arr[i].time;
        }
    }
    private void calculateSlack(Node[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            arr[i].slack = arr[i].LS - arr[i].ES;
        }
    }

    public void CPM(){
        calculateE(T);
        calculateL(T);
        calculateSlack(T);
        System.out.println("Critical Path: ");

        //if the earliest/latest finish/start are the same, then it belongs in the critical path
        for(Node n : T){
            if(n.slack == 0){
                System.out.println(n + " ");
            }
        }
    }

    public static class Node{

        private String name;
        private double time;
        private double ES;
        private double EF;
        private double LS;
        private double LF;
        private double slack;

        public Node[] succ;
        public Node[] pred;

        private Node(){
            succ = new Node[10];
            pred = new Node[10];
        }

        private Node(String name, double time, double ES, double EF, double LS, double LF, double slack){
            this.name = name;
            this.time = time;
            this.ES = ES;
            this.EF = EF;
            this.LS = LS;
            this.LF = LF;
            this.slack = slack;
        }

        public String getName(){
            return name;
        }
        public double getES(){
            return ES;
        }
        public double getEF(){
            return EF;
        }
        public double getLS(){
            return LS;
        }
        public double getLF(){
            return LF;
        }
        public double getSlack(){
            return EF - ES;
        }
    }


}
