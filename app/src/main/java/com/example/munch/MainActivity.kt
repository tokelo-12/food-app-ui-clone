                                                                     package com.example.munch

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Icon
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Motorcycle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.RoomService
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.munch.ui.theme.MunchTheme
import org.w3c.dom.Text
import java.time.format.TextStyle
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
                    Scaffold(
                        bottomBar = {

                                MunchBottomNavigation()

                        }
                    ) {padding ->
                        HomeScreen(Modifier.padding(padding))

                    }
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
            modifier = Modifier.fillMaxWidth(),
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
fun ChipSort(
             @StringRes text: Int,
             @StringRes chiptext2: Int,
             @StringRes chiptext3: Int,
             @StringRes chiptext4: Int,
             @StringRes chiptext5: Int,
             @StringRes chiptext6: Int

){
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy
            (
            16.dp, Alignment.Start
        ),

    ) {
        item {
            AssistChip(
                onClick = { /*TODO*/ },
                label = {
                    Text(
                        text = stringResource(text),
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(500),
                            letterSpacing = 0.1.sp,
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
                    .wrapContentWidth()
                    .height(32.dp),
//                    .align(Alignment.CenterVertically),


                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Sort Icon",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            )
        }

        item {
            AssistChip(
                onClick = { /*TODO*/ },
                label = {
                    Text(
                        text = stringResource(chiptext2),
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(500),
                            letterSpacing = 0.1.sp,
                        )
                    )
                },

                modifier = Modifier
                    .wrapContentWidth()
                    .height(32.dp),
            )
        }

        item {
            AssistChip(
                onClick = { /*TODO*/ },
                label = {
                    Text(
                        text = stringResource(chiptext3),
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(500),
                            letterSpacing = 0.1.sp,
                        )
                    )
                },

                modifier = Modifier
                    .wrapContentWidth()
                    .height(32.dp),
            )
        }

        item {
            AssistChip(
                onClick = { /*TODO*/ },
                label = {
                    Text(
                        text = stringResource(chiptext4),
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(500),
                            letterSpacing = 0.1.sp,
                        )
                    )
                },

                modifier = Modifier
                    .wrapContentWidth()
                    .height(32.dp),
            )
        }

        item {
            AssistChip(
                onClick = { /*TODO*/ },
                label = {
                    Text(
                        text = stringResource(chiptext5),
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(500),
                            letterSpacing = 0.1.sp,
                        )
                    )
                },

                modifier = Modifier
                    .wrapContentWidth()
                    .height(32.dp),
            )
        }

        item {
            AssistChip(
                onClick = { /*TODO*/ },
                label = {
                    Text(
                        text = stringResource(chiptext6),
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(500),
                            letterSpacing = 0.1.sp,
                        )
                    )
                },

                modifier = Modifier
                    .wrapContentWidth()
                    .height(32.dp),
            )
        }
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
            .height(124.dp)
    ) {
        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(3.dp))
//                .shadow(elevation = 10.dp)

        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                modifier = Modifier
                    .size(height = 96.dp,width = 80.dp),

                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

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
//        contentPadding = PaddingValues(horizontal = 16.dp),
//        modifier = Modifier.padding(vertical = 8.dp),
    ){
        items(componentsRowElements){item ->
            ComponentPair(text = item.text,drawable = item.drawable)
        }
    }
}

@Composable
fun OrderNowCard(
    modifier: Modifier = Modifier,
    @DrawableRes drawable: Int,

) {
        // Material Components like Button, Card, Switch, etc
        Card(
            modifier = Modifier
                .height(136.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),

        ) {
            Row{

                Card(
                    modifier = Modifier
                        .width(192.dp)
                        .padding(all = 20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceBetween,
                    ){
                        Text(text = "PlaceHolder Text")

                        Spacer(modifier = Modifier.heightIn(min = 24.dp))

                        Button(
                            onClick = { /*TODO*/ },

                            contentPadding = PaddingValues(top = 10.dp, start = 16.dp,
                                end = 16.dp, bottom = 10.dp),

                            modifier = Modifier
                                .height(36.dp)
                                .wrapContentWidth(),

                            shape = RoundedCornerShape(12.dp)


                        ) {
                                Text(
                                    text = "Order Now",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight(500),
                                        lineHeight = 16.sp,
                                        letterSpacing = 0.5.sp,
                                        ),


                                    )

                                Spacer(modifier = Modifier.width(8.dp))

                                Icon(
                                    imageVector = Icons.Default.ArrowRightAlt ,
                                    contentDescription = "null",
                                    modifier = Modifier.size(16.dp)
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
                .clip(RoundedCornerShape(16.dp))
        ){
            Box(
                contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = painterResource(drawable),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()

                )

                FavouriteButton()

            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(text),
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(600),
                    letterSpacing = 0.5.sp,

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
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            letterSpacing = 0.4.sp,
                            fontWeight = FontWeight(400),
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
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(400),
                    letterSpacing = 0.4.sp,
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
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(400),
                    letterSpacing = 0.4.sp,
                )
            )

            Icon(
                imageVector = Icons.Filled.FiberManualRecord,
                contentDescription = null,
                modifier = Modifier
                    .size(2.dp)
            )

            Text(text = stringResource(R.string.PlaceHolderDistance),
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(400),
                    letterSpacing = 0.4.sp,
                )
            )
        }
    }

}

