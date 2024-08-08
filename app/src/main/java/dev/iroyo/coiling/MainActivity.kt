package dev.iroyo.coiling

import android.Manifest.permission.READ_MEDIA_IMAGES
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.os.Environment.getExternalStoragePublicDirectory
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import dev.iroyo.coiling.ui.theme.CoilingTheme
import java.io.File


internal const val PRODUCT_IMAGE_ASPECT_RATIO = 5f / 7f

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val launcher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission Accepted: Do something
                    Log.d("ExampleScreen", "PERMISSION GRANTED")

                } else {
                    // Permission Denied: Do something
                    Log.d("ExampleScreen", "PERMISSION DENIED")
                }
            }
            val context = LocalContext.current

            CoilingTheme {
                var load by remember {
                    mutableStateOf(false)
                }
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.White) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Button(onClick = {
                            when (PackageManager.PERMISSION_GRANTED) {
                                ContextCompat.checkSelfPermission(context, READ_MEDIA_IMAGES) -> {
                                    // Some works that require permission
                                    load = !load
                                }

                                else -> {
                                    // Asking for permission
                                    launcher.launch(READ_MEDIA_IMAGES)
                                }
                            }


                        }) {
                            Text(text = "LoadImages")
                        }
                        if (load) {
                            Row(

                                horizontalArrangement = Arrangement.spacedBy(1.dp)
                            ) {
                                NewImage(
                                    url = "https://shop.mango.com/assets/rcs/pics/static/T7/fotos/S/77004767_40_D7.jpg?ts=1722591772050&imdensity=1&imwidth=538",
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(PRODUCT_IMAGE_ASPECT_RATIO)
                                )
                                NewImage(
                                    url = "https://shop.mango.com/assets/rcs/pics/static/T7/fotos/S/77004767_40_D7.jpg?ts=1722591772050&imdensity=1&imwidth=538",
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(PRODUCT_IMAGE_ASPECT_RATIO)
                                )
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                                val path = getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).path + File.separator + "test.avif"
                                loadImageUsingImageDecoder(File(path))?.let {
                                    Image(
                                        painter = BitmapPainter(it.asImageBitmap()),
                                        contentDescription = ""
                                    )
                                }

                            }
                        }
                    }

                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private fun loadImageUsingImageDecoder(imageFile: File) = runCatching {
        val source = ImageDecoder.createSource(imageFile)
        ImageDecoder.decodeBitmap(source)
    }.onFailure {
        Log.e("TEST", "fail", it)
    }.getOrNull()


}