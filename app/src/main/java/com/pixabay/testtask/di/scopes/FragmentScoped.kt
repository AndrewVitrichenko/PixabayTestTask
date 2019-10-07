package com.pixabay.testtask.di.scopes

import javax.inject.Scope

import kotlin.annotation.Retention
import kotlin.annotation.AnnotationRetention

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScoped