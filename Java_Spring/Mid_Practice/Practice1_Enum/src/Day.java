public enum Day{
    SUNDAY ("WEEKDAY"),
    MONDAY ("WEEKDAY"),
    TUESDAY ("WEEK DAY"),
    WEDNESDAY ("WEEK DAY"),
    THURSDAY ("WEEK DAY"),
    FRIDAY ("WEEK END"),
    SATURDAY ("WEEK END"),
    OFFDAY;

    private String param;

    Day(){
        this.param="Unknown";
        System.out.println("This is a default constructor.");
    }

    Day(String day){
        this.param=day;
        System.out.println("This is a parameterized constructor.");
    }

    public String getDay(){
        return this.param;
    }

}
