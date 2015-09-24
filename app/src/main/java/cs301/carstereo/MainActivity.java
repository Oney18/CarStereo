package cs301.carstereo;

import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity implements View.OnLongClickListener{

    Switch powSwitch;
    SeekBar volLevel;
    Button volDown;
    Button volUp;
    Button tunDown;
    Button tunUp;
    Button pre1;
    Button pre2;
    Button pre3;
    Button pre4;
    Button pre5;
    Button pre6;
    RadioButton FM;
    RadioButton AM;
    RadioButton CD;
    RadioGroup options;
    TextView disp;

    double FMval;
    int AMval;
    DecimalFormat df = new DecimalFormat("#.0");
    int[] AMpresets = {550, 600, 650, 700, 750, 800};
    double[] FMpresets = {90.9, 92.9, 94.9, 96.9, 98.9, 100.9};
    int volVal;
    MediaPlayer templesPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FMval = 98.1;
        AMval = 1110;
        volVal = 50;
        disp = (TextView) findViewById(R.id.stereoDisp);
        powSwitch = (Switch) findViewById(R.id.pow_switch);
        volLevel = (SeekBar) findViewById(R.id.volBar);
        volDown = (Button) findViewById(R.id.vol_less_button);
        volUp = (Button) findViewById(R.id.vol_plus_button);
        tunDown = (Button) findViewById(R.id.tuning_down);
        tunUp = (Button) findViewById(R.id.tuning_up);
        pre1 = (Button) findViewById(R.id.preset1);
        pre2 = (Button) findViewById(R.id.preset2);
        pre3 = (Button) findViewById(R.id.preset3);
        pre4 = (Button) findViewById(R.id.preset4);
        pre5 = (Button) findViewById(R.id.preset5);
        pre6 = (Button) findViewById(R.id.preset6);
        FM = (RadioButton) findViewById(R.id.FM_toggle);
        AM = (RadioButton) findViewById(R.id.AM_toggle);
        CD = (RadioButton) findViewById(R.id.CD_toggle);
        options = (RadioGroup) findViewById(R.id.radio_options);
        volLevel.setProgress(volVal);

        templesPlayer= MediaPlayer.create(MainActivity.this, R.raw.temples);

        volLevel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true; //overrides the seek bar being able ot be dragged
            }
        });

        pre1.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                resetPreset(v);
                return true;
            }
        });

        pre2.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                resetPreset(v);
                return true;
            }
        });

        pre3.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                resetPreset(v);
                return true;
            }
        });

        pre4.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                resetPreset(v);
                return true;
            }
        });

        pre5.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                resetPreset(v);
                return true;
            }
        });

        pre6.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                resetPreset(v);
                return true;
            }
        });
        this.displayUpdate();


    }

    public void playMusic(View view)
    {
        if(powSwitch.isChecked() && CD.isChecked())
        {
            templesPlayer.start();
        }
    }

    public void stopMusic(View view)
    {
        if(powSwitch.isChecked() && CD.isChecked())
        {
            templesPlayer.pause();
        }
    }

    public void volUp(View view)
    {
        if(powSwitch.isChecked())
        {
            volVal += 5;
            volLevel.setProgress(volVal);
        }
    }

    public void volDown(View view)
    {
        if(powSwitch.isChecked())
        {
            volVal -= 5;
            volLevel.setProgress(volVal);
        }
    }

    public void tunPre(View view)
    {
        Button presetSelected = (Button) view;
        int numSelected = Integer.valueOf(presetSelected.getText().toString());
        if(FM.isChecked())
        {
            FMval = FMpresets[numSelected-1];
        }
        else if(AM.isChecked())
        {
            AMval = AMpresets[numSelected-1];
        }
        this.displayUpdate();
    }

    public void dispUpdate(View view)
    {
        this.displayUpdate();
    }

    public void tunDown(View view)
    {
        if(FM.isChecked())
        {
            FMval -= .2;
            if(FMval < 88.1) FMval = 107.9;
        }
        else if(AM.isChecked())
        {
            AMval -= 10;
            if(AMval < 530) AMval = 1700;
        }
        this.displayUpdate();
    }

    public void tunUp(View view)
    {
        if(FM.isChecked())
        {
            FMval += .2;
            if (FMval > 107.9) FMval = 88.1;
        }
        else if(AM.isChecked())
        {
            AMval += 10;
            if (AMval > 1700) AMval = 530;
        }
        this.displayUpdate();
    }

    private void displayUpdate() {
        if(!CD.isChecked() && templesPlayer.isPlaying())
        {
            templesPlayer.reset();
            templesPlayer= MediaPlayer.create(MainActivity.this, R.raw.temples);
        }

        if(AM.isChecked())
        {
            disp.setText(AMval + " kHz");
        }
        else if(FM.isChecked())
        {
            disp.setText(df.format(FMval) + " MHz");
        }
        else if (CD.isChecked())
        {
            disp.setText("CD ENABLED");
        }
        else
        {
            disp.setText("");
        }
    }

    public void changePower(View view) {

        if(!powSwitch.isChecked())
        {
            volDown.setBackgroundResource(R.color.Gray);
            volUp.setBackgroundResource(R.color.Gray);
            tunDown.setBackgroundResource(R.color.Gray);
            tunUp.setBackgroundResource(R.color.Gray);
            pre1.setBackgroundResource(R.color.Gray);
            pre2.setBackgroundResource(R.color.Gray);
            pre3.setBackgroundResource(R.color.Gray);
            pre4.setBackgroundResource(R.color.Gray);
            pre5.setBackgroundResource(R.color.Gray);
            pre6.setBackgroundResource(R.color.Gray);
            volDown.setTextColor(getResources().getColor(R.color.Gray));
            volUp.setTextColor(getResources().getColor(R.color.Gray));
            tunDown.setTextColor(getResources().getColor(R.color.Gray));
            tunUp.setTextColor(getResources().getColor(R.color.Gray));
            pre1.setTextColor(getResources().getColor(R.color.Gray));
            pre2.setTextColor(getResources().getColor(R.color.Gray));
            pre3.setTextColor(getResources().getColor(R.color.Gray));
            pre4.setTextColor(getResources().getColor(R.color.Gray));
            pre5.setTextColor(getResources().getColor(R.color.Gray));
            pre6.setTextColor(getResources().getColor(R.color.Gray));
            options.clearCheck();
            options.setEnabled(false);
            AM.setEnabled(false);
            FM.setEnabled(false);
            CD.setEnabled(false);
            if(templesPlayer.isPlaying())
            {
                templesPlayer.reset();
                templesPlayer= MediaPlayer.create(MainActivity.this, R.raw.temples);
            }
        }
        else
        {
            volDown.setBackgroundResource(android.R.drawable.btn_default);
            volUp.setBackgroundResource(android.R.drawable.btn_default);
            tunDown.setBackgroundResource(android.R.drawable.btn_default);
            tunUp.setBackgroundResource(android.R.drawable.btn_default);
            pre1.setBackgroundResource(android.R.drawable.btn_default);
            pre2.setBackgroundResource(android.R.drawable.btn_default);
            pre3.setBackgroundResource(android.R.drawable.btn_default);
            pre4.setBackgroundResource(android.R.drawable.btn_default);
            pre5.setBackgroundResource(android.R.drawable.btn_default);
            pre6.setBackgroundResource(android.R.drawable.btn_default);
            volDown.setTextColor(getResources().getColor(R.color.Black));
            volUp.setTextColor(getResources().getColor(R.color.Black));
            tunDown.setTextColor(getResources().getColor(R.color.Black));
            tunUp.setTextColor(getResources().getColor(R.color.Black));
            pre1.setTextColor(getResources().getColor(R.color.Black));
            pre2.setTextColor(getResources().getColor(R.color.Black));
            pre3.setTextColor(getResources().getColor(R.color.Black));
            pre4.setTextColor(getResources().getColor(R.color.Black));
            pre5.setTextColor(getResources().getColor(R.color.Black));
            pre6.setTextColor(getResources().getColor(R.color.Black));
            options.setEnabled(true);
            AM.setEnabled(true);
            FM.setEnabled(true);
            CD.setEnabled(true);
            FM.setChecked(true);


        }
        this.displayUpdate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean resetPreset(View v) {
        Button presetSelected = (Button) v;
        int numSelected = Integer.valueOf(presetSelected.getText().toString());
        if(FM.isChecked())
        {
            FMpresets[numSelected-1] = FMval;
        }
        else if(AM.isChecked())
        {
            AMpresets[numSelected-1] = AMval;
        }
        disp.setText(numSelected + " RESET");
        return true;
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
