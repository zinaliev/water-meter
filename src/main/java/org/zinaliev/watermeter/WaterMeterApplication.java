package org.zinaliev.watermeter;

import org.zinaliev.watermeter.impl.LandscapeVisualizerImpl;
import org.zinaliev.watermeter.impl.WaterMeterImpl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.zinaliev.watermeter.impl.LandscapeVisualizerImpl.MAX_HILLS_COUNT;
import static org.zinaliev.watermeter.impl.LandscapeVisualizerImpl.MAX_HILL_HEIGHT;

public class WaterMeterApplication {

  public static final String AUTO_GENERATE_MODE = "auto";
  public static final String HILL_DELIMITER = " ";

  private static final Random random = new Random();

  public static void main(String[] args) throws IOException {

    try (Scanner input = new Scanner(System.in);
         BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out))) {

      boolean isAutoMode = args.length == 1 && args[0].toLowerCase().equals(AUTO_GENERATE_MODE);


      LandscapeVisualizer visualizer = new LandscapeVisualizerImpl();
      WaterMeter waterMeter = new WaterMeterImpl(visualizer);

      writeLine("Press [CONTROL + C] to terminate", output);

      while (true) {
        writeLine("---", output);

        int[] landscape;

        if (isAutoMode) {
          writeLine("Press [ENTER] to generate a new landscape", output);
          input.nextLine();
          landscape = generateLandscape();

          writeLine(landscape.length + " hill(s) landscape generated:", output);
          writeLine(Arrays.stream(landscape)
                  .mapToObj(String::valueOf)
                  .collect(Collectors.joining(", ")),
              output);

        } else {

          writeLine("Type space separated hills' heights to describe a landscape and press [ENTER]", output);

          String inputLine = input.nextLine();
          String[] hills = inputLine.split(HILL_DELIMITER);

          try {
            landscape = Arrays.stream(hills).mapToInt(Integer::parseInt).toArray();
          } catch (Exception e) {
            writeLine(e.getClass() + e.getMessage(), output);
            continue;
          }
        }

        writeLine(output);

        try {
          long water = waterMeter.calculateWaterAmount(landscape);
          writeLine(visualizer.draw(), output);
          writeLine("Water: " + water, output);
        } catch (IllegalArgumentException iae) {
          writeLine(iae.getMessage(), output);
        }
      }
    }
  }

  private static int[] generateLandscape() {
    int[] result = new int[random.nextInt(MAX_HILLS_COUNT)];

    for (int i = 0; i < result.length; i++)
      result[i] = random.nextInt(MAX_HILL_HEIGHT);

    return result;
  }

  private static void writeLine(BufferedWriter output) throws IOException {
    writeLine("", output);
  }

  private static void writeLine(String text, BufferedWriter output) throws IOException {
    output.write(text);
    output.newLine();
    output.flush();
  }
}
