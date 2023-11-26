package vn.edu.iuh.fit.lab01.services;

import vn.edu.iuh.fit.lab01.entities.Account;
import vn.edu.iuh.fit.lab01.entities.AccountStatus;
import vn.edu.iuh.fit.lab01.repositories.AccountRepository;

import java.util.List;

public class AccountService {
    private AccountRepository repository;

    public AccountService() {
        repository = new AccountRepository();
    }

    public List<Account> getAll(){
        return repository.getAll();
    }

    public Account get(String accountId){
        return repository.get(accountId);
    }

    public Account get(String email, String pass){
        return repository.get(email, pass);
    }

    public List<Account> get(){
        return repository.get();
    }

    public boolean add(Account account){
        return repository.add(account);
    }

    public boolean update(Account account){
        return repository.update(account);
    }
    public boolean update(String accountId, AccountStatus status){
        return repository.update(accountId, status);
    }

    public boolean delete(String accountId){
        return repository.delete(accountId);
    }
}
