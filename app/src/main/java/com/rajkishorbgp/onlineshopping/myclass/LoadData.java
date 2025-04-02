package com.rajkishorbgp.onlineshopping.myclass;

import android.content.Context;
import android.view.View;

import com.rajkishorbgp.onlineshopping.HomeDatabase;
import com.rajkishorbgp.onlineshopping.ItemClass;
import com.rajkishorbgp.onlineshopping.R;

import java.util.ArrayList;

public class LoadData {

    public LoadData(Context context){
        ArrayList<ItemClass> homeArrayList = new ArrayList<>();

        int itemCartColor = R.color.cart_image_red;
        homeArrayList.add(new ItemClass("iPhone 15 (Blue, 128 GB)","RAM | ROM \n Processor A16 Bionic Chip, 6 Core Processor | Hexa Core \n Front Camera\n48MP + 12MP\n Front Camera\n12MP\nDisplay\n6.1 inch All Screen OLED Display",
                79900,R.drawable.iphone15, itemCartColor,0));

        homeArrayList.add(new ItemClass("APPLE AirPods Pro","APPLE AirPods Pro (2nd generation) with Active Noise Cancellation, Spatial Audio Bluetooth Headset  (White, True Wireless)",
                24990,R.drawable.earbard, itemCartColor,0));

        homeArrayList.add(new ItemClass("T-Shirt", "Men Printed Round Neck Cotton Blend Black T-Shirt", 203,
                R.drawable.tshirt, itemCartColor,0));

        homeArrayList.add(new ItemClass("Smart Bulb","realme LED Wi-Fi Smart Bulb 9W Smart Bulb",884,R.drawable.balbe, itemCartColor,0));

        homeArrayList.add(new ItemClass("MacBook AIR M2","APPLE 2022 MacBook AIR M2 - (8 GB/256 GB SSD/Mac OS Monterey) MLY13HN/A  (13.6 Inch, Starlight, 1.24 kg)",97990,R.drawable.macbook, itemCartColor,0));

        homeArrayList.add(new ItemClass("A4 Notebook","Classmate Pulse A4 Notebook Unruled 300 Pages  (Multicolor)",179,R.drawable.notbook, itemCartColor,0));

        homeArrayList.add(new ItemClass("Home Delight 138 LEDs","Home Delight 138 LEDs 2.49 m Yellow Rice Lights  (Pack of 1)",425,R.drawable.home_light, itemCartColor,0));

        homeArrayList.add(new ItemClass("Hair Oil","Parachute Advansed Jasmine Gold Hair Oil  (1500 ml)",544,R.drawable.oil, itemCartColor,0));

        homeArrayList.add(new ItemClass("Boots","Boots For Men  (Khaki)",2919,R.drawable.boot, itemCartColor,0));

        homeArrayList.add(new ItemClass("Girls Lehenga","Girls Lehenga Choli Party Wear Embroidered Lehenga, Choli and Dupatta Set  (Pink, Pack of 1)",332,R.drawable.langa, itemCartColor,0));

        HomeDatabase homeDatabase = new HomeDatabase( context);
        for (int i = 0; i < homeArrayList.size(); i++) {
            ItemClass itemClass = homeArrayList.get(i);
            homeDatabase.addData(itemClass.itemName,itemClass.itemDisc, itemClass.prise,itemClass.image,itemClass.itemCartColor,itemClass.isCart);
        }
    }
}
