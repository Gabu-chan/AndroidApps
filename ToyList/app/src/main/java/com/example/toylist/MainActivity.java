package com.example.toylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mToyListRV;
    private ToyListAdapter mToyListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToyListRV = (RecyclerView) findViewById(R.id.toy_list);


        String[] toyList = new String[] {
                "Red Toy Wagon",
                "Chemistry Set",
                "Yo-Yo",
                "Pop-Up Book",
                "Generic Uncopyrighted Mouse",
                "Finger Paint",
                "Sock Monkey",
                "Microscope Set",
                "Beach Ball",
                "BB Gun",
                "Green Soldiers",
                "Bubbles",
                "Spring Toy",
                "Fortune Telling Ball",
                "Plastic Connecting Blocks",
                "Water Balloon",
                "Paint-by-Numbers Kit",
                "Tuber Head",
                "Cool Ball with Holes in It",
                "Toy Truck",
                "Flying Disc",
                "Two-Handed Pogo Stick",
                "Toy Hoop",
                "Dysmorphia Doll",
                "Toy Train",
                "Fake Vomit",
                "Toy Telephone",
                "Barrel of Primates",
                "Remote Control Car",
                "Square Puzzle Cube",
                "Football",
                "Intergalactic Electronic Phasers",
                "Baby Horse Dolls",
                "Machines that turn into other Machines",
                "Teddy Bears",
                "Shaved Ice Maker",
                "Glow Stick",
                "Squirt Guns",
                "Miniature Replica Animals Stuffed with Beads that you swore to your parents would be worth lots of money one day",
                "Creepy Gremlin Doll",
                "Neodymium-Magnet Toy"
        };


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mToyListRV.setLayoutManager(layoutManager);
        mToyListRV.setHasFixedSize(true);
        mToyListAdapter = new ToyListAdapter(toyList);
        mToyListRV.setAdapter(mToyListAdapter);


    }
}