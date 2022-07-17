package com.eyepax.color;

class GridNode {

	private Color_Grid color_Grid;
	private char color;
	private int id;

	public GridNode(int id, int Cord_x, int Cord_y, char color) {
		this.color_Grid = new Color_Grid(Cord_x, Cord_y);
		this.color = color;
		this.id = id;
	}

	public Color_Grid getColour_Grid() {
		return color_Grid;
	}

	public char getColor() {
		return color;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;

		GridNode gridNode = (GridNode) obj;

		if (color != gridNode.color)
			return false;

		return color_Grid.equals(gridNode.color_Grid);

	}

	@Override
	public int hashCode() {
		int result = color_Grid.hashCode();
		result = 31 * result + (int) color;
		return result;
	}

	@Override
	public String toString() {
		return " [" + color_Grid.toString() + ", " + color + "] ";
	}
}
