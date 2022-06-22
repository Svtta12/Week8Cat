package science.example.week5cat.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import science.example.week5cat.databinding.ActivityApplicationBinding

class ApplicationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApplicationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}