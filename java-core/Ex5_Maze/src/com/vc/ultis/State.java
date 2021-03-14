package com.vc.ultis;

public class State implements Comparable<State>{
    private int x;
    private int y;
    private int expansionLevel;
    private int[] goal;
    private State parent;


    public State(int x, int y, int expansionLevel, int[] goal, State parent){
        this.x = x;
        this.y = y;
        this.expansionLevel = expansionLevel;
        this.goal = goal;
        this.parent = parent;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getExpansionLevel() {
        return expansionLevel;
    }

    public int heuristic(){
        return Math.abs(goal[0]-this.x)+Math.abs(goal[1]-this.y);
    }

    @Override
    public int compareTo(State e){
    	int h=this.heuristic() - e.heuristic();
    	int g= this.getExpansionLevel()- e.getExpansionLevel();
        return h+g ;
    }

//     @Override
//     public boolean equals(Object o){
//         if(o == this) return true;
//         if(!(o instanceof State)) return false;

//         State e = (State)o;
//         return this.getX() == e.getX() && this.getY() == e.getY();
//     }

      @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

    

    public boolean hasParent(){
        return parent != null;
    }

    public State getParent(){
        return parent;
    }
    
    public String toString(){
        return "{("+this.getX()+","+this.getY()+"),expansion level:"+this.getExpansionLevel()+"}";
    }
}
