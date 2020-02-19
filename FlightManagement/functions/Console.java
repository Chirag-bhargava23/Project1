package functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

public class Console {

	public static void main(String[] args) {
		Database prodDB = new Database();
		prodDB.bootstrap();
		boolean always = true;
		String passengerName = null;
		int flightNumber = 0;
		int seatNumber = 0;
		LocalDate departureDate = LocalDate.now();
		BufferedReader screenInput = new BufferedReader(new InputStreamReader(System.in));
		
		while(always)
		{
			System.out.println("Enter Passenger name: ");
			
			try {
				passengerName = screenInput.readLine();
			} catch (IOException e) {
				System.out.println("Sorry, i dont understand.");
			}
			
			boolean result = prodDB.addPassenger(passengerName);
			

			if(result)
			{
				System.out.println("Welcome back: " +  passengerName);
			}
			else{
				System.out.println("Welcome: " +  passengerName);
			}
			System.out.println("\nHere are available flights: ");
			for(Flight item: prodDB.getFlight()){
				System.out.println(item);
			}
			System.out.println("Enter the flight no to book");
			try{
			flightNumber = Integer.parseInt(screenInput.readLine());
			} catch (IOException e) {
				System.out.println("Please enter flight no");
		}
			catch (NumberFormatException e) {
				System.out.println("That was not a number");
			}
			
			
			System.out.println("\nAssuming you are flying today,");
			System.out.println("Here are available seats on that flight: ");
			ArrayList<Seat> openSeats = prodDB.getOpenSeats(departureDate, flightNumber);
			for(Seat item: openSeats){
				System.out.print(item.getSeatNo() + ",");
			}
			System.out.println("\nEnter the seat you want: ");
			try{
				seatNumber = Integer.parseInt(screenInput.readLine());
				
			}
			catch (IOException e) {
				System.out.println("Please enter a seat no");
		}
			String ticketInfo = prodDB.addTicket(departureDate, passengerName, flightNumber, seatNumber);
			System.out.println("\nReservations Successful!! Here are your ticket details");
			System.out.println(ticketInfo + "\n");
		}
		
		

	}

}
