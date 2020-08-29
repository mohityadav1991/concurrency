package com.assignment.bookmyshow.service.user;

import com.assignment.bookmyshow.entity.Account;
import com.assignment.bookmyshow.repository.AccountRepository;

public class UserService {
  AccountRepository accountRepository;

  public UserService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public boolean addUser(Account account) {
    accountRepository.saveUser(account);
    return true;
  }

  public boolean deleteUser(Account account) {
    accountRepository.deleteUser(account);
    return true;
  }
}
