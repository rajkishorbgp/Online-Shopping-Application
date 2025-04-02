package com.rajkishorbgp.onlineshopping;

public class ItemClass {
    public int prise;
    public int image;
    public String itemName;
    public String itemDisc;
    public int itemCartColor;

    public int isCart;
    public ItemClass(String itemName, String itemDisc, int prise, int image, int itemCartColor,int isCart){
        this.itemName=itemName;
        this.itemDisc = itemDisc;
        this.prise = prise;
        this.image = image;
        this.itemCartColor = itemCartColor;
        this.isCart = isCart;
    }
    public void setCartColor(int itemCartColor){
        this.itemCartColor=itemCartColor;
    }
}
