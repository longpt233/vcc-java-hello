package com.vc.ultis;

import java.util.HashSet;
import java.util.PriorityQueue;


/*
*
*
*
*
*
*
*
*/
public class Astar{

    private int[] start;
    private int[] goal;
    private int[][] maze;
    private State solution;
    @SuppressWarnings("unused")
	private static final int WALL = 1;
    
    private static final int PATH = 0;
    private static final int SOLUTION = 2;
    

    public void setStart(int[] start) {
        this.start = start;
    }

    public void setGoal(int[] goal) {
        this.goal = goal;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public int[][] getMaze(){
        return maze;
    }

    public int[] getStart(){
        return start;
    }

    public int[] getGoal(){
        return goal;
    }

    public State getSolution(){
        return solution;
    }

    public Astar(int[] start, int[] goal, int[][] maze){
        this.start = start;
        this.goal = goal;
        this.maze = maze;
        this.solution = null;
    }

    /*
     * @return array 
     * @author 
     * @summary
     *
     */
    public int [][] getResMaze(){
        int[][]resMaze =maze.clone();
        if(getSolution() != null){
            for(State current = getSolution(); current.hasParent(); current = current.getParent()){
                resMaze[current.getParent().getY()][current.getParent().getX()] =SOLUTION;
            }
        }

        return resMaze;
    }

    public void solve(){
        State goalNode = new State(goal[0], goal[1], 0, goal, null);
        State current = new State(start[0], start[1], 0, goal, null);
	// priority => state phai impl comperable
        PriorityQueue<State> open = new PriorityQueue<State>();
	// hashset là con của  hash=> state phai overide ca hashcode va equals
	    // ngoài ra còn có hashtable, hashmap
	    // nếu không ghi đè hashcode, mặc dù cùng equals nhưng nó hash ra kết quả khác nhau nên mặc định 2 object khác nhau
	    // nếu không ghi đè equals , có thế sẽ có obj trùng nhau do hash thiếu trường mà cần  equals chẳng hạn
        HashSet<State> explored = new HashSet<State>();

        open.add(current);

        while(!open.isEmpty() ){
        	
        	current = open.poll();
            if (current.equals(goalNode)) {
            	break;
            }
            //Up
            if(current.getY() > 0){
                State node = new State(
                        current.getX(),
                        current.getY()-1,
                        current.getExpansionLevel() + 1,
                        goal,
                        current
                     
                );
                
                // hoac != WALL cung duoc 
                if((maze[current.getY()-1][current.getX()] == PATH||maze[current.getY()-1][current.getX()] == SOLUTION) && !explored.contains(node)
                	&& !open.contains(node)) {
                    open.add(node);                    
                }
            }
            //Down
            if(current.getY() < maze.length-1){
                State node = new State(
                        current.getX(),
                        current.getY() + 1,
                        current.getExpansionLevel() + 1,
                        goal,
                        current
                );

                if((maze[current.getY()+1][current.getX()] == PATH || maze[current.getY()+1][current.getX()] == SOLUTION)
                	&& !explored.contains(node) && !open.contains(node)) {
                    open.add(node);                   
                }
            }
            //Left
            if(current.getX() > 0){
                State node = new State(
                        current.getX() - 1,
                        current.getY(),
                        current.getExpansionLevel() + 1,
                        goal,
                        current
                );

                if((maze[current.getY()][current.getX()-1] == PATH||maze[current.getY()][current.getX()-1] == SOLUTION )
                		&& !explored.contains(node) && !open.contains(node)) {
                    open.add(node);
                }
            }
            //Right
            if(current.getX() < maze[current.getY()].length-1){
                State node = new State(
                        current.getX()+1,
                        current.getY(),
                        current.getExpansionLevel() + 1,
                        goal,
                        current
                );

                if((maze[current.getY()][current.getX()+1] == PATH ||maze[current.getY()][current.getX()+1] == SOLUTION)
                		&& !explored.contains(node) && !open.contains(node)) {
                    open.add(node);
                }
            }
            explored.add(current);
        }

        this.solution = current;
        
    }

}
