package doctor.task.dentist.view.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import doctor.task.dentist.R
import doctor.task.dentist.base.INavigation
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity(), INavigation {

    var currentFragment = 0
    var selectedColor = 0
    var unselectedColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        //Initialize colors
        selectedColor = ContextCompat.getColor(applicationContext,R.color.white)
        unselectedColor = ContextCompat.getColor(applicationContext,R.color.colorPrimary)

        handleViewPager()
        handleNavigation()
    }

    override fun handleNavigation() {
        doctor_btn.setOnClickListener {
            currentFragment = 0
            registeration_viewpager.setCurrentItem(currentFragment)
        }
        patient_btn.setOnClickListener {
            currentFragment = 1
            registeration_viewpager.setCurrentItem(currentFragment)
        }
    }

    override fun handleViewPager() {
        registeration_viewpager.adapter = RegistrationPagerAdapter(supportFragmentManager)
        registeration_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                currentFragment = position
                handleNavView(currentFragment)
            }

            override fun onPageSelected(position: Int) {

            }
        })
    }

    override fun handleNavView(id: Int) {
        when(id){
            0 -> {
                doctor_btn.setBackgroundResource(R.drawable.doctor_selected)
                doctor_btn.setTextColor(selectedColor)

                patient_btn.setBackgroundResource(R.drawable.patient_unselected)
                patient_btn.setTextColor(unselectedColor)
            }
            1 -> {
                patient_btn.setBackgroundResource(R.drawable.patient_selected)
                patient_btn.setTextColor(selectedColor)

                doctor_btn.setBackgroundResource(R.drawable.doctor_unselected)
                doctor_btn.setTextColor(unselectedColor)
            }
        }
    }
}
