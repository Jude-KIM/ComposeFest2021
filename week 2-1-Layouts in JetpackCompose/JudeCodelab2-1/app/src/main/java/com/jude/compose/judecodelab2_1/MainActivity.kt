package com.jude.compose.judecodelab2_1

import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter
import com.jude.compose.judecodelab2_1.ui.theme.JudeCodelab21Theme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JudeCodelab21Theme {
                // A surface container using the 'background' color from the theme
                LayoutCodelab()
            }
        }
    }
}

@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
    JudeCodelab21Theme {
        Surface(color = MaterialTheme.colors.background) {
            Text("Hi there!", Modifier.firstBaselineToTop(32.dp))
        }

    }
}

@Preview
@Composable
fun TextWithNormalPaddingPreview() {
    JudeCodelab21Theme {
        Surface(color = MaterialTheme.colors.background) {
            Text("Hi there!", Modifier.padding(top = 32.dp))
        }
    }
}

fun Modifier.firstBaselineToTop(value: Dp) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        val firstBaseline = placeable[FirstBaseline]

        val y = value.roundToPx() - firstBaseline
        val height = placeable.height + y
        layout(placeable.width, height) {
            placeable.placeRelative(0, y)
        }

    }
)

@Composable
fun LayoutCodelab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "JudeCodelab") },
                actions = {
                    IconButton(onClick = { /* todo */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        BodyContent(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .background(Color.LightGray)
        )
    }
}

val topics = listOf(
    "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary",
    "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy",
    "Religion", "Social sciences", "Technology", "TV", "Writing"
)

@ExperimentalCoilApi
@Composable
fun BodyContent(modifier: Modifier) {

    /*
    val listSize = 100
// We save the scrolling position with this state
    val scrollState = rememberLazyListState()
// We save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Row {
            Button(
                modifier = Modifier.weight(0.5f),
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(0)
                    }
                }) {
                Text(text = "Go to top")
            }

            Button(
                modifier = Modifier.weight(0.5f),
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(listSize - 1)
                    }
                }) {
                Text(text = "Go to bottom")
            }

        }

        LazyColumn(state = scrollState) {
            items(100) {
                ImageListItem(index = it)
            }
        }
    }

     */

//    JudeColumn(modifier.padding(8.dp)) {
//        Text(text = "JudeColumn")
//        Text(text = "place items")
//        Text(text = "vertically")
//        Text(text = "we've done it by hand!")
//
//    }

    StaggeredGrid(modifier = modifier.horizontalScroll(rememberScrollState()), rows = 4) {
        for (topic in topics) {
            Chip(Modifier.padding(8.dp), topic)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun ImageListItem(index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(Color.Yellow)
            .clickable { }) {
        Image(
            painter = rememberImagePainter(data = "https://developer.android.com/images/brand/Android_Robot.png"),
            contentDescription = "Android Logo",
            modifier = Modifier.size(48.dp)
        )
        Spacer(
            modifier = Modifier
                .width(10.dp)
                .background(MaterialTheme.colors.primary)
        )
        Text("List Item $index", style = MaterialTheme.typography.subtitle1)

    }
}

@Preview
@Composable
fun LayoutsCodelabPreview() {
    JudeCodelab21Theme {
        Surface(color = Color.White) {
            LayoutCodelab()
        }

    }
}

@Composable
fun ProfileView(modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable { }
            .padding(16.dp)) {
        Surface(
            modifier = Modifier.size(48.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) { }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text("Jude Aires(김동주)", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }

        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    JudeCodelab21Theme {
        Surface(color = MaterialTheme.colors.background) {
            ProfileView()
        }
    }
}

@Composable
fun JudeColumn(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        var posY = 0
        //set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            //place 해야함.
            placeables.forEach {
                it.placeRelative(x = 0, y = posY)
                posY = posY + it.height
            }
        }

    }
}

@Composable
fun StaggeredGrid(
    modifier: Modifier = Modifier,
    rows: Int = 3,
    content: @Composable () -> Unit
) {
    Layout(content = content, modifier = modifier) { measurables, constraints ->

        val rowWidths = IntArray(rows) { 0 }
        val rowHeights = IntArray(rows) { 0 }

        val placeables = measurables.mapIndexed { index, measurable ->
            val placeable = measurable.measure(constraints)

            // Track the width and max height of each row
            val row = index % rows
            rowWidths[row] += placeable.width
            rowHeights[row] = Math.max(rowHeights[row], placeable.height)
            placeable
        }

        val width = rowWidths.maxOrNull()
            ?.coerceIn(constraints.minWidth.rangeTo(constraints.maxWidth)) ?: constraints.minWidth

        val height =
            rowHeights.sumOf { it }.coerceIn(constraints.minHeight.rangeTo(constraints.maxHeight))

        // Y position of each row
        val rowY = IntArray(rows) { 0 }
        for (i in 1 until rows) {
            rowY[i] = rowY[i - 1] + rowHeights[i - 1]
        }

        //width = row 중에 제일 긴 것의 width, height = 모든 row's height 를 합친 값.
        layout(width, height) {
            //각 row별로 child의 width를 더해서 next item의 x값으로 사용
            val rowX = IntArray(rows) { 0 }
            placeables.forEachIndexed { index, placeable ->
                val row = index % rows
                placeable.placeRelative(rowX[row], rowY[row])
                rowX[row] += placeable.width
            }

        }

    }
}

@Composable
fun Chip(modifier: Modifier = Modifier, text: String) {
    Card(
        modifier = modifier,
        border = BorderStroke(color = Color.Black, width = Dp.Hairline),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp, 16.dp)
                    .background(color = MaterialTheme.colors.secondary)
            )
            Spacer(Modifier.width(4.dp))
            Text(text = text)
        }
    }
}

