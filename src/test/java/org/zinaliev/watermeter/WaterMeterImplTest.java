package org.zinaliev.watermeter;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.zinaliev.watermeter.WaterMeterImpl.MAX_HILL_SIZE;
import static org.zinaliev.watermeter.WaterMeterImpl.MAX_LANDSCAPE_LEN;
import static org.zinaliev.watermeter.WaterMeterImpl.MIN_HILL_SIZE;

public class WaterMeterImplTest {

  private final Random random = new Random();

  private final WaterMeterImpl waterMeter = new WaterMeterImpl();

  @Test(expected = IllegalArgumentException.class)
  public void testCalculateWaterAmount_NullLandscape_ThrowsException() {
    waterMeter.calculateWaterAmount(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCalculateWaterAmount_OversizedLandscape_ThrowsException() {
    waterMeter.calculateWaterAmount(new int[MAX_LANDSCAPE_LEN + 1]);
  }

  @Test
  public void testCalculateWaterAmount_ZeroHillLandscape_ReturnsZero() {
    assertEquals(0, waterMeter.calculateWaterAmount(new int[0]));
  }

  @Test
  public void testCalculateWaterAmount_OneHillLandscape_ReturnsZero() {
    assertEquals(0, waterMeter.calculateWaterAmount(new int[]{randomHill()}));
  }

  @Test
  public void testCalculateWaterAmount_TwoHillsLandscape_ReturnsZero() {
    assertEquals(0, waterMeter.calculateWaterAmount(new int[]{randomHill(), randomHill()}));
  }

  private int randomHill() {
    return MIN_HILL_SIZE + random.nextInt(MAX_HILL_SIZE - MIN_HILL_SIZE);
  }
}