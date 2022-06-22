package science.example.week5cat

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import science.example.week5cat.activity.SecondActivity
import science.example.week5cat.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var vModel: CatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Fresco.initialize(context)
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        vModel = ViewModelProvider(requireActivity())[CatViewModel::class.java]

        setImage()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val navHostFragment = requireActivity().supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        val navhost = navHostFragment.navController

        binding.apply {
            buttonYes.setOnClickListener {
                vModel.Like()
                setImage()
            }
            buttonNo.setOnClickListener {
                setImage()
            }

            buttonFavorite.setOnClickListener {
                navhost.navigate(R.id.action_mainFragment_to_secondActivity)
//                findNavController().navigate(R.id.action_mainFragment_to_secondActivity)


            }
            buttonApp.setOnClickListener {
                showPopupMenu(view = buttonApp)
            }
        }
    }


    private fun setImage() {
        CoroutineScope(Job()).launch {
            binding.imageCat.setImageURI(vModel.getCat().url)
        }
    }


    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        val context = view.context

        popupMenu.menu.add(0,ID_APP, Menu.NONE, context.getString(R.string.app))
        popupMenu.setOnMenuItemClickListener{
            when (it.itemId){
                ID_APP -> {
                    findNavController().navigate(R.id.action_mainFragment_to_applicationActivity)
                }
            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }

    companion object {
        private const val ID_APP = 1
    }
}