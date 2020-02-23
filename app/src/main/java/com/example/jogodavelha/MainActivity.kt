package com.example.jogodavelha

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var jogadores = 0
    var quadradoDoJogador01:Int = R.drawable.ic_launcher_background
    var quadradodoJogador02: Int = R.drawable.ic_launcher_foreground
    var placarJogador01: Int = 0
    var placarJogador02: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buReiniciarPlacar.setOnClickListener { reiniciarPlacar() }

        val noValueForSharedPreferences = "0"

        val sharedPreferences = getSharedPreferences("jogoDaVelha", Context.MODE_PRIVATE)

        // Singup Actvity 01
        placarJogador01=
            sharedPreferences.getString("placarPlayer01", noValueForSharedPreferences).toInt()

        placarJogador02 =
            sharedPreferences.getString("placarPlayer02", noValueForSharedPreferences).toInt()

        tvPlacarPlayer01.text = placarJogador01.toString()
        tvPlacarPlayer02.text = placarJogador02.toString()


        buJogadores.setOnClickListener {
        modoDeJogo()
        }

        ivBotaoBe.setOnClickListener {
            quadradoDoJogador01 = R.drawable.quadrado_do_bernardo
            quadradodoJogador02 = R.drawable.quadrado_do_pai
            tvBotaoBe.text = "01"
            tvBotaoPai.text = "02"

        }

        ivBotaoPai.setOnClickListener {
            quadradoDoJogador01 = R.drawable.quadrado_do_pai
            quadradodoJogador02 = R.drawable.quadrado_do_bernardo
            tvBotaoBe.text = "Jogador 02"
            tvBotaoPai.text = "Jogador01"
        }

        buReiniciar.setOnClickListener { restart() }



    }

    fun putSharedPreferences() {
        val settings = getSharedPreferences("jogoDaVelha", Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putString("placarPlayer01", tvPlacarPlayer01.text.toString())
        editor.putString("placarPlayer02", tvPlacarPlayer02.text.toString())
        editor.apply()
    }

    fun restart(){
        recreate()
    }


    fun modoDeJogo(){
        if (jogadores == 1 ){
            jogadores = 2
            buJogadores.text = "2 Jogadores"
            buJogadores.setBackgroundColor(Color.GREEN)
        }
        else{
            jogadores = 1
            buJogadores.text = "1 Jogador"
            buJogadores.setBackgroundColor(Color.RED)
        }
    }



    fun buClick(view: View) {
        try {
            val buSelected = view as Button
            var cellID = 0
            when (buSelected.id) {
                R.id.Bu1 -> cellID = 1
                R.id.Bu2 -> cellID = 2
                R.id.Bu3 -> cellID = 3
                R.id.Bu4 -> cellID = 4
                R.id.Bu5 -> cellID = 5
                R.id.Bu6 -> cellID = 6
                R.id.Bu7 -> cellID = 7
                R.id.Bu8 -> cellID = 8
                R.id.Bu9 -> cellID = 9
            }

            //Toast.makeText(this,"ID"+cellID,Toast.LENGTH_SHORT).show()

            PlayGame(cellID, buSelected)
            Winner()
        }catch (e: Exception){}

    }

    fun reiniciarPlacar(){
        val settings = getSharedPreferences("jogoDaVelha", Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putString("placarPlayer01", "0")
        editor.putString("placarPlayer02", "0")
        editor.apply()
        recreate()
    }



    var Player1 = ArrayList<Int>()
    var Player2 = ArrayList<Int>()
    var activePlayer = 1
    var countPlays = 8



    fun PlayGame(cellID: Int, buSelected: Button) {
        if (activePlayer == 1) {
            buSelected.text = "X"
            buSelected.setBackgroundResource(quadradoDoJogador01)
            Player1.add(cellID)
            activePlayer = 2
            if(jogadores==1){
                AutoPlay()
            }

            countPlays -= 1
        } else {
            buSelected.text = "0"
            buSelected.setBackgroundResource(quadradodoJogador02)
            Player2.add(cellID)
            activePlayer = 1
            countPlays -= 1
        }
        buSelected.isEnabled = false
    }





    fun Winner() {
        var winer = -1
        //row 1
        if (Player1.contains(1) && Player1.contains(2) && Player1.contains(3)) {
            winer = 1
        }
        if (Player2.contains(1) && Player2.contains(2) && Player2.contains(3)) {
            winer = 2
        }

        //row 2
        if (Player1.contains(4) && Player1.contains(5) && Player1.contains(6)) {
            winer = 1
        }
        if (Player2.contains(4) && Player2.contains(5) && Player2.contains(6)) {
            winer = 2
        }

        //row3
        if (Player1.contains(7) && Player1.contains(8) && Player1.contains(9)) {
            winer = 1
        }
        if (Player2.contains(7) && Player2.contains(8) && Player2.contains(9)) {
            winer = 2
        }

        //collum 1
        if (Player1.contains(1) && Player1.contains(4) && Player1.contains(7)) {
            winer = 1
        }
        if (Player2.contains(1) && Player2.contains(4) && Player2.contains(7)) {
            winer = 2
        }

        //row 2
        if (Player1.contains(2) && Player1.contains(5) && Player1.contains(8)) {
            winer = 1
        }
        if (Player2.contains(2) && Player2.contains(5) && Player2.contains(8)) {
            winer = 2
        }

        //row3
        if (Player1.contains(3) && Player1.contains(6) && Player1.contains(9)) {
            winer = 1
        }
        if (Player2.contains(3) && Player2.contains(6) && Player2.contains(9)) {
            winer = 2
        }
        //X1
        if (Player1.contains(1) && Player1.contains(5) && Player1.contains(9)) {
            winer = 1
        }
        if (Player2.contains(1) && Player2.contains(5) && Player2.contains(9)) {
            winer = 2
        }

        //X1
        if (Player1.contains(3) && Player1.contains(5) && Player1.contains(7)) {
            winer = 1
        }
        if (Player2.contains(3) && Player2.contains(5) && Player2.contains(7)) {
            winer = 2
        }

        if (winer != -1) {
            if (winer == 1) {
                textView.setText("Player 1 WON (X)")
                placarJogador01 +=1
                tvPlacarPlayer01.text = placarJogador01.toString()
                putSharedPreferences()
            }

            if (winer == 2) {
                textView.setText("Player 2 WON (O)")
                placarJogador02 +=1
                tvPlacarPlayer02.text = placarJogador02.toString()
                putSharedPreferences()
            }
        }
    }

    fun AutoPlay() {

        val emptyCells = ArrayList<Int>()
        for (cellID in 1..9) {
            if (!(Player1.contains(cellID) || Player2.contains(cellID))) {
                emptyCells.add(cellID)
            }
        }
        val r = java.util.Random()
        val randIndex = r.nextInt(emptyCells.size - 0) + 0
        val cellID = emptyCells[randIndex]
        var buSelected: Button?
        when (cellID) {
            1 -> buSelected = Bu1
            2 -> buSelected = Bu2
            3 -> buSelected = Bu3
            4 -> buSelected = Bu4
            5 -> buSelected = Bu5
            6 -> buSelected = Bu6
            7 -> buSelected = Bu7
            8 -> buSelected = Bu8
            9 -> buSelected = Bu9
            else -> {
                buSelected = Bu1
            }
        }
        PlayGame(cellID, buSelected)
    }
}







