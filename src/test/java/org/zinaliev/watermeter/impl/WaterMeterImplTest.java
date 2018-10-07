package org.zinaliev.watermeter.impl;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.zinaliev.watermeter.impl.WaterMeterImpl.MAX_HILL_HEIGHT;
import static org.zinaliev.watermeter.impl.WaterMeterImpl.MAX_LANDSCAPE_LEN;
import static org.zinaliev.watermeter.impl.WaterMeterImpl.MIN_HILL_HEIGHT;

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

  @Test(expected = IllegalArgumentException.class)
  public void testCalculateWaterAmount_MinHillHeightViolated_ThrowsException() {
    waterMeter.calculateWaterAmount(new int[]{MIN_HILL_HEIGHT - 1});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCalculateWaterAmount_MaxHillHeightViolated_ThrowsException() {
    waterMeter.calculateWaterAmount(new int[]{MAX_HILL_HEIGHT + 1});
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

  @Test
  public void testCalculateWaterAmount_SinglePitLandscape() {
    assertEquals(1, waterMeter.calculateWaterAmount(new int[]{0, 1, 0, 1, 0}));
  }

  @Test
  public void testCalculateWaterAmount_EqualPitHillSequencedLandscape() {
    assertEquals(5, waterMeter.calculateWaterAmount(new int[]{5, 6, 5, 6, 5, 6, 5, 6, 5, 6, 5, 6}));
  }

  @Test
  public void testCalculateWaterAmount_DifferentHeightPitsLandscape() {

//                                                                  #  ~  ~  #
//                                                                  #  #  ~  #
//                                                            #  ~  #  #  #  #  ~  ~  #
//                                                            #  ~  #  #  #  #  #  #  #
//                                                            #  #  #  #  #  #  #  #  #  #

    assertEquals(7, waterMeter.calculateWaterAmount(new int[]{3, 1, 5, 4, 3, 5, 2, 2, 3, 1}));
  }

  @Test
  public void testCalculateWaterAmount_TaskLandscape() {

//                                                             #  ~  ~  ~  #
//                                                             #  ~  ~  #  #  #
//                                                             #  ~  #  #  #  #  ~  #
//                                                             #  #  #  #  #  #  ~  #
//                                                             #  #  #  #  #  #  ~  #  #

    assertEquals(9, waterMeter.calculateWaterAmount(new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1}));
  }

  private int randomHill() {
    return MIN_HILL_HEIGHT + random.nextInt(MAX_HILL_HEIGHT - MIN_HILL_HEIGHT);
  }
}