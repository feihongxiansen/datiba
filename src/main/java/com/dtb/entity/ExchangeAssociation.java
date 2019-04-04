package com.dtb.entity;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/4-21:50
 */
public class ExchangeAssociation extends Exchange {

    private User user;

    private GiftWithBLOBs gift;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GiftWithBLOBs getGift() {
        return gift;
    }

    public void setGift(GiftWithBLOBs gift) {
        this.gift = gift;
    }
}
