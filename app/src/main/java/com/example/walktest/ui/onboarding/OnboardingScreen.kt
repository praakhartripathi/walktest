package com.example.walktest.ui.onboarding

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class OnboardingPageData(
    val tagline: String,
    val icon: ImageVector,
    val bgGradient: List<Color>,
    val featureTitle: String,
    val featureSubtitle: String,
    val accentColor: Color = Color(0xFFFC5200)
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onJoinClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    val orange = Color(0xFFFC5200)

    val pages = listOf(
        OnboardingPageData(
            tagline = "Track every step of your journey.",
            icon = Icons.Default.DirectionsWalk,
            bgGradient = listOf(Color(0xFF1A0A00), Color(0xFF3D1800), Color(0xFF7A3200)),
            featureTitle = "Live Activity Tracking",
            featureSubtitle = "Distance • Pace • Time • Elevation",
            accentColor = orange
        ),
        OnboardingPageData(
            tagline = "Get motivation from your people.",
            icon = Icons.Default.Group,
            bgGradient = listOf(Color(0xFF001A0A), Color(0xFF003D18), Color(0xFF006B2A)),
            featureTitle = "Social Feed",
            featureSubtitle = "Kudos • Comments • Challenges",
            accentColor = Color(0xFF00C96B)
        ),
        OnboardingPageData(
            tagline = "Make progress toward goals.",
            icon = Icons.Default.ShowChart,
            bgGradient = listOf(Color(0xFF000D1A), Color(0xFF001A3D), Color(0xFF003D7A)),
            featureTitle = "Weekly Progress",
            featureSubtitle = "Goals • Streaks • Achievements",
            accentColor = Color(0xFF0088FF)
        ),
        OnboardingPageData(
            tagline = "Route options that never run out.",
            icon = Icons.Default.Map,
            bgGradient = listOf(Color(0xFF1A001A), Color(0xFF3D0044), Color(0xFF6B007A)),
            featureTitle = "Explore Routes",
            featureSubtitle = "Discover • Save • Navigate",
            accentColor = Color(0xFFCC44FF)
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { pageIndex ->
            OnboardingPageContent(page = pages[pageIndex])
        }

        // Gradient overlay at bottom for legibility
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xEE080808))
                    )
                )
        )

        // Bottom content pinned at bottom
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 28.dp)
                .padding(bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = pages[pagerState.currentPage].tagline,
                color = Color.White,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp,
                modifier = Modifier.padding(bottom = 22.dp)
            )

            // Page dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 30.dp)
            ) {
                repeat(pages.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    val width by animateDpAsState(
                        targetValue = if (isSelected) 24.dp else 8.dp,
                        label = "dot_width"
                    )
                    Box(
                        modifier = Modifier
                            .height(8.dp)
                            .width(width)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) pages[pagerState.currentPage].accentColor
                                else Color(0xFF444444)
                            )
                    )
                }
            }

            // Join for free button
            Button(
                onClick = onJoinClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = orange)
            ) {
                Text(
                    text = "Join for free",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            TextButton(onClick = onLoginClick) {
                Text(
                    text = "Log In",
                    color = orange,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // App name at the top
        Text(
            text = "WALKTEST",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 4.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding()
                .padding(top = 16.dp)
        )
    }
}

@Composable
fun OnboardingPageContent(page: OnboardingPageData) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(page.bgGradient))
    ) {
        // Decorative circles
        Box(
            modifier = Modifier
                .size(300.dp)
                .offset(x = (-60).dp, y = (-40).dp)
                .clip(CircleShape)
                .background(page.accentColor.copy(alpha = 0.12f))
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopEnd)
                .offset(x = 50.dp, y = 100.dp)
                .clip(CircleShape)
                .background(page.accentColor.copy(alpha = 0.08f))
        )

        // Floating mockup card
        Card(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 120.dp, start = 32.dp, end = 32.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1E)),
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(page.accentColor.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = page.icon,
                        contentDescription = null,
                        tint = page.accentColor,
                        modifier = Modifier.size(36.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = page.featureTitle,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = page.featureSubtitle,
                    color = Color(0xFF999999),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Simulated data row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem("12.4 km", "Distance", page.accentColor)
                    StatItem("5:32", "Pace", page.accentColor)
                    StatItem("1h 08m", "Time", page.accentColor)
                }
            }
        }
    }
}

@Composable
private fun StatItem(value: String, label: String, accent: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            color = accent,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            color = Color(0xFF888888),
            fontSize = 12.sp
        )
    }
}
