package proj1;

import java.util.ArrayList;

public class AccountManagermentImp implements AccountManagerment {
    private ArrayList<Account> accounts;

    public AccountManagermentImp(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public AccountManagermentImp() {}

    @Override
    public boolean signup(Account account) {
        // TODO Auto-generated method stub
        if (account == null) {
            return false;
        }
        if (account.getUsername() == null || account.getUsername().isEmpty()) {
            return false;
        }
        if (account.getPassword() == null || account.getPassword().isEmpty()) {
            return false;
        }
        if (account.getEmail() == null || account.getEmail().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean login(Account account) {
        if (account == null) {
            return false;
        }
        if (account.getUsername() == null || account.getUsername().trim().isEmpty()) {
            return false;
        }
        if (account.getPassword() == null || account.getPassword().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void logout(Account account) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean remove(Account account) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean recoverAccount(Account account) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean changePassword(Account account) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void changeAccountInfor(Account account) {
        // TODO Auto-generated method stub
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}
