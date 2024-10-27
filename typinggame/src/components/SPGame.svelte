<script>
import { onMount } from "svelte";
import { getWords } from "../helpermethotds/words_client";
import { isLoggedIn, getLocalUser } from "../clients/User_client.js";
import { StartGame } from "../clients/Game_client.js";
import {GameData} from "../helpermethotds/GameData.js";
import {endGameWithUser} from "../helpermethotds/game_client.js"

let user = getLocalUser();
let dbWords = [];
let i = 0;
let wordCount = 0;
let containerDiv;
let words = "";
let startTime;
let gameFinished = false;
let endMessage = "";
let game; 

async function beginGame(){
    // Fetch words from the database
    let retrieved = await getWords();

    //Ready words in the frontend
    dbWords = retrieved;
    words = readyWords(dbWords);
    spanerizeString(containerDiv, words);
    startTime = Date.now();

    console.log("User: " + user.username + user.id);
    if (isLoggedIn(user)){
        game = await StartGame()
            if (!game){
                console.error("Could not start game");
            }
    }
}

function readyWords(dbWords) {
    let wordsStr = "";
    for (let i = 0; i < dbWords.length; i++) {
        wordsStr += dbWords[i] + " "; 
    }
    return wordsStr.trimEnd(); 
}

function spanerizeString(WordContainer, wordsStr) {
    containerDiv.innerHTML = ""; // Clear the container
    for (let i = 0; i < wordsStr.length; i++) {
        const charSpan = document.createElement('span');
        charSpan.classList.add('char');
        charSpan.innerHTML = wordsStr[i];

        // If the character is a space, add a class for styling (optional)
        if (wordsStr[i] === ' ') {
            charSpan.innerHTML = '&nbsp;';
        }

        WordContainer.appendChild(charSpan);
    }
    containerDiv = WordContainer;
    WordContainer.children[0].classList.add("white");
}

async function stopGame() {
    const endTime = Date.now(); // Get the end time
    const timeTaken = (endTime - startTime) / 1000; // Calculate time taken in seconds
    const minutesTaken = timeTaken / 60;
    const wpm = (wordCount / minutesTaken).toFixed(0); // Calculate WPM

    if (localStorage.getItem('username')){
        // Create the game object to send to the backend
        await endGameWithUser(game.id, wpm, localStorage.getItem('username'))
            .then(data =>{
                    console.log('Game ended succesfully: ', data);
                    endMessage = `Congrats! You finished in ${timeTaken.toFixed(2)} seconds with a wpm of ${wpm}`
                    gameFinished = true;
                    })
    } else {
        endMessage = `Congrats! You finished in ${timeTaken.toFixed(2)} seconds with a wpm of ${wpm}`
            gameFinished = true;
    }
    document.removeEventListener('keydown', handleKeyEvent);
}



function handleKeyEvent(event) {
    if (gameFinished) return; // Ignore events if the game is finished

    let currentChar = containerDiv.children[i];

    // Handle space input separately
    if (event.key === ' ' && currentChar.innerHTML === '&nbsp;') {
        // Correctly typed a space
        currentChar.classList.remove("red", "white", "space");
        currentChar.classList.add("green");
        wordCount++; // Increment word count
        i++; // Move to the next character
    } else if (event.key === currentChar.innerHTML) {
        // Handle correct character input
        currentChar.classList.remove("red", "white");
        currentChar.classList.add("green");

        i++; // Move to the next character

        // Check if we've reached the end
        if (i >= words.length) {
            stopGame(); // End the game if all characters have been typed
            return; // Exit to prevent further processing
        }
    } else {
        // Handle incorrect input
        currentChar.classList.add("red");
        currentChar.classList.remove("white");
    }

    // Highlight the next character if it exists
    if (i < words.length) {
        if (containerDiv.children[i].innerHTML === '&nbsp;'){
            containerDiv.children[i].classList.add("space");
        }else {
            containerDiv.children[i].classList.add("white");
        }
    }
}



function playAgain() {
    i = 0;
    wordCount = 0;
    gameFinished = false;
    endMessage = "";
    beginGame(); 
    document.addEventListener('keydown', handleKeyEvent);
}

onMount(() => {
        document.addEventListener('keydown', handleKeyEvent);
        beginGame(); 
        });
</script>

<style>
#WordContainer {
    font-size: 20px;
    background-color: black;
color: grey;
       border-radius: 10px;
       padding-top: 20px;
       padding-bottom: 40px;
}

:global(.char) {
display: inline-block;
transition: color 0.2s ease;
color: grey; 
}

:global(.char.green) {
color: green;
}

:global(.char.white) {
color: white;
}

:global(.char.space) {
display: inline-block;
width: 0.51em; /* Adjust the width as needed */
height: 0.9em; /* Set height to match the text height */
border: 1px solid white; /* Make the space visible */
        background-color: transparent; /* Transparent background */
}

:global(.char.red) {
color: red;
animation: -global-shake 1s; /* Add the shake animation */
}

@keyframes -global-shake {
    0% { transform: translateX(0); }
    25% { transform: translateX(-2px); }
    50% { transform: translateX(2px); }
    75% { transform: translateX(-2px); }
    100% { transform: translateX(0); }
}
</style>

<div id="WordContainer" bind:this={containerDiv}>
{#if words.length > 0}
{:else}
Loading Words .....
{/if} 
</div>

{#if gameFinished}
<div>
<h2>{endMessage}</h2> <!-- Display the end message -->
<button on:click={playAgain}>Play Again</button> <!-- Button to play again -->
</div>
{/if}

