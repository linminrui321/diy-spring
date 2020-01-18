package org.litespring.service.v3;

import org.litespring.dao.v3.AccountDao;
import org.litespring.dao.v3.ItemDao;

public class PetStoreService {
    private ItemDao itemDao;
    private AccountDao accountDao;
    private int version;

    public PetStoreService(ItemDao itemDao, AccountDao accountDao, int version) {
        this.itemDao = itemDao;
        this.accountDao = accountDao;
        this.version = version;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
