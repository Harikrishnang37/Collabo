package com.penguins.collabo.service;

import com.penguins.collabo.models.AccessRequest;

import java.util.List;

public interface AccessRequestService {

    List<AccessRequest> getAccessRequests();

    public void saveAccessRequest(AccessRequest accessRequest);

    public List<AccessRequest> getAccessRequestsBySenderUserId(int userId);
    public List<AccessRequest> getAccessRequestsByReceiverUserId(int userId);

    public void deleteAccessRequest(AccessRequest accessRequest);

    public AccessRequest getAccessRequestByTuple(int docId, int fromUserId);


}
