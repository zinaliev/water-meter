package org.zinaliev.watermeter.impl;

import org.junit.Test;
import org.zinaliev.watermeter.LandscapeVisualizer;

import static org.junit.Assert.assertEquals;
import static org.zinaliev.watermeter.impl.LandscapeVisualizerImpl.LIMITS_EXCEEDED_TEXT;
import static org.zinaliev.watermeter.impl.LandscapeVisualizerImpl.MAX_HILLS_COUNT;
import static org.zinaliev.watermeter.impl.LandscapeVisualizerImpl.MAX_HILL_HEIGHT;

public class LandscapeVisualizerImplTest {

  private final LandscapeVisualizer visualizer = new LandscapeVisualizerImpl();

  @Test
  public void testDraw_LandscapeExcedingMaxHillsCount_ReturnsWarningText() {
    visualizer.registerLandscape(new int[MAX_HILLS_COUNT + 1]);

    assertEquals(LIMITS_EXCEEDED_TEXT, visualizer.draw());
  }

  @Test
  public void testDraw_LandscapeWithExceedingHillsHeight_ReturnsWarningText() {
    visualizer.registerLandscape(new int[]{MAX_HILL_HEIGHT + 1});

    assertEquals(LIMITS_EXCEEDED_TEXT, visualizer.draw());
  }

  @Test
  public void testDraw_LandscapeOfZeroHeightHills_ReturnsAStringOfSpaces() {
    visualizer.registerLandscape(new int[]{0, 0});

    assertEquals("", visualizer.draw().trim());
  }

  @Test
  public void testDraw_LandscapeWithNoWater() {
    visualizer.registerLandscape(new int[]{1, 1, 1});
    assertEquals("# # # " + System.lineSeparator(), visualizer.draw());
  }

  @Test
  public void testDraw_LandscapeWithWater() {
    visualizer.registerLandscape(new int[]{1, 0, 1});
    visualizer.setWaterLevel(1, 1);
    assertEquals("# ~ # " + System.lineSeparator(), visualizer.draw());
  }

  @Test
  public void testDraw_LandscapeWithWaterExceedingHillMaxHeight_ShowsInvalidlySolvedLandscape() {
    visualizer.registerLandscape(new int[]{1, 1, 1});
    visualizer.setWaterLevel(1, 1);

    String expected = "  ~   " + System.lineSeparator() +
                      "# # # " + System.lineSeparator();

    assertEquals(expected, visualizer.draw());
  }


}