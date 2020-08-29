package com.assignment.bookmyshow.repository;

import com.assignment.bookmyshow.entity.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class AccountRepository {
  // this acts as In-Memory DB
  private static ConcurrentHashMap<Long, Account> users = new ConcurrentHashMap<>();

  public static List<Account> getAllUsers() {
    return users.values().stream().collect(Collectors.toList());
  }

  public static Account getUser(String id) {
    return users.get(id);
  }

  public static void saveUser(Account u) {
    users.putIfAbsent(u.getId(), u);
  }

  public static void deleteUser(Account u) {
    users.remove(u);
  }
}
