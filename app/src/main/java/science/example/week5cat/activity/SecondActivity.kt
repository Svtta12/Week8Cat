package science.example.week5cat.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import science.example.week5cat.CatViewModel
import science.example.week5cat.adapter.SecondAdapter
import science.example.week5cat.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var adapter: SecondAdapter
    private lateinit var model: CatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)

        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            adapter = SecondAdapter(model.getFavoriteCats(), this@SecondActivity)
            binding.recyclerView.layoutManager = GridLayoutManager(this@SecondActivity, 2)
            binding.recyclerView.adapter = adapter
        }

    }
}