package com.eyepax.color;

public class Color_Grid {
	
	//	Grid Coordination X & Y
	private int Cord_x;
	private int Cord_y;
	
	public Color_Grid() {
		super();
	}

	public Color_Grid(int cord_x, int cord_y) {
		super();
		Cord_x = cord_x;
		Cord_y = cord_y;
	}

	public int getCord_x() {
		return Cord_x;
	}

	public int getCord_y() {
		return Cord_y;
	}
	
	// Direction of Color Grids
	// Base Grid Center point X,Y
	// North - northCord
	public Color_Grid northCord() {
		return new Color_Grid(Cord_x, Cord_y-1);	
	}
	
	// East - eastCord
	public Color_Grid eastCord() {
		return new Color_Grid(Cord_x+1, Cord_y);	
	}
	
	// West - westCord
	public Color_Grid westCord() {
		return new Color_Grid(Cord_x-1, Cord_y);	
	}
	
	// South - southCord
	public Color_Grid southCord() {
		return new Color_Grid(Cord_x, Cord_y+1);	
	}
	
	
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		
		if(obj == null || getClass() != obj.getClass())
			return false;
		
		Color_Grid color_Grid = (Color_Grid) obj;
		
		if(Cord_x != color_Grid.Cord_x)
			return false;
		return Cord_y == color_Grid.Cord_y;
			
	}
	
	public int hashCode() {
		int result = Cord_x;
		result = 31 * result + Cord_y;
		return result;	
	}
	
	public String toString() {
		return "(" + Cord_x +","+ Cord_y +")";	
	}
	
}
