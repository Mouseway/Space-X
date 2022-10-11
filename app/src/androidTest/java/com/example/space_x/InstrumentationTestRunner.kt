package com.example.space_x

import android.app.Application
import android.app.Instrumentation
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

internal class InstrumentationTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?, className: String?, context: Context?
    ): Application {
        return Instrumentation.newApplication(TestApplication::class.java, context)
    }
}