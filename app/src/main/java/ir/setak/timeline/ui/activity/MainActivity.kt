package ir.setak.timeline.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.setak.timeline.ui.fragment.PostFragment
import com.example.timeline.R
import ir.setak.timeline.model.DummyContent
import ir.setak.timeline.model.Item

class MainActivity : AppCompatActivity(), PostFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: Item?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