@Preview
@Composable
fun ChipPreview() {
    JudeCodelab21Theme() {
        Chip(text = "Hi Jude")

    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        val (btn1, btn2, text) = createRefs()

        Button(
            onClick = { },
            modifier = Modifier.constrainAs(btn1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Btn1")
        }

        Text(text = "This is text", modifier = Modifier.constrainAs(text) {
            top.linkTo(btn1.bottom, margin = 8.dp)
            centerAround(btn1.end)
        })

        val barrier = createEndBarrier(btn1, text)
        Button(onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(btn2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text(text = "Btn2")
        }
    }
}

@Preview
@Composable
fun constraintPreview() {
    JudeCodelab21Theme {
        ConstraintLayoutContent()
    }
}

@Composable
fun LargeConstraintLayout() {
    ConstraintLayout {
        val text = createRef()

        val guideline = createGuidelineFromStart(fraction = 0.5f)
        Text(
            "This is a very very very very very very very long text",
            Modifier.constrainAs(text) {
                linkTo(start = guideline, end = parent.end)
                width = Dimension.preferredWrapContent
            }
        )
    }
}

@Preview
@Composable
fun LargeConstraintLayoutPreview() {
    JudeCodelab21Theme {
//        LargeConstraintLayout()
        DecoupledConstraintLayout()
    }
}

@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if (maxWidth < maxHeight) {
            decoupledConstraints(margin = 16.dp) // Portrait constraints
        } else {
            decoupledConstraints(margin = 32.dp) // Landscape constraints
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("btn1")
            ) {
                Text("Button")
            }

            Text("Text", Modifier.layoutId("tv"))
        }
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("btn1")
        val text = createRefFor("tv")

        constrain(button) {
            top.linkTo(parent.top, margin= margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}

@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = text1
        )

        Divider(color = Color.Black, modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),

            text = text2
        )
    }
}

@Preview
@Composable
fun TwoTextsPreview() {
    JudeCodelab21Theme() {
        Surface(color = MaterialTheme.colors.primary) {
            TwoTexts(text1 = "Hi", text2 = "there")
        }
    }
}