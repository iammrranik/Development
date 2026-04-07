public class Car{
    public String model;
    public boolean isEngineOn;
    public Car(String model){
        this.model = model;
        this.isEngineOn = false;

    }

    class Engine{
        void start(){
            if(!isEngineOn){
                isEngineOn=true;
                System.out.println(model + " engine started.");
            }else{
                System.out.println(model + " engine is already on.");
            }
        }

        void stop(){
            if(isEngineOn){
                isEngineOn = false;
                System.out.println(model + " engine stopped.");
            }else{
                System.out.println(model + " engine is already stopped.");
            }
        }
    }
}
