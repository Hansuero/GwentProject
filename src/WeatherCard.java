import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class WeatherCard extends JLabel
{
    private String weatherName;
    public enum WeatherType
    {

        CLEAR,
        ECLIPSE,
        FOG,
        HEATWAVE,
        NICEBREEZE,
        RAIN,
        WIND
    }

    private WeatherType Weathertype;
    boolean isWeather;

    // Methods
    WeatherCard(String type)
    {
        super();
        this.isWeather = true;
        weatherName = type;
        this.setSize(108, 200);
        ImageIcon icon = new ImageIcon("res/image/" + type.toLowerCase() +".jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icon);
        if (type.contains("CLEAR"))
        {
            this.Weathertype = WeatherType.CLEAR;
        } else if (type.contains("ECLIPSE"))
        {
            this.Weathertype = WeatherType.ECLIPSE;
        } else if (type.contains("FOG"))
        {
            this.Weathertype = WeatherType.FOG;
        } else if (type.contains("HEATWAVE"))
        {
            this.Weathertype = WeatherType.HEATWAVE;
        } else if (type.contains("NICEBREEZE"))
        {
            this.Weathertype = WeatherType.NICEBREEZE;
        } else if (type.contains("RAIN"))
        {
            this.Weathertype = WeatherType.RAIN;
        } else if (type.contains("WIND"))
        {
            this.Weathertype = WeatherType.WIND;
        }

    }

    WeatherCard(WeatherCard card)
    {
        super();
        isWeather = true;
        weatherName = card.weatherName;
        this.setSize(50, 60);
        ImageIcon icon = new ImageIcon("res/image/"+ weatherName +".jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icon);
        this.Weathertype = card.Weathertype;
    }

    public WeatherType getWeatherType()
    {
        return this.Weathertype;
    }
}