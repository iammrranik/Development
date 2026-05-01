public enum Day {
    SUNDAY ("WEEKDAY"),
    MONDAY ("WEEKDAY"),
    TUESDAY ("WEEKDAY"),
    WEDNESDAY ("WEEKDAY"),
    THURSDAY ("WEEKDAY"),
    FRIDAY ("WEEKEND"),
    SATURDAY ("WEEKEND"),
    OFFDAY;

    Day(){
        System.out.println("This is a default constructor.");
    }

    Day(String type){
        System.out.println("This is a parametarized constructor.");
    }

}
