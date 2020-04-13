package com.sandeep.guestbook.api.service;

import com.sandeep.guestbook.api.entity.Guest;
import com.sandeep.guestbook.api.entity.GuestCreationRequest;
import com.sandeep.guestbook.api.entity.GuestResponse;
import com.sandeep.guestbook.api.util.GuestBookUtil;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.*;

@Service
public class GuestServiceImpl implements GuestService {

    private Map<Long, Guest> guestRepositoryMap = new HashMap<Long,Guest>();

    public void saveGuestInfo(GuestCreationRequest guestCreationRequest) {

        Guest guest = new Guest();
		Long id = new Random().nextLong();
		guest.setId(id);
        guest.setGuestName(guestCreationRequest.getGuestName());
        guest.setComment(guestCreationRequest.getComment());
        guest.setCreationDateTime(GuestBookUtil.getTimeInUTC());

        guestRepositoryMap.put(id, guest);

    }

    public void updateGuestInfo(GuestCreationRequest guestCreationRequest, long guestResourceId) {
        Guest guest = new Guest();
        guest.setId(guestResourceId);
        guest.setGuestName(guestCreationRequest.getGuestName());
        guest.setComment(guestCreationRequest.getComment());
        guest.setCreationDateTime(GuestBookUtil.getTimeInUTC());
        guestRepositoryMap.put(guestResourceId, guest);

    }

    public List<GuestResponse> getGuestList() {
        List<GuestResponse> guestList = new ArrayList<GuestResponse>();
		
		// using values() for iteration over keys 
        for (Guest guest : guestRepositoryMap.values())  {
            GuestResponse guestResponse = new GuestResponse();
            guestResponse.setGuestName(guest.getGuestName());
            guestResponse.setGuestId(guest.getId());
            guestResponse.setComment(guest.getComment());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(guest.getCreationDateTime());
            guestResponse.setCreatedDateTime(strDate);
            guestList.add(guestResponse);
		} 
        
        return guestList;
    }

    public GuestResponse getGuestInfoById(long guestResourceId) {
        Guest guest = guestRepositoryMap.get(guestResourceId);
        GuestResponse guestResponse = new GuestResponse();
        guestResponse.setGuestName(guest.getGuestName());
        guestResponse.setGuestId(guest.getId());
        guestResponse.setComment(guest.getComment());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(guest.getCreationDateTime());
        guestResponse.setCreatedDateTime(strDate);
        return guestResponse;

    }

    public void deleteGuestInfoById(long guestResourceId) {
        guestRepositoryMap.remove(guestResourceId);

    }
}
