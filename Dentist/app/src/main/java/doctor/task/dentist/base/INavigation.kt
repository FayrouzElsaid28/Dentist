package doctor.task.dentist.base

interface INavigation {
    fun handleNavigation() {}
    fun handleViewPager() {}
    fun handleNavView(id: Int) {}
    fun fragmentSwitch(id: Int){}
}