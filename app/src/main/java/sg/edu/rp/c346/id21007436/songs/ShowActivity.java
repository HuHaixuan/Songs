package sg.edu.rp.c346.id21007436.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    ListView lv;
    Button btn5star;
    Boolean b5star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        lv = findViewById(R.id.lvSong);
        btn5star = findViewById(R.id.btn5star);
        b5star = false;

        DBHelper db = new DBHelper(ShowActivity.this);

        // Insert a task
        ArrayList<Song> alSong = db.getSongs();
        db.close();
        ArrayList<Song> alSong5star = new ArrayList<Song>();
        for ( int i = 0; i < alSong.size(); i++ ){
            if (alSong.get(i).getStars() == 5) {
                alSong5star.add(alSong.get(i));
            }
        }

        ArrayAdapter adapter = new ArrayAdapter<>(ShowActivity.this, android.R.layout.simple_list_item_1,alSong);
        lv.setAdapter(adapter);

        btn5star.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                b5star = true;
                ArrayAdapter adapter = new ArrayAdapter<>(ShowActivity.this, android.R.layout.simple_list_item_1,alSong5star);
                lv.setAdapter(adapter);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                if (b5star == false){
                    Song songDetail = alSong.get(position);
                    Intent i = new Intent(ShowActivity.this, EditActivity.class);
                    i.putExtra("song", songDetail);
                    startActivity(i);
                }else{
                    Song songDetail = alSong5star.get(position);
                    Intent i = new Intent(ShowActivity.this, EditActivity.class);
                    i.putExtra("song", songDetail);
                    startActivity(i);
                }
            }
        });

    }
}