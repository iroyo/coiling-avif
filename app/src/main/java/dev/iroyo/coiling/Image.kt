package dev.iroyo.coiling

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun NewImage(
    url: String?,
    modifier: Modifier = Modifier,
) {
    val headers = NetworkHeaders.Builder()
        .set("Accept", "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*")
        .build()

    val request = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .httpHeaders(headers)
        .crossfade(250)
        .build()

    AsyncImage(
        model = request,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )


}
