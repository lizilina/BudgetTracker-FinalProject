package com.example.budgettracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import com.example.budgettracker.databinding.ActivityAddTransactionBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
            val description = binding.descriptionInput.text.toString()
            val amount = binding.amountInput.text.toString().toDoubleOrNull()

            if(label.isEmpty())
                binding.labelLayout.error = "Please enter a valid label"

            else if(amount == null)
                binding.amountLayout.error = "Please enter a valid amount"

            else {
                val transaction = Transaction(0, label, amount, description )
                insert(transaction)
            }
        }

        binding.closeBtn.setOnClickListener{
            finish()
        }
    }

    private fun insert(transaction: Transaction){
        val  db = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "transactions").build()

        GlobalScope.launch {
            db.transactionDao().insertAll(transaction)
            finish()
        }

    }

}