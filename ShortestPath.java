import java.util.Arrays;

public class ShortestPath{
        
    // weighted adjacency matrix for distances between buildings (based on a network graph we made)   
    private static final int[][] CAMPUS_MAP_GRAPH = new int[35][35];

    public ShortestPath(){ 
        /* int array fills with zeros by default, so we just add in the nonzero values
        Names of buildings in order from 0-34: 
        Frost, Morrow, Val, Drew, Cohan, Lipton, Arms, Mo, Converse, Morgan, Mayo, Seelye, Hitch,
        Downtown, Porter, Garman, Lyceum, Track/fieldhouse, Athletics, Rink, Webster, Smudd, Ford,
        Lee, GwayC, Nicholls, Scce, Beneski, Jenks, Moore, Clark, Fayerweather, Chapin, Barrett, Keefe */

        CAMPUS_MAP_GRAPH[0][1] = 398; CAMPUS_MAP_GRAPH[0][7] = 343; CAMPUS_MAP_GRAPH[0][8] = 445; CAMPUS_MAP_GRAPH[0][20] = 791; CAMPUS_MAP_GRAPH[0][33] = 227;

        CAMPUS_MAP_GRAPH[1][0] = 398; CAMPUS_MAP_GRAPH[1][2] = 196; CAMPUS_MAP_GRAPH[1][32] = 207;

        CAMPUS_MAP_GRAPH[2][1] = 196; CAMPUS_MAP_GRAPH[2][3] = 178; CAMPUS_MAP_GRAPH[2][29] = 153; CAMPUS_MAP_GRAPH[2][30] = 174; CAMPUS_MAP_GRAPH[2][31] = 174;

        CAMPUS_MAP_GRAPH[3][2] = 178; CAMPUS_MAP_GRAPH[3][4] = 147; CAMPUS_MAP_GRAPH[3][30] = 170;

        CAMPUS_MAP_GRAPH[4][3] = 147; CAMPUS_MAP_GRAPH[4][5] = 153;

        CAMPUS_MAP_GRAPH[5][4] = 153; CAMPUS_MAP_GRAPH[5][6] = 344; CAMPUS_MAP_GRAPH[5][14] = 215; CAMPUS_MAP_GRAPH[5][15] = 327;

        CAMPUS_MAP_GRAPH[6][5] = 344; CAMPUS_MAP_GRAPH[6][7] = 143; CAMPUS_MAP_GRAPH[6][8] = 280;

        CAMPUS_MAP_GRAPH[7][0] = 343; CAMPUS_MAP_GRAPH[7][6] = 143;

        CAMPUS_MAP_GRAPH[8][0] = 445; CAMPUS_MAP_GRAPH[8][6] = 280; CAMPUS_MAP_GRAPH[8][9] = 303; CAMPUS_MAP_GRAPH[8][11] = 746; CAMPUS_MAP_GRAPH[8][16] = 770;

        CAMPUS_MAP_GRAPH[9][8] = 303; CAMPUS_MAP_GRAPH[9][10] = 589; CAMPUS_MAP_GRAPH[9][16] = 411;

        CAMPUS_MAP_GRAPH[10][9] = 589; CAMPUS_MAP_GRAPH[10][11] = 217;

        CAMPUS_MAP_GRAPH[11][8] = 746; CAMPUS_MAP_GRAPH[11][10] = 217; CAMPUS_MAP_GRAPH[11][12] = 246; CAMPUS_MAP_GRAPH[11][15] = 561;

        CAMPUS_MAP_GRAPH[12][11] = 246; CAMPUS_MAP_GRAPH[12][13] = 984;

        CAMPUS_MAP_GRAPH[13][12] = 984; CAMPUS_MAP_GRAPH[13][14] = 294;

        CAMPUS_MAP_GRAPH[14][5] = 215; CAMPUS_MAP_GRAPH[14][13] = 294; CAMPUS_MAP_GRAPH[14][15] = 318;

        CAMPUS_MAP_GRAPH[15][5] = 327; CAMPUS_MAP_GRAPH[15][11] = 591; CAMPUS_MAP_GRAPH[15][14] = 318;

        CAMPUS_MAP_GRAPH[16][8] = 770; CAMPUS_MAP_GRAPH[16][9] = 411; CAMPUS_MAP_GRAPH[16][18] = 1012; CAMPUS_MAP_GRAPH[16][20] = 880;

        CAMPUS_MAP_GRAPH[17][18] = 1943;

        CAMPUS_MAP_GRAPH[18][16] = 1012; CAMPUS_MAP_GRAPH[18][17] = 1943; CAMPUS_MAP_GRAPH[18][19] = 483; CAMPUS_MAP_GRAPH[18][20] = 830; CAMPUS_MAP_GRAPH[18][21] = 760; CAMPUS_MAP_GRAPH[18][22] = 886;

        CAMPUS_MAP_GRAPH[19][18] = 483;

        CAMPUS_MAP_GRAPH[20][0] = 791; CAMPUS_MAP_GRAPH[20][16] = 880; CAMPUS_MAP_GRAPH[20][18] = 830; CAMPUS_MAP_GRAPH[20][21] = 360;

        CAMPUS_MAP_GRAPH[21][18] = 760; CAMPUS_MAP_GRAPH[21][20] = 360; CAMPUS_MAP_GRAPH[21][22] = 333; CAMPUS_MAP_GRAPH[21][26] = 879; CAMPUS_MAP_GRAPH[21][33] = 664; CAMPUS_MAP_GRAPH[21][34] = 788;

        CAMPUS_MAP_GRAPH[22][18] = 886; CAMPUS_MAP_GRAPH[22][21] = 333; CAMPUS_MAP_GRAPH[22][23] = 56; CAMPUS_MAP_GRAPH[22][25] = 131; CAMPUS_MAP_GRAPH[22][26] = 843;

        CAMPUS_MAP_GRAPH[23][22] = 56; CAMPUS_MAP_GRAPH[23][24] = 26;

        CAMPUS_MAP_GRAPH[24][23] = 26; CAMPUS_MAP_GRAPH[24][25] = 39;

        CAMPUS_MAP_GRAPH[25][22] = 131; CAMPUS_MAP_GRAPH[25][24] = 39;

        CAMPUS_MAP_GRAPH[26][21] = 879; CAMPUS_MAP_GRAPH[26][22] = 843; CAMPUS_MAP_GRAPH[26][27] = 573; CAMPUS_MAP_GRAPH[26][34] = 366;

        CAMPUS_MAP_GRAPH[27][26] = 573; CAMPUS_MAP_GRAPH[27][28] = 290; CAMPUS_MAP_GRAPH[27][29] = 287; CAMPUS_MAP_GRAPH[27][31] = 211; CAMPUS_MAP_GRAPH[27][34] = 248;

        CAMPUS_MAP_GRAPH[28][27] = 290;

        CAMPUS_MAP_GRAPH[29][2] = 153; CAMPUS_MAP_GRAPH[29][27] = 287; CAMPUS_MAP_GRAPH[29][31] = 238;

        CAMPUS_MAP_GRAPH[30][2] = 174; CAMPUS_MAP_GRAPH[30][3] = 170;

        CAMPUS_MAP_GRAPH[31][2] = 230; CAMPUS_MAP_GRAPH[31][27] = 211; CAMPUS_MAP_GRAPH[31][29] = 238; CAMPUS_MAP_GRAPH[31][32] = 191;

        CAMPUS_MAP_GRAPH[32][1] = 207; CAMPUS_MAP_GRAPH[32][31] = 191; CAMPUS_MAP_GRAPH[32][33] = 151; CAMPUS_MAP_GRAPH[32][34] = 178;

        CAMPUS_MAP_GRAPH[33][0] = 227; CAMPUS_MAP_GRAPH[33][21] = 664; CAMPUS_MAP_GRAPH[33][32] = 151;CAMPUS_MAP_GRAPH[33][34] = 403;

        CAMPUS_MAP_GRAPH[34][21] = 788; CAMPUS_MAP_GRAPH[34][26] = 366; CAMPUS_MAP_GRAPH[34][27] = 348; CAMPUS_MAP_GRAPH[34][32] = 178; CAMPUS_MAP_GRAPH[34][33] = 403;

    }   


