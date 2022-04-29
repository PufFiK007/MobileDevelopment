package com.example.laba2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.laba2.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newGame()
    }

    private fun newGame() {
        binding.UserWord.text?.clear()

        val sWord = getWord()

        val mixed = sWord.toCharArray().let {
            it.shuffle()
            it.concatToString()
        }

        binding.SrumbleWord.text = mixed
        var tmp: Int = 1

        binding.button.setOnClickListener {
            val userWord = binding.UserWord.text.toString() ?: ""

            if (userWord.equals(sWord)) {
                Toast.makeText(this, "Right!", Toast.LENGTH_SHORT).show()
                newGame()
                tmp = 1
            } else {
                val hint: String = showLetter(tmp, sWord)
                Toast.makeText(this, "Wrong! Word starts with " + hint, Toast.LENGTH_SHORT).show()
                binding.SrumbleWord.text = hint(sWord, tmp)
                tmp++
        }
        }

    }

    private fun getWord(): String {
        val words = resources.getStringArray(R.array.Words)
        return words[Random.nextInt(words.size)]
    }

    private fun hint(word: String, tmp: Int): String {
        var hintWord: String = ""
        var shuffleWord: String = ""

        if(tmp >= word.length-1)
            return word

        for(i in 0..word.length-1)
        {
            if(i < tmp)
                hintWord += word[i]
                else
                    shuffleWord += word[i]
        }

        shuffleWord = shuffleWord.toCharArray().let {
            it.shuffle()
            it.concatToString()
        }

        hintWord += shuffleWord
        return hintWord
    }

    private fun showLetter(tmp: Int, word: String): String {

        if(tmp >= word.length-1)
            return word

        var letters: String = ""
        for(i in 0..tmp-1)
        {
            letters += word[i]
        }
        return letters
    }
}
