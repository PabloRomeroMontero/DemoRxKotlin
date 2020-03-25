package pablo.romero.demorxkotlin.ui

import People
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import pablo.romero.demorxkotlin.R
import pablo.romero.demorxkotlin.data.ResultPeople
import pablo.romero.demorxkotlin.data.remote.PeopleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    val BASEURL: String = "https://swapi.co/api/"
    private val listAdapter: MainActivityAdapter =
        MainActivityAdapter().apply {

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        setupRecyclerView()
        listener()
    }

    private fun listener() {
        buttonSearch.setOnClickListener { getCurrentPeople()}
    }


    fun getCurrentPeople(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val service = retrofit.create(PeopleService::class.java)
        val call = service.getCurrentPeopleData(characterNameEditText.text.toString())
        call.enqueue(object : Callback<ResultPeople> {
            override fun onResponse(call: Call<ResultPeople>, response: Response<ResultPeople>) {
                // ESTO DA PUTO 404
                isEmpty.text = response.code().toString()
                if (response.code() == 200) {
                    listAdapter.submitList(response.body().results)
                    isEmpty.text = response.body().results.size.toString()
                }
            }
            override fun onFailure(call: Call<ResultPeople>?, t: Throwable?) {
                if (t != null) {
                    characterNameEditText.setText(t.message.toString())
                }
//                isEmpty.visibility = if (listAdapter.itemCount == 0)  View.INVISIBLE else View.INVISIBLE

            }
        })
    }






    private fun setupRecyclerView() {
        recyclerViewCharacter.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }
}
