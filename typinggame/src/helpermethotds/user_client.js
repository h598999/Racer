
const baseUrl = 'http://localhost:8080/api/users';

/**
    * Logs in a user by sending the user credentials to the backend.
    * @param {String} username - The user's username.
    * @param {String} password - The user's password.
    * @returns {Promise<user|null>}
    */
    export async function loginUser(username, password) {
        const user = {username, password};

        try {
            const response = await fetch(`${baseUrl}/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user) // Send the user object as JSON
            });

            if (!response.ok) {
                // Handle unsuccessful login (e.g., user not found)
                console.error('Login failed:', response.status);
                return null; // Login failed
            }

            const loggedInUser = await response.json(); // Parse the JSON response
            console.log('Login successful:', loggedInUser);
            return loggedInUser; // Return the user object if login is successful
        } catch (error) {
            console.error('There was a problem logging in:', error);
            return null; // Return null in case of error
        }
    }

/**
    * Logs in a user by sending the user credentials to the backend.
    * @param {String} username - The user's username.
    * @param {String} password - The user's password.
    * @returns {Promise<user|null>}
    */
    export async function registerUser(username, password){
        const user = {username, password};

        try {
            const response = await fetch(`${baseUrl}/register`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user) // Send the user object as JSON
            });

            if (response.status === 400) {
                alert("Username taken")
                console.error('Register failed username taken:', response.status);
                return null; // Login failed
            } else if(!response.ok){
                console.error('Register failed :', response.status);
                return null; // Login failed
            }

            const registeredUser = await response.json(); // Parse the JSON response
            return registeredUser ; // Return the user object if login is successful
        } catch (error) {
            console.error('There was a problem registering:', error);
            return null; // Return null in case of error
        }
    }

/**
    * Logs in a user by sending the user credentials to the backend.
    * @param {String} username - The user's username.
    * @returns {Promise<user|null>}
    */
    export async function getUser(username){
        const user = {username};

        try {
            const response = await fetch(`${baseUrl}/${username}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
            });

            if(!response.ok){
                console.error('Could not find user:', response.status);
                return null; // Login failed
            }

            const registeredUser = await response.json(); // Parse the JSON response
            return registeredUser ; // Return the user object if login is successful
        } catch (error) {
            console.error('There was a problem retrieving user:', error);
            return null; // Return null in case of error
        }
    }

