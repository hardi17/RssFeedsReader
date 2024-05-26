package com.hardi.skynews.data.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "enclosure", strict = false)
data class Enclosure   @JvmOverloads constructor(
    @field:Attribute(name = "url")
    var url: String? = null
)