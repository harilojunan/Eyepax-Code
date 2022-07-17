package com.eyepax.color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Color_Grid_Challenge {

	public static final char[] COLORS = { 'R', 'B', 'G','W','Y','O' };

	private HashMap<Color_Grid, GridNode> challenge;

	private int column;
	private int row;

	public void challenge_initialize(int columns, int rows) {
		this.column = columns;
		this.row = rows;

		Random random = new Random();
		this.challenge = new HashMap<>();
		for (int i = 0; i < columns * rows; i++) {
			int Cord_x = i % columns;
			int Cord_y = (int) Math.floor(i / columns);
			this.challenge.put(new Color_Grid(Cord_x, Cord_y),
					new GridNode(i, Cord_x, Cord_y, COLORS[random.nextInt(3)]));
		}

	}

	public GridNode getGridNode(int Cord_x, int Cord_y) {
		return this.challenge.get(new Color_Grid(Cord_x, Cord_y));
	}

	public void printChallenge() {
		for (int y = 0; y < row; y++) {
			for (int x = 0; x < column; x++) {
				if (x == this.column - 1) {
					System.out.println(getGridNode(x, y).getColor());
				} else {
					System.out.print(getGridNode(x, y).getColor() + ", ");
				}
			}
		}
	}

	public void PrintChallengeWithGridBlock(GridBlock gridBlock) {
		for (int y = 0; y < row; y++) {
			for (int x = 0; x < column; x++) {
				GridNode node = getGridNode(x, y);
				char color = gridBlock.hasNode(node) ? '=' : node.getColor();
				if (x == this.column - 1) {
					System.out.println(color);
				} else {
					System.out.print(color + ",");
				}
			}
		}
	}

	private List<GridNode> detectAdjacentNodes(GridNode node, GridBlock gridBlock) {

		List<GridNode> nodes = new ArrayList<>();

		Color_Grid color_Grid = node.getColour_Grid();

		GridNode northCord = this.challenge.get(color_Grid.northCord());
		if (northCord != null && northCord.getColor() == node.getColor() && !gridBlock.hasNode(northCord)) {
			nodes.add(northCord);
		}

		GridNode eastCord = this.challenge.get(color_Grid.eastCord());
		if (eastCord != null && eastCord.getColor() == node.getColor() && !gridBlock.hasNode(eastCord)) {
			nodes.add(eastCord);
		}

		GridNode westCord = this.challenge.get(color_Grid.westCord());
		if (westCord != null && westCord.getColor() == node.getColor() && !gridBlock.hasNode(westCord)) {
			nodes.add(westCord);
		}

		GridNode southCord = this.challenge.get(color_Grid.southCord());
		if (southCord != null && southCord.getColor() == node.getColor() && !gridBlock.hasNode(southCord)) {
			nodes.add(southCord);
		}
		return nodes;
	}

	public GridBlock getBlockSequence(int Cord_x, int Cord_y) {

		Color_Grid firstColour_Grid = new Color_Grid(Cord_x, Cord_y);

		GridNode firstGrid_Node = this.challenge.get(firstColour_Grid);

		GridBlock gridBlock = new GridBlock(firstGrid_Node.getColor());
		gridBlock.addNode(firstGrid_Node);

		LinkedList<GridNode> inspectNodes = new LinkedList<>();
		inspectNodes.addAll(detectAdjacentNodes(firstGrid_Node, gridBlock));

		while (!inspectNodes.isEmpty()) {
			GridNode nextInspectNode = inspectNodes.remove();
			gridBlock.addNode(nextInspectNode);
			inspectNodes.addAll(detectAdjacentNodes(nextInspectNode, gridBlock));
		}
		return gridBlock;
	}

	public GridBlock getLargestBlock() {
		Set<Color_Grid> allColor_Grids = new HashSet<>(this.challenge.keySet());
		List<GridBlock> allGridBlocks = new ArrayList<>();

		while (!allColor_Grids.isEmpty()) {
			Color_Grid color_Grid = allColor_Grids.iterator().next();
			GridBlock newGridBlock = getBlockSequence(color_Grid.getCord_x(), color_Grid.getCord_y());
			allGridBlocks.add(newGridBlock);
			allColor_Grids.removeAll(newGridBlock.allColour_Grids());
		}
		Collections.sort(allGridBlocks);
		return allGridBlocks.size() > 0 ? allGridBlocks.get(0) : null;
	}

	public static void main(String[] args) {
		int WIDTH = 20;
		int HEIGHT = 24;

		Color_Grid_Challenge challenge = new Color_Grid_Challenge();
		challenge.challenge_initialize(WIDTH, HEIGHT);
		challenge.printChallenge();

		GridBlock gridBlock = challenge.getLargestBlock();

		if (gridBlock != null) {
			System.out.println("THIS_IS_THE_LARGEST_BLOCK____&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println("THIS_IS_THE_LARGEST_BLOCK____&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println("THIS_IS_THE_LARGEST_BLOCK____&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println("THIS_IS_THE_LARGEST_BLOCK____&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println("THIS_IS_THE_LARGEST_BLOCK____&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println("THIS_IS_THE_LARGEST_BLOCK____&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			challenge.PrintChallengeWithGridBlock(gridBlock);

		}

	}

}
