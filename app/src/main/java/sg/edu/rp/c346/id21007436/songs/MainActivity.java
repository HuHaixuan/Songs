package sg.edu.rp.c346.id21007436.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShowList;
    EditText etTitle, etSingers, etYear;
    RadioGroup rgStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnEdit);
        btnShowList = findViewById(R.id.btnDelete);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                String title = etTitle.getText().toString();
                String singers = etSingers.getText().toString();
                String stringYear = etYear.getText().toString();
                int year = Integer.parseInt(stringYear);
                int stars = 0;

                int checkedRadioId = rgStars.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.rb1){
                    stars = 1;
                } else if(checkedRadioId == R.id.rb2){
                    stars = 2;
                } else if(checkedRadioId == R.id.rb3){
                    stars = 3;
                } else if(checkedRadioId == R.id.rb4){
                    stars = 4;
                } else if(checkedRadioId == R.id.rb5){
                    stars = 5;
                }

                db.insertSong(title, singers, year, stars);

            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });
    }
}