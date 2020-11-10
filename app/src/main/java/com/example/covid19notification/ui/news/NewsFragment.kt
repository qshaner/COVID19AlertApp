package com.example.covid19notification.ui.news
import org.json.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.covid19notification.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_news.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.w3c.dom.Text


class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel



    override fun onCreateView(

            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        newsViewModel =
                ViewModelProviders.of(this).get(NewsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        val button: Button = root.findViewById(R.id.buttonState)
        val editText: EditText = root.findViewById(R.id.editTextTextZip)
        val queue : RequestQueue = Volley.newRequestQueue(context)


        button.setOnClickListener{
            val stateInitials = editText.text.toString()
            Log.i("State Initials Select: ", stateInitials)
            val apiURL = "https://api.covidtracking.com/v1/states/$stateInitials/current.json"
            Log.i("The api URL ", apiURL)
            val deaths: TextView = root.findViewById(R.id.textViewDeaths)
            val deathIncrease: TextView = root.findViewById(R.id.textViewDeathIncrease)
            val currentHospital: TextView = root.findViewById(R.id.textViewHospitalized)
            val positiveIncrease: TextView = root.findViewById(R.id.textViewPositiveIncrease)


            //Now lets create our strings for the text boxs.
            //Going to be using VOLLEY for apy call.
            val json = JsonObjectRequest(Request.Method.GET, apiURL, null,
                Response.Listener { response ->
                    //for the response, log it, then set the text views at the appropriate cases.
                    Log.i("In API call.", response.toString())
                    deaths.setText((response.getInt("death")).toString())
                    Log.i("Death Count", response.getInt("death").toString() + " Deaths. ")
                    deathIncrease.setText(response.getInt("deathIncrease").toString() + " More deaths from Yesterday.")
                    currentHospital.setText(response.getInt("hospitalizedCurrently").toString() + " Are currently hospitlized from Covid 19. ")
                    positiveIncrease.setText(response.getInt("positiveIncrease").toString() + " More cases from Yesterday.")


                },
                Response.ErrorListener {
                    //in case of error, set text views to null and say "state not found. Check your state initials."
                    //I will at one point want to make sure to listen for errors
                    deaths.setText(("Error. Please select a valid state."))
                    deathIncrease.setText((""))
                    currentHospital.setText((""))
                    positiveIncrease.setText((""))

                })
                //this will add the json request to the queue to be requested.
                queue.add(json)



        }

        newsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }


}