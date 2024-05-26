package com.hardi.skynews.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementArray
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class FeedItems @JvmOverloads constructor(
    @field:Element(name = "title")
    @param:Element(name = "title")
    var title: String? = null,

    @field:Element(name = "link")
    @param:Element(name = "link")
    var link: String? = null,

    @field:ElementArray(name = "description", required = false)
    @param:ElementArray(name = "description", required = false)
    @field:Path("item/description")
    @param:Path("item/description")
    var description: String? = null,

    @field:Element(name = "enclosure", required = false)
    @param:Element(name = "enclosure", required = false)
    var enclosure: Enclosure? = null
)
