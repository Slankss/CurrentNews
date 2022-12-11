package com.okankkl.currentnews

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.okankkl.currentnews.ui.theme.CurrentNewsTheme
import com.okankkl.currentnews.ui.theme.TopBarColor
import java.lang.System.load

class WebsiteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var url = remember { mutableStateOf("") }
            intent?.let {
                var value = it.getStringExtra("url")
                if(value != null){
                    url.value = value
                }

            }

            CurrentNewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    OpenUrl(url = url)
                }
            }
        }
    }
}


@Composable
fun OpenUrl(url : MutableState<String>) {

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {

        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
                webViewClient = WebViewClient()
                loadUrl(url.value)
            }
        }, update = {
            it.loadUrl(url.value)
        })
    }





}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CurrentNewsTheme {

    }
}