package org.zinaliev.watermeter;

public interface LandscapeVisualizer {

  void registerLandscape(int[] landscape);

  void setWaterLevel(int index, int waterDepth);

  String draw();
}