    /* This method computes the shortest distances from a given source node (priority building) to all other nodes in the campus map graph.
       It utilizes Dijkstra's algorithm to iteratively explore and update the shortest distances.
       Parameters: source: The index of the priority building in the campus map graph.
       Returns: An array containing the shortest distances from the source node to all other nodes.
       we will call this four times, once for each priority building/location
    */
    public int[] findShortestPath(int source) { // source = priority building
        int[] shortestDistances = new int[35]; // Initialize an array to store the shortest distances from the source node to all other nodes.
        boolean[] visited = new boolean[35]; // Initialize a boolean array to keep track of visited nodes.
        Arrays.fill(shortestDistances, Integer.MAX_VALUE); // Initialize all distances to infinity, except for the source node which has distance 0.
        shortestDistances[source] = 0;

        // Update shortest distances for nodes directly connected to the source node; separated for comprehension 
        for (int i = 0; i < 35; i++) { // for each building
            if(CAMPUS_MAP_GRAPH[source][i] != 0){ // Check if there's an edge from the source to the current node
                shortestDistances[i] = CAMPUS_MAP_GRAPH[source][i]; // Fill shortestDistances array at that building's index with the distance between that building and the priority building
            // ^ this array now only has distances in the indicies of the buildings that are directly connected to the priority building (on the network)
            }
        }    

        visited[source] = true; // Mark the source node as visited.

        // Iteratively explore and update shortest distances to all other nodes.
        for (int index = 0; index < 35; index++) {  
            int currNode = minDistanceIndex(shortestDistances, visited); // Find the node with the minimum distance from the source among unvisited nodes.
            visited[currNode] = true;  // Mark the current node as visited.

            // Update shortest distances for all unvisited neighbors of the current node.
            for (int k = 0; k < 35; k++) {
                if(CAMPUS_MAP_GRAPH[currNode][k] != 0 && !visited[k] && CAMPUS_MAP_GRAPH[currNode][k] + shortestDistances[currNode] < shortestDistances[k]){
                    // update shortest distances if a shorter path is found through the current node
                    shortestDistances[k] = CAMPUS_MAP_GRAPH[currNode][k] + shortestDistances[currNode];
                }
            }
        }
        // return the array containing shortest distances from the source ndoe to all other nodes
        return shortestDistances;
    }

    /* We want the new "currNode" to be the vertex with the min distance to the previously checked node
       BUT only from the set of vertices that have not yet been visited because we don't want to visit the same node twice
    */
    private int minDistanceIndex(int[] shortestDistances, boolean[] visited) {
        int min = Integer.MAX_VALUE; // Set an initial minimum distance to a large value.
        int minIndex = 0;
        for (int j = 0; j < 35; j++) { // Iterate through all nodes
            if (!visited[j] && shortestDistances[j] <= min) { // Check unvisited nodes with distances less than or equal to the current minimum
                min = shortestDistances[j]; // Update the minimum distance
                minIndex = j; // Update the index of the node with the minimum distance
            }   
        }
        return minIndex; // Return the index of the node with the minimum distance
    }

}   