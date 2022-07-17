package com.eyepax.color;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GridBlock implements Comparable<GridBlock> {

	private char color;

	private Set<GridNode> gridNodes;

	public GridBlock(char color) {
		this.color = color;
		gridNodes = new HashSet<>();
	}

	public Set<GridNode> allGridNodes() {
		return gridNodes;
	}

	public Set<Color_Grid> allColour_Grids() {
		return gridNodes.stream().map(n -> n.getColour_Grid()).collect(Collectors.toSet());
	}

	public boolean addNode(GridNode gridNode) {
		if (gridNode != null && !gridNodes.contains(gridNode) && gridNode.getColor() == this.color) {
			return gridNodes.add(gridNode);
		}
		return false;
	}

	public boolean hasNode(GridNode gridNode) {
		if (gridNode == null)
			return false;
		return gridNodes.stream().anyMatch(n -> n.getId() == gridNode.getId());
	}

	public void display() {
		gridNodes.forEach(n -> System.out.println(n.toString()));
	}

	public int size() {
		return gridNodes.size();
	}

	@Override
	public int compareTo(GridBlock gridBlock) {
		// TODO Auto-generated method stub
		return gridBlock.size() - this.size();
	}

}
