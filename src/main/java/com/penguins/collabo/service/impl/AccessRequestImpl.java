package com.penguins.collabo.service.impl;

import com.penguins.collabo.Repository.AccessRequestRepository;
import com.penguins.collabo.models.AccessRequest;
import com.penguins.collabo.service.AccessRequestService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AccessRequestImpl implements AccessRequestService {
    private AccessRequestRepository ARRepository;

    AccessRequestImpl(AccessRequestRepository ARRepository) {
        this.ARRepository = ARRepository;
    }

    @Override
    public List<AccessRequest> getAccessRequests() {
        List<AccessRequest> accessRequests = ARRepository.findAll();
        return accessRequests;
    }

    @Override
    public void saveAccessRequest(AccessRequest accessRequest) {
        ARRepository.save(accessRequest);
    }

    @Override
    public List<AccessRequest> getAccessRequestsBySenderUserId(int userId) {
        List<AccessRequest> accessRequests = ARRepository.findAll();
        List<Integer> indexesToBeRemoved = new ArrayList<Integer>();

        for(int i =0; i < accessRequests.size(); i++){
            if (accessRequests.get(i).getFromUserId() != userId ){
                indexesToBeRemoved.add(i);
            }
        }

        for (Integer i : indexesToBeRemoved) {
            accessRequests.remove(i);
        }

        return accessRequests;
    }

    @Override
    public List<AccessRequest> getAccessRequestsByReceiverUserId(int userId) {
        List<AccessRequest> accessRequests = ARRepository.findAll();
        Iterator<AccessRequest> iterator = accessRequests.iterator();

        while(iterator.hasNext()){
            AccessRequest accessRequest = iterator.next();
            if (accessRequest.getToUserId() != userId){
                iterator.remove();
            }
        }

        return accessRequests;
    }

    @Override
    public void deleteAccessRequest(AccessRequest accessRequest) {
        ARRepository.delete(accessRequest);
    }

    @Override
    public AccessRequest getAccessRequestByTuple(int docId, int fromUserId) {
        List<AccessRequest> accessRequests = ARRepository.findAll();

        for(AccessRequest ar : accessRequests){
            if(ar.getDocId() == docId && ar.getFromUserId() == fromUserId){
                return ar;
            }
        }
        return null;
    }
}
