public class Main {
    
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        // instance of the SelectionFrame class, where the user actually interacts with the program (choosing priority
        // buildings from the dropdown menus)
        SelectionFrame selectionFrame = new SelectionFrame();
        runGraphics(selectionFrame);
        ShortestPath shortestPathCalculator = new ShortestPath();

        // array that will store all buildings' total distance to all priorities
        int[] totalDistances = new int[35];
        // will hold the indices for the top three recommended dorms
        int[] topThreeDorms = new int[3];
        // will hold strings for the top 3 recommended dorms
        String[] results = new String[3];
        // array that will store the average distance from the top three dorms to all priorities 
        int[] avgDistances = new int[3]; 
        
        totalDistances = getTotalDist(shortestPathCalculator, selectionFrame);
        topThreeDorms = getTopThree(totalDistances);
        avgDistances = getAverageDistances(topThreeDorms, totalDistances);

        // update the results array with the dorm at the topThree indices
        results[0] = "Closest Dorm: " + SelectionFrame.CAMPUS_BUILDINGS[topThreeDorms[0]];
        results[1] = "Second Closest Dorm: " + SelectionFrame.CAMPUS_BUILDINGS[topThreeDorms[1]];
        results[2] = "Third Closest Dorm: " + SelectionFrame.CAMPUS_BUILDINGS[topThreeDorms[2]];

        // once the initial graphics are run, and the user clicks submit, show the result page with all of the statistics
        ResultFrame resultFrame = new ResultFrame(results, getShortestDist(selectionFrame, shortestPathCalculator,topThreeDorms), 
        getFurthestDist(selectionFrame, shortestPathCalculator,topThreeDorms), avgDistances);
        resultFrame.display();

