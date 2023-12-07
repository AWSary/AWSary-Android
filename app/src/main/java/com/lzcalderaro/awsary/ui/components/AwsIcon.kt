package com.lzcalderaro.awsary.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lzcalderaro.awsary.webservice.dto.AwsItem


@Composable
fun IconList(item: AwsItem) {

    Column(
    ){
        IconImage(item.imageURL)
        IconTitle(item.name)
    }
}

@Composable
fun IconHeader(item: AwsItem) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.height(70.dp).fillMaxWidth()
    ) {
        IconImage(url = item.imageURL)
        Column {
            IconTitleHeader(text = item.name)
        }
    }
}

@Composable
fun IconTitleHeader(text: String) {
    Surface(
        modifier = Modifier.padding(3.dp)
    )
    {
        Text(
            text = text,
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )

    }
}

@Composable
fun IconTitle(text: String) {
    Surface(
        modifier = Modifier
            .padding(PaddingValues(top = 3.dp))
            .fillMaxWidth()
    )
    {
        Text(
            text = text,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
        )

    }
}

@Composable
fun IconImage(url: String) {
    Surface(
        modifier = Modifier
            .padding(10.dp)
            .aspectRatio(1f),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
    )
    {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            contentDescription = "AWS Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxSize()
        )
    }
}
