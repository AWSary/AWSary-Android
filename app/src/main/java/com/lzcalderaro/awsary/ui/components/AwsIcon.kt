package com.lzcalderaro.awsary.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.lzcalderaro.awsary.webservice.dto.AwsItem


@Composable
fun AwsIcon(item: AwsItem, callback: (AwsItem) -> Unit) {

    Column(modifier = Modifier.padding(5.dp).clickable {
       callback(item)
    }) {
        image(item.imageURL)
        title(item.name)
    }
}

@Composable
fun title(text: String) {
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
    )
    {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun image(url: String) {
    Surface(
        modifier = Modifier
            .padding(5.dp)
            .aspectRatio(1f),
        border = BorderStroke(0.5.dp, Color.LightGray),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
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
                .size(100.dp),
        )
    }
}