@Composable
fun FavouriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color.White
){
    var isFavourite by remember {
        mutableStateOf(false)
    }

    IconToggleButton(
        checked = isFavourite,
        onCheckedChange ={isFavourite = !isFavourite}
    ) {
       Icon(
           tint = color,
           imageVector = if (isFavourite){
               Icons.Filled.Favorite
           }else{
               Icons.Default.FavoriteBorder
           },
           contentDescription = "favorite icon"
       )
    }
}

@Composable
fun RestaurantCardColumn(){
    Column(
//        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
//        contentPadding = PaddingValues(top = 14.dp),
    ){
        restaurantColumnElements.forEach {item ->
            RestaurantCard(text = item.text,drawable = item.drawable)
        }
    }
}

@Composable
fun RecentlyViewed(
    @DrawableRes drawable: Int,
    @StringRes text: Int
){
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 156.dp, height = 96.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(text),
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(600),
                    letterSpacing = 0.5.sp,
                )
            )

            //Restaurant and distance info

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
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight(400),
                        letterSpacing = 0.4.sp,
                    )
                )

                Icon(
                    imageVector = Icons.Filled.FiberManualRecord,
                    contentDescription = null,
                    modifier = Modifier
                        .size(2.dp)
                )

                Text(text = stringResource(R.string.PlaceHolderDistance),
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight(400),
                        letterSpacing = 0.4.sp,
                    )
                )
            }
        }
}

@Composable
fun RecentlyViewedRow(){
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy
            (
            20.dp, Alignment.Start
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
//            .padding(top = 20.dp)
    ){
        items(RecentlyViewedRowElemets){
            item -> RecentlyViewed(drawable = item.drawable, text = item.text)
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
                fontWeight = FontWeight(500),
                lineHeight = 28.sp,
            )
        )
        Spacer(modifier = Modifier.height(14.dp))
        content()
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    LazyColumn(
        modifier
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Search()
        }
        item {
            ChipSort(
                R.string.homeChip1,
                R.string.homeChip2,
                R.string.homeChip3,
                R.string.homeChip4,
                R.string.homeChip5,
                R.string.homeChip6,

            )
        }
        item {
            ComponentPairRow()
        }
        item {
            OrderNowCard(
                Modifier.height(136.dp),
                R.drawable.img8_food
            )
        }
        item {
            HomeSection(title = R.string.SlotTitle) {
                RestaurantCardColumn()
            }
        }

        item {
            HomeSection(title = R.string.SlotTitle2) {
                RecentlyViewedRow()
            }
        }
    }
}

@Composable
fun MunchBottomNavigation(){
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth(),
        
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.NavHome),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.5.sp,
                        lineHeight = 16.sp,
                    )
                )
            },
            selected = true,
            onClick = { /*TODO*/ }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.RoomService,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.NavRestaurants),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.5.sp,
                        lineHeight = 16.sp,
                    )
                )
            },
            selected = true,
            onClick = {}
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.ReceiptLong,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.NavOrders),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.5.sp,
                        lineHeight = 16.sp,
                    )
                )
            },
            selected = true,
            onClick = {}
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.NavProfile),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.5.sp,
                        lineHeight = 16.sp,
                    )
                )
            },
            selected = true,
            onClick = {}
        )
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

private val RecentlyViewedRowElemets = listOf(
    R.drawable.img9_food to R.string.Restaurant4,
    R.drawable.img13_food to R.string.Restaurant2,
    R.drawable.img16_food to R.string.Restaurant6,
    R.drawable.img17_food to R.string.Restaurant4,
).map{
    DrawableStringPair(it.first,it.second)
}

data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
)

@Preview
@Composable
fun FavouriteButtonPreview(){
    MunchTheme{
        FavouriteButton()
    }
}

//@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
//@Composable
//fun RecentlyViewedPreview(){
//    MunchTheme{
//        RecentlyViewed(
//            R.string.Restaurant5,
//            R.drawable.img14_food,
//        )
//    }
//}

//@Preview
//@Composable
//fun MunchHomePreview(){
//    MunchTheme {
//        Scaffold(
//            bottomBar = {
//                Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                MunchBottomNavigation()
//            }
//            }
//        ) {padding ->
//            HomeScreen(Modifier.padding(padding))
//
//        }
//    }
//}

//@Preview
//@Composable
//fun BottomNavigationPreview(){
//    MunchTheme {
//        MunchBottomNavigation()
//    }
//}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeScreenPreview(){
    MunchTheme {
        HomeScreen()
    }
}

//@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
//@Composable
//fun HomeSectionPreview(){
//    MunchTheme{
//        HomeSection(title = R.string.SlotTitle) {
//            RestaurantCardColumn()
//        }
//    }
//}

//@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
//@Composable
//fun RestaurantCardPreview(){
//    MunchTheme{
//        RestaurantCard(R.string.Restaurant4,R.drawable.img9_food)
//    }
//}
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
//@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
//@Composable
//fun ComponentPairRowPreview(){
//    MunchTheme{
//        ComponentPairRow()
//    }
//}
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
//            R.string.homeChip1,
//            R.string.homeChip2,
//            R.string.homeChip3,
//
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
