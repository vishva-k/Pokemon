//package com.driuft.random_pets_starter
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.codepath.asynchttpclient.AsyncHttpClient
//import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
//import okhttp3.Headers
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        getDogImageURL()
//    }
//
//    private fun getDogImageURL() {
//        val client = AsyncHttpClient()
//
//        client["https://dog.ceo/api/breeds/image/random", object : JsonHttpResponseHandler() {
//            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
//                Log.d("Dog Success", "$json")
//            }
//
//            override fun onFailure(
//                statusCode: Int,
//                headers: Headers?,
//                errorResponse: String,
//                throwable: Throwable?
//            ) {
//                Log.d("Dog Error", errorResponse)
//            }
//        }]
//    }
//}

package com.driuft.random_pets_starter

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.driuft.random_pets_starter.R
import okhttp3.Headers
//import com.loopj.android.http.AsyncHttpClient
//import com.loopj.android.http.JsonHttpResponseHandler
//import cz.msebera.android.httpclient.Header
import okhttp3.internal.http2.Header
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonList: MutableList<Pokemon>
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Adding item dividers
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        pokemonList = mutableListOf()
        loadPokemonList()

        pokemonAdapter = PokemonAdapter(pokemonList)
//        { pokemon ->
//            Toast.makeText(this@MainActivity, "Clicked on ${pokemon.name}", Toast.LENGTH_SHORT).show()
//        }
        recyclerView.adapter = pokemonAdapter
    }

    private fun loadPokemonList() {
        val asyncHttpClient = AsyncHttpClient()
        val url = "https://pokeapi.co/api/v2/pokemon?limit=20"

        asyncHttpClient[url, object : JsonHttpResponseHandler() {

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                Log.d("mainActivity",response)
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                val resultsArray: JSONArray = json!!.jsonObject.getJSONArray("results")
                for (i in 0 until resultsArray.length()) {
                    val pokemonObject = resultsArray.getJSONObject(i)
                    val name = pokemonObject.getString("name")
                    val pokemonUrl = pokemonObject.getString("url")
                    val pokemonId = i+1
                    val pokemon = Pokemon(name.replaceFirstChar { it.uppercase() }, pokemonUrl, pokemonId)
                    pokemonList.add(pokemon)
                }
                pokemonAdapter.notifyDataSetChanged()
            }
        }]
    }

//    private fun getIdFromUrl(url: String): Int {
//        val idString = url.split("/").toTypedArray().last()
//        return idString.toInt()
//    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        val searchItem = menu?.findItem(R.id.action_search)
//        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                pokemonAdapter.filter.filter(newText)
//                return false
//            }
//        })
//        return true
//    }
}
