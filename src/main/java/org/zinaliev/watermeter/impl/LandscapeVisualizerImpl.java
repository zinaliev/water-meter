package org.zinaliev.watermeter.impl;

import org.zinaliev.watermeter.LandscapeVisualizer;

public class LandscapeVisualizerImpl implements LandscapeVisualizer {

  public static final int MAX_HILLS_COUNT = 50;
  public static final int MAX_HILL_HEIGHT = 20;
  public static final String LIMITS_EXCEEDED_TEXT = "Visualizer limitations exceeded - can not draw the landscape";

  private int[] landscape;
  private int[] water;

  @Override
  public void registerLandscape(int[] landscape) {
    this.landscape = landscape;
    this.water = new int[landscape.length];
  }

  @Override
  public void setWaterLevel(int index, int depth) {
    water[index] = depth;
  }

  @Override
  public String draw() {
    int maxHeight = getMaxHeight();

    if(landscape.length > MAX_HILLS_COUNT || maxHeight > MAX_HILL_HEIGHT){
      return LIMITS_EXCEEDED_TEXT;
    }

    StringBuilder sb = new StringBuilder();

    for (int h = maxHeight; h > 0; h--) {
      for(int i = 0; i < landscape.length; i++){
        sb.append(getCellType(h, i).getSymbol());
        sb.append(' ');
      }

      sb.append(System.lineSeparator());
    }

    return sb.toString();
  }

  private int getMaxHeight() {
    int maxHeight = Integer.MIN_VALUE;

    if(landscape == null)
      return maxHeight;

    for(int i = 0; i < landscape.length; i++){
      int curHeight = landscape[i] + water[i];

      if(curHeight > maxHeight)
        maxHeight = curHeight;
    }

    return maxHeight;
  }

  private CellType getCellType(int h, int i) {
    if(h > landscape[i] + water[i])
      return CellType.AIR;
    else if (h > landscape[i])
      return CellType.WATER;
    else
      return CellType.GROUND;
  }

  private enum CellType {
    AIR(' '),
    WATER('~'),
    GROUND('#');

    private final char symbol;

    CellType(char symbol) {
      this.symbol = symbol;
    }

    public char getSymbol() {
      return symbol;
    }
  }
}
