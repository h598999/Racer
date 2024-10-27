<script>
    import Menu from "./Menu.svelte";
    import { User } from "../classes/User";
    import { loginUser } from "../clients/User_client";
    export let activeView;
    export let setActiveView;

    let username = '';
    let password = '';

    /**
    * Logs in a user by sending the user credentials to the backend.
    * @param {String} username - The user's username.
    * @param {String} password - The user's password.
    */
    async function logIn(username, password){
        const response = await loginUser(username, password);
        if (response){
            let user = new User(response.id, response.username, response.gamesPlayed);
            sessionStorage.setItem('User', JSON.stringify(user))
            sessionStorage.setItem('username', response.username)
            setActiveView('home')
        } else {
            alert("Could not log in")
        }
    }

</script>

<style>
 #Input{
     justify-content: center;
     background-color: #e6dbda;
     color: black;
     border-top-left-radius: 10px;
     border-bottom-left-radius: 10px;
     border-top-right-radius: 10px;
     border-bottom-right-radius: 10px;
     padding-bottom: 100px;
   }
  input{
      border-color: darkgoldenrod;
      background-color: #e6dbda;
      color: black;
      width: 300px;
      height: 40;
      padding: 10px;
      border-top-left-radius: 10px;
      border-bottom-left-radius: 10px;
      border-top-right-radius: 10px;
      border-bottom-right-radius: 10px;
    }

  button{
      background-color: darkgoldenrod;
      margin-top: 10px;
    }

  button:hover{
      background-color: grey;
      margin-top: 10px;
    }

</style>

<Menu {activeView} {setActiveView}/>

<div id="Input">
  <h3>Login: </h3>
   Brukernavn: <br>
   <input type="text" placeholder="Username:" bind:value={username}> 
   <br>
   Passord:    <br>
   <input type="password" placeholder="Password:" bind:value={password}> 
   <br>
   <button on:click={() => logIn(username, password)}>Log in</button>
   <br>
   <button on:click={()=>setActiveView('register')}> Create user </button>
</div>
