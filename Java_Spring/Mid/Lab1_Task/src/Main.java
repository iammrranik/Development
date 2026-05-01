enum TrafficLight {
    RED,
    YELLOW,
    GREEN;

    public TrafficLight nxtLight(TrafficLight curLight){
        if(curLight.equals(TrafficLight.RED)){
            return TrafficLight.GREEN;
        }else if(curLight.equals(TrafficLight.GREEN)){
            return TrafficLight.YELLOW;
        }else{
            return TrafficLight.RED;
        }
    }

}

public class Main {
    public static void main(String[] args) {

        TrafficLight l=TrafficLight.RED;
        for (int i = 0; i < 10; i++) {
            System.out.print(l);
            if(i<9){
                System.out.print(" -> ");
            }
            l=l.nxtLight(l);
        }

    }
}