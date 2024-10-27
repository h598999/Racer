export class Game{
    /**
        *@param {String} id 
        *@param {Date} startTime 
        *@param {Date} endTime 
        *@param {String} WPM 
        */
    constructor(id, startTime, endTime, WPM){
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.WPM = WPM;
    }

    getId() {
        return this.id;
    }

    getStartTime() {
        return this.startTime;
    }

    getEndTime() {
        return this.endTime;
    }

    getWPM() {
        return this.WPM;
    }

    /** @param{String} id */
    setId(id) {
        this.id = id;
    }

    /** @param{Date} startTime*/
    setStartTime(startTime) {
        this.startTime = startTime;
    }

    /** @param{Date} endTime*/
    setEndTime(endTime) {
        this.endTime = endTime;
    }

    /** @param{String} WPM*/
    setWPM(WPM) {
        this.WPM = WPM;
    }
}

export class User{
    /**
        * @param{number} id 
        * @param{String} username
        * @param{Array<Game>} gamesPlayed
        */
    constructor(id, username, gamesPlayed){ 
        this.id = id;
        this.username = username;
        this.gamesPlayed = gamesPlayed;
        }

    getId() {
        return this.id;
    }

    getUsername() {
        return this.username;
    }

    getGamesPlayed() {
        return this.gamesPlayed;
    }

    /** @param{number} id */
    setId(id) {
        this.id = id;
    }

    /** @param{String} username*/
    setUsername(username) {
        this.username = username;
    }

    /** @param{Array<Game>} gamesPlayed*/
    setGamesPlayed(gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
    
}
