import { GameData } from "./GameData";

const baseUrl = 'http://localhost:8080/api/game/';

export async function StartGame(){
  const response = await fetch(baseUrl + 'start', {method: 'POST'});
  const data = await response.json();
  let gameId = data.id;

  window.location.href = `/game/${gameId}`;
}

/**
    *
    * @param{String} wpm 
    * @param{Number} gameId
    * @returns {Promise<JSON>} 
    */
// export async function endGame(gameId, wpm){
//     const response = await fetch(baseUrl + `end/${gameId}`,{ 
//         method: 'POST',
//         headers: {'Content-Type': 'application/json'},
//         body: JSON.stringify({
//             wpm: wpm,
//             username: null,
//         }) 
//     })
//     .then(response => {
//         if (!response.ok) {
//             throw new Error('Network response was not ok');
//         }
//         return response.json();
//     })
//         .catch(error => {
//             console.error('There was a problem saving the game:', error);
//         });
//     return await response;
// }

/**
    *
    * @param{String} wpm 
    * @param{String} username
    * @param{Number} gameId
    * @returns {Promise<JSON>} 
    */
export async function endGameWithUser(gameId, wpm, username){
    const response = await fetch(baseUrl + `end/${gameId}`,{ 
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            username: username,
            wpm: wpm,
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
        .catch(error => {
            console.error('There was a problem saving the game:', error);
        });
    return await response;
}


