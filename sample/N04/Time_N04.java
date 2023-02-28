package N04;

public class Time_N04 {
    private int hour;
    private int minute;
    private int second;

    public Time_N04(int hour, int minute, int second) {
        this.hour = hour;
        if (minute > 60) {
            this.minute = minute % 60;
            this.hour = this.hour + (minute / 60);
        }
        this.second = second;
    }

    public Time_N04() {
        this.minute = 0;
        this.second = 0;
    }

    public void setTime(int hour, int minute, int second) {
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }

    public void printTime() {
        System.out.println(getHour() + ":" + getMinute() + ":" + getSecond());
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = (hour >= 0 && hour <= 24 ? hour : 0);
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = (minute >= 0 && minute <= 60 ? minute : 0);
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = (second >= 0 && second <= 60 ? second : 0);
    }
}

class test {
    public static void main(String[] args) {
        Time_N04 a = new Time_N04(1, 2, 3);
        a.printTime();
    }
}