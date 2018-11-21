import java.io.*;
import java.util.*;

public class working_system {
    public static Map<Integer, stop> stopList = new HashMap<>();
    public static Map<Integer, bus> busList = new HashMap<>();
    public static Map<Integer, route> routeList = new HashMap<>();
    private static Comparator<event> comparator = new eventTimeComparator();
    public static Queue<event> eventList = new PriorityQueue<event>(1,comparator);


    public static void main(String[] args) throws IOException {

        try {

            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // REMEMBER TO CHAGNE BACK TO READING FROM args !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            String file_path = "../Project_Testing/test10_perimeter_scenic.txt";
            File inputFile = new File(file_path);
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            //File inputFile = new File(args[0]);
            BufferedReader inputText = new BufferedReader(new FileReader(inputFile));
            String inputCommand = "";
            //System.out.println("the input command is:");
            while ((inputCommand = inputText.readLine()) != null) {
                //System.out.println(inputCommand);
                String[] input = inputCommand.split(",");
                /*
                for (int i = 0; i < input.length; i++) {
                    System.out.println(input[i]);
                }*/
                switch(input[0]) {
                    case "add_stop":
                        stop newStop = new stop(Integer.parseInt(input[1]), input[2],
                                Integer.parseInt(input[3]),
                                Double.parseDouble(input[4]),
                                Double.parseDouble(input[5]));
                        stopList.put(Integer.parseInt(input[1]), newStop);
                        break;
                    case "add_route":
                        route newRoute = new route(Integer.parseInt(input[1]),
                                Integer.parseInt(input[2]),
                                input[3]);
                        routeList.put(Integer.parseInt(input[1]), newRoute);
                        break;
                    case"add_bus":
                        bus newBus = new bus(Integer.parseInt(input[1]),
                                Integer.parseInt(input[2]),
                                Integer.parseInt(input[3]),
                                Integer.parseInt(input[4]),
                                Integer.parseInt(input[5]),
                                Integer.parseInt(input[6]),
                                Integer.parseInt(input[7]),
                                Integer.parseInt(input[8]));
                        busList.put(Integer.parseInt(input[1]), newBus);
                        break;
                    case"add_event":
                        event newEvent = new event(Integer.parseInt(input[1]),
                                input[2],
                                Integer.parseInt(input[3]));
                        busList.get(Integer.parseInt((input[3]))).setBusStartTime(Integer.parseInt(input[1]));
                        eventList.add(newEvent);
                        break;
                    case "extend_route":
                        route routeSelected = routeList.get(Integer.parseInt(input[1]));
                        routeSelected.extend_route(Integer.parseInt(input[2]));
                        break;
                    case "add_depot":
                        break;
                }
            }
        } catch (IOException e) {
           e.printStackTrace();
        }

        //BufferedWriter writer = new BufferedWriter(new FileWriter("output_temp"));
        //PrintWriter printWriter = new PrintWriter(writer);

        for (int i = 0; i < 20; i++) {
            event eventNow = eventList.poll();
            //System.out.println(eventNow.getLogicTime());
            int busID = eventNow.getID();
            bus busNow = busList.get(busID);
            System.out.printf("b:%d->s:%d@%d//p:%d/f:%d\n",
                    busID,
                    busNow.getBusNextLocation(),
                    busNow.getNextLocationTime(), 0, 0);
            //printWriter.printf("b:%d->s:%d@%d//p:%d/f:%d\n",
            //        busID,
            //        busNow.getBusNextLocation(),
            //        busNow.getNextLocationTime(), 0, 0);
            int nextTime = busNow.getNextLocationTime();
            event nextEvent = new event(nextTime, "move_bus", busID);
            eventList.add(nextEvent);
            busNow.moveBus();
        }
        //printWriter.close();
   }

   public static class eventTimeComparator implements Comparator<event> {
        public int compare(event x, event y) {
            //if (x.getLogicTime() != y.getLogicTime()) {
               return (x.getLogicTime() - y.getLogicTime());
            //} else {
            //    return (x.getID() - y.getID());
           // }
        }
   }
}
