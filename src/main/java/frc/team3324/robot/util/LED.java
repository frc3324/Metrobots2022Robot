package frc.team3324.robot.util;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;


public class LED {

    public enum Color {
        RED,
        ORANGE,
        YELLOW,
        GREEN,
        BLUE,
        PURPLE
    }


    private AddressableLED led = new AddressableLED(9);
    private AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(60);

    public LED() {
        led.setLength(ledBuffer.getLength());
        led.setData(ledBuffer);
        led.start();
    }

    public void setColor(Color color) {
        int[] RGB;

        switch (color) {
            default:
            case RED:
                RGB = new int[] {255, 0, 0};
                break;
            case ORANGE:
                RGB = new int[] {255, 165, 0};
                break;
            case YELLOW:
                RGB = new int[] {0, 255, 255};
                break;
            case GREEN:
                RGB = new int[] {0, 255, 0};
                break;
            case BLUE:
                RGB = new int[] {0, 0, 255};
                break;
            case PURPLE:
                RGB = new int[] {255, 0, 255};
                break;
        }
        

        for (int i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, RGB[0], RGB[1], RGB[2]);
        }

        led.setData(ledBuffer);
    }
}