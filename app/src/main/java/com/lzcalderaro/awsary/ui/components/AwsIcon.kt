package com.lzcalderaro.awsary.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lzcalderaro.awsary.webservice.dto.AwsItem

class AwsIcon(private val item: AwsItem) {

    @Composable
    fun IconList() {

        Column(modifier = Modifier
            .padding(5.dp)
        ){
            IconImage(item.imageURL)
            IconTitle(item.name)
        }
    }

    @Composable
    fun IconHeader() {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.height(70.dp)
        ) {
            IconImage(url = item.imageURL)
            Column {
                IconTitle(text = item.name, align = TextAlign.Start)
            }
        }
    }

    @Composable
    fun IconTitle(text: String, align: TextAlign = TextAlign.Center) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth(),
        )
        {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                textAlign = align
            )

        }
    }

    @Composable
    fun IconImage(url: String) {
        Surface(
            modifier = Modifier
                .padding(5.dp)
                .aspectRatio(1f),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
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
                    .fillMaxSize()
            )
        }
    }

}
