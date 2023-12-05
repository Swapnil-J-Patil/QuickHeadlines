package com.example.newsapplication.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.Model.Article
import com.example.newsapplication.Model.Data
import com.example.newsapplication.Adapter.NewsAdapter
import com.example.newsapplication.Controller.NewsService
import com.example.newsapplication.R
import com.example.newsapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAdapter: NewsAdapter
    private val articles: MutableList<Article> = mutableListOf() // Initialize as an empty list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView and its adapter
        newsAdapter = NewsAdapter(applicationContext,articles)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = newsAdapter

        // Make a network call to fetch news articles
        var general=findViewById<TextView>(R.id.General)
        var sports=findViewById<TextView>(R.id.Sports)
        var entertainment=findViewById<TextView>(R.id.Entertainment)

        fetchData("general") //Default value
        general.setOnClickListener {
            fetchData("general")
        }
        sports.setOnClickListener {
            fetchData("sports")
        }

        entertainment.setOnClickListener {
            fetchData("entertainment")
        }


    }

    private fun fetchData(key: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(NewsService::class.java)
        val call = service.getNews("in",key,"4f1e10925ea14c399766b1e20924435b")

        call.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        articles.clear()
                        articles.addAll(it.articles)
                        newsAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()
            }
        })
    }
}