## About Minesweeper
It's Android app written in Kotlin that creates a Minesweeper board in the log output or with a simple Jetpack Compose UI.

## Requirements
- **Minimum SDK:** 29 (Android 10)

## How to Run

1. **Clone the repo:**

```bash
 git clone https://github.com/VictoriaTaiwan/Minesweeper.git
```
2. Open in Android Studio:

- File > Open > Select the project directory

- Let Gradle sync and finish indexing

3. Build the app:
- Select your preferred device or emulator (must be API 29 or above)
- Click Run 

By default, the app generates a Minesweeper board using 
```
println(createTextBoard())
```
Check Logcat to view the board output.

## Example

Board size is 10x10, starting point is x(5), y (5), mines number is 20.
🚩 - Start point
🔲 - Safe spot
💣 - Bomb

🔲  🔲  🔲  🔲  🔲  🔲  🔲  💣  🔲  🔲 
 
🔲  🔲  🔲  🔲  🔲  💣  🔲  💣  💣  💣 
 
🔲  🔲  🔲  🔲  💣  🔲  🔲  💣  🔲  🔲 
  
🔲  💣  🔲  🔲  🔲  🔲  💣  🔲  🔲  🔲 

🔲  💣  🔲  🔲  🔲  🔲  🔲  🔲  🔲  🔲 

🔲  🔲  🔲  🔲  💣  🚩  🔲  🔲  🔲  🔲 

🔲  🔲  🔲  🔲  🔲  🔲  🔲  🔲  🔲  💣 
  
🔲  🔲  🔲  💣  🔲  💣  🔲  🔲  💣  🔲 

💣  🔲  💣  💣  🔲  🔲  🔲  🔲  🔲  🔲 
  
🔲  🔲  🔲  🔲  🔲  💣  🔲  💣  🔲  🔲 

## Optional UI (Jetpack Compose)

You can uncomment the included Compose UI in MainActivity to enable a basic interactive version:
```
enableEdgeToEdge()
setContent {
    MinesweeperTheme {
        val boardText = remember { mutableStateOf("Click the button to start!") }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                content = { Text("Generate Board") },
                onClick = { boardText.value = createTextBoard() }
            )
            Text(
                text = boardText.value,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
```

## Example
Board size is 10x10, starting point is x(2), y (1), mines number is 30.
🚩 - Start point
🔲 - Safe spot
💣 - Bomb

[Video_example](https://github.com/user-attachments/assets/cb066cde-0917-4dbc-aa09-b5bc0ae3d494)


