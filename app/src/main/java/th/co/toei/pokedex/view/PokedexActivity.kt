package th.co.toei.pokedex.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import th.co.toei.pokedex.R
import th.co.toei.pokedex.alert.AlertMessageDialogFragment
import th.co.toei.pokedex.base.BaseActivity
import th.co.toei.pokedex.databinding.ActivityPokedexBinding

@AndroidEntryPoint
class PokedexActivity : BaseActivity() {

    private lateinit var binding: ActivityPokedexBinding

    private val pokedexViewModel: PokedexViewModel by viewModels()

    private lateinit var pokedexAdapter: PokedexAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokedexBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeData()
        init()
    }

    private fun init() {
        pokedexViewModel.getPokemonList()
    }

    private fun observeData() {
        pokedexViewModel.pokemonListLiveData.observe(this, Observer {
            if (!::pokedexAdapter.isInitialized) {
                initAdapter()
            }

            pokedexAdapter.setListData(it)
        })

        pokedexViewModel.loadingLiveData.observe(this, Observer {
            if (it) {
                alertLoading.show(supportFragmentManager, TAG)
            } else {
                alertLoading.dismiss()
            }
        })

        pokedexViewModel.errorMessageLiveData.observe(this, Observer {
            AlertMessageDialogFragment.Builder()
                .setMessage(it)
                .build()
                .show(supportFragmentManager, TAG)
        })
    }

    private fun initAdapter() {
        pokedexAdapter = PokedexAdapter()

        binding.recyclerViewPokemonList.apply {
            layoutManager =
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                else GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = pokedexAdapter
        }
    }

    companion object {
        private const val TAG = "PokedexActivity"
    }
}