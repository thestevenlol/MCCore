package me.stevenlol.core.util.formatting;

public class IntegerToTimeString {

    public static String convert(int time) {
        int days = time / 86400;
        time -= days * 86400;
        int hours = time / 3600;
        time -= hours * 3600;
        int minutes = time / 60;
        time -= minutes * 60;
        int seconds = time;

        StringBuilder builder = new StringBuilder();
        if (days > 0) {
            builder.append(days).append(days == 1 ? " day " : " days ");
        }
        if (hours > 0) {
            builder.append(hours).append(hours == 1 ? " hour " : " hours ");
        }
        if (minutes > 0) {
            builder.append(minutes).append(minutes == 1 ? " minute " : " minutes ");
        }
        if (seconds > 0) {
            builder.append(seconds).append(seconds == 1 ? " second" : " seconds");
        }
        return builder.toString();
    }

}
