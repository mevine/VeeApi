package com.example.veeapi.views

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.veeapi.databinding.ActivityMainBinding
import com.example.veeapi.repository.Repository
import com.example.veeapi.viewmodel.ViewModel
import com.example.veeapi.viewmodel.ViewModelFactory
import com.example.veeapi.adapter.AdapterModel
import com.example.veeapi.responsemodel.DataAdapterList
import com.example.veeapi.responsemodel.Hit

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private lateinit var viewModel: ViewModel

    // adapter and adapterList
    var adapterModel : AdapterModel? = null
    val dataAdapterArrayList : ArrayList<DataAdapterList> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.progressBar.visibility = View.VISIBLE
        /**
         * init recyclerView
         */
        val linearLayoutManager = LinearLayoutManager(this)
        binding!!.recyclerVee.layoutManager = linearLayoutManager


        /**
         * init ViewModel
         */
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ViewModel::class.java]

        val searchString = "dogs"
        displayData(searchString)
    }

    @SuppressLint("SetTextI18n")
    private fun displayData(searchString: String) {
        viewModel.fetchApiData(searchString)
        viewModel.myApiResponse.observe(this) { response ->
            if (response.isSuccessful) {
                binding!!.progressBar.visibility = View.GONE
                /**
                 * Display data to UI
                 * USE adapters and recyclerViews to display data
                 */
                val results = response.body()?.total.toString()
                val webHits = response.body()?.totalHits.toString()
                binding!!.searchTitle.text = "Search Title : $searchString"
                binding!!.totalResults.text = "Search Results : $results"
                binding!!.totalWebHits.text = "Search Results : $webHits"

                val parseDataToAdapter : List<Hit>? = response.body()?.hits?.toMutableList()
                Log.d("res", parseDataToAdapter.toString())
                /**
                 * Iterate through Mutable JSONArray [{},{},{},{}...]
                 */
                for (i in parseDataToAdapter!!.iterator()) {
                    val id = i.id
                    val user = i.user
                    val largeImageURL = i.largeImageURL
                    val views = i.views
                    val tags = i.tags

                    // Feed our adapter Data ArrayList
                    val dataList : DataAdapterList = DataAdapterList()
                    dataList.id = id.toString()
                    dataList.user = user
                    dataList.largeImageURL = largeImageURL
                    dataList.views = views.toString()
                    dataList.tags = tags
                    dataAdapterArrayList.add(dataList)
                }
                adapterModel = AdapterModel(dataAdapterArrayList, this)
                binding!!.recyclerVee.adapter = adapterModel
            } else {
                // API responds with and error or we called the api the wrong way
                binding!!.progressBar.visibility = View.GONE
                Log.d("errorMessage", response.toString())
            }
        }
    }
}