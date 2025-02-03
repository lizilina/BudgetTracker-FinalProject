package com.example.budgettracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.budgettracker.databinding.ActivityAddTransactionBinding

class AddTransactionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTransactionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        binding = ActivityAddTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.labelInput.addTextChangedListener {
            if(it!!.count() > 0)
                binding.labelLayout.error = null
        }

        binding.amountInput.addTextChangedListener {
            if(it!!.count() > 0)
                binding.amountLayout.error = null
        }



        binding.addTransactionbtn.setOnClickListener{
            val label = binding.labelInput.text.toString()
            val amount = binding.amountInput.text.toString().toDoubleOrNull()

            if(label.isEmpty())
                binding.labelLayout.error = "Please enter a valid label"

            if(amount == null)
                binding.amountLayout.error = "Please enter a valid amount"
        }

        binding.closeBtn.setOnClickListener{
            finish()
        }



    }
}