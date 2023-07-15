package sg.edu.rp.c346.id21007436.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class EditActivity extends AppCompatActivity {

    Button btnEdit, btnDelete, btnCancel;
    EditText etID, etTitle, etSingers, etYear;
    RadioGroup rgStars;
    Song songDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);

        Intent i = getIntent();
        songDetails = (Song) i.getSerializableExtra("song");

        etID.setText(songDetails.getId() + "");
        etID.setEnabled(false);
        etTitle.setText(songDetails.getTitle());
        etSingers.setText(songDetails.getSingers());
        etYear.setText(songDetails.getYear() + "");
        int star = songDetails.getStars();
        if (star == 1) {
            rgStars.check(R.id.rb1);
        } else if (star == 2) {
            rgStars.check(R.id.rb2);
        } else if (star == 3) {
            rgStars.check(R.id.rb3);
        } else if (star == 4) {
            rgStars.check(R.id.rb4);
        } else {
            rgStars.check(R.id.rb5);
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                songDetails.setTitle(etTitle.getText().toString());
                songDetails.setSingers(etSingers.getText().toString());
                String stringYear = etYear.getText().toString();
                int year = Integer.parseInt(stringYear);
                songDetails.setYear(year);
                int stars = 0;

                int checkedRadioId = rgStars.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.rb1){
                    songDetails.setStars(1);
                } else if(checkedRadioId == R.id.rb2){
                    songDetails.setStars(2);
                } else if(checkedRadioId == R.id.rb3){
                    songDetails.setStars(3);
                } else if(checkedRadioId == R.id.rb4){
                    songDetails.setStars(4);
                } else if(checkedRadioId == R.id.rb5){
                    songDetails.setStars(5);
                }
                dbh.editSong(songDetails);
                dbh.close();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(songDetails.getId());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(EditActivity.this, ShowActivity.class);
                startActivity(i);
            }
        });

    }

}