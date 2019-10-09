package com.pixabay.testtask.data.datasource.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun io(): Scheduler

    fun ui(): Scheduler

    fun computation(): Scheduler

    fun trampoline() : Scheduler
}