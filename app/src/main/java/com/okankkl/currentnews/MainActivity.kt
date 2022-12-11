package com.okankkl.currentnews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.okankkl.currentnews.Model.News
import com.okankkl.currentnews.ViewModel.MainActivityViewModel
import com.okankkl.currentnews.ui.theme.CurrentNewsTheme
import com.okankkl.currentnews.ui.theme.Typography
import com.okankkl.currentnews.ui.theme.White_100
import com.okankkl.currentnews.ui.theme.myFontFamily

//private lateinit var viewModel : MainActivityViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = MainActivityViewModel()

        setContent {
            CurrentNewsTheme {
                // A surface container using the 'background' color from the theme
                CurrentNews(viewModel)
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun CurrentNews(viewModel: MainActivityViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        NewsList(viewModel = viewModel)
    }
}

@Composable
fun NewsList(viewModel: MainActivityViewModel) {

    var newsList = viewModel.news_list
    
    LazyColumn(modifier = Modifier){
        items(items = newsList,itemContent = {Item(news = it)})
    }

}

@Composable
fun Item(news : News){

    var mContext = LocalContext.current

    Row(modifier = Modifier
        .padding(10.dp)
        .clickable {
            openDetailActivity(mContext, news)
        },
        verticalAlignment = Alignment.CenterVertically)
    {
        AsyncImage(model = news.urlToImage, contentDescription = "asd",
            modifier = Modifier
                .height(100.dp)
                .weight(5f)
            )

        Spacer(modifier = Modifier.width(15.dp))

        Text(text = news.title,modifier = Modifier
            .fillMaxHeight()
            .weight(5f),
            textAlign = TextAlign.Start,
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold


        )
    }

    Divider(modifier = Modifier.height(2.dp).padding(start = 10.dp,end=10.dp), color = White_100)


}

fun openDetailActivity(mContext: Context,news : News){

    val intent = Intent(mContext,DetailActivity::class.java)
    intent.putExtra("title",news.title)
    intent.putExtra("url",news.url)
    intent.putExtra("url_to_image",news.urlToImage)
    intent.putExtra("description",news.description)
    intent.putExtra("author",news.author)
    intent.putExtra("publishedAt",news.publishedAt)


    mContext.startActivity(intent)

}


