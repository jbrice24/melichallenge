package com.jonathanbriceno.melichallenge.ui.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jonathanbriceno.melichallenge.R
import com.jonathanbriceno.melichallenge.domain.model.ProductDomain

@Composable
fun Pod(product: ProductDomain) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier.size(128.dp),
                model = product.image.orEmpty(),
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = "",
                error = {
                    Column(verticalArrangement = Arrangement.Center) {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = stringResource(R.string.text_image_not_available),
                            style = MaterialTheme.typography.labelMedium,
                            textAlign = TextAlign.Center
                        )
                    }

                },
                onError = {
                    print("Error loading image: ${it.result}")
                }
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = product.brand.orEmpty(),
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = product.name.orEmpty(),
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = product.line.orEmpty(),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = product.color.orEmpty(),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }

        Divider(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(1.dp)
                .background(Color.LightGray)
        )
    }
}