package kr.ac.kumoh.prof.w1201prerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kumoh.prof.w1201prerecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // build.gradle에 추가
    // implementation 'androidx.activity:activity-ktx:1.6.1'
    private val model: ListViewModel by viewModels()

    // build.gradle에 추가하지 않고 이렇게 AndroidViewModel과 똑같이 사용해도됨
    //private lateinit var model: ListViewModel
    private val songAdapter = SongAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // build.gradle에 추가하지 않고 이렇게 AndroidViewModel과 똑같이 사용해도됨
        //model = ViewModelProvider(this)[ListViewModel::class.java]


        model.getList().observe(this, Observer<ArrayList<String>> {
            songAdapter.notifyDataSetChanged()
        })

        for (i in 1..3) {
            model.add("사랑에 연습이 있었다면")
        }

        binding.list.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = songAdapter
        }
    }

    inner class SongAdapter: RecyclerView.Adapter<SongAdapter.ViewHolder>() {
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val txSong: TextView = itemView.findViewById(android.R.id.text1)
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.txSong.text = model.getSong(position)
        }

        override fun getItemCount() = model.getSize()
    }
}