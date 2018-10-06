package org.zinaliev.watermeter;

public class WaterMeterImpl implements WaterMeter {

  public static final int MAX_LANDSCAPE_LEN = 32000;
  public static final int MAX_HILL_SIZE = 32000;
  public static final int MIN_HILL_SIZE = 0;

  @Override
  public long calculateWaterAmount(int[] landscape) {
    validate(landscape);

    int leftHillIndex = 0;
    int rightHillIndex = landscape.length - 1;

    while (rightHillIndex - leftHillIndex > 1) {

    }

    return 0;
  }

  private void validate(int[] landscape) {
    if (landscape == null)
      throw new IllegalArgumentException("landscape array can not be null");

    if (landscape.length > MAX_LANDSCAPE_LEN)
      throw new IllegalArgumentException("landscape length - " + landscape.length + " exceeds max allowed value - " + MAX_LANDSCAPE_LEN);
  }
}
