package com.example.myrecipe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Ingredients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        String[] SpicesName={"salt","pepper","cinnamon","cloves","ginger","carom"};
        Boolean IsRice=false,IsOnion=false,IsCarrot=false,IsPotatoِ=false,IsEggplant=false,IsZucchini=false,IsCorn=false,IsTomato=false;

        Button rice = findViewById(R.id.rice);
        Button onion = findViewById(R.id.onion);
        Button carrot = findViewById(R.id.carrot);
        Button potatoِ = findViewById(R.id.potato);
        Button Eggplant = findViewById(R.id.Eggplant);
        Button zucchini = findViewById(R.id.zucchini);
        Button corn = findViewById(R.id.corn);
        Button Tomato = findViewById(R.id.Tomato);

        Button save=findViewById(R.id.save);

        TextView riceA = findViewById(R.id.riceA);
        TextView onionA = findViewById(R.id.onionA);
        TextView carrotA = findViewById(R.id.carrotA);
        TextView potatoِA = findViewById(R.id.potatoA);
        TextView EggplantA = findViewById(R.id.EggplantA);
        TextView zucchiniA = findViewById(R.id.zucchiniA);
        TextView cornA = findViewById(R.id.cornA);
        TextView TomatoA = findViewById(R.id.TomatoA);

         String[] Wplant = {""};
        Boolean[] Isplant={IsRice,IsOnion,IsCarrot,IsPotatoِ,IsEggplant,IsZucchini,IsCorn,IsTomato};
        String[] plantName={"rice","onion","carrot","potatoِ","Eggplant","zucchini","corn","Tomato"};
        Button[] plant = {rice, onion, carrot, potatoِ, Eggplant, zucchini, corn, Tomato};

        for (int i=0;i<plant.length;i++){
            int ii=i;
            plant[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Isplant[ii]) {
                        Isplant[ii] = true;
                        plant[ii].setBackgroundColor(Color.YELLOW);
                    } else {
                        Isplant[ii] = false;
                        plant[ii].setBackgroundColor(Color.LTGRAY);
                    }

                }
            });
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sp=new Intent(Ingredients.this,addP.class);
                sp.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                for (int i=0;i<plant.length;i++){
                    if (Isplant[i]){
                        Wplant[0]=Wplant[0]+"  "+plantName[i];
                    }
                    sp.putExtra(plantName[i],Isplant[i].toString());
                }
                try {
                    for (int i=0;i<SpicesName.length;i++){
                        sp.putExtra(SpicesName[i],(String) getIntent().getSerializableExtra(SpicesName[i]));
                    }
                    sp.putExtra("spices",(String) getIntent().getSerializableExtra("spices"));
                }
                catch (Exception e){}
                sp.putExtra("plant",Wplant[0]);
                startActivity(sp);
            }
        });
    }
}