        // use a try catch to be able to use Thread.sleep(1)
        try {
            // continually check if the user has clicked restart
            while(true) {
                Thread.sleep(1);
                // if they do, run again
                if (resultFrame.playAgain) {
                    run();
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // used in the getShortestDist and getFurthestDist methods to check if the index being checked matches the index of one of the selections
    private static boolean isSelection(SelectionFrame selectionFrame, int i) {
        return (i == selectionFrame.findPriority(SelectionFrame.CAMPUS_BUILDINGS, selectionFrame.selection1) 
                || i == selectionFrame.findPriority(SelectionFrame.CAMPUS_BUILDINGS, selectionFrame.selection2) 
                || i == selectionFrame.findPriority(SelectionFrame.CAMPUS_BUILDINGS, selectionFrame.selection3)
                || i == selectionFrame.findPriority(SelectionFrame.CAMPUS_BUILDINGS, selectionFrame.selection4));
    }

    // get all buildings' total distance to all priorities
    private static int[] getTotalDist(ShortestPath shortestPathCalculator, SelectionFrame selectionFrame) {
        int[] total = new int[35];
        for (int i = 0; i < 35; i++) {
            // set each index in total to the sum of the shortest path from the building at that building to each priority
            total[i] = shortestPathCalculator.findShortestPath(selectionFrame.findPriority(SelectionFrame.CAMPUS_BUILDINGS, selectionFrame.selection1))[i] + 
            shortestPathCalculator.findShortestPath(selectionFrame.findPriority(SelectionFrame.CAMPUS_BUILDINGS, selectionFrame.selection2))[i] + 
            shortestPathCalculator.findShortestPath(selectionFrame.findPriority(SelectionFrame.CAMPUS_BUILDINGS, selectionFrame.selection3))[i] + 
            shortestPathCalculator.findShortestPath(selectionFrame.findPriority(SelectionFrame.CAMPUS_BUILDINGS, selectionFrame.selection4))[i];
        }
        return total;
    }

    // returns a pair containing the distance from the #1 recommended dorm to the closest priority building and the index of that priority building 
    private static Pair getShortestDist(SelectionFrame selectionFrame, ShortestPath shortestPathCalculator, int[] topThree) {
        int[] distFromResultOne = new int[35];    
        // find the shortest distances from the first result to all other points    
        distFromResultOne = shortestPathCalculator.findShortestPath(topThree[0]);
        // initially set to max just to initialize it so that the first iteration in the loop finds an initial minimum
        int firstResShortestDist = Integer.MAX_VALUE;
        int firstResShortestDistIndex = -1;
        
        for (int i = 0; i < 35; i++) {
            if(isSelection(selectionFrame, i)) {
                // if its less, set that as the new min value and index
                if(distFromResultOne[i] < firstResShortestDist) {
                    firstResShortestDist = distFromResultOne[i];
                    firstResShortestDistIndex = i;
                }
            }
        }
        return new Pair(firstResShortestDistIndex, firstResShortestDist);
    }
    
    // returns a pair containing the distance from the #1 recommended dorm to the furthest priority building and the index of that priority building
    // works the same as getShortestDist but for further instead of shorter
    private static Pair getFurthestDist(SelectionFrame selectionFrame, ShortestPath shortestPathCalculator, int[] topThree) {
        int[] distFromResultOne = new int[35];
        distFromResultOne = shortestPathCalculator.findShortestPath(topThree[0]);
        int firstResFurthestDist = Integer.MIN_VALUE;
        int firstResFurthestDistIndex = -1;
        for (int i = 0; i < 35; i++) {
            if(isSelection(selectionFrame, i)) {
                if(distFromResultOne[i] > firstResFurthestDist) {
                    firstResFurthestDist = distFromResultOne[i];
                    firstResFurthestDistIndex = i;
                }
            }
        }
        return new Pair(firstResFurthestDistIndex, firstResFurthestDist);
    }

    /* find index with shortest total distance that is also a dorm (so we exclude the non-dorm indices)
    don't want to look at distances to indices 0, 6, 8, 9, 13, 16, 17, 18, 19, 20, 21, 26, 27, 30, 31, 32, 33, 34 
    because they are non dorms 
    */
    private static int[] getTopThree(int[] totalDistances) {
        // creating a copy of the totalDistances array so that when it is modified, initial array stays the same
        int[] totalCopy = totalDistances.clone();
        
        int[] topThree = new int[3];
        int minDistIndex = -1;
        int minDist = Integer.MAX_VALUE;

        // outer loop to find the top three
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 35; j++) {
                // ignore indices of buildings that are not dorms
                // update the min as you iterate over the loop
                if ((totalCopy[j] < minDist) && j != 0 && j != 6 && j != 8 && j != 9 && j != 13 &&
                    j != 16 && j != 17 && j != 18 && j != 19 && j != 20 && j != 21 && j != 26 &&
                    j != 27 && j != 30 && j != 31 && j != 32 && j != 33 && j != 34) {
                    minDist = totalCopy[j];
                    minDistIndex = j;
                }
            }
            // once a min is found, set it to max so we don't find it as a min again
            totalCopy[minDistIndex] = Integer.MAX_VALUE;
            topThree[i] = minDistIndex;
            // once a min is found, re-initialize the min so we can continue to find other mins
            minDist = Integer.MAX_VALUE;
        }
        return topThree;
    }

    // gets the average distances from the top three dorm selections using the total distances array 
    private static int[] getAverageDistances(int[] topThree, int[] total) {
        int dist;
        int[] avgDistances = new int[3];
        for (int i = 0; i < 3; i++) {
            dist = total[topThree[i]];
            avgDistances[i] = dist / 4;
        }
        return avgDistances;
    }
    
    // runner method that checks status of booleans to display different screens 
    private static void runGraphics(SelectionFrame selectionFrame) {
        MenuFrame menuFrame = new MenuFrame();
        InstructionFrame instructionFrame = new InstructionFrame();
       
        menuFrame.display();

        // conditions to check which buttons have / haven't been pressed, and display the next windo
        try {
            while(!selectionFrame.isSubmitted) {
                Thread.sleep(1);
                if(menuFrame.startClicked) {   
                    selectionFrame.display();
                    menuFrame.startClicked = false;
                }
                if(menuFrame.instructionsClicked) {
                    instructionFrame.display();
                    menuFrame.instructionsClicked = false;
                }
                if(instructionFrame.startClicked){
                    selectionFrame.display();
                    instructionFrame.startClicked = false;
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
