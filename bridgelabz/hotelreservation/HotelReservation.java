package com.bridgelabz.hotelreservation;
import java.util.ArrayList;
import java.text.ParseException;
import java.util.Comparator;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
public class HotelReservation {
private List<Hotel> hotelList = new ArrayList<Hotel>();
	
	
	/**
	 * @param hotelName
	 * @param weekdayRegularCustRate
	 * @param weekendRegularCustRate
	 * @param rating
	 * @return
	 */
	public boolean addHotel(String hotelName, int weekdayRegularCustRate,int weekendRegularCustRate,int rating) {
		Hotel hotel = new Hotel(hotelName,weekdayRegularCustRate,weekendRegularCustRate,rating);
		hotelList.add(hotel);
		return true;
	}
	
	/**
	 * @param start
	 * @param end
	 * @return Cheapest hotel
	 */
	public Hotel findCheapestHotel(String startD, String endD) {
		Date startDate=null;
        Date endDate = null;
        try {
			startDate = new SimpleDateFormat("dd-MMM-yyyy").parse(startD);
			endDate = new SimpleDateFormat("dd-MMM-yyyy").parse(endD); 
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
        long noOfDays = 1+(endDate.getTime()- startDate.getTime())/1000/60/60/24;
        
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);        

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        long noOfWeekdays = 0;
        if (startCal.getTimeInMillis() < endCal.getTimeInMillis()) {
        
        do {
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++noOfWeekdays;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()); 
        
        long noOfWeekends = noOfDays - noOfWeekdays;
        System.out.println("Weekends "+ noOfWeekends +"Weekdays "+noOfWeekdays);
        
        for(Hotel hotel: hotelList) {
        	long totalRate = noOfWeekdays*hotel.getWeekdayRegularCustRate()+noOfWeekends*hotel.getWeekendRegularCustRate();
        	hotel.setTotalRate(totalRate);
        }
        Hotel cheapestHotel = hotelList.stream().sorted(Comparator.comparing(Hotel::getTotalRate)).findFirst().orElse(null);
        
		return cheapestHotel;
        }
        else 
        	return null;
	}
	
    /**
     * UC5
     * @param args
     */
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
    	HotelReservation hotelReservation = new HotelReservation();
    	sc.close();
    	
        System.out.println( "Welcome to Hotel Reservation System Program" );
        
        hotelReservation.addHotel("Lakewood", 110, 90, 3);
        hotelReservation.addHotel("Bridgewood", 160, 60, 4);
        hotelReservation.addHotel("Ridgewood", 220, 150, 5);
        //UC5 Init
        
        System.out.println("Do you want to add a Hotel?(Y/N)");
        char choice = sc.nextLine().charAt(0);
        while(choice=='Y') {
	        System.out.println("Add a hotel \nEnter hotel name:");
	        String hotelName = sc.nextLine();
	        System.out.println("Enter weekday regular customer rate:");
	        int weekdayRegularCustRate = Integer.parseInt(sc.nextLine());
	        System.out.println("Enter weekend regular customer rate:");
	        int weekendRegularCustRate = Integer.parseInt(sc.nextLine());
	        System.out.println("Enter Rating");
	        int rating = Integer.parseInt(sc.nextLine());
	        hotelReservation.addHotel(hotelName,weekdayRegularCustRate,weekendRegularCustRate,rating);
	        System.out.println("Do you want to add another Hotel?(Y/N)");
	        choice = sc.nextLine().charAt(0);
        }
        
        System.out.println("Enter date range to find hotel in format(dd-MMM-yyyy)");
        System.out.println("Enter start date");
        String start = sc.nextLine();
        System.out.println("Enter end date");
        String end = sc.nextLine();
        Hotel cheapestHotel = hotelReservation.findCheapestHotel(start,end);
        if(cheapestHotel!=null)
        	System.out.println(cheapestHotel.getHotelName()+", Total rates :$"+cheapestHotel.getTotalRate());
        else 
        	System.out.println("Improper dates");
    }
}
