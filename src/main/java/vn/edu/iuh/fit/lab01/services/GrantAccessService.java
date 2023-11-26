package vn.edu.iuh.fit.lab01.services;

import vn.edu.iuh.fit.lab01.entities.GrantAccess;
import vn.edu.iuh.fit.lab01.repositories.GrantAccessRepository;

import java.util.ArrayList;

public class GrantAccessService {
    private GrantAccessRepository grantAccessRepository;

    public GrantAccessService(){
        this.grantAccessRepository = new GrantAccessRepository();
    }

    public boolean add(GrantAccess grantAccess){
        return grantAccessRepository.add(grantAccess);
    }

    public ArrayList<GrantAccess> findGrantByAccountId(String accountId){
        return grantAccessRepository.findGrantByAccountId(accountId);
    }

    public boolean checkGrantAccess(String accountId, String roleId){
        return grantAccessRepository.get(roleId, accountId) != null;
    }
}
