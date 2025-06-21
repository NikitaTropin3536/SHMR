package com.example.financialapp

import android.os.Bundle
import android.provider.SyncStateContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.financialapp.ui.theme.FinancialAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinancialAppTheme {
                /*
                Это что - то, что можно использовать для переключения между экранами
                 */
                val navController = rememberNavController()

                Surface(color = Color.White) {
                    Scaffold(
                        // Bottom navigation
                        bottomBar = {
                            BottomNavigationBar(navController = navController)
                        }, content = { padding ->
                            NavHostContainer(
                                navController = navController,
                                padding = padding
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {
    /*
    Это наш основной навигационный график, определяющий экраны
     */
    NavHost(
        navController = navController,
        startDestination = MainRoutes.EXPENSES_ROUTE,

        // Set the padding provided by scaffold
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable(MainRoutes.EXPENSES_ROUTE) {
                ExpensesScreen()
            }
            composable(MainRoutes.GAINS_ROUTE) {
                GainsScreen()
            }
            composable(MainRoutes.ACCOUNT_ROUTE) {
                AccountScreen()
            }
            composable(MainRoutes.ARTICLES_ROUTE) {
                ArticlesScreen()
            }
            composable(MainRoutes.SETTINGS_ROUTE) {
                SettingsScreen()
            }
        })
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        containerColor = Color.White
    ) {
        // Получаем текущее состояние навигационного стека
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // Получаем текущий маршрут, чтобы определить, какой элемент навигации активен
        val currentRoute = navBackStackEntry?.destination?.route

        // Перебираем все элементы навигации (navItems)
        navItems.forEach { navItem ->
            // Добавляем каждый элемент навигации в NavigationBar
            NavigationBarItem(
                // Определяем, выбран ли текущий элемент навигации
                selected = currentRoute == navItem.route,

                // Обработчик клика на элемент навигации
                onClick = {
                    // Переходим на указанный маршрут при клике
                    navController.navigate(navItem.route)
                },

                // Иконка элемента навигации
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(navItem.icon), // Векторная иконка
                        contentDescription = navItem.title.toString()
                    )
                },

                // Текстовая метка элемента навигации
                label = {
                    Text(text = navItem.title.toString())
                },

                // Опция, чтобы метка всегда отображалась или нет
                alwaysShowLabel = false,

                // Настройка цветов для элементов навигации
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Green, // Цвет иконки при выборе
                    unselectedIconColor = Color.Black, // Цвет иконки при не выборе
                    selectedTextColor = Color.Black, // Цвет текста при выборе
                    indicatorColor = Color(0xFF195334) // Цвет индикатора выбранного элемента
                )
            )
        }
    }
}