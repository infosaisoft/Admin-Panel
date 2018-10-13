package com.harbor.service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbor.bo.DoctorAvaliblityBo;
import com.harbor.bo.SettingsBo;
import com.harbor.bo.ShiftBo;
import com.harbor.bo.SlotBo;
import com.harbor.common.CustomIdGenerator;
import com.harbor.common.SlotGenerator;
import com.harbor.common.SlotTimer;
import com.harbor.dao.DoctorAvaliblityDao;
import com.harbor.dao.SlotDao;
import com.harbor.dto.DoctorAvaliblityDto;
import com.harbor.dto.SettingsDto;
import com.harbor.dto.ShiftDto;
import com.harbor.dto.SlotDto;

@Service
public class DoctorAvaliblityserviceImpl implements DoctorAvalibityService {

	@Autowired
	DoctorAvaliblityDao docdao;
	
	@Autowired
	SlotDao slotdao;
	
	SlotGenerator slotgen=null;
    List<DateTime> listslot=null;
   List<DateTime> listarry=new ArrayList<>();
	@Override
	public String registration(DoctorAvaliblityDto docdto) {
		DoctorAvaliblityBo docbo = null;
		int count = 0;
       Calendar cal=null;
       int month=0;
       int week=0;
		 cal=Calendar.getInstance();
		 month=cal.get((Calendar.MONTH));
		 week=cal.get(Calendar.WEEK_OF_MONTH);
		// copy dto bo
		docbo = new DoctorAvaliblityBo();
		BeanUtils.copyProperties(docdto, docbo);
              docbo.setMonth(month+1);
              docbo.setWeek(week);
		// use dao
		count = docdao.insertAvaliblity(docbo);

		if (count == 0) {
			return "fail";
		}
		return "success";
	}

	@Override
	public List<DoctorAvaliblityDto> featchAllAppotiment(long hid) {
		List<DoctorAvaliblityDto> listdto = new ArrayList<>();
		List<DoctorAvaliblityBo> listbo = null;

		// use dao
		listbo = docdao.getAllAvaliblity(hid);

		listbo.forEach(bo -> {
			DoctorAvaliblityDto dto = new DoctorAvaliblityDto();

			BeanUtils.copyProperties(bo, dto);

			listdto.add(dto);

		});

		return listdto;
	}

	@Override
	public String remooveAvaliblity(String avail_id) {
		int count = 0;

		// use dao
		count = docdao.deleteAvaliblity(avail_id);

		if (count == 0) {
			return "fail";
		}

		return "success";
	}
	
	@Override
	public ShiftDto viewShifByName(long hid, String shift_name) {
	 ShiftDto dto=null;
	 ShiftBo bo=null;
	 SlotTimer slot=null;
	 
	 SettingsDto sdto=null;
	 SettingsBo sbo=null;
	 	 
	 bo=docdao.getShiftByName(hid, shift_name);
	 sbo=docdao.getDurationPatient(hid);
	 
	 //copy bo to dto
	 dto=new ShiftDto();
	 slot=new SlotTimer();
	 BeanUtils.copyProperties(bo, dto);
	 
	//copy bo to dto
	 sdto=new SettingsDto();
	 BeanUtils.copyProperties(sbo, sdto);
	 slot.setStar_time(dto.getStart_time());
	 slot.setEnd_time(dto.getEnd_time());
	 
	 slot.setSlot_duration(sdto.getSlot_duration());
	 slot.setMax_patient(sdto.getMax_patient());

	 slotgen=new SlotGenerator();
	
	listslot=slotgen.generateSlot(slot);
       
	  for(DateTime dt:listslot) {
		  listarry.add(dt);
		  
	  }
	 
		return dto;
	}
	
	
	@Override
	public String regisrationSlot(SlotDto slotdto) {
		SlotBo bo=null;
		String slotid=null;
		int count=0;
 DateTime time1= listarry.get(0);
 DateTime time2= listarry.get(1);
int totalShiftminutes = Minutes.minutesBetween(time1, time2).getMinutes();

System.out.println("minutes:::::::::"+totalShiftminutes);
	
		for(DateTime time:listarry) {
		    slotdto.setStart_time(time.toDate());
		         DateTime end=    time.plusMinutes(totalShiftminutes);
		      slotdto.setEnd_time(end.toDate());
		     //copy dto to bo
				 bo=new SlotBo();
					slotdto.setSlots_id(slotid);
					BeanUtils.copyProperties(slotdto,bo);
					//use dao;
				count=slotdao.insertSlot(bo); 
				
		}
		listarry.removeAll(listslot);
		
		if(count==0) {
			return "fail";
		}
		return "success";
	}
	
	
}
