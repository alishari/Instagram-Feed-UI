package ir.setak.timeline.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.timeline.R
import ir.setak.timeline.model.PostV2
import ir.setak.timeline.ui.fragment.PostFragment

class MainActivity : AppCompatActivity(), PostFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: PostV2?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
