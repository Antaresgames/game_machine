package com.game_machine.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Grid {

	private float max;
	private int cellSize = 0;
	private float convFactor;
	private int width;
	private int cellCount;

	private ConcurrentHashMap<String, GridValue> deltaIndex = new ConcurrentHashMap<String, GridValue>();
	private ConcurrentHashMap<String, GridValue> objectIndex = new ConcurrentHashMap<String, GridValue>();
	private ConcurrentHashMap<Integer, ConcurrentHashMap<String, GridValue>> cells = new ConcurrentHashMap<Integer, ConcurrentHashMap<String, GridValue>>();
	private ConcurrentHashMap<Integer, Set<Integer>> cellsCache = new ConcurrentHashMap<Integer, Set<Integer>>();
	private ConcurrentHashMap<String, ArrayList<GridValue>> neighborsCache = new ConcurrentHashMap<String, ArrayList<GridValue>>();
	private ConcurrentHashMap<String, Long> lastNeighborsCall = new ConcurrentHashMap<String, Long>();

	public Grid(int max, int cellSize) {
		this.max = max;
		this.cellSize = cellSize;
		this.convFactor = 1.0f / this.cellSize;
		this.width = (int) (this.max / this.cellSize);
		this.cellCount = this.width * this.width;
	}

	public int getWidth() {
		return this.width;
	}

	public int getCellCount() {
		return this.cellCount;
	}

	public Set<Integer> cellsWithinRadius(float x, float y) {
		int cellHash = hash(x, y);
		return cellsWithinRadius(cellHash, x, y);
	}

	public Set<Integer> cellsWithinRadius(int cellHash, float x, float y) {
		int key = cellHash;
		Set<Integer> cells = cellsCache.get(key);
		if (cells != null) {
			return cells;
		}
		cells = new HashSet<Integer>();

		int offset = this.cellSize;

		int startX = (int) (x - offset);
		int startY = (int) (y - offset);
		int endX = (int) (x + offset);
		int endY = (int) (y + offset);

		for (int rowNum = startX; rowNum <= endX; rowNum += this.cellSize) {
			for (int colNum = startY; colNum <= endY; colNum += this.cellSize) {
				if (rowNum >= 0 && colNum >= 0) {
					cells.add(hash(rowNum, colNum));
				}
			}
		}
		cellsCache.put(key, cells);
		return cells;
	}

	public ArrayList<GridValue> neighbors(float x, float y, String entityType) {
		int myCell = hash(x, y);
		return neighbors(myCell, x, y, entityType);
	}

	public ArrayList<GridValue> neighbors(int myCell, float x, float y, String entityType) {
		ArrayList<GridValue> result;

		GridValue[] gridValues;
		result = new ArrayList<GridValue>();
		Set<Integer> cells = cellsWithinRadius(myCell, x, y);
		for (int cell : cells) {
			gridValues = gridValuesInCell(cell);
			if (gridValues != null) {
				for (GridValue gridValue : gridValues) {
					if (gridValue != null) {
						if (entityType == null) {
							result.add(gridValue);
						} else if (gridValue.entityType.equals(entityType)) {
							result.add(gridValue);
						}
					}
				}
			}
		}
		return result;
	}

	public GridValue[] gridValuesInCell(int cell) {
		ConcurrentHashMap<String, GridValue> cellGridValues = cells.get(cell);

		if (cellGridValues != null) {
			GridValue[] a = new GridValue[cellGridValues.size()];
			cellGridValues.values().toArray(a);
			return a;
			// return cellGridValues.values();
		} else {
			return null;
		}
	}

	public void updateFromDelta(GridValue[] gridValues) {
		for (GridValue gridValue : gridValues) {
			if (gridValue != null) {
				objectIndex.put(gridValue.id, gridValue);
			}
		}
	}

	public GridValue[] currentDelta() {
		GridValue[] a = new GridValue[deltaIndex.size()];
		deltaIndex.values().toArray(a);
		deltaIndex.clear();
		return a;
	}

	public GridValue get(String id) {
		return objectIndex.get(id);
	}

	public void remove(String id) {
		GridValue indexValue = objectIndex.get(id);
		if (indexValue != null) {
			int cell = indexValue.cell;
			ConcurrentHashMap<String, GridValue> cellGridValues = cells.get(cell);
			if (cellGridValues != null) {
				cellGridValues.remove(id);
			}
			objectIndex.remove(id);
		}
	}

	public Boolean set(String id, float x, float y, float z, String entityType) {
		GridValue oldValue = objectIndex.get(id);

		if (oldValue != null) {
			if (oldValue.x == x && oldValue.y == y) {
				return false;
			}
		}

		int cell = hash(x, y);
		if (!cells.containsKey(cell)) {
			cells.put(cell, new ConcurrentHashMap<String, GridValue>());
		}

		GridValue gridValue;
		gridValue = new GridValue(id, cell, x, y, z, entityType);

		if (oldValue == null) {
			objectIndex.put(id, gridValue);
		} else {
			if (oldValue.cell != cell) {
				cells.get(oldValue.cell).remove(id);
			}
			objectIndex.replace(id, gridValue);
		}
		cells.get(cell).put(id, gridValue);

		deltaIndex.put(id, gridValue);

		return true;
	}

	public int hash2(float x, float y) {
		return (int) (Math.floor(x / this.cellSize) + Math.floor(y / this.cellSize) * width);
	}

	public int hash(float x, float y) {
		return (int) ((x * this.convFactor)) + (int) ((y * this.convFactor)) * this.width;
	}
}
