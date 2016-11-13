package com.maiboroda.swagger

/**
 * HTTP media types taken from HTTP mime-types
 * TODO: probably better to rename into MimeType
 */
enum class MediaType(val value:String) {
    /**
     * Public constant media type that includes all media ranges (i.e. "&#42;/&#42;").
     */
    ALL("*/*"),

    /**
     *  Public constant media type for {@code application/atom+xml}.
     */
    APPLICATION_ATOM_XML("application/atom+xml"),

    /**
     * Public constant media type for {@code application/x-www-form-urlencoded}.
     */
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),

    /**
     * Public constant media type for {@code application/json}.
     * @see #APPLICATION_JSON_UTF8
     */
    APPLICATION_JSON("application/json"),

    /**
     * Public constant media type for {@code application/json;charset=UTF-8}.
     */
    APPLICATION_JSON_UTF8("${APPLICATION_JSON.value};charset=UTF-8"),

    /**
     * Public constant media type for {@code application/octet-stream}.
     */
    APPLICATION_OCTET_STREAM("application/octet-stream"),

    /**
     * Public constant media type for {@code application/pdf}.
     * @since 4.3
     */
    APPLICATION_PDF("application/pdf"),

    /**
     * Public constant media type for {@code application/xhtml+xml}.
     */
    APPLICATION_XHTML_XML("application/xhtml+xml"),

    /**
     * Public constant media type for {@code application/xml}.
     */
    APPLICATION_XML("application/xml"),

    /**
     * Public constant media type for {@code image/gif}.
     */
    IMAGE_GIF("image/gif"),

    /**
     * Public constant media type for {@code image/jpeg}.
     */
    IMAGE_JPEG("image/jpeg"),

    /**
     * Public constant media type for {@code image/png}.
     */
    IMAGE_PNG("image/png"),

    /**
     * Public constant media type for {@code multipart/form-data}.
     */
    MULTIPART_FORM_DATA("multipart/form-data"),

    /**
     * Public constant media type for {@code text/event-stream}.
     * @see <a href="https://www.w3.org/TR/eventsource/">Server-Sent Events W3C recommendation</a>
     */
    TEXT_EVENT_STREAM("text/event-stream"),

    /**
     * Public constant media type for {@code text/html}.
     */
    TEXT_HTML("text/html"),

    /**
     * Public constant media type for {@code text/markdown}.
     * @since 4.3
     */
    TEXT_MARKDOWN("text/markdown"),


    /**
     * Public constant media type for {@code text/plain}.
     */
    TEXT_PLAIN("text/plain"),

    /**
     * Public constant media type for {@code text/xml}.
     */
    TEXT_XML("text/xml")
}