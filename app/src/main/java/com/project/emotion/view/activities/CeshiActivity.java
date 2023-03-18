package com.project.emotion.view.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.project.emotion.R;

public class CeshiActivity extends Activity {

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ceshi);
    }

    public void fineshedtest(View view)
    {
        if (score < 16)
            Toast.makeText(getApplicationContext(),"你可能有轻度的心理问题，可尝试着进行自我心理咨询,得分："+score,Toast.LENGTH_LONG).show();

        else if(score >16)
            Toast.makeText(getApplicationContext(),"你有较严重的心理问题，这时应考虑到医院进行心理咨询,得分："+score,Toast.LENGTH_LONG).show();
        score=0;
    }

    public void onRadioClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.ans0101:
                break;
            case R.id.ans0102:
                score++;
                break;
            case R.id.ans0103:
                score=score+2;
                break;
            case R.id.ans0104:
                score=score+3;
                break;
            case R.id.ans0201:
                break;
            case R.id.ans0202:
                score++;
                break;
            case R.id.ans0203:
                score=score+2;
                break;
            case R.id.ans0204:
                score=score+3;
                break;
            case R.id.ans0301:
                break;
            case R.id.ans0302:
                score++;
                break;
            case R.id.ans0303:
                score=score+2;
                break;
            case R.id.ans0304:
                score=score+3;
                break;
            case R.id.ans0401:
                break;
            case R.id.ans0402:
                score++;
                break;
            case R.id.ans0403:
                score=score+2;
                break;
            case R.id.ans0404:
                score=score+3;
                break;
            case R.id.ans0501:
                break;
            case R.id.ans0502:
                score++;
                break;
            case R.id.ans0503:
                score=score+2;
                break;
            case R.id.ans0504:
                score=score+3;
                break;
            case R.id.ans0601:
                break;
            case R.id.ans0602:
                score++;
                break;
            case R.id.ans0603:
                score=score+2;
                break;
            case R.id.ans0604:
                score=score+3;
                break;
            case R.id.ans0701:
                break;
            case R.id.ans0702:
                score++;
                break;
            case R.id.ans0703:
                score=score+2;
                break;
            case R.id.ans0704:
                score=score+3;
                break;
            case R.id.ans0801:
                break;
            case R.id.ans0802:
                score++;
                break;
            case R.id.ans0803:
                score=score+2;
                break;
            case R.id.ans0804:
                score=score+3;
                break;
            case R.id.ans0901:
                break;
            case R.id.ans0902:
                score++;
                break;
            case R.id.ans0903:
                score=score+2;
                break;
            case R.id.ans0904:
                score=score+3;
                break;
            case R.id.ans1001:
                break;
            case R.id.ans1002:
                score++;
                break;
            case R.id.ans1003:
                score=score+2;
                break;
            case R.id.ans1004:
                score=score+3;
                break;
            case R.id.ans1101:
                break;
            case R.id.ans1102:
                score++;
                break;
            case R.id.ans1103:
                score=score+2;
                break;
            case R.id.ans1104:
                score=score+3;
                break;
            case R.id.ans1201:
                break;
            case R.id.ans1202:
                score++;
                break;
            case R.id.ans1203:
                score=score+2;
                break;
            case R.id.ans1204:
                score=score+3;
                break;
            case R.id.ans1301:
                break;
            case R.id.ans1302:
                score++;
                break;
            case R.id.ans1303:
                score=score+2;
                break;
            case R.id.ans1304:
                score=score+3;
                break;
            case R.id.ans1401:
                break;
            case R.id.ans1402:
                score++;
                break;
            case R.id.ans1403:
                score=score+2;
                break;
            case R.id.ans1404:
                score=score+3;
                break;
            case R.id.ans1501:
                break;
            case R.id.ans1502:
                score++;
                break;
            case R.id.ans1503:
                score=score+2;
                break;
            case R.id.ans1504:
                score=score+3;
                break;
            case R.id.ans1601:
                break;
            case R.id.ans1602:
                score++;
                break;
            case R.id.ans1603:
                score=score+2;
                break;
            case R.id.ans1604:
                score=score+3;
                break;
            case R.id.ans1701:
                break;
            case R.id.ans1702:
                score++;
                break;
            case R.id.ans1703:
                score=score+2;
                break;
            case R.id.ans1704:
                score=score+3;
                break;
            case R.id.ans1801:
                break;
            case R.id.ans1802:
                score++;
                break;
            case R.id.ans1803:
                score=score+2;
                break;
            case R.id.ans1804:
                score=score+3;
                break;
            case R.id.ans1901:
                break;
            case R.id.ans1902:
                score++;
                break;
            case R.id.ans1903:
                score=score+2;
                break;
            case R.id.ans1904:
                score=score+3;
                break;
            case R.id.ans2001:
                break;
            case R.id.ans2002:
                score++;
                break;
            case R.id.ans2003:
                score=score+2;
                break;
            case R.id.ans2004:
                score=score+3;
                break;
        }
    }
}
