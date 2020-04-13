package com.sandeep.guestbook.api.service;

import com.sandeep.guestbook.api.entity.GuestCreationRequest;
import com.sandeep.guestbook.api.entity.GuestResponse;

import java.util.List;

public interface GuestService {

    public void saveGuestInfo(GuestCreationRequest guestCreationRequest);

    public List<GuestResponse> getGuestList();

    public GuestResponse getGuestInfoById(long guestResourceId);

    public void deleteGuestInfoById(long guestResourceId);

    public void updateGuestInfo(GuestCreationRequest guestCreationRequest, long guestResourceId);
}

