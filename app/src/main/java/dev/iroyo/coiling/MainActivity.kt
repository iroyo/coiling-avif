package dev.iroyo.coiling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.iroyo.coiling.ui.theme.CoilingTheme

internal const val PRODUCT_IMAGE_ASPECT_RATIO = 5f / 7f

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoilingTheme {
                var load by remember {
                    mutableStateOf(false)
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Button(onClick = { load = !load }) {
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
                        }
                    }

                }
            }
        }
    }
}