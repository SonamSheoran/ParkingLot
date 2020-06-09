package Assignment.ParkingLot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
	int h=0;
	int MAX_SIZE = 0;

    private class Car {
        String regNo;
        String hour;
        String slotNo;
        public Car(String regNo, String hour,String slotNo) {
            this.regNo = regNo;
            this.hour = hour;
            this.slotNo=slotNo;
        }
        
        public Car() {
        	
        }
    }
    // Available slots list
    ArrayList<Integer> availableSlotList;
    // Map of Slot, Car
    Map<String, Car> map1;
    // Map of RegNo, Slot
    Map<String, String> map2;
    // Map of slot, List of RegNo
   Map<String, ArrayList<String>> map3;
    // MAp of regNo, car
    Map<String,Car> map4;


    public void createParkingLot(String lotCount) {
        try {
            this.MAX_SIZE = Integer.parseInt(lotCount);
        } catch (Exception e) {
            System.out.println("Invalid lot count");
            System.out.println();
        }
        this.availableSlotList = new ArrayList<Integer>() {};
        for (int i=1; i<= this.MAX_SIZE; i++) {
            availableSlotList.add(i);
        }
        this.map1 = new HashMap<String, Car>();
        this.map2 = new HashMap<String, String>();
        this.map3 = new HashMap<String, ArrayList<String>>();
        this.map4 = new HashMap<String, Car>();
        System.out.println("Created parking lot with " + lotCount + " slots");
        System.out.println();
    }
    public void park(String regNo) {
    	String hour = null;
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map1.size() == this.MAX_SIZE) {
            System.out.println("Sorry, parking lot is full");
            System.out.println();
        } else {
            Collections.sort(availableSlotList);
            String slot = availableSlotList.get(0).toString();
            Car car = new Car(regNo, hour,slot);
            this.map1.put(slot, car);
            this.map2.put(regNo, slot);
            this.map4.put(regNo, car);
            if (this.map3.containsKey(regNo)) {
                ArrayList<String> regNoList = this.map3.get(regNo);
                this.map3.remove(regNo);
                regNoList.add(regNo);
                this.map3.put(regNo, regNoList);
            } else {
                ArrayList<String> regNoList = new ArrayList<String>();
                regNoList.add(regNo);
                this.map3.put(regNo, regNoList);
            }
            System.out.println("Allocated slot number: " + slot);
            System.out.println();
            availableSlotList.remove(0);
        }
    }
    public void leave(String regNo, String hour) {
    	int charge =10;
    	
    	 try {
             this.h = Integer.parseInt(hour);
         } catch (Exception e) {
             System.out.println("Invalid hours");
             System.out.println();
         }
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map4.size() > 0) {
            Car carToLeave = this.map4.get(regNo);
           
            if (carToLeave != null) {
                this.map1.remove(carToLeave.slotNo);
                this.map2.remove(carToLeave.regNo);
                this.map4.remove(regNo);
				ArrayList<String> regNoList = this.map3.get(regNo);
                if(h<=2) {
                	int  charge1=charge;
                	 System.out.println(regNo +"with Slot Number"+ carToLeave.slotNo +" is free with charge "+charge1);
                	
                }else {
                	int  charge2=(int) (charge+h*10);
                	 System.out.println(regNo +"with Slot Number"+ carToLeave.slotNo +" is free with charge "+charge2);
                }
                if (regNoList.contains(carToLeave.regNo)) {
                    regNoList.remove(carToLeave.regNo);
                }
                // Add the Lot No. back to available slot list.
                this.availableSlotList.add(Integer.parseInt(carToLeave.slotNo));
                
               
               
                System.out.println();
            } else {
                System.out.println("Slot number " + carToLeave.slotNo + " is already empty");
                System.out.println();
            }
        } else {
            System.out.println("Parking lot is empty");
            System.out.println();
        }
    }
    public void status() {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map1.size() > 0) {
            // Print the current status.
            System.out.println("Slot No.\tRegistration No.\t");
            Car car;
            for (int i = 1; i <= this.MAX_SIZE; i++) {
                String key = Integer.toString(i);
                if (this.map1.containsKey(key)) {
                    car = this.map1.get(key);
                    System.out.println(i + "\t" + car.regNo + "\t");
                }
            }
            System.out.println();
        } else {
            System.out.println("Parking lot is empty");
            System.out.println();
        }
    }
    
    
    public void getSlotNumberFromRegNo(String regNo) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map2.containsKey(regNo)) {
            System.out.println(this.map2.get(regNo));
        } else {
            System.out.println("Not found");
            System.out.println();
        }
    }
}
