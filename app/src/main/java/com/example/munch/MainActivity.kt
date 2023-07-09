package com.example.munch

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.munch.ui.theme.MunchTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MunchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Search()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search() {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val items = remember  {
        mutableStateListOf("Burger", "Burrito" )
    }

        SearchBar(
            query = text ,
            onQueryChange = {
                text = it
            } ,
            onSearch = {
                active = false
                items.add(text)
                text = ""
            },
            active = active ,
            onActiveChange = {
                active = it
            },
            placeholder = {
                Text(text = "Search")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable(){
                        if(text.isNotEmpty()){
                            text = ""
                        }else{
                            active = false
                        }
                    },
                    imageVector = Icons.Default.Close, contentDescription = "Close Icon")
            },

        ) {
            items.forEach{
                Row(modifier = Modifier.padding(all = 14.dp)){
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        imageVector = Icons.Default.History, contentDescription = "History Icon"
                    )
                    Text(text = it)

                }
            }
        }
    }

@Composable
fun ChipSort(modifier: Modifier = Modifier,
             @StringRes text: Int,
             @StringRes chiptext2: Int,
             @StringRes chiptext3: Int,
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        AssistChip(
            onClick = { /*TODO*/ },
            label = {
                Text(
                    text = stringResource(text),
                    style = MaterialTheme.typography.labelLarge
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Sort,
                    contentDescription = "Sort Icon",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            },
            modifier = Modifier
                .widthIn(min = 97.dp)
                .height(32.dp)
                .align(Alignment.CenterVertically),



            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Sort Icon",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )

        AssistChip(
            onClick = { /*TODO*/ },
            label = {
                Text(
                    text = stringResource(chiptext2),
                    style = MaterialTheme.typography.labelLarge
                )
            },

            modifier = Modifier
                .widthIn(min = 97.dp)
                .height(32.dp),
        )

        AssistChip(
            onClick = { /*TODO*/ },
            label = {
                Text(
                    text = stringResource(chiptext3),
                    style = MaterialTheme.typography.labelLarge
                )
            },

            modifier = Modifier
                .widthIn(min = 97.dp)
                .height(32.dp),
            )
    }
}

@Composable
fun ComponentPair(
    @StringRes text: Int,
    @DrawableRes drawable: Int,
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .height(120.dp)
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(3.dp)),

            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Composable
fun ComponentPairRow(){

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier.padding(vertical = 8.dp),
    ){
        items(componentsRowElements){item ->
            ComponentPair(text = item.text,drawable = item.drawable)
        }
    }
}

@Composable
fun OrderNowCard(
    @DrawableRes drawable: Int,
) {
        // Material Components like Button, Card, Switch, etc
        Card(
            modifier = Modifier
                .height(136.dp)
                .width(328.dp),

        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
            ) {

                Card(
                    modifier = Modifier
                        .width(192.dp)
                        .padding(all = 20.dp)
                ) {
                    Column{
                        Text(text = "PlaceHolder Text here")

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = { /*TODO*/ },

                            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = "Order Now",
                            )
                        }
                    }
                }


                Image(
                    painter = painterResource(drawable),
                    contentDescription = "food image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.width(136.dp),

                )
            }

        }

}


private val componentsRowElements = listOf(
    R.drawable.img1_food to R.string.FoodItem1,

    R.drawable.img2_food to R.string.FoodItem2,

    R.drawable.img3_food to R.string.FoodItem3,

    R.drawable.img4_food to R.string.FoodItem4,

    R.drawable.img5_food to R.string.FoodItem5,

    R.drawable.img6_food to R.string.FoodItem6,

    R.drawable.img7_food to R.string.FoodItem7,

).map {DrawableStringPair(it.first, it.second)}

data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
)

@Preview
@Composable
fun OrderNowCardPreview(){
    MunchTheme{
        OrderNowCard(
            R.drawable.img8_food
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun ComponentPairRowPreview(){
    MunchTheme{
        ComponentPairRow()
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun ComponentPairPreview(){
    MunchTheme{
        ComponentPair(R.string.FoodItem1, R.drawable.img1_food,)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun ChipSortPreview(){
    MunchTheme{
        ChipSort(
            modifier = Modifier.padding(10.dp),
            R.string.homeChip1,
            R.string.homeChip2,
            R.string.homeChip3
        )
    }
}


@Preview
@Composable
fun SearchBarPreview(){
    MunchTheme{
        Search()
    }
}

