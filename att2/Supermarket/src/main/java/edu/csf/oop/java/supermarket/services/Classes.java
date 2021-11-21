package edu.csf.oop.java.supermarket.services;

import edu.csf.oop.java.supermarket.supermarket.ListOfGoods;
import edu.csf.oop.java.supermarket.supermarket.SupermarketState;
import edu.csf.oop.java.supermarket.time.Time;

public class Classes {
    private Time time;
    private ListOfGoods list;
    private SupermarketState supermarketState;
    private int[] sells;

    public Classes() {
    }

    public Classes(Time time, ListOfGoods list, SupermarketState supermarketState, int[] sells) {
        this.time = time;
        this.list = list;
        this.supermarketState = supermarketState;
        this.sells = sells;
    }

    public Time getTime() {
        return time;
    }

    public ListOfGoods getList() {
        return list;
    }

    public SupermarketState getSupermarketState() {
        return supermarketState;
    }

    public int[] getSells() {
        return sells;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setList(ListOfGoods list) {
        this.list = list;
    }

    public void setSupermarketState(SupermarketState supermarketState) {
        this.supermarketState = supermarketState;
    }

    public void setSells(int[] sells) {
        this.sells = sells;
    }
}
