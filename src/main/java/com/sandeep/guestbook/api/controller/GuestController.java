package com.sandeep.guestbook.api.controller;

import com.sandeep.guestbook.api.GuestBookErrorConst;
import com.sandeep.guestbook.api.GuestBookErrorType;
import com.sandeep.guestbook.api.entity.GuestCreationRequest;
import com.sandeep.guestbook.api.entity.GuestResponse;
import com.sandeep.guestbook.api.entity.GuestResponseWithError;
import com.sandeep.guestbook.api.exception.GuestAPIException;
import com.sandeep.guestbook.api.service.GuestService;
import com.sandeep.guestbook.api.util.GuestBookUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("guests")

public class GuestController {

    public static final Logger logger = LoggerFactory.getLogger(GuestController.class);

    @Autowired
    private GuestService guestService;

    @PostMapping
    public ResponseEntity saveGuestInfo(@RequestBody GuestCreationRequest guestCreationRequest) throws GuestAPIException {
        logger.info("Inside Post Request to create Guest ");
        if (!GuestBookUtil.isFilled(guestCreationRequest.getGuestName())) {
            List<String> errorMessages = new ArrayList<String>();
            errorMessages.add("Guest Name is missing");
            throw new GuestAPIException(HttpStatus.BAD_REQUEST, GuestBookErrorConst.GB_00001_GUEST_NAME_IS_MISSING, errorMessages, GuestBookErrorType.BUSINESS_ERRORS);
        }
        guestService.saveGuestInfo(guestCreationRequest);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GuestResponse>> getGuestsList() throws GuestAPIException {
        List<GuestResponse> guestList = guestService.getGuestList();
        HttpStatus httpStatus = HttpStatus.OK;
        if (guestList == null || guestList.isEmpty()) {
            GuestResponse guestResponse = new GuestResponse();
            HashMap<String, String> errorResponse = new HashMap<String, String>();
            errorResponse.put(GuestBookErrorConst.NO_RECORDS_FOUND, GuestBookErrorConst.NO_GUESTS_FOUND);
            guestList.add(new GuestResponseWithError(errorResponse, guestResponse));
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<List<GuestResponse>>(guestList, httpStatus);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<GuestResponse> getGuestInfoById(@PathVariable("id") long guestResourceId) throws GuestAPIException {
        GuestResponse guestResponse = guestService.getGuestInfoById(guestResourceId);
        if (guestResponse.getGuestName() == null) {
            logger.error("Guest with id {} not found." + guestResourceId);
            return new ResponseEntity<GuestResponse>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<GuestResponse>(guestResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteGuestInfoById(@PathVariable("id") long guestResourceId) throws GuestAPIException {
        GuestResponse guestResponse = guestService.getGuestInfoById(guestResourceId);
        if (guestResponse == null) {
            logger.error("Guest with id {} not found." + guestResourceId);
            return new ResponseEntity<GuestResponse>(HttpStatus.NOT_FOUND);
        }
        guestService.deleteGuestInfoById(guestResourceId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateGuestInfo(@PathVariable("id") long guestResourceId, @RequestBody GuestCreationRequest guestCreationRequest) throws GuestAPIException {
        GuestResponse guestResponse = guestService.getGuestInfoById(guestResourceId);
        if (guestResponse == null) {
            logger.error("Guest with id {} not found." + guestResourceId);
            return new ResponseEntity<GuestResponse>(HttpStatus.NOT_FOUND);
        }
        if (!GuestBookUtil.isFilled(guestCreationRequest.getGuestName())) {
            List<String> errorMessages = new ArrayList<String>();
            errorMessages.add("Guest Name is missing");
            throw new GuestAPIException(HttpStatus.BAD_REQUEST, GuestBookErrorConst.GB_00001_GUEST_NAME_IS_MISSING, errorMessages, GuestBookErrorType.BUSINESS_ERRORS);
        }
        guestService.updateGuestInfo(guestCreationRequest, guestResourceId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
