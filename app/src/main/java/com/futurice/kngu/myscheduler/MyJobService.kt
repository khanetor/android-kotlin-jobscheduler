package com.futurice.kngu.myscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Handler
import android.os.Message
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class MyJobService : JobService(), AnkoLogger {
    override fun onStopJob(params: JobParameters?): Boolean {
        mJobHandler.removeMessages(1)
        return false
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        info("Hi World")
        mJobHandler.sendMessage(Message.obtain(mJobHandler, 1, params))
        return false
    }

    private val mJobHandler = Handler(Handler.Callback {
        toast("Hello world")
        info("Hello World")
        jobFinished(it.obj as JobParameters, false)
        true
    })
}