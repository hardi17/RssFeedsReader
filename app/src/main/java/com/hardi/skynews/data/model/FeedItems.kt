package com.hardi.skynews.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class FeedItems @JvmOverloads constructor(
    @field:Element(name = "title")
    @param:Element(name = "title")
    var title: String? = null,

    @field:Element(name = "link")
    @param:Element(name = "link")
    var link: String? = null,

    @field:Element(name = "description")
    @param:Element(name = "description")
    var description: String? = null,

    @field:Element(name = "thumbnail", required = false)
    @param:Element(name = "thumbnail", required = false)
    @Namespace(prefix = "media")
    var thumbnail: Thumbnail? = null
)
