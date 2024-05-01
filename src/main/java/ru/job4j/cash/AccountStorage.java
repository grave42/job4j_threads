package ru.job4j.cash;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized void add(Account account) {
        accounts.put(account.id(), account);
    }

    public synchronized void update(Account account) {
        Optional<Account> accToUpdate = getById(account.id());
        if (accToUpdate.isPresent()) {
            accounts.put(account.id(), account);
        }
    }

    public synchronized void delete(int id) {
        Optional<Account> accToDelete = getById(id);
        if (accToDelete.isPresent()) {
            accounts.remove(id);
        }
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        Optional<Account> from = getById(fromId);
        Optional<Account> to = getById(toId);
        if (from.isPresent() && to.isPresent()) {
            update(new Account(fromId, from.get().amount() - amount));
            update(new Account(toId, to.get().amount() + amount));
        }
    }
}