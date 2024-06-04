package com.hardi.skynews.data.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "thumbnail", strict = false)
data class Thumbnail   @JvmOverloads constructor(
    @field:Attribute(name = "url")
    var url: String? = null
)