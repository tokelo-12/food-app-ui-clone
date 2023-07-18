package com.example.munch

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Motorcycle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material.icons.outlined.Star
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    RestaurantCardColumn()
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
                    style = TextStyle(
                        fontSize = 14.sp
                    )
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
                    style = TextStyle(
                        fontSize = 14.sp
                    )
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
                    style = TextStyle(
                        fontSize = 14.sp
                    )
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
    modifier: Modifier,
    @DrawableRes drawable: Int,

) {
        // Material Components like Button, Card, Switch, etc
        Card(
            modifier = Modifier
                .height(136.dp)
                .fillMaxWidth(),

        ) {
            Row{

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
                                style = TextStyle(
                                    fontSize = 12.sp
                                )
                            )
                        }
                    }
                }


                Image(
                    painter = painterResource(drawable),
                    contentDescription = "NULL",
                    contentScale = ContentScale.Crop,
                    modifier = modifier,

                )
            }

        }

}

@Composable
fun RestaurantCard(
    @StringRes text: Int,
    @DrawableRes drawable: Int
){
    Column(
        modifier = Modifier
            .height(256.dp)
            .fillMaxWidth()
//            .width(328.dp)
            .padding(bottom = 22.dp)
    ) {
        Card(
            modifier = Modifier
                .height(168.dp)
                .fillMaxWidth() //inherits width from parent Column i.e 328.dp
        ){
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()

            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(text),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Card(
                modifier = Modifier
                    .width(44.dp)
                    .height(20.dp),

            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = 6.dp),


                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        modifier = Modifier
                            .size(10.dp)
                    )

                    Text(
                        text = "4.7",
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }


        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(20.dp)
        ){
            Icon(
                imageVector = Icons.Filled.Timelapse,
                contentDescription = "time icon",
                modifier = Modifier.size(12.dp)
            )


            Text(text = "35 min",
                style = TextStyle(
                    fontSize = 12.sp
                )
            )

            Icon(
                imageVector = Icons.Filled.FiberManualRecord,
                contentDescription = null,
                modifier = Modifier
                    .size(2.dp)
            )

            Icon(
                imageVector = Icons.Default.Motorcycle,
                contentDescription = null,
                modifier = Modifier.size(14.dp)
            )

            Text(text = "M12 Delivery Fee",
                style = TextStyle(
                    fontSize = 12.sp
                )
            )

            Icon(
                imageVector = Icons.Filled.FiberManualRecord,
                contentDescription = null,
                modifier = Modifier
                    .size(2.dp)
            )

            Text(text = stringResource(R.string.PlaceHolderDeliveryFee),
                style = TextStyle(
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Composable
fun RestaurantCardColumn(){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 10.dp),
    ){
        items(restaurantColumnElements){item ->
            RestaurantCard(text = item.text,drawable = item.drawable)
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Column(modifier){
        Text(
            text = stringResource(title),
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight(500)
            )
        )
        content()
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

private val restaurantColumnElements = listOf(
    R.drawable.img11_food to R.string.Restaurant3,
    R.drawable.img13_food to R.string.Restaurant2,
    R.drawable.img14_food to R.string.Restaurant1,
    R.drawable.img9_food to R.string.Restaurant4,
).map{DrawableStringPair(it.first, it.second)}

data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
)

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeSectionPreview(){
    MunchTheme{
        HomeSection(title = R.string.SlotTitle) {
            RestaurantCardColumn()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun RestaurantCardPreview(){
    MunchTheme{
        RestaurantCard(R.string.Restaurant4,R.drawable.img9_food)
    }
}
//
//@Preview
//@Composable
//fun OrderNowCardPreview(){
//    MunchTheme{
//        OrderNowCard(
//            Modifier.height(136.dp),
//            R.drawable.img8_food
//        )
//    }
//}
//
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun ComponentPairRowPreview(){
    MunchTheme{
        ComponentPairRow()
    }
}
//
//
//@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
//@Composable
//fun ComponentPairPreview(){
//    MunchTheme{
//        ComponentPair(R.string.FoodItem1, R.drawable.img1_food,)
//    }
//}
//
//@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
//@Composable
//fun ChipSortPreview(){
//    MunchTheme{
//        ChipSort(
//            modifier = Modifier.padding(10.dp),
//            R.string.homeChip1,
//            R.string.homeChip2,
//            R.string.homeChip3
//        )
//    }
//}
//
//
//@Preview
//@Composable
//fun SearchBarPreview(){
//    MunchTheme{
//        Search()
//    }
//}

//@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
//@Composable
//fun RestaurantCardColumnPreview(){
//    MunchTheme{
//        RestaurantCardColumn()
//    }
//}
//
//@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
//@Composable
//fun TestPreview(){
//    MunchTheme{
//        ColumnTest()
//    }
//}
