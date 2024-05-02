package ru.job4j.cash;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        Optional<Account> from = getById(fromId);
        Optional<Account> to = getById(toId);
        if (from.isPresent() && to.isPresent() && from.get().amount() >= amount) {
            update(new Account(fromId, from.get().amount() - amount));
            update(new Account(toId, to.get().amount() + amount));
        }
    }
}