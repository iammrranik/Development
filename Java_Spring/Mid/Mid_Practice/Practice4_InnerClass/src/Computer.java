public class Computer {
    private String brand;
    private String model;
    private OperatingSystem os;

    public OperatingSystem getOs() {
        return os;
    }

    public Computer(String brand, String model, String osName){
        this.brand = brand;
        this.model = model;
        this.os = new OperatingSystem(osName);
    }

    class OperatingSystem {
        private String osName;

        public OperatingSystem(String osName){
            this.osName = osName;
        }

        public String getOsName() {
            return osName;
        }

        @Override
        public String toString(){
            return "Computer Model: " + model + ", OS: " + osName;
        }
    }

    static class USB{
        private String type;

        public USB(String type){
            this.type = type;
        }

        @Override
        public String toString(){
            return "USB Type: " + type;
        }
    }

}
