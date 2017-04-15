package com.interviewcracker.support

import org.robolectric.RuntimeEnvironment


fun getString(resourceID: Int):String {
    return RuntimeEnvironment.application.getString(resourceID)
}