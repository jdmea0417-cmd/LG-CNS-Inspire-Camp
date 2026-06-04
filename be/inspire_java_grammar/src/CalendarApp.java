import test.MyCalendar;

public class CalendarApp {
    public static void main(String[] args) {
        MyCalendar calendar = new MyCalendar();

        // bad case
        // calendar.year = -2026;
        // System.out.println(calendar.year);

        calendar.setYear(-2016);
        int year = calendar.getYear();
        System.out.println("년도 : "+year);
    }
}
