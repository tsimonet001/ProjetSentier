package etu.demo.piv1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import etu.demo.piv1.Question;
import etu.demo.piv1.R;

public class Quizz extends AppCompatActivity {
    private TextView scoreTextView;
    private Button nextButton, reloadButton; // remplacement du bouton reset
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private int score = 0;
    private int questionNumber = 0;
    private List<Question> questionList = new ArrayList<>(); // Initialize the question list
    private CountDownTimer countDownTimer; // Initialize the CountDownTimer
    private long timeLeftInMillis = 90000; // 90 seconds in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);


        scoreTextView = findViewById(R.id.scoreTextView);
        nextButton = findViewById(R.id.nextButton);
        reloadButton = findViewById(R.id.resetButton);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);

        // Initialize question list here (from SQLite or JSON file)
        questionList.add(new Question("À quelle période peut-on observer la floraison de l'Airelle des marais ?", "En hiver", "En automne", "En été", "En été"));
        questionList.add(new Question("Quels sont les propriétés thérapeutiques de l'ancolie commune ?", "Antipyrétique et antihistaminique", "Astringente et antiseptique", "Antibiotique et anti-inflammatoire", "Astringente et antiseptique"));
        questionList.add(new Question("Quel est le statut de l'asphodèle blanc ?", "En danger critique d'extinction", "Préoccupation mineure", "En danger d'extinction", "Préoccupation mineure"));
        questionList.add(new Question("Quel est le symbole associé au Bouton d'or ?", "La paix", "La joie, l'impatience, l'enfance et la moquerie", "La pureté", "La joie, l'impatience, l'enfance et la moquerie"));
        questionList.add(new Question("Comment consommer le Chèvrefeuille pour traiter les bronches ?", "Infusion des feuilles", "Décoction d'écorce", "Sirop de fleurs", "Infusion des feuilles"));
        questionList.add(new Question("Quels sont les critères de reconnaissance du houx ?", "Feuilles persistantes et épineuses, fruits en forme de baies jaunes", "Feuilles caduques et dentelées, fruits en forme de capsules vertes", "Feuilles coriaces et persistantes, fruits en forme de baies pouvant varier de couleur", "Feuilles coriaces et persistantes, fruits en forme de baies pouvant varier de couleur"));
        questionList.add(new Question("Quelle est l'origine du cépage Noah ?", "Il est originaire de France", "Il est originaire des États-Unis", "Il est originaire d'Amérique du Sud", "Il est originaire des États-Unis"));
        questionList.add(new Question("Pourquoi l'achillée millefeuille était-elle utilisée lors de la fête païenne de la Saint-Jean au Moyen Âge ?", "Pour célébrer la naissance de Jean le Baptiste", "Pour conjurer le diable et les mauvais sorts", "Pour honorer les druides", "Pour conjurer le diable et les mauvais sorts"));
        questionList.add(new Question("Pourquoi les Celtes utilisaient-ils le noisetier ?", "Pour éloigner les loups et les serpents", "Pour soigner les plaies", "Pour la magie druidique, la divination et la parole prophétique", "Pour la magie druidique, la divination et la parole prophétique"));
        questionList.add(new Question("Pourquoi les fougères ont-elles été populaires en Grande-Bretagne à l'époque victorienne ?", "Elles étaient très décoratives", "Elles étaient peu étudiées jusque-là et ont suscité un regain d'intérêt", "Elles avaient des propriétés magiques", "Elles étaient peu étudiées jusque-là et ont suscité un regain d'intérêt"));

        showQuestion();
        reloadButton.setOnClickListener(new View.OnClickListener() { // remplacement du bouton reset
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
// Initialize CountDownTimer here
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                nextButton.setEnabled(false);
                radioButton1.setEnabled(false);
                radioButton2.setEnabled(false);
                radioButton3.setEnabled(false);
                showResult();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        TextView countDownTextView = findViewById(R.id.timerTextView);
        countDownTextView.setText(timeLeftFormatted);
    }
    private void showQuestion() {

        Question question = questionList.get(questionNumber);

        // Set question text here
        TextView questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(question.getQuestionText());

        // Set radio button text here
        radioButton1.setText(question.getAnswer1());
        radioButton2.setText(question.getAnswer2());
        radioButton3.setText(question.getAnswer3());

        radioGroup.clearCheck();

        nextButton.setEnabled(false);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        if (view.getId() == R.id.radioButton1 ||
                view.getId() == R.id.radioButton2 ||
                view.getId() == R.id.radioButton3) {
            if (checked) {
                nextButton.setEnabled(true);
            }
        }
    }


    public void onNextButtonClicked(View view) {
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == -1) {
            // User didn't select any answer
            return;
        }

        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        String selectedAnswer = selectedRadioButton.getText().toString();

        Question question = questionList.get(questionNumber);
        if (selectedAnswer.equals(question.getCorrectAnswer())) {
            score++;
            scoreTextView.setText("Score : " + score);
        }

        if (questionNumber < questionList.size() - 1) {
            questionNumber++;
            showQuestion();
        } else {
            showResult();
        }
    }


    private void showResult() {
        String message;
        if (score >= 5) {
            message = "Bravo tu as gagné un cadeau !";
        } else {
            message = "Oh mince ! Tu as perdu !";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Résultat");
        builder.setMessage("Score : " + score + "\n" + message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Reset score and question number for new game
                score = 0;
                questionNumber = 0;
                showQuestion();
            }
        });
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

        scoreTextView.setText("Score : " + score);
    }
}