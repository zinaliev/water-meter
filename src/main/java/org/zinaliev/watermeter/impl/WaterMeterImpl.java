package org.zinaliev.watermeter.impl;

import org.zinaliev.watermeter.LandscapeVisualizer;
import org.zinaliev.watermeter.WaterMeter;

public class WaterMeterImpl implements WaterMeter {

  public static final int MAX_HILLS_COUNT = 32000;
  public static final int MAX_HILL_HEIGHT = 32000;
  public static final int MIN_HILL_HEIGHT = 0;

  private final LandscapeVisualizer visualizer;

  public WaterMeterImpl() {
    this(null);
  }

  public WaterMeterImpl(LandscapeVisualizer visualizer) {
    this.visualizer = visualizer;
  }

  @Override
  public long calculateWaterAmount(int[] landscape) {
    validate(landscape);
    if (visualizer != null)
      visualizer.registerLandscape(landscape);

    int result = 0;

    int leftHillIndex = 0;
    int rightHillIndex = landscape.length - 1;

    validateHillHeight(landscape, leftHillIndex);
    validateHillHeight(landscape, rightHillIndex);

    int leftEdgeHillHeight;
    int rightEdgeHillHeight;

    int curHillIndex;
    int curWaterLevel = Integer.MIN_VALUE;

    while (rightHillIndex - leftHillIndex > 1) {

      leftEdgeHillHeight = Math.max(landscape[leftHillIndex], curWaterLevel);
      rightEdgeHillHeight = Math.max(landscape[rightHillIndex], curWaterLevel);

      if (leftEdgeHillHeight <= rightEdgeHillHeight)
        curHillIndex = ++leftHillIndex;
      else
        curHillIndex = --rightHillIndex;

      curWaterLevel = Math.min(leftEdgeHillHeight, rightEdgeHillHeight);

      validateHillHeight(landscape, curHillIndex);

      int waterDepth = curWaterLevel - landscape[curHillIndex];

      if (waterDepth > 0) {
        result += waterDepth;

        if (visualizer != null)
          visualizer.setWaterLevel(curHillIndex, waterDepth);
      }
    }

    return result;
  }

  private void validateHillHeight(int[] landscape, int hillIndex) {
    if (landscape.length == 0)
      return;

    if (hillIndex < 0 || hillIndex >= landscape.length)
      return;

    if (landscape[hillIndex] < MIN_HILL_HEIGHT)
      throw new IllegalArgumentException("hill height at position " + hillIndex + " = " + landscape[hillIndex] + " is less than min allowed value");

    if (landscape[hillIndex] > MAX_HILL_HEIGHT)
      throw new IllegalArgumentException("hill height at position " + hillIndex + " = " + landscape[hillIndex] + " is grater than max allowed value");
  }

  private void validate(int[] landscape) {
    if (landscape == null)
      throw new IllegalArgumentException("landscape array can not be null");

    if (landscape.length > MAX_HILLS_COUNT)
      throw new IllegalArgumentException("hills count - " + landscape.length + " exceeds max allowed value - " + MAX_HILLS_COUNT);
  }
}
