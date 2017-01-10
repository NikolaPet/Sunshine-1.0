package com.example.nikola.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by stefa_000 on 1/4/2017.
 */

public class ThirdActivity extends AppCompatActivity {
    ImageView imgView;
    ImageView imgView2;
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        imgView = (ImageView) findViewById(R.id.imageView2);
        imgView2 = (ImageView) findViewById(R.id.imageView3);
        txtView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        int number = intent.getIntExtra(SecondActivity.EXTRA_MESSAGE,0);
        if(number == 1) {
            txtView.setText("А а \n А како Авион");
        }
        else if(number == 6) {
            txtView.setText("Ѓ ѓ \n Ѓ како Ѓердан");
            imgView2.setImageResource(R.drawable.necklace);
        }
        else if(number == 11) {
            txtView.setText("И и \n И како Играчки");
            imgView2.setImageResource(R.drawable.toys);
        }
        else if(number == 16) {
            txtView.setText("М м \n М како Мајмун");
            imgView2.setImageResource(R.drawable.monkey);
        }
        else if(number == 21) {
            txtView.setText("Р р \n Р како Рака");
            imgView2.setImageResource(R.drawable.hand);
        }
        else if(number == 26) {
            txtView.setText("Ф ф \n Ф како Фудбал");
            imgView2.setImageResource(R.drawable.ball);
        }
        else if(number == 2) {
            txtView.setText("Б б \n Б како Балони");
            imgView2.setImageResource(R.drawable.ballons);
        }
        else if(number == 7) {
            txtView.setText("Е е \n Е како Еж");
            imgView2.setImageResource(R.drawable.hedgehog);
        }
        else if(number == 12) {
            txtView.setText("Ј ј \n Ј како Јагода");
            imgView2.setImageResource(R.drawable.strawberry);
        }
        else if(number == 17) {
            txtView.setText("Н н \n Н како Носорог");
            imgView2.setImageResource(R.drawable.rhino);
        }
        else if(number == 22) {
            txtView.setText("С с \n С како Сонце");
            imgView2.setImageResource(R.drawable.sun);
        }
        else if(number == 27) {
            txtView.setText("Х х \n Х како Хеликоптер");
            imgView2.setImageResource(R.drawable.helicopter);
        }
        else if(number == 3){
            txtView.setText("В в \n В како Воз");
            imgView2.setImageResource(R.drawable.train);
        }
        else if(number == 8){
            txtView.setText("Ж ж \n Ж како Жирафа");
            imgView2.setImageResource(R.drawable.giraffe);
        }
        else if(number == 13){
            txtView.setText("К к \n К како камион");
            imgView2.setImageResource(R.drawable.truck);
        }
        else if(number == 18){
            txtView.setText("Њ њ \n Њ како Сирење");
            imgView2.setImageResource(R.drawable.cheese);
        }
        else if(number == 23){
            txtView.setText("Т т \n Т како Тигар");
            imgView2.setImageResource(R.drawable.tiger);
        }
        else if(number == 28){
            txtView.setText("Ц ц \n Ц како Цвет");
            imgView2.setImageResource(R.drawable.flower);
        }
        else if(number == 4){
            txtView.setText("Г г \n Г како Гулаб");
            imgView2.setImageResource(R.drawable.dove);
        }
        else if(number == 9){
            txtView.setText("З з \n З како Зима");
            imgView2.setImageResource(R.drawable.winter);
        }
        else if(number == 14){
            txtView.setText("Л л \n Л како Лав");
            imgView2.setImageResource(R.drawable.lion);
        }
        else if(number == 19){
            txtView.setText("О о \n О како Облачиња");
            imgView2.setImageResource(R.drawable.clouds);
        }
        else if(number == 24){
            txtView.setText("Ќ ќ \n Ќ како Ќебе");
            imgView2.setImageResource(R.drawable.blanket);
        }
        else if(number == 29){
            txtView.setText("Ч ч \n Ч како Чадор");
            imgView2.setImageResource(R.drawable.umbrella);
        }
        if(number == 5){
            txtView.setText("Д д \n Д како Дрво");
            imgView2.setImageResource(R.drawable.tree);
        }
        else if(number == 10){
            txtView.setText("Ѕ ѕ \n Ѕ како Ѕвоно");
            imgView2.setImageResource(R.drawable.bell);
        }
        else if(number == 15){
            txtView.setText("Љ љ \n Љ како Љубов");
            imgView2.setImageResource(R.drawable.love);
        }
        else if(number == 20){
            txtView.setText("П п \n П како Панда");
            imgView2.setImageResource(R.drawable.panda);
        }
        else if(number == 25){
            txtView.setText("У у \n У како Уста");
            imgView2.setImageResource(R.drawable.mouth);
        }
        else if(number == 30){
            txtView.setText("Џ џ \n Џ како Џамлии");
            imgView2.setImageResource(R.drawable.marbles);
        }
        else if(number == 31){
            txtView.setText("Ш ш \n Ш како Шапка");
            imgView2.setImageResource(R.drawable.hat);
        }
    }

}
