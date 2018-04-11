package com.futurice.kngu.myscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.design.snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mJobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        mComponentName = ComponentName(this, MyJobService::class.java)

        scheduleBtn.setOnClickListener {
            snackbar(mainActivity, "Schedule")

            val builder = JobInfo.Builder(1, mComponentName)
            builder.setPeriodic(5000)

            mJobScheduler.schedule(builder.build())
        }

        unscheduleBtn.setOnClickListener {
            snackbar(mainActivity, "Unschedule")

            mJobScheduler.cancelAll()
        }
    }

    private lateinit var mJobScheduler: JobScheduler
    private lateinit var mComponentName: ComponentName
}
