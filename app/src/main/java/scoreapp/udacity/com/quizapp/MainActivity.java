package scoreapp.udacity.com.quizapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int rightAnswer = 0;
    int wrongAnswer = 0;
    TextView result = null;
    EditText nameText = null;
    CheckBox usaCheckBox = null;
    CheckBox australiaCheckBox = null;
    CheckBox algeriaCheckbox = null;
    CheckBox indiaCheckBox = null;
    String resultBuilder = null;
    EditText   founderOfGoogleText = null;
    List<Integer> idLists  = new ArrayList<Integer>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Name Field
        nameText = (EditText) findViewById(R.id.name);

        //Question 1 in Radiobutton
        RadioButton googleRadio        = (RadioButton) findViewById(R.id.google);
        googleRadio.setOnClickListener(this);
        RadioButton appleRadio         = (RadioButton) findViewById(R.id.apple);
        appleRadio.setOnClickListener(this);
        RadioButton androidRadio       = (RadioButton) findViewById(R.id.android_inc);
        androidRadio.setOnClickListener(this);
        RadioButton nokiaRadio         = (RadioButton) findViewById(R.id.nokia);
        nokiaRadio.setOnClickListener(this);

        //Question 2 in checkBox
        algeriaCheckbox     = (CheckBox)    findViewById(R.id.algeria);
        indiaCheckBox       = (CheckBox)    findViewById(R.id.india);
        usaCheckBox         = (CheckBox)    findViewById(R.id.usa);
        australiaCheckBox   = (CheckBox)    findViewById(R.id.australia);

        //Question 3 in EditText
        founderOfGoogleText = (EditText)    findViewById(R.id.googleFounder);

        //Question 4 in RadioButton
        RadioButton mice1              = (RadioButton) findViewById(R.id.mice1);
        mice1.setOnClickListener(this);
        RadioButton mice2              = (RadioButton) findViewById(R.id.mice2);
        mice2.setOnClickListener(this);
        RadioButton mice3              = (RadioButton) findViewById(R.id.mice3);
        mice3.setOnClickListener(this);
        RadioButton mice4              = (RadioButton) findViewById(R.id.mice4);
        mice4.setOnClickListener(this);

        //Question 5 in RadioButton
        RadioButton androidDigit       = (RadioButton) findViewById(R.id.android_digit);
        androidDigit.setOnClickListener(this);
        RadioButton androidCapitalize  = (RadioButton) findViewById(R.id.android_capitalize);
        androidCapitalize.setOnClickListener(this);
        RadioButton androidAutotext    = (RadioButton) findViewById(R.id.android_autoText);
        androidAutotext.setOnClickListener(this);
        RadioButton androidPadding     = (RadioButton) findViewById(R.id.android_padding);
        androidPadding.setOnClickListener(this);

        //Result Button
        Button getResult = (Button) findViewById(R.id.result_button);
        getResult.setOnClickListener(this);

        // Result TextView to display
        result = (TextView) findViewById(R.id.result);

    }

    // SetOnClickListener Implementations
    @Override
    public void onClick(View v)
     {
        int id = v.getId();
        boolean isClickedFirstTime = this.preventDoubleClickingOfOptions(id);
        switch (id)
        {
            case R.id.google:
                if(isClickedFirstTime) {
                    rightAnswer++;
                }
            break;
            case R.id.apple:
                if(isClickedFirstTime){
                    wrongAnswer++;
                }
            break;
            case R.id.android_inc:
                if(isClickedFirstTime){
                    wrongAnswer++;
            }
            break;
            case R.id.nokia:
                if(isClickedFirstTime){
                    wrongAnswer++;
                }
            break;
            case R.id.mice1:
                if(isClickedFirstTime){
                    rightAnswer++;
                }
            break;
            case R.id.mice2:
                if(isClickedFirstTime){
                    wrongAnswer++;
                }
            break;
            case R.id.mice3:
                if(isClickedFirstTime){
                    wrongAnswer++;
                }
            break;
            case R.id.mice4:
                if(isClickedFirstTime){
                    wrongAnswer++;
                }
            break;
            case R.id.android_autoText:
                if(isClickedFirstTime){
                    wrongAnswer++;
                }
            break;
            case R.id.android_padding:
                if(isClickedFirstTime){
                    rightAnswer++;
                }
            break;
            case R.id.android_capitalize:
                if(isClickedFirstTime){
                    wrongAnswer++;
                }
            break;
            case R.id.android_digit:
                if(isClickedFirstTime){
                    wrongAnswer++;
                }
            break;
            case R.id.result_button:
                this.displayResult();
            break;

        }
     }

     //This Method Validate CheckBox Answer
      private void validateCheckBoxAnswer() {
          if (usaCheckBox.isChecked() && australiaCheckBox.isChecked() && !(indiaCheckBox.isChecked()) && !(algeriaCheckbox.isChecked())) {
              rightAnswer++;
              return;
          }else if(!(usaCheckBox.isChecked()) && !(australiaCheckBox.isChecked()) && !(indiaCheckBox.isChecked()) && !(algeriaCheckbox.isChecked())){
         // Do nothing if none of the checkBox are Checked
          }else {
            wrongAnswer++;
        }

     }

     // This Method Validate answer for EditText(Google Founder) Question
   private void checkAnswer(EditText founderOfGoogleText){
        int id = founderOfGoogleText.getId();
        if(preventDoubleClickingOfOptions(id)){
           if(founderOfGoogleText.getText().toString().equalsIgnoreCase("larry page") ||
                   founderOfGoogleText.getText().toString().equalsIgnoreCase("sergey brin"))
           {
               rightAnswer++;
           }else if(founderOfGoogleText.getText().toString().equals(""))
           {
             //Do not increase any point
           }else{
               wrongAnswer++;
           }

       }
   }

   //This Method Display Result
    private void displayResult() {
        this.validateCheckBoxAnswer();
        this.checkAnswer(founderOfGoogleText);
        String name = nameText.getText().toString();
        if(name.equalsIgnoreCase("")) {
            Toast.makeText(this,"Please enter your Name", Toast.LENGTH_LONG).show();
        }else{
            resultBuilder = " Hello " + name + "," + " You have given " + rightAnswer + " right answers and " + wrongAnswer + " wrong answers";
        }
        TextView orderSummaryTextView = (TextView) findViewById(R.id.result);
        orderSummaryTextView.setText(resultBuilder);
        this.reset(name);

    }

    //This Method prevents incrementing of points(i.e. wrongAnswer && rightAnswer
    // from double clicking of user
    private boolean preventDoubleClickingOfOptions(int id){
        boolean isClickedfirstTime = false;
        for( Integer idCheck : idLists){
            if(idCheck == id ) {
                isClickedfirstTime = false;
                 return isClickedfirstTime;
            }
        }
        isClickedfirstTime = true;
        idLists.add(id);
        return isClickedfirstTime;
    }

    // This Method resets everything except Name Field
    private void reset(String name){
        RadioGroup firstGroup  = (RadioGroup) findViewById( R.id.firstRadioGroup);
        firstGroup.clearCheck();
        RadioGroup secondGroup = (RadioGroup) findViewById(R.id.secondRadioGroup);
        secondGroup.clearCheck();
        RadioGroup thirdGroup  = (RadioGroup) findViewById(R.id.thirdRadioGroup);
        thirdGroup.clearCheck();
        EditText  editText = (EditText) findViewById(R.id.googleFounder);
        editText.setText("");
        CheckBox firstCheckBox = (CheckBox) findViewById(R.id.algeria);
        firstCheckBox.setChecked(false);
        CheckBox secondCheckBox = (CheckBox) findViewById(R.id.india);
        secondCheckBox.setChecked(false);
        CheckBox thirdCheckBox = (CheckBox) findViewById(R.id.usa);
        thirdCheckBox.setChecked(false);
        CheckBox fourthCheckBox = (CheckBox) findViewById(R.id.australia);
        fourthCheckBox.setChecked(false);
        name = null;
        rightAnswer = 0;
        wrongAnswer = 0;
        idLists.clear();
    }
}
