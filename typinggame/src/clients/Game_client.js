import { Game, User } from "../classes/User";

const baseUrl = 'http://localhost:8080/api/game/';

/** @returns{Promise<null|Game>} */
export async function StartGame(){
    try {
        const response = await fetch(baseUrl + 'start', { method: 'POST' });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json(); 
        return new Game(data.id, data.startTime, data.endTime, data.wpm);
    } catch (error) {
        console.error('There was a problem starting the game: ', error);
        return null;
    }
}

/**
    *@param {String} wpm 
    *@param {User} user 
    *@param {Game} runningGame 
    *@returns {Promise<Game>}
    */
export async function endGame(runningGame, wpm, user){
    const response = await fetch(baseUrl + `end/${runningGame.getId()}`,
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                username: user.getUsername(),
                wpm: wpm,
            })
        }).then(response => {
            if (!response.ok){
                throw new Error('Network response was not ok');
            }
            return response.json();
        }).catch(error =>{
            console.error('There was a problem saving the game: ', error);
        });
    const data = await response.json();
    return new Game(data.id, data.startTime, data.endTime, data.wpm);
}


