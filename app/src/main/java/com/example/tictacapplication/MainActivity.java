package com.example.tictacapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    int counter = 0;
    ConstraintLayout constraintLayout;
    TextView player1Score;
    TextView player2Score;
    String[] initialBoard = {"", "", "", "", "", "", "", "", ""};
    ArrayList<String> board = new ArrayList<>(Arrays.asList(initialBoard));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.containerLayout);
        player1Score = findViewById(R.id.player1Score);
        player2Score = findViewById(R.id.player2Score);
    }

    public void onClickPlay(View view) {
        Button button = (Button) view;
        Drawable top;
        String symbol = null;
        if (board.get(Integer.parseInt(button.getTag().toString())).isEmpty()) {
            if (counter % 2 == 0) {
                top = getResources().getDrawable(R.drawable.x_size);
                symbol = "x";
            } else {
                top = getResources().getDrawable(R.drawable.o_size);
                symbol = "o";
            }
            board.set(Integer.parseInt(button.getTag().toString()), symbol);
            button.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
            counter++;
        }

        if (symbol!= null && checkWinner(symbol)) {
            LottieAnimationView lottieAnimationView = findViewById(R.id.lottieAnimation);
            lottieAnimationView.playAnimation();
            lottieAnimationView.setVisibility(View.VISIBLE);

            clearBoard();
            if (symbol.equals("x"))
                player1Score.setText(String.valueOf(Integer.parseInt(player1Score.getText().toString()) + 1));
            else
                player2Score.setText(String.valueOf(Integer.parseInt(player2Score.getText().toString()) + 1));
        }

        if (counter == 9 && !checkWinner(symbol)) {
            clearBoard();
        }
    }

    private boolean checkWinner(String symbol) {
        //rows
        // 0 1 2
        // 3 4 5
        // 6 7 8
        for (int i = 0; i < board.size(); i += 3) {
            if (symbol.equals(board.get(i)) && symbol.equals(board.get(i + 1)) && symbol.equals(board.get(i + 2))) {
                return true;
            }
        }

        //columns
        //0 3 6
        //1 4 7
        //2 5 8
        for (int i = 0; i < 3; i++) {
            if (symbol.equals(board.get(i)) && symbol.equals(board.get(i + 3)) && symbol.equals(board.get(i + 6))) {
                return true;
            }
        }

        //diagonals
        //0 4 8
        //2 4 6
        return (symbol.equals(board.get(0)) && symbol.equals(board.get(4)) && symbol.equals(board.get(8))) ||
                (symbol.equals(board.get(2)) && symbol.equals(board.get(4)) && symbol.equals(board.get(6)));
    }

    private void clearBoard() {
        board = new ArrayList<>(Arrays.asList(initialBoard));
        counter = 0;
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (constraintLayout.getChildAt(i) instanceof Button)
                ((Button) constraintLayout.getChildAt(i)).setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }
}