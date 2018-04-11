package com.futurice.kngu.myscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Handler
import android.os.Message
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class MyJobService : JobService(), AnkoLogger {
    override fun onStopJob(params: JobParameters?): Boolean {
        println("Job stopped")
        mJobHandler.removeMessages(1)
        return false
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        info("Hi World")
        println("Job started")
        mJobHandler.sendMessage(Message.obtain(mJobHandler, 1, params))
        return true
    }

    private val mJobHandler = Handler(Handler.Callback {
        toast("Hello world")
        info("Hello World")
        val httpClient = OkHttpClient()
        val request = Request.Builder().url("http://10.0.2.2:8000").build()
        httpClient.newCall(request).execute()
        jobFinished(it.obj as JobParameters, false)
        true
    })
}