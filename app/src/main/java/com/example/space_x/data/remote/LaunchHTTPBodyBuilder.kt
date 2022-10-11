package com.example.space_x.data.remote

class LaunchHTTPBodyBuilder {

    private val query: MutableMap<String, Any> = mutableMapOf()
    private val options: MutableMap<String, Any> = mutableMapOf()

    fun setPage(page: Int): LaunchHTTPBodyBuilder {
        options[PAGE_KEY] = page
        return this
    }

    fun build(): Map<String, Any> {
        return mapOf(
            QUERY_KEY to query,
            OPTIONS_KEY to options
        )
    }

    fun setUpcoming(bool: Boolean): LaunchHTTPBodyBuilder = setKey(UPCOMING_KEY, bool)
    fun setPast(bool: Boolean): LaunchHTTPBodyBuilder = setKey(PAST_KEY, bool)

    fun removeUpcoming(): LaunchHTTPBodyBuilder = removeKey(UPCOMING_KEY)
    fun removePast(): LaunchHTTPBodyBuilder = removeKey(PAST_KEY)

    fun setDatesInterval(from: Long, to: Long): LaunchHTTPBodyBuilder {
        return setKey(DATE_UNIX,
            mapOf(
                GREATER to from,
                LOWER to to
            )
        )
    }

    private fun removeKey(key: String): LaunchHTTPBodyBuilder {
        query.remove(key)
        return this
    }

    private fun setKey(key:String, value: Any): LaunchHTTPBodyBuilder{
        query[key] = value
        return this
    }

    companion object {
        private const val QUERY_KEY = "query"
        private const val OPTIONS_KEY = "options"
        private const val UPCOMING_KEY = "upcoming"
        private const val PAST_KEY = "past"
        private const val PAGE_KEY = "page"
        private const val DATE_UNIX = "date_unix"
        private const val GREATER = "\$gte"
        private const val LOWER = "\$lte"
    }
}
