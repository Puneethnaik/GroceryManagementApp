package com.example.puneeth.grocerymanagement;

public class GroceryItem {
    private String qrCode;
    private String itemName;
    private String qrFormatName;
    private int count;
    public GroceryItem() {

    }

    public GroceryItem(String qrCode, String itemName, String qrFormatName, int count) {
        this.qrCode = qrCode;
        this.itemName = itemName;
        this.qrFormatName = qrFormatName;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getQrFormatName() {
        return qrFormatName;
    }

    public void setQrFormatName(String qrFormatName) {
        this.qrFormatName = qrFormatName;
    }


    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
