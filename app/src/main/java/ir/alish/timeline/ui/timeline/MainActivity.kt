package ir.alish.timeline.ui.timeline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.alish.timeline.R
import ir.alish.timeline.data.Post

class MainActivity : AppCompatActivity(), PostFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: Post?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
