package com.electionapp.android.ui.main.fragments.pvcstats

import com.electionapp.android.model.admin.StatGroup
import com.electionapp.android.model.admin.StatItem
import com.electionapp.android.ui.base.BaseViewModel
import com.electionapp.android.utils.extensions.mutableLiveDataOf


/**
 * Created by aliumujib on 08/06/2018.
 */

class PVCAdminStatsViewModel() : BaseViewModel() {

    val totalStat = mutableLiveDataOf<StatItem>()
    val lgaStatGroup = mutableLiveDataOf<StatGroup>()
    val wardStatGroup = mutableLiveDataOf<StatGroup>()
    val genderStatGroup = mutableLiveDataOf<StatGroup>()
    val professionStatGroup = mutableLiveDataOf<StatGroup>()


    override fun setUp() {
        super.setUp()

        val totalStat = StatItem(2000000, "Total Votes")
        this.totalStat.value = totalStat

        val lgaStat1 = StatItem(124505, "Ikorodu")
        val lgaStat2 = StatItem(345505, "Eti Osa")
        val lgaStat3 = StatItem(34456, "Oskiu")
        val lgaStat4 = StatItem(67777, "Agege")

        val statGroup = StatGroup(mutableListOf(lgaStat1, lgaStat2, lgaStat3, lgaStat4), "Top LGAs")
        this.lgaStatGroup.value = statGroup

        val wardsStat1 = StatItem(124505, "Ward 1")
        val wardsStat2 = StatItem(345505, "Ward 2")
        val wardsStat3 = StatItem(34456, "Ward 3")
        val wardsStat4 = StatItem(67777, "Ward 4")

        val wardGroup = StatGroup(mutableListOf(wardsStat1, wardsStat2, wardsStat3, wardsStat4), "Top Wards")
        this.wardStatGroup.value = wardGroup


        val genderStat1 = StatItem(23425, "Gender 1")
        val genderStat2 = StatItem(49590, "Gender 2")
        val genderGroup = StatGroup(mutableListOf(genderStat1, genderStat2), "Top Genders")
        this.genderStatGroup.value = genderGroup


        val professionStat1 = StatItem(3234534, "Lawyers")
        val professionStat2 = StatItem(34523, "Doctors")
        val professionStat3 = StatItem(456236, "Sandoctors")
        val professionStat4 = StatItem(566773, "Mechanics")
        val professionStat5 = StatItem(245234, "Super men")
        val professionStat6 = StatItem(234324, "Professors")
        val professionStat7 = StatItem(545678, "Money Bags")
        val professionStatGroup = StatGroup(mutableListOf(professionStat1, professionStat2, professionStat3, professionStat4, professionStat5,
                professionStat6, professionStat7), "Top Professions")
        this.professionStatGroup.value = professionStatGroup

    }
}