package com.rajkishorbgp.onlineshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

public class HomeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ItemClass> homeArrayList;
    private HomeDatabase homeDatabase;
    private CartDatabase cartDatabase;


    public HomeAdapter(Context context, ArrayList<ItemClass> homeArrayList) {
        this.context = context;
        this.homeArrayList = homeArrayList;
        this.homeDatabase = new HomeDatabase(context);
        this.cartDatabase = new CartDatabase(context);
    }

    @Override
    public int getCount() {
        return homeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return homeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.home_list_view, parent, false);
        }

        ItemClass item = homeArrayList.get(position);
        ImageView itemImage = view.findViewById(R.id.itemImage);
        TextView itemName = view.findViewById(R.id.itemName);
        TextView itemDec = view.findViewById(R.id.itemDec);
        ImageView cartImage = view.findViewById(R.id.cartImage);
        TextView itemPrise = view.findViewById(R.id.itemPrise);

        itemImage.setImageResource(item.image);
        itemName.setText(item.itemName);
        itemDec.setText(item.itemDisc);
        itemPrise.setText("Rs." + item.prise);
        cartImage.setColorFilter(ContextCompat.getColor(context, item.itemCartColor));

        cartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.isCart == 0) {
                    item.isCart = 1;
                    boolean isAdded = cartDatabase.addData(item.itemName,item.itemDisc,item.prise,item.image,item.itemCartColor,item.isCart);
                    item.setCartColor(R.color.cart_image_green);
                    homeDatabase.updateIsCart(item.itemName, 1, R.color.cart_image_green);
                    cartImage.setColorFilter(ContextCompat.getColor(context, item.itemCartColor));
                    Toast.makeText(context, isAdded ? "Item added to cart" : "Failed to add item to cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Already in cart", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
