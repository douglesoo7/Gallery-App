package dougles.project.galleryapp.activitiesandFragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dougles.project.galleryapp.R
import dougles.project.galleryapp.adapter.FragmentAdapter

class MainActivity : AppCompatActivity() {


    private lateinit var adapter: FragmentAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter
        TabLayoutMediator(
            tabLayout,
            viewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Images"
                }
                1 -> {
                    tab.text = "Videos"
                }
            }
        }.attach()
    }
}
