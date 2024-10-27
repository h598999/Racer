import { User } from "../classes/User";

const baseUrl = 'http://localhost:8080/api/users';

/**
    *If there exist a user in the database with the username and password given
    *if the user exist the method will return the user object stored in the database
    *will return null if the username or password is incorrect
    *@param {String} username 
    *@param {String} password 
    *@return {Promise<User|null>}
    */
    export async function loginUser(username, password){
        try{
            const response = await fetch(baseUrl+"/login", {method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    username: username,
                    password: password
                })
            });
            if (!response.ok){
                return null;
            }
            const data = await response.json();
            return new User(data.id, data.username, data.gamesPlayed);
        } catch(error){
            console.log("Error logging in: " + error);
            return null;
        }
    }

/**
    *Registers a user with the username and password into the database
    *will not work if username or password is missing 
    *will not work if the username is taken
    *@param {String} username 
    *@param {String} password 
    *@return {Promise<User|null>}
    */
    export async function registerUser(username, password){
        try{
            const response = await fetch(baseUrl+"/register", {method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    username: username,
                    password: password
                })
            });
            if (response.status === 400){
                alert("Username taken");
                console.error('Register failed username taken:', response.status);
                return null; 
            } else if(!response.ok){
                alert("Error registering user");
                console.error('Register failed:', response.status);
                return null; 
            }
            const data = await response.json();
            return new User(data.id, data.username, data.gamesPlayed);
        } catch(error){
            console.log("Error registering user: " + error);
            return null;
        }
    }

/**
    *Takes a username and returns the user associated
    *with the username from the database.
    *@param {String} username 
    *@returns {Promise<User|null>}
    */
    export async function getUser(username){
        try{
            const response = await fetch(baseUrl+"/"+username, {
                method: 'GET',
                headers: {'Content-Type':'application/json'},
            })
            if (!response.ok){
                return null;
            }
            const data = await response.json();
            return new User(data.id, data.username, data.gamesPlayed);
        }catch(error){
            console.log("Error retrieving user: " + error);
            return null;
        }
    }
    
    /**
    *@param {User} user;
    *@returns{Promise<boolean>}
    */
    export async function isLoggedIn(user){
        if (!user || !user.username){
            console.error(user);
            console.error("Invalid user object");
            return false;
        }
        try{
            const response = await fetch(`${baseUrl}/${user.username}`, {method: 'GET'})
            if (!response.ok){
                return false;
            }
            sessionStorage.removeItem('User');
            return true;
        } catch (error){
            console.error(error);
            return false;
        }
    }

    /** @returns{User|null} */
    export function getLocalUser() {
        let userString = sessionStorage.getItem('User');

        if (!userString) {
            return null; 
        }

        try {
            let jsonObject = JSON.parse(userString);
            
            if (jsonObject && jsonObject.id && jsonObject.username && jsonObject.gamesPlayed !== undefined) {
                return new User(jsonObject.id, jsonObject.username, jsonObject.gamesPlayed);
            }
        } catch (e) {
            console.error("Error parsing User from sessionStorage:", e);
        }
        return null; 
    }
