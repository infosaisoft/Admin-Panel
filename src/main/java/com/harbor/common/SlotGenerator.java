package com.harbor.common;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class SlotGenerator {

	

	public List<DateTime> generateSlot(SlotTimer slottime) {
		Time start_time = null;
		DateTimeFormatter sdf = DateTimeFormat.forPattern("HH:mm:ss");
	     Time end_time = null;
		Minutes totalShiftTime = null;
		int totalShiftMinuties = 0;
		int slotDuration = 0;
		int maxPatient = 0;
		int totalpatient = 0;
		int slotinterval = 0;
		int singleslotmin=0;
		List<DateTime>slotarray=new ArrayList<>();
		
		start_time = slottime.getStar_time();
		end_time = slottime.getEnd_time();
		
	
        
		
		 DateTime dt=new DateTime(start_time.getTime());
		 DateTime dt1=new DateTime(end_time.getTime());
		 
		 
		 
	
		totalShiftTime = Minutes.minutesBetween(dt, dt1);
		
		
		totalShiftMinuties = totalShiftTime.getMinutes();
		 System.out.println("diff time::"+totalShiftMinuties);

		slotDuration = Integer.parseInt(slottime.getSlot_duration());
		maxPatient = Integer.parseInt(slottime.getMax_patient());

		totalpatient = (totalShiftMinuties / slotDuration) * maxPatient;
		System.out.println("totalpatient::"+totalpatient);
		
		slotinterval = totalShiftMinuties / totalpatient;
		System.out.println("slotinterval::"+slotinterval);
		
		//covert each patient slot in minutes
		singleslotmin=totalShiftMinuties/totalpatient;
		
		System.out.println("singleslotmin::"+singleslotmin);
		slotarray.add(dt);
		
		//generting slot for total patient
		for(int i=1;i<totalpatient;i++) {
		dt=dt.plusMinutes(singleslotmin);
			 slotarray.add(dt);
		}
		
		return slotarray;
	}

}
