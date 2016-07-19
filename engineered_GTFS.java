package engine;

import model.*;
import object.*;

import MySqlDB_management.ProcessingValue;

public class engineered_GTFS {
	public static void main(String[] args) {
		System.out.println("-------- ENGINEERED PARSER APPLICATION:");
		System.out.println("\tLOADS GTFS DATA INTO A MYSQL DB");
		System.out.println("\tLOADS AND PRINT TO STDOUT ENGINEERED POJO OBJECTS FILLED WITH THE DATA");
		
		System.out.println("--------");

		System.out.println("--------\tCREATION OF THE SQL DATABASE WITH GTFS.ZIP FILES\t--------");

		ProcessingValue data = new ProcessingValue();

		data.processingValue();

		System.out.println("--------\tCREATION OF THE MYSQL DATABASE COMPLETED SUCCESFULLY\t--------");

		System.out.println("----------------------USING POJO STRUCTURE FOR GTFS DATA-------------------------");
		System.out.println("--------\tAGENCY OBJECTS\t--------");

		Obj_Agency object_agency = new Obj_Agency();
		for (Agency_pojo agency : object_agency.getAgencyObjects()) {
			System.out.println(agency);
		}

		System.out.println("--------\tAGENCY OBJECTS PROCESSED SUCCESSFULLY\t--------");
		System.out.println("");
		System.out.println("--------\tCALENDAR DATES OBJECTS\t--------");

		Obj_Calendar_dates object_calendar_dates = new Obj_Calendar_dates();

		for (Calendar_dates_pojo calendar_dates : object_calendar_dates.getCalendar_datesObjects()) {
			System.out.println(calendar_dates);
		}

		System.out.println("--------\tCALENDAR DATES OBJECTs PROCESSED SUCCESSFULLY\t--------");

		System.out.println("");
		System.out.println("--------\tCALENDAR OBJECT\t--------");

		Obj_Calendar object_calendar = new Obj_Calendar();

		for (Calendar_pojo calendar : object_calendar.getCalendarObjects()) {
			System.out.println(calendar);
		}

		System.out.println("--------\tCALENDAR OBJECT PROCESSED SUCCESSFULLY\t--------");

		System.out.println("");
		System.out.println("--------\tFEED INFO OBJECTS\t--------");


		Obj_Feed_info object_feed_info = new Obj_Feed_info();

		for (Feed_info_pojo feed_info : object_feed_info.getFeed_infoObjects()) {
			System.out.println(feed_info);
		}
		System.out.println("--------\tFEED INFO OBJECTS PROCESSED SUCCESSFULLY\t--------");
		
		System.out.println("");
		System.out.println("");
		System.out.println("Not all the pojos have been printed to stdout because of lack of time, sorry!");
		

	}
}