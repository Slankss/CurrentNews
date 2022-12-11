package com.okankkl.currentnews

import android.content.Intent
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.okankkl.currentnews.Model.News
import com.okankkl.currentnews.ui.theme.CurrentNewsTheme
import com.okankkl.currentnews.ui.theme.Link_Color
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            val emptyNews = News(null,"","","","","","","")
            val mIntent = intent
            val news = remember { mutableStateOf(emptyNews) }

            intent?.let {
                var url_to_image = it.getStringExtra("url_to_image")
                var url = it.getStringExtra("url")
                var title = it.getStringExtra("title")
                var description = it.getStringExtra("description")
                var author = it.getStringExtra("author")
                if(author == null){
                    author = ""
                }
                var publishedAt = it.getStringExtra("publishedAt")
                var created_news = News(null,author,title!!,description!!,url!!,url_to_image!!,publishedAt!!,"")
                news.value = created_news
            }

            CurrentNewsTheme {
                // A surface container using the 'background' color from the theme
                DetailPage(news)
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun DetailPage(news: MutableState<News>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()


        ) {

            AsyncImage(model = news.value.urlToImage, contentDescription = "",
                modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(10.dp))

            Column(modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
            ) {
                Text(text = news.value.title, fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp))

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = news.value.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Justify,
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(20.dp))

                var author = "Yazar : ${news.value.author}"
                Text(text = author,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomText(url = news.value.url)

                Spacer(modifier = Modifier.height(20.dp))

                var date = news.value.publishedAt.replace("T"," ").replace("Z","")

                Text(text = date,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.End,
                    fontSize = 16.sp,
                )

            }


        }

    }
}

@Composable
fun CustomText(url: String) {

    var mContext = LocalContext.current
    Text(
        buildAnnotatedString {

            withStyle(style = SpanStyle(
                fontWeight = FontWeight.Normal
            )
            ) {
                append("Haberin Kaynağı : ")
            }
            withStyle(style = SpanStyle(
                color = Link_Color,
                fontWeight = FontWeight.SemiBold,
                )){
                append(url)
            }
        },modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .clickable {
                 var intent = Intent(mContext,WebsiteActivity::class.java)
                 intent.putExtra("url",url)
                 mContext.startActivity(intent)
            }
        ,
        textAlign = TextAlign.Start,
        fontSize = 14.sp,

    )
}



