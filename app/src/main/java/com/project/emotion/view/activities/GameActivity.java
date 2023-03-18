package com.project.emotion.view.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.emotion.R;
import com.project.emotion.utils.BastScode;
import com.project.emotion.widget.GameView;


public class GameActivity extends Activity implements View.OnClickListener{
    public TextView tvScrore;//计分的
    public TextView tvBestScore;//最高分
    public int score = 0;
    private int bestScores;//历史最高成绩
    private Button bt;

    private static GameActivity mainActivity = null;
    public GameActivity(){
        mainActivity = this;
    }

    public static GameActivity getMainActivity() {
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        inital();

    }

    @SuppressLint("SetTextI18n")
    public void inital() {
        tvBestScore = (TextView) findViewById(R.id.maxSorce);
        tvScrore = (TextView) findViewById(R.id.tvSorce);
        bt = (Button)findViewById(R.id.bt_cx);
        bt.setOnClickListener(this);
        BastScode bastScode = new BastScode(this);
        bestScores = bastScode.getBestScode();
        tvBestScore.setText(bestScores+"");
    }

    @Override
    public void onClick(View v) {
        GameView.getGameView().startGame();
    }

    public void clearScore(){
        score = 0;
        showScore();
    }
    public void showScore(){
        tvScrore.setText(score+"");
    }
    public void addScore(int s){
        score+=s;
        showScore();
        if (score > bestScores){
            bestScores = score;
            BastScode bs = new BastScode(this);
            bs.setBestScode(bestScores);
            tvBestScore.setText(bestScores+"");
        }
    }

    /**
     * 菜单、返回键响应
     */
    private long exitTime = 0;


}
