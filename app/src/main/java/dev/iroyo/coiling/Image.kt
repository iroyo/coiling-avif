package dev.iroyo.coiling

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.Parameters

@Composable
fun NewImage(
    url: String?,
    modifier: Modifier = Modifier,
    parameters: Parameters = Parameters()
) {
    val request = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .setHeader("Accept", "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*")
        .crossfade(250)
        .parameters(parameters)
        .build()

    AsyncImage(
        model = request,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )


}